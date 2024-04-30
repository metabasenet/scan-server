package com.ether.data.vo;

import com.ether.data.entity.Transaction;

import java.io.Serializable;
import java.math.BigDecimal;

public class TransactionVO extends Transaction implements Serializable {

    private String contractaddress;
    private Byte transactionindex;
    private BigDecimal gasused;
    private BigDecimal cumulativegasused;
    private BigDecimal effectivegasprice;
    private Byte status;
    private Long timestamp;

    public String getContractaddress() {
        return contractaddress;
    }

    public void setContractaddress(String contractaddress) {
        this.contractaddress = contractaddress;
    }

    @Override
    public Byte getTransactionindex() {
        return transactionindex;
    }

    @Override
    public void setTransactionindex(Byte transactionindex) {
        this.transactionindex = transactionindex;
    }

    public BigDecimal getGasused() {
        return gasused;
    }

    public void setGasused(BigDecimal gasused) {
        this.gasused = gasused;
    }

    public BigDecimal getCumulativegasused() {
        return cumulativegasused;
    }

    public void setCumulativegasused(BigDecimal cumulativegasused) {
        this.cumulativegasused = cumulativegasused;
    }

    public BigDecimal getEffectivegasprice() {
        return effectivegasprice;
    }

    public void setEffectivegasprice(BigDecimal effectivegasprice) {
        this.effectivegasprice = effectivegasprice;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    private String method;
}
