package com.ether.data.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.ether.data.common.Web3Client;
import com.ether.data.dao.ContractVerfityCodeMapper;
import com.ether.data.dao.ContractVerfityMapper;
import com.ether.data.dao.MethodHashMapper;
import com.ether.data.entity.ContractVerfity;
import com.ether.data.entity.ContractVerfityCode;
import com.ether.data.entity.ContractVerfityWithBLOBs;
import com.ether.data.entity.MethodHash;
import com.ether.data.service.ContractVerfityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ContractVerfityServiceImpl implements ContractVerfityService {
    @Value("${system.uploadPath}")
    private String rootPath;

    @Value("${system.MainNet}")
    private String mainNet;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ContractVerfityMapper contractVerfityMapper;
    @Autowired
    private ContractVerfityCodeMapper contractVerfityCodeMapper;
    @Autowired
    private MethodHashMapper methodHashMapper;

    @Override
    public ContractVerfity getCompileInfo(String contractAddress) {
        ContractVerfity contractVerfity = contractVerfityMapper.selectByPrimaryKey(contractAddress);
        return contractVerfity;
    }

    @Override
    public boolean updateCompileInfo(ContractVerfity contractVerfity) {
        int count = 0;
        ContractVerfity contractVerfityTmp = contractVerfityMapper.selectByPrimaryKey(contractVerfity.getContractaddress());
        if (contractVerfityTmp == null) {
            count = contractVerfityMapper.insertSelectiveCompileInfo(contractVerfity);
        } else {
            count = contractVerfityMapper.updateSelectiveCompileInfo(contractVerfity);
        }
        return count > 0 ? true : false;
    }

    @Override
    public Integer saveFileInfo(ContractVerfityCode contractVerfityCode) {
        contractVerfityCodeMapper.insertSelective(contractVerfityCode);
        return contractVerfityCode.getId();
    }

    @Override
    public boolean deleteFile(String contractAddress, String fileName) {
        List<ContractVerfityCode> contractVerfityCodeList = contractVerfityCodeMapper.selectByContractAddress(contractAddress);
        Object filePath = contractVerfityCodeList.stream().filter(f -> f.getFilename().equals(fileName)).map(ContractVerfityCode::getFilepath).toArray()[0];
        File file = new File(rootPath + File.separator + String.valueOf(filePath));
        contractVerfityCodeMapper.deleteFileInfo(contractAddress, fileName);
        return file.delete();
    }

    @Override
    public List<Map> getFileInfo(String contractAddress) {
        List<ContractVerfityCode> contractVerfityCodeList = contractVerfityCodeMapper.selectByContractAddress(contractAddress);
        List<Map> mapList = new LinkedList<>();
        for (ContractVerfityCode contractVerfityCode : contractVerfityCodeList) {
            Map<String, Object> map = new HashMap<>();
            map.put("contractAddress", contractVerfityCode.getContractaddress());
            map.put("fileName", contractVerfityCode.getFilename());
            String filePath = rootPath + File.separator + contractVerfityCode.getFilepath();
            String sourceCode = readFile(filePath);
            map.put("sourceCode", sourceCode);



            mapList.add(map);
        }
        return mapList;
    }

    @Override
    public boolean compile(String contractAddress) throws Exception {
        ContractVerfityWithBLOBs contractVerfity = contractVerfityMapper.selectByPrimaryKey(contractAddress);
        String compileVersion = contractVerfity.getCompilerversion();
        Integer flag = contractVerfity.getOptimization();
        String contractName = contractVerfity.getContractname();
        String optimize = flag == 1 ? "-optimize" : "";
        List<ContractVerfityCode> contractVerfityCodeList = contractVerfityCodeMapper.selectByContractAddress(contractAddress);

        List<String> filePathList = new LinkedList<>();
        for (ContractVerfityCode contractVerfityCode : contractVerfityCodeList) {
            String filePath = rootPath + File.separator + contractVerfityCode.getFilepath();
            filePathList.add(filePath);

            String methodSign = "solc {} --hashes";
            String methodSignCommand = StrUtil.format(methodSign, filePath);
            String value = ececCommand(methodSignCommand);
            String[] values = value.split("\r\n");
            for (String hashMapping : values) {
                if (hashMapping.contains(":") && hashMapping.contains("(")) {
                    MethodHash methodHash = new MethodHash();
                    methodHash.setMethod(hashMapping.split(":")[1]);
                    if (hashMapping.split(":")[0].length() < 10) {
                        methodHash.setHash("0x" + hashMapping.split(":")[0]);
                        methodHash.setType(1);
                    } else {
                        methodHash.setHash("0x" + hashMapping.split(":")[0].substring(0,8));
                        methodHash.setType(2);
                    }
                    methodHashMapper.insert(methodHash);
                }
            }
        }

        if (filePathList.size() > 0) {
            String osName = System.getProperty("os.name").toLowerCase();
            String cmd = "";
            String sudo = "";
            if (osName.contains("win")) {
                cmd = ".cmd";
            } else {
                sudo = "sudo";
            }

            String installCommandTemplate = "{} npm{} -g install solc@{}";
            String installCommand = StrUtil.format(installCommandTemplate, sudo, cmd, compileVersion);
            log.info("install cmd:" + installCommand);
            ececCommand(installCommand);

            String outputPath = rootPath + File.separator + contractVerfityCodeList.get(0).getFilepath().replace(contractVerfityCodeList.get(0).getFilename(), "");
            String compileCommandTemplate = "{} solcjs{}  {} {} --abi --bin -o {}";
            String filePaths = filePathList.stream().collect(Collectors.joining("  "));
            String compileCommand = StrUtil.format(compileCommandTemplate, sudo, cmd, optimize, filePaths, outputPath);
            log.info("compile cmd:" + compileCommand);
            ececCommand(compileCommand);

            File file = new File(outputPath);
            List<String> abiFile = Arrays.stream(file.listFiles()).filter(f -> f.getName().contains("_" + contractName + ".abi")).map(File::getPath).collect(Collectors.toList());

            if (abiFile.size() > 0) {
                contractVerfity.setAbi(readFile(abiFile.get(0)));
            } else {
                throw new Exception("abi file not found");
            }

            List<String> binFile = Arrays.stream(file.listFiles()).filter(f -> f.getName().contains("_" + contractName + ".bin")).map(File::getPath).collect(Collectors.toList());
            if (binFile.size() > 0) {
                contractVerfity.setBytecode(readFile(binFile.get(0)));
            } else {
                throw new Exception("bin file not found");
            }
            int count = contractVerfityMapper.updateByPrimaryKeySelective(contractVerfity);

            return count > 0 ? true : false;
        } else {
            throw new Exception("source code file not found");
        }
    }

    public boolean vertify(String contractAddress) throws Exception {
        Web3Client client = new Web3Client(mainNet);
        String deployedByteCode = client.getByteCode(contractAddress).replace("0x", "");
        if (deployedByteCode == null || deployedByteCode == "") {
            return false;
        }
        ContractVerfityWithBLOBs contractVerfity = contractVerfityMapper.selectByPrimaryKey(contractAddress);
        String byteCode = contractVerfity.getBytecode();
        deployedByteCode = deployedByteCode.substring(0, deployedByteCode.length() - 89);
        if (byteCode.contains(deployedByteCode)) {
            Integer deployedCodeCount = countOccurrences(deployedByteCode, "6080604052");
            Integer contractCodeCount = countOccurrences(byteCode, "6080604052");
            if (contractCodeCount - deployedCodeCount == 1) {
                contractVerfity.setVerifystatus(1);
                contractVerfityMapper.updateByPrimaryKey(contractVerfity);
                return true;
            }
        }
        return false;
    }

    public static int countOccurrences(String source, String target) {
        int count = 0;
        int index = 0;
        while ((index = source.indexOf(target, index)) != -1) {
            count++;
            index += target.length();
        }
        return count;
    }

    private String readFile(String filePath) {
        String sourceCode = "";
        try {
            FileReader reader = new FileReader(filePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sourceCode += (line + "\r\n");
            }
            bufferedReader.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sourceCode;
    }

    private synchronized String ececCommand(String command) {
        Process proc = null;
        try {
            proc = Runtime.getRuntime().exec(command);
            int exitValue = proc.waitFor();
            InputStreamReader inputStreamReader = new InputStreamReader(proc.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String value = "";
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                log.info(line);
                value += (line + "\r\n");
            }
            return value;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            try {
                if (null != proc) {
                    proc.destroy();
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
    }

    private ResponseEntity<String> getHandle(String byteCodeUrl, String address) throws Exception {
        try {
            String params = "{\"method\": \"eth_getCode\",\"params\": [\"0x0145248aacf05e9e93ef78748f179c8c7cbfa93e\",\"latest\"],\"id\": 2,\"jsonrpc\": \"2.0\"}";
            Object paramObject = JSON.parseObject(params);
            ResponseEntity<String> response = restTemplate.postForEntity(byteCodeUrl, paramObject, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                return response;
            } else {
                throw new Exception("result error:" + byteCodeUrl);
            }
        } catch (Exception e) {
            throw new Exception("interface error:" + byteCodeUrl);
        }
    }
}
