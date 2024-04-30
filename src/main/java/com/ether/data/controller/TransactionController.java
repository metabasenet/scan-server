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
        Map map = transactionServiceImpl.getTransactionByBlockHash(blockHash);
        return ResultUtils.successResult(map);
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

//    @GetMapping("/page1")
//    public JSONObject pageBlock(Integer page, Integer pageSize) {
//        PageInfo<Transaction> pageInfo = transactionServiceImpl.getTransactionByPage1(page, pageSize);
//        return ResultUtils.successResult(pageInfo);
//    }
//
//    @GetMapping("/page2")
//    public JSONObject pageBlock2(Integer page, Integer pageSize) {
//        Cache.ValueWrapper res = cacheManager.getCache("Transaction").get("0x00a91dbf1be6f22ffcf01c690960da8d8183eb8e1f8a279c59c2d932f3aac3ec");
//
//        return ResultUtils.successResult(res.get());
//    }
}
