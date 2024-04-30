package com.ether.data.service.impl;

import com.ether.data.dao.PlatformBalanceMapper;
import com.ether.data.service.PlatformBalanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PlatformBalanceServiceImpl implements PlatformBalanceService {
    @Autowired
    private PlatformBalanceMapper platformBalanceMapper;

    @Override
    public PageInfo<Map> getBalanceByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = platformBalanceMapper.selectAllBalance(null);
        return new PageInfo<>(list);
    }

    @Override
    public List<Map> getBalanceByAddress(String address) {
        return platformBalanceMapper.selectAllBalance(address);
    }
}