package com.ether.data.service.impl;

import com.ether.data.dao.TransactionCountPerDayMapper;
import com.ether.data.dao.TransactionErc20Mapper;
import com.ether.data.dao.TransactionMapper;
import com.ether.data.dao.TransactionReceiptMapper;
import com.ether.data.entity.Transaction;
import com.ether.data.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionErc20Mapper transactionErc20Mapper;
    @Autowired
    private TransactionReceiptMapper transactionReceiptMapper;
    @Autowired
    private TransactionCountPerDayMapper transactionCountPerDayMapper;
    @Autowired
    private CacheManager cacheManager;

    @Override
    public PageInfo<Map> getTransactionByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionMapper.selectAllTransaction();
        return new PageInfo<>(list);
    }

    @Override
    public Map getTransactionByHash(String hash) {
        return transactionMapper.selectTransactionByHash(hash);
    }

    @Override
    public Map getTransactionByBlockHash(String blockHash) {
        return transactionMapper.selectTransactionByBlockHash(blockHash);
    }

    @Override
    public PageInfo<Map> getTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionMapper.selectTransactionByAddress(address);
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Map> getTransactionLogs(String hash, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionErc20Mapper.selectByTransactionHash(hash);
        return new PageInfo<>(list);
    }

    @Override
    public List<Map> transcationCountInfo() {
        return transactionCountPerDayMapper.transcationCountInfo();
    }

    @Override
    public PageInfo<Transaction> getTransactionByPage1(Integer page, Integer pageSize) {


        List<Transaction> transactionList = transactionMapper.selectAll();
        Cache transaction = cacheManager.getCache("Transaction");
        for (int i = 0; i < transactionList.size(); i++) {
            transaction.put(transactionList.get(i).getHash(), transactionList.get(i));
        }


//        Cache lastedTransactionCount = cacheManager.getCache("transaction");
//        lastedTransactionCount.put(tx.getHash(), tx);
//        PageHelper.startPage(page, pageSize);
//        List<Transaction> list = (List<Transaction>) cacheManager.getCache("yourCache");
        return new PageInfo<>(null);
    }


}

