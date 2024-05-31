package com.ether.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ether.data.dto.ApiLogDto;
import com.ether.data.service.ApiService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private ApiService apiServiceImpl;

    @PostMapping("/getLogs")
    public JSONObject insertBlock(@RequestBody ApiLogDto dto) {
        if (dto.getOrder() == null) {
            dto.setOrder("DESC");
        }
        if (dto.getLimit() == 0 && dto.getPageSize() == 0) {
            dto.setPage(1);
            dto.setPageSize(100);
        }

        PageInfo<Map> list = apiServiceImpl.apiLog(dto);
        return ResultUtils.successResult(list);
    }
}
