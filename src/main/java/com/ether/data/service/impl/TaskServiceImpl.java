package com.ether.data.service.impl;

import com.ether.data.dao.BlockMapper;
import com.ether.data.dao.TransactionCountPerDayMapper;
import com.ether.data.dao.TransactionReceiptMapper;
import com.ether.data.util.LocalCacheManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class TaskServiceImpl {
    @Autowired
    private TransactionCountPerDayMapper transactionCountPerDayMapper;
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionReceiptMapper transactionReceiptMapper;


    @Scheduled(cron = "${system.task.transactionCountPerDay}")
    public void transactionCountPerDayTask() {
        transactionCountPerDayMapper.transcationCountByDay();
    }

    @PostConstruct
    @Scheduled(cron = "${system.task.lastedTransactionInfo}")
    public void lastedTransactionCountTask() {
        List<Map> mapList = blockMapper.lastedTranscationCount();
        Cache lastedTransactionCount = LocalCacheManager.getCache("LastedTransaction");
        lastedTransactionCount.put("lastedTransactionCount", mapList);
    }

    @PostConstruct
    @Scheduled(cron = "${system.task.lastedTransactionInfo}")
    public void lastedTransactionInfoTask() {
        Map map = transactionReceiptMapper.lastedTransactionFee();
        Cache lastedTransactionFee = LocalCacheManager.getCache("LastedTransaction");
        lastedTransactionFee.put("lastedTransactionFee", map);
    }

    @PostConstruct
    public void blockCache() {
//        Map map = transactionReceiptMapper.lastedTransactionFee();
//        Cache lastedTransactionCount = cacheManager.getCache("LastedTransactionFee");
//        lastedTransactionCount.put("lastedTransactionFee", map);
    }
}
