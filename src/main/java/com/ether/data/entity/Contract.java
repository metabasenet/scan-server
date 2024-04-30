package com.ether.data.entity;

import java.math.BigDecimal;
import java.util.Date;

public class Contract {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.contractAddress
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String contractaddress;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.blockHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String blockhash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.transactionHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String transactionhash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.blockNumber
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private BigDecimal blocknumber;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.creator
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String creator;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.createTime
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private Date createtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.status
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private Integer status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.totalSupply
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private BigDecimal totalsupply;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.ercName
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String ercname;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.ercSymbol
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String ercsymbol;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.decimals
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private Integer decimals;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column contract.abi
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    private String abi;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.contractAddress
     *
     * @return the value of contract.contractAddress
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getContractaddress() {
        return contractaddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.contractAddress
     *
     * @param contractaddress the value for contract.contractAddress
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setContractaddress(String contractaddress) {
        this.contractaddress = contractaddress == null ? null : contractaddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.blockHash
     *
     * @return the value of contract.blockHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getBlockhash() {
        return blockhash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.blockHash
     *
     * @param blockhash the value for contract.blockHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash == null ? null : blockhash.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.transactionHash
     *
     * @return the value of contract.transactionHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getTransactionhash() {
        return transactionhash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.transactionHash
     *
     * @param transactionhash the value for contract.transactionHash
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setTransactionhash(String transactionhash) {
        this.transactionhash = transactionhash == null ? null : transactionhash.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.blockNumber
     *
     * @return the value of contract.blockNumber
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public BigDecimal getBlocknumber() {
        return blocknumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.blockNumber
     *
     * @param blocknumber the value for contract.blockNumber
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setBlocknumber(BigDecimal blocknumber) {
        this.blocknumber = blocknumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.creator
     *
     * @return the value of contract.creator
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.creator
     *
     * @param creator the value for contract.creator
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.createTime
     *
     * @return the value of contract.createTime
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public Date getCreatetime() {
        return createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.createTime
     *
     * @param createtime the value for contract.createTime
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.status
     *
     * @return the value of contract.status
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.status
     *
     * @param status the value for contract.status
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.totalSupply
     *
     * @return the value of contract.totalSupply
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public BigDecimal getTotalsupply() {
        return totalsupply;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.totalSupply
     *
     * @param totalsupply the value for contract.totalSupply
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setTotalsupply(BigDecimal totalsupply) {
        this.totalsupply = totalsupply;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.ercName
     *
     * @return the value of contract.ercName
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getErcname() {
        return ercname;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.ercName
     *
     * @param ercname the value for contract.ercName
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setErcname(String ercname) {
        this.ercname = ercname == null ? null : ercname.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.ercSymbol
     *
     * @return the value of contract.ercSymbol
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getErcsymbol() {
        return ercsymbol;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.ercSymbol
     *
     * @param ercsymbol the value for contract.ercSymbol
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setErcsymbol(String ercsymbol) {
        this.ercsymbol = ercsymbol == null ? null : ercsymbol.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.decimals
     *
     * @return the value of contract.decimals
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public Integer getDecimals() {
        return decimals;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.decimals
     *
     * @param decimals the value for contract.decimals
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setDecimals(Integer decimals) {
        this.decimals = decimals;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column contract.abi
     *
     * @return the value of contract.abi
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public String getAbi() {
        return abi;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column contract.abi
     *
     * @param abi the value for contract.abi
     *
     * @mbg.generated Fri Mar 22 19:28:42 CST 2024
     */
    public void setAbi(String abi) {
        this.abi = abi == null ? null : abi.trim();
    }
}