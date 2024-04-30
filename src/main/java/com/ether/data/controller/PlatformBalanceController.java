package com.ether.data.controller;


import com.alibaba.fastjson.JSONObject;
import com.ether.data.service.PlatformBalanceService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/balance")
public class PlatformBalanceController {

    @Autowired
    private PlatformBalanceService platformBalanceServiceImpl;

    @GetMapping("/page")
    public JSONObject pageBalance(Integer page, Integer pageSize) {
        PageInfo<Map> pageInfo = platformBalanceServiceImpl.getBalanceByPage(page, pageSize);
        return ResultUtils.successResult(pageInfo);
    }

    @GetMapping("/getByAddress")
    public JSONObject pageBalanceByAddress(String address) {
        List<Map> list = platformBalanceServiceImpl.getBalanceByAddress(address);
        return ResultUtils.successResult(list);
    }
}
