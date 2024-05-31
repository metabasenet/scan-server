package com.ether.data.service.impl;

import com.ether.data.dao.ContractMapper;
import com.ether.data.dao.SwapPairsMapper;
import com.ether.data.dao.SwapTxPriceMapper;
import com.ether.data.entity.Contract;
import com.ether.data.entity.SwapPairs;
import com.ether.data.service.SwapService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SwapServiceImpl implements SwapService {
    @Autowired
    private SwapTxPriceMapper swapTxPriceMapper;

    @Autowired
    private SwapPairsMapper swapPairsMapper;

    @Autowired
    private ContractMapper contractMapper;

    @Override
    public PageInfo<Map> getPairPrice(String tokenAddress1, String tokenAddress2, Integer type, Integer page, Integer pageSize) {
        SwapPairs swapPairs01 = swapPairsMapper.selectPairByTokenAddress(tokenAddress1, tokenAddress2);
        if (swapPairs01 != null) {
            PageHelper.startPage(page, pageSize);
            List<Map> mapList = swapTxPriceMapper.getPairPrice01(swapPairs01.getPairAddress(), type);
            mapList = updateOpenPrice(mapList);
            return new PageInfo<>(mapList);
        }
        SwapPairs swapPairs10 = swapPairsMapper.selectPairByTokenAddress(tokenAddress2, tokenAddress1);
        if (swapPairs10 != null) {
            PageHelper.startPage(page, pageSize);
            List<Map> mapList = swapTxPriceMapper.getPairPrice10(swapPairs10.getPairAddress(), type);
            mapList = updateOpenPrice(mapList);
            return new PageInfo<>(mapList);
        }
        return new PageInfo<>(null);
    }

    private List<Map> updateOpenPrice(List<Map> mapList) {
        for (int i = 0; i < mapList.size(); i++) {
            Map map = mapList.get(i);
            if (i + 1 < mapList.size()) {
                map.put("first", mapList.get(i + 1).get("last"));
            }
        }
        return mapList;
    }

    @Override
    public PageInfo<Map> getPairPriceFlow(String tokenAddress1, String tokenAddress2, Integer page, Integer pageSize) {
        SwapPairs swapPairs01 = swapPairsMapper.selectPairByTokenAddress(tokenAddress1, tokenAddress2);
        if (swapPairs01 != null) {
            PageHelper.startPage(page, pageSize);
            List<Map> mapList = swapTxPriceMapper.getPairPriceFlow01(swapPairs01.getPairAddress());
            return new PageInfo<>(mapList);
        }
        SwapPairs swapPairs10 = swapPairsMapper.selectPairByTokenAddress(tokenAddress2, tokenAddress1);
        if (swapPairs10 != null) {
            PageHelper.startPage(page, pageSize);
            List<Map> mapList = swapTxPriceMapper.getPairPriceFlow01(swapPairs10.getPairAddress());
            return new PageInfo<>(mapList);
        }
        return new PageInfo<>(null);
    }

    @Override
    public PageInfo<SwapPairs> getPairs(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<SwapPairs> mapList = swapPairsMapper.selectAll();
        return new PageInfo<>(mapList);
    }


    @Override
    public List<Contract> getTokens() {
        List<Contract> contractList = contractMapper.selectAllToken();
        return contractList;
    }
}
