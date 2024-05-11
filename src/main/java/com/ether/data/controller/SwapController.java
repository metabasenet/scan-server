package com.ether.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ether.data.entity.SwapPairs;
import com.ether.data.service.SwapService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/swap")
public class SwapController {
    @Autowired
    private SwapService swapServiceImpl;

    @GetMapping("/getPrice")
    public JSONObject getPrice(String address, Integer type, Integer page, Integer pageSize) {
        PageInfo<Map> mapList = swapServiceImpl.getPairPrice(address, type, page, pageSize);
        return ResultUtils.successResult(mapList);
    }

    @GetMapping("/getPairs")
    public JSONObject getPairs(Integer page, Integer pageSize) {
        PageInfo<SwapPairs> mapList = swapServiceImpl.getPairs(page, pageSize);
        return ResultUtils.successResult(mapList);
    }
}
