package com.ether.data.common;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGasPrice;
import org.web3j.protocol.core.methods.response.EthGetCode;
import org.web3j.protocol.http.HttpService;

import java.math.BigInteger;
import java.util.List;

public class Web3Client {
    private static Web3j web3j = null;

    public Web3Client(String mainNet) {
        web3j = Web3j.build(new HttpService(mainNet));
    }

    public String getByteCode(String address) {
        try {
            EthGetCode code = web3j.ethGetCode(address, DefaultBlockParameterName.LATEST).send();
            return code.getCode();
        } catch (Exception ex) {
            return null;
        }
    }

    public BigInteger getGasPrice() {
        try {
            EthGasPrice code = web3j.ethGasPrice().send();
            return code.getGasPrice();
        } catch (Exception ex) {
            return null;
        }

    }
}
