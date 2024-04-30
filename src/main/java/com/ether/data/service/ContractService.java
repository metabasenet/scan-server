package com.ether.data.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface ContractService {
    PageInfo<Map> getContractByPage(Integer page, Integer pageSize);

    Map getContractByAddress(String address);

    PageInfo<Map> getContractTransactionByContractAddress(String contractAddress, Integer page, Integer pageSize);

    PageInfo<Map> getContractTransactionByAddress(String address, Integer page, Integer pageSize);

    PageInfo<Map> selectAllContractTransation(Integer page, Integer pageSize);

    PageInfo<Map> getContractBalanceByPage(Integer page, Integer pageSize, String contractAddress);

    Long getContractTransationCountByAddress(String contractAddress);

    List<Map> selectTokenByAddress(String address);
}
