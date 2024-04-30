package com.ether.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ether.data.entity.Block;
import com.ether.data.service.BlockService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/block")
public class BlockController {
    @Autowired
    private BlockService blockServiceImpl;

    @PostMapping("/insertBlock")
    public JSONObject insertBlock(@RequestBody Block block) {
        return ResultUtils.successResult(blockServiceImpl.insertBlock(block));
    }

    @GetMapping("/page")
    public JSONObject pageBlock(Integer page, Integer pageSize) {
        PageInfo<Block> pageInfo = blockServiceImpl.getBlockByPage(page, pageSize);
        return ResultUtils.successResult(pageInfo);
    }

    @GetMapping("/getByNumber")
    public JSONObject getByNumber(Integer blockNumber) {
        Block block = blockServiceImpl.getByBlockNumber(blockNumber);
        return ResultUtils.successResult(block);
    }


    @GetMapping("/search")
    public JSONObject search(String searchInfo,Integer type) {
        Map map = blockServiceImpl.search(searchInfo, type);
        return ResultUtils.successResult(map);
    }
}
