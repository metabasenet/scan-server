package com.ether.data.service;

import com.ether.data.entity.ContractVerfity;
import com.ether.data.entity.ContractVerfityCode;

import java.util.List;
import java.util.Map;

public interface ContractVerfityService {

    ContractVerfity getCompileInfo(String contractAddress);

    boolean updateCompileInfo(ContractVerfity contractVerfity);

    Integer saveFileInfo(ContractVerfityCode contractVerfityCode);

    boolean deleteFile(String contractAddress, String fileName);

    List<Map> getFileInfo(String contractAddress);

    boolean compile(String contractAddress) throws Exception;

    boolean vertify(String contractAddress) throws Exception;
}
