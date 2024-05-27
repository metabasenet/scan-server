package com.ether.data.service.impl;

import com.ether.data.common.Web3Client;
import com.ether.data.dao.*;
import com.ether.data.entity.Block;
import com.ether.data.entity.Contract;
import com.ether.data.entity.MethodHash;
import com.ether.data.entity.TransactionPlatform;
import com.ether.data.service.TransactionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private TransactionErc20Mapper transactionErc20Mapper;
    @Autowired
    private TransactionCountPerDayMapper transactionCountPerDayMapper;
    @Autowired
    private TransactionPlatformMapper transactionPlatformMapper;
    @Autowired
    private MethodHashMapper methodHashMapper;

    @Autowired
    private CacheManager cacheManager;

    @Value("${system.cache.maxElementNumber}")
    private Long maxElementNumber;

    @Override
    public PageInfo<Map> getTransactionByPage(Integer page, Integer pageSize) {
        if (page * pageSize < maxElementNumber) {
            Cache cache = cacheManager.getCache("BlockServerCache");
            List<Map> blockCacheList = (List<Map>) cache.get("transaction").get();
            Long totalTransactionCount = (Long) cache.get("totalTransactionCount").get();

            PageHelper.startPage(page, pageSize);
            List<Map> returnList = blockCacheList.subList((page - 1) * pageSize, page * pageSize);
            PageInfo<Map> blockPageInfo = new PageInfo<>(returnList);
            blockPageInfo.setTotal(totalTransactionCount);
            blockPageInfo.setNextPage(page + 1);
            return blockPageInfo;
        }

        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionMapper.selectAllTransaction();
        return new PageInfo<>(list);
    }

    @Override
    public List<Map> getLastedTransfer(String contractAddress, String userAddress) {
        List<Map> mapList = new LinkedList<>();
        if (contractAddress.startsWith("0x000000000000000000000000000000000000000")) {
            mapList = transactionMapper.selectPlatformInternalRransaction(userAddress);
        } else {
            userAddress = userAddress.replace("0x", "0x000000000000000000000000");
            mapList = transactionErc20Mapper.getContractTransaction(contractAddress, userAddress);
        }
        for (Map<String, String> map : mapList) {
            String fromAddress = map.get("from").replace("0x000000000000000000000000", "0x");
            map.put("from", fromAddress);
            String toAddress = map.get("to").replace("0x000000000000000000000000", "0x");
            map.put("to", toAddress);
        }
        return mapList;
    }

    @Override
    public Map getTransactionByHash(String hash) {
        return transactionMapper.selectTransactionByHash(hash);
    }

    @Override
    public List<Map> getTransactionByBlockHash(String blockHash) {
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
    public List<TransactionPlatform> getPlatformTransactionInfo(String transactionHash) {
        List<TransactionPlatform> list = transactionPlatformMapper.selectByHash(transactionHash);
        return list;
    }

    @Override
    public PageInfo<TransactionPlatform> getPlatformTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<TransactionPlatform> list = transactionPlatformMapper.selectByAddress(address);
        return new PageInfo<>(list);
    }

    @Override
    public List<Map> transcationCountInfo() {
        List<Map> mapList = transactionCountPerDayMapper.transcationCountInfo();
        return mapList;
    }

    @Override
    public List<Map> getInternalTransactionInfo(String transactionHash) {
        List<Map> mapList = transactionErc20Mapper.selectInternalTransactionByHash(transactionHash);
        for (Map<String, String> map : mapList) {
            String fromAddress = map.get("from").replace("0x000000000000000000000000", "0x");
            map.put("from", fromAddress);
            String toAddress = map.get("to").replace("0x000000000000000000000000", "0x");
            map.put("to", toAddress);
        }
        return mapList;
    }

    @Override
    public PageInfo<Map> getInternalTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionErc20Mapper.selectInternalTransactionByAddress(address);
        return new PageInfo<>(list);
    }

    @Override
    public MethodHash getMethdNameByHash(String methodHash) {
        return methodHashMapper.selectByPrimaryKey(methodHash);
    }
}

