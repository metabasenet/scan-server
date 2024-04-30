package com.ether.data.service;

import com.ether.data.entity.Transaction;
import com.ether.data.entity.TransactionPlatform;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    PageInfo<Map> getTransactionByPage(Integer page, Integer pageSize);

    Map getTransactionByHash(String hash);

    Map getTransactionByBlockHash(String blockHash);

    PageInfo<Map> getTransactionByAddress(String address, Integer page, Integer pageSize);

    PageInfo<Map> getTransactionLogs(String hash, Integer page, Integer pageSize);

    List<TransactionPlatform> getPlatformTransactionInfo(String transactionHash);

    List<Map> transcationCountInfo();

    PageInfo<Transaction> getTransactionByPage1(Integer page, Integer pageSize);
}
