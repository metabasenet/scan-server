package com.ether.data.service.impl;

import com.ether.data.dao.ContractMapper;
import com.ether.data.dao.Erc20BalanceMapper;
import com.ether.data.dao.TransactionErc20Mapper;
import com.ether.data.entity.Contract;
import com.ether.data.service.ContractService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractMapper contractMapper;
    @Autowired
    private TransactionErc20Mapper transactionErc20Mapper;
    @Autowired
    private Erc20BalanceMapper erc20BalanceMapper;

    @Override
    public PageInfo<Map> getContractByPage(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = contractMapper.selectAll();
        return new PageInfo<>(list);
    }

    public Map getContractByAddress(String address) {
        return contractMapper.selectByContractAddress(address);
    }

    //contract address transaction
    public PageInfo<Map> getContractTransactionByContractAddress(String contractAddress, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionErc20Mapper.getContractTransactionByContractAddress(contractAddress);
        for (Map<String, String> map : list) {
            String fromAddress = map.get("from").replace("0x000000000000000000000000", "0x");
            map.put("from", fromAddress);
            String toAddress = map.get("to").replace("0x000000000000000000000000", "0x");
            map.put("to", toAddress);
        }
        return new PageInfo<>(list);
    }

    //ordinary address transaction
    public PageInfo<Map> getContractTransactionByAddress(String address, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionErc20Mapper.getContractTransactionByAddress(address);
        for (Map<String, String> map : list) {
            String fromAddress = map.get("from").replace("0x000000000000000000000000", "0x");
            map.put("from", fromAddress);
            String toAddress = map.get("to").replace("0x000000000000000000000000", "0x");
            map.put("to", toAddress);
        }
        return new PageInfo<>(list);
    }

    public PageInfo<Map> selectAllContractTransation(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = transactionErc20Mapper.selectAllContractTransation();
        for (Map<String, String> map : list) {
            String fromAddress = map.get("from").replace("0x000000000000000000000000", "0x");
            map.put("from", fromAddress);
            String toAddress = map.get("to").replace("0x000000000000000000000000", "0x");
            map.put("to", toAddress);
        }
        return new PageInfo<>(list);
    }

    @Override
    public PageInfo<Map> getContractBalanceByPage(Integer page, Integer pageSize, String contractAddress) {
        PageHelper.startPage(page, pageSize);
        List<Map> list = erc20BalanceMapper.selectContractBalance(contractAddress);
        return new PageInfo<>(list);
    }

    @Override
    public Long getContractTransationCountByAddress(String contractAddress) {
        return transactionErc20Mapper.getContractTransactionCountByAddress(contractAddress);
    }

    @Override
    public List<Map> selectTokenByAddress(String address) {
        return erc20BalanceMapper.selectTokenByAddress(address);
    }
}
