package com.ether.data.service;

import com.ether.data.entity.SwapPairs;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface SwapService {
    PageInfo<Map> getPairPrice(String pairAddress, Integer type, Integer page, Integer pageSize);
    PageInfo<SwapPairs> getPairs(Integer page, Integer pageSize);
}
