package com.ether.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ether.data.common.Web3Client;
import com.ether.data.entity.MntPrice;
import com.ether.data.service.MntPriceService;
import com.ether.data.service.TransactionService;
import com.ether.data.util.LocalCacheManager;
import com.ether.data.util.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/market")
public class MarketController {

    @Value("${system.MainNet}")
    private String mainNet;

    @Autowired
    private TransactionService transactionServiceImpl;

    @Autowired
    private MntPriceService mntPriceServiceImpl;

    @GetMapping("/getPrice")
    public Map getPrice() {
        MntPrice mntPrice = mntPriceServiceImpl.getPrice();
        Map<String, String> map = new HashMap<>();
        map.put("price", mntPrice.getPrice());
        return map;
    }


    @GetMapping("/getGasPrice")
    public Map getGasPrice() {
        Web3Client client = new Web3Client(mainNet);
        BigInteger price = client.getGasPrice();
        Map<String, BigInteger> map = new HashMap<>();
        map.put("price", price);
        return map;
    }

    @GetMapping("/transcationCountByDay")
    public List<Map> transcationCountByDay() {
        return transactionServiceImpl.transcationCountInfo();
    }

    @GetMapping("/lastedTranscationCount")
    public JSONObject lastedTranscationCount() {
        Cache cache = LocalCacheManager.getCache("LastedTransaction");
        Cache.ValueWrapper res = cache.get("lastedTransactionCount");
        if (res != null) {
            List<Map> mapList = (List<Map>) res.get();
            return ResultUtils.successResult(mapList);
        } else {
            return ResultUtils.errorResult("error");
        }
    }

    @GetMapping("/lastedTransactionFee")
    public JSONObject lastedTransactionFee() {
        Cache cache = LocalCacheManager.getCache("LastedTransaction");
        Cache.ValueWrapper res = cache.get("lastedTransactionFee");
        if (res != null) {
            Map map = (Map) res.get();
            return ResultUtils.successResult(map);
        } else {
            return ResultUtils.errorResult("error");
        }
    }
}
