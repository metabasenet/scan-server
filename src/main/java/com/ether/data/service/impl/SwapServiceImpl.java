package com.ether.data.service.impl;

import com.ether.data.dao.SwapTxMapper;
import com.ether.data.service.SwapService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SwapServiceImpl implements SwapService {
    @Autowired
    private SwapTxMapper swapTxMapper;

    @Override
    public PageInfo<Map> getPairPrice(String pairAddress, Integer type,Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> mapList= swapTxMapper.getPairPrice(pairAddress, type);
        return new PageInfo<>(mapList);
    }
}
