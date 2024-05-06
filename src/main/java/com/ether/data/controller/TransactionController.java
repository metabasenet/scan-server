package com.ether.data.controller;


import com.alibaba.fastjson.JSONObject;
import com.ether.data.entity.Transaction;
import com.ether.data.entity.TransactionPlatform;
import com.ether.data.service.TransactionService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TransactionService transactionServiceImpl;

    @GetMapping("/page")
    public JSONObject pageTransaction(Integer page, Integer pageSize) {

        PageInfo<Map> pageInfo = transactionServiceImpl.getTransactionByPage(page, pageSize);
        return ResultUtils.successResult(pageInfo);
    }

    @GetMapping("/getByHash")
    public JSONObject getByHash(String hash) {
        Map map = transactionServiceImpl.getTransactionByHash(hash);
        return ResultUtils.successResult(map);
    }

    @GetMapping("/getByBlockHash")
    public JSONObject getByBlockHash(String blockHash) {
        List<Map> mapList = transactionServiceImpl.getTransactionByBlockHash(blockHash);
        return ResultUtils.successResult(mapList);
    }

    @GetMapping("/getByAddress")
    public JSONObject getTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageInfo<Map> mapList = transactionServiceImpl.getTransactionByAddress(address, page, pageSize);
        return ResultUtils.successResult(mapList);
    }

    @GetMapping("/getTransactionLogs")
    public JSONObject getTransactionLogs(String hash, Integer page, Integer pageSize) {
        PageInfo<Map> mapList = transactionServiceImpl.getTransactionLogs(hash, page, pageSize);
        return ResultUtils.successResult(mapList);
    }

    @GetMapping("/getPlatformTransactionInfo")
    public JSONObject getPlatformTransactionInfo(String transactionHash) {
        List<TransactionPlatform> mapList = transactionServiceImpl.getPlatformTransactionInfo(transactionHash);
        return ResultUtils.successResult(mapList);
    }

    @GetMapping("/getPlatformTransactionByAddress")
    public JSONObject getPlatformTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageInfo<TransactionPlatform> mapList = transactionServiceImpl.getPlatformTransactionByAddress(address, page, pageSize);
        return ResultUtils.successResult(mapList);
    }
}
