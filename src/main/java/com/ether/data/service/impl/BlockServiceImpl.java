package com.ether.data.service.impl;

import com.ether.data.dao.BlockMapper;
import com.ether.data.dao.ContractMapper;
import com.ether.data.dao.PlatformBalanceMapper;
import com.ether.data.dao.TransactionMapper;
import com.ether.data.entity.Block;
import com.ether.data.entity.Contract;
import com.ether.data.service.BlockService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BlockServiceImpl implements BlockService {
    @Autowired
    private BlockMapper blockMapper;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private PlatformBalanceMapper platformBalanceMapper;
    @Autowired
    private ContractMapper contractMapper;

    @Override
    public boolean insertBlock(Block block) {
        Integer count = blockMapper.insert(block);
        return count > 0 ? true : false;
    }

    @Override
    public PageInfo<Block> getBlockByPage(Integer page, Integer pageSize) {
        if (page * pageSize > 10000) {

        }

        PageHelper.startPage(page, pageSize);
        List<Block> list = blockMapper.selectAll();
        return new PageInfo<>(list);
    }

    @Override
    public Block getByBlockNumber(Integer blockNumber) {
        Block block = blockMapper.selectByPrimaryKey(BigDecimal.valueOf(blockNumber));
        return block;
    }

    @Override
    public Map search(String searchInfo, Integer type) {
        Map map = new HashMap();
        if (type == null) {
            Block block = null;
            try {
                block = blockMapper.selectByHashOrNumber(new BigDecimal(searchInfo), null);
            } catch (Exception ex) {
                block = blockMapper.selectByHashOrNumber(null, searchInfo);
            }
            map.put("block", block);

            Map transactionMap = transactionMapper.selectTransactionByHash(searchInfo);
            map.put("transaction", transactionMap);

            List<Map> mapList = platformBalanceMapper.selectAllBalance(searchInfo);
            if (mapList != null && mapList.size() > 0) {
                Map address = platformBalanceMapper.selectAllBalance(searchInfo).get(0);
                map.put("address", address);
            }

            Contract contract = contractMapper.selectByPrimaryKey(searchInfo);
            map.put("contract", contract);
        } else if (type == 1) {
            Block block = null;
            try {
                block = blockMapper.selectByHashOrNumber(new BigDecimal(searchInfo), null);
            } catch (Exception ex) {
                block = blockMapper.selectByHashOrNumber(null, searchInfo);
            }
            map.put("block", block);
        } else if (type == 2) {
            Map transactionMap = transactionMapper.selectTransactionByHash(searchInfo);
            map.put("transaction", transactionMap);
        } else if (type == 3) {
            List<Map> mapList = platformBalanceMapper.selectAllBalance(searchInfo);
            if (mapList != null && mapList.size() > 0) {
                Map address = platformBalanceMapper.selectAllBalance(searchInfo).get(0);
                map.put("address", address);
            }
        } else if (type == 4) {
            Contract contract = contractMapper.selectByPrimaryKey(searchInfo);
            map.put("contract", contract);
        }
        return map;
    }
}
