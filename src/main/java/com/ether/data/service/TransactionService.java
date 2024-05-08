package com.ether.data.service;

import com.ether.data.entity.TransactionPlatform;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    PageInfo<Map> getTransactionByPage(Integer page, Integer pageSize);

    Map getTransactionByHash(String hash);

    List<Map> getTransactionByBlockHash(String blockHash);

    PageInfo<Map> getTransactionByAddress(String address, Integer page, Integer pageSize);

    PageInfo<Map> getTransactionLogs(String hash, Integer page, Integer pageSize);

    List<TransactionPlatform> getPlatformTransactionInfo(String transactionHash);

    PageInfo<TransactionPlatform> getPlatformTransactionByAddress(String address, Integer page, Integer pageSize);

    List<Map> transcationCountInfo();

    List<Map> getInternalTransactionInfo(String transactionHash);

    PageInfo<Map> getInternalTransactionByAddress(String address, Integer page, Integer pageSize);
}
