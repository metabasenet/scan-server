package com.ether.data.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import com.alibaba.fastjson.JSONObject;
import com.ether.data.entity.Contract;
import com.ether.data.entity.ContractVerfity;
import com.ether.data.entity.ContractVerfityCode;
import com.ether.data.service.ContractService;
import com.ether.data.service.ContractVerfityService;
import com.ether.data.util.ResultUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contractVertify")
public class ContractVerfityController {
    @Value("${system.uploadPath}")
    private String rootPath;

    @Autowired
    private ContractVerfityService contractVerfityServiceImpl;

    @Autowired
    private ContractService contractServiceImpl;

    @GetMapping("/getByContractAddress")
    public JSONObject getByContractAddress(String contractAddress) {
        Map contract = contractServiceImpl.getContractByAddress(contractAddress);
        if (contract == null) {
            return ResultUtils.errorResult("contract is not found");
        } else {
            ContractVerfity contractVerfity = contractVerfityServiceImpl.getCompileInfo(contractAddress);
            return ResultUtils.successResult(contractVerfity);
        }
    }

    @PostMapping("/updateContractCompileInfo")
    public JSONObject updateContractCompileInfo(@RequestBody ContractVerfity contractVerfity) throws Exception {
        boolean contractUpdataRes = contractVerfityServiceImpl.updateCompileInfo(contractVerfity);
        if (contractUpdataRes) {
            boolean compileRes = contractVerfityServiceImpl.compile(contractVerfity.getContractaddress());
            if (compileRes) {
                boolean verfityRes = contractVerfityServiceImpl.vertify(contractVerfity.getContractaddress());
                if (verfityRes) {
                    return ResultUtils.successResult("Contract verfity success");
                } else {
                    return ResultUtils.errorResult("Contract verfity failed");
                }
            } else {
                return ResultUtils.errorResult("Contract compile failed");
            }
        } else {
            return ResultUtils.errorResult("Contract info update failed");
        }
    }

    @ResponseBody
    @PostMapping("/uploadFiles")
    public JSONObject uploadFiles(@RequestBody List<MultipartFile> files, HttpServletRequest request) {
        ContractVerfityCode contractVerfityCode = new ContractVerfityCode();
        String contractName = request.getParameter("contractName");
        contractVerfityCode.setContractaddress(request.getParameter("contractAddress"));
        if (CollectionUtil.isEmpty(files)) {
            return ResultUtils.errorResult("files is empty");
        }
        String path = rootPath + File.separator + contractName;
        if (!FileUtil.exist(path)) {
            FileUtil.mkdir(path);
        }
        try {
            for (MultipartFile file : files) {
                String fileName = file.getOriginalFilename();
                contractVerfityCode.setFilename(fileName);
                if (StringUtils.isNotBlank(fileName) && fileName.endsWith(".sol")) {
                    InputStream fileStream = file.getInputStream();
                    String dest = path + File.separator + fileName;
                    FileUtil.writeFromStream(fileStream, new File(dest));
                    fileStream.close();
                    contractVerfityCode.setFilepath(dest.replace(rootPath + File.separator, ""));
                    contractVerfityServiceImpl.saveFileInfo(contractVerfityCode);
                }
            }
        } catch (Exception ex) {
            return ResultUtils.errorResult("upload failed");
        }
        return ResultUtils.successResult("upload success");
    }

    @ResponseBody
    @PostMapping("/deleteFile")
    public JSONObject deleteFile(String contractAddress, String fileName) {
        boolean res = contractVerfityServiceImpl.deleteFile(contractAddress, fileName);
        if (res) {
            return ResultUtils.successResult("delete success");
        } else {
            return ResultUtils.errorResult("delete failed");
        }
    }

    @ResponseBody
    @GetMapping("/getFileInfo")
    public List<Map> getFileInfo(String contractAddress) {
        return contractVerfityServiceImpl.getFileInfo(contractAddress);
    }

    @GetMapping("/compile")
    public JSONObject compile(String contractAddress) throws Exception {
        boolean res = contractVerfityServiceImpl.compile(contractAddress);
        if (res) {
            return ResultUtils.successResult("compile success");
        } else {
            return ResultUtils.errorResult("compile failed");
        }
    }

    @GetMapping("/vertify")
    public JSONObject vertify(String contractAddress) throws Exception {
        boolean res = contractVerfityServiceImpl.vertify(contractAddress);
        if (res) {
            return ResultUtils.successResult("vertify success");
        } else {
            return ResultUtils.errorResult("vertify failed");
        }
    }
}
