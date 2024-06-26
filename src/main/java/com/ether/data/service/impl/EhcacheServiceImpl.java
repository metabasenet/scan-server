package com.ether.data.service.impl;

import com.ether.data.dao.BlockMapper;
import com.ether.data.dao.TransactionMapper;
import com.ether.data.entity.Block;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EhcacheServiceImpl {
    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private CacheManager cacheManager;

    @Value("${system.cache.maxElementNumber}")
    private Long maxElementNumber;

    @PostConstruct
    public void blockServerCache() {
        Cache cache = cacheManager.getCache("BlockServerCache");
        List<Block> blockList = blockMapper.selectBlockCacheInfo(maxElementNumber);
        Long totalBlockCount = blockMapper.selectAllCount();

        Long minBlockNumber = Long.valueOf(String.valueOf(blockList.get(blockList.size() - 1).getNumber()));
        List<Map> transactionList = transactionMapper.selectTransactionCacheInfo(minBlockNumber);
        Long transactionCount = transactionMapper.selectAllTransactionCount();

        for (Block block : blockList) {
            Long transactionGasFees = 0L;
            List<Map> transactionBlockList = transactionList.stream().filter(f -> Long.valueOf(String.valueOf(f.get("blockNumber"))).equals(Long.valueOf(String.valueOf(block.getNumber())))).collect(Collectors.toList());
            for (Map map : transactionBlockList) {
                transactionGasFees += Long.valueOf(String.valueOf(map.get("gasUsed"))) * Long.valueOf(String.valueOf(map.get("effectiveGasPrice")));
            }
            block.setTransactionGasFees(transactionGasFees);
        }

        cache.put("block", blockList);
        cache.put("totalBlockCount", totalBlockCount);

        cache.put("transaction", transactionList);
        cache.put("totalTransactionCount", transactionCount);
    }

    @Scheduled(cron = "${system.cache.updateFreq}")
    public void blockServerCacheUpdate() {
        Cache cache = cacheManager.getCache("BlockServerCache");
        List<Block> blockCacheList = (List<Block>) cache.get("block").get();
        List<Block> blockIncrementList = blockMapper.selectBlockCacheInfoByFixedFreq();
        List<Map> transactionIncrementList = new LinkedList<>();
        Integer size = blockIncrementList.size();
        for (int i = size - 1; i >= 0; i--) {
            BigDecimal blockNumber = blockIncrementList.get(i).getNumber();
            Long count = blockCacheList.stream().filter(f -> f.getNumber().equals(blockNumber)).count();
            if (count <= 0) {
                blockCacheList.add(0, blockIncrementList.get(i));
                blockCacheList.remove(blockCacheList.size() - 1);
                List<Map> transactionList = transactionMapper.selectTransactionByBlockHash(blockIncrementList.get(i).getHash());
                for (Map map : transactionList) {
                    transactionIncrementList.add(0, map);
                }
            }
        }
        cache.put("block", blockCacheList);
        Long totalBlockCount = blockMapper.selectAllCount();
        cache.put("totalBlockCount", totalBlockCount);


        List<Map> transactionCacheList = (List<Map>) cache.get("transaction").get();
        Integer transactionSize = transactionIncrementList.size();
        for (int i = transactionSize - 1; i >= 0; i--) {
            String transactionHash = (String) transactionIncrementList.get(i).get("hash");
            Long count = transactionCacheList.stream().filter(f -> f.get("hash").equals(transactionHash)).count();
            if (count <= 0) {
                transactionCacheList.add(0, transactionIncrementList.get(i));
                transactionCacheList.remove(transactionCacheList.size() - 1);
            }
        }
        cache.put("transaction", transactionCacheList);
        Long totalTransactionCount = transactionMapper.selectAllTransactionCount();
        cache.put("totalTransactionCount", totalTransactionCount);
    }
}
