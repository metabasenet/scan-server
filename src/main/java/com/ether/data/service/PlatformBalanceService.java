package com.ether.data.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface PlatformBalanceService {
    PageInfo<Map> getBalanceByPage(String address, Integer page, Integer pageSize);

    List<Map> getBalanceByAddress(String address);
}
