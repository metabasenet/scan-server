package com.ether.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.ether.data.entity.Contract;
import com.ether.data.service.ContractService;
import com.ether.data.util.ResultUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ContractService contractServiceImpl;

    @GetMapping("/page")
    public JSONObject pageContract(Integer page, Integer pageSize) {
        PageInfo<Map> pageInfo = contractServiceImpl.getContractByPage(page, pageSize);
        return ResultUtils.successResult(pageInfo);
    }

    @GetMapping("/getContractByAddress")
    public JSONObject getContractByAddress(String address) {
        Map contract = contractServiceImpl.getContractByAddress(address);
        return ResultUtils.successResult(contract);
    }

    @GetMapping("/getContactTransationByContractAddress")
    public JSONObject getContactTransationByContractAddress(String contractAddress, Integer page, Integer pageSize) {
        PageInfo<Map> transactionErc20List = contractServiceImpl.getContractTransactionByContractAddress(contractAddress, page, pageSize);
        return ResultUtils.successResult(transactionErc20List);
    }

    @GetMapping("/getContactTransationByAddress")
    public JSONObject getContactTransationByAddress(String address, Integer page, Integer pageSize) {
        PageInfo<Map> transactionErc20List = contractServiceImpl.getContractTransactionByAddress(address, page, pageSize);
        return ResultUtils.successResult(transactionErc20List);
    }

    @GetMapping("/getAllContactTransation")
    public JSONObject getAllContactTransation(Integer page, Integer pageSize) {
        Date d1=new Date();
        PageInfo<Map> transactionErc20List = contractServiceImpl.selectAllContractTransation(page, pageSize);
        Date d2 =new Date();
        System.out.println(d2.getTime()-d1.getTime());
        return ResultUtils.successResult(transactionErc20List);
    }

    @GetMapping("/getContactBalance")
    public JSONObject getContactBalance(Integer page, Integer pageSize, String contractAddress,String address) {
        PageInfo<Map> contractBalanceList = contractServiceImpl.getContractBalanceByPage(page, pageSize, contractAddress, address);
        return ResultUtils.successResult(contractBalanceList);
    }

    @GetMapping("/getContractTransationCountByAddress")
    public JSONObject getContractTransationCountByAddress(String contractAddress) {
        Long count = contractServiceImpl.getContractTransationCountByAddress(contractAddress);
        return ResultUtils.successResult(count);
    }

    @GetMapping("/selectTokenByAddress")
    public JSONObject selectTokenByAddress(String address) {
        List<Map> list = contractServiceImpl.selectTokenByAddress(address);
        return ResultUtils.successResult(list);
    }
}
