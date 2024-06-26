package com.ether.data.service;

import com.ether.data.entity.Contract;
import com.ether.data.entity.SwapPairs;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface SwapService {
    PageInfo<Map> getPairPrice(String tokenAddress1, String tokenAddress2, Integer type, Integer page, Integer pageSize);

    PageInfo<Map> getPairPriceFlow(String tokenAddress1, String tokenAddress2, Integer page, Integer pageSize);

    PageInfo<SwapPairs> getPairs(Integer page, Integer pageSize);

    List<Contract> getTokens();
}
