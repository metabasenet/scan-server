package com.ether.data.util;

import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

public class LocalCacheManager {
    private volatile static ConcurrentMapCacheManager cacheManager = null;

    private static ConcurrentMapCacheManager cacheManager(){
        if(null == cacheManager){
            synchronized (LocalCacheManager.class){
                if(null == cacheManager){
                    cacheManager = new ConcurrentMapCacheManager();
                }
            }
        }
        return cacheManager;
    }

    public static Cache getCache(String cacheName){
        return cacheManager().getCache(cacheName);
    }
}
