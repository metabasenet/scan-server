package com.ether.data.service;

import com.ether.data.entity.Block;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

public interface BlockService {

    boolean insertBlock(Block block);

    PageInfo<Block> getBlockByPage(Integer page, Integer pageSize);

    Block getByBlockNumber(Integer blockNumber);

    Map search(String search, Integer type);
}
