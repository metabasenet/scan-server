package com.ether.data.entity;

import java.math.BigDecimal;

public class Block {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.number
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private BigDecimal number;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.hash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private String hash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.parentHash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private String parenthash;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.timestamp
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private Long timestamp;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.transactionCount
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private Integer transactioncount;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.nonce
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private String nonce;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.difficulty
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private Long difficulty;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.gasLimit
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private BigDecimal gaslimit;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.gasUsed
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private BigDecimal gasused;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.gasPrice
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private BigDecimal gasprice;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.miner
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private String miner;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.extraData
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private String extradata;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column block.baseFeePerGas
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    private BigDecimal basefeepergas;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.number
     *
     * @return the value of block.number
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public BigDecimal getNumber() {
        return number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.number
     *
     * @param number the value for block.number
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setNumber(BigDecimal number) {
        this.number = number;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.hash
     *
     * @return the value of block.hash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public String getHash() {
        return hash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.hash
     *
     * @param hash the value for block.hash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setHash(String hash) {
        this.hash = hash == null ? null : hash.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.parentHash
     *
     * @return the value of block.parentHash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public String getParenthash() {
        return parenthash;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.parentHash
     *
     * @param parenthash the value for block.parentHash
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setParenthash(String parenthash) {
        this.parenthash = parenthash == null ? null : parenthash.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.timestamp
     *
     * @return the value of block.timestamp
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.timestamp
     *
     * @param timestamp the value for block.timestamp
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.transactionCount
     *
     * @return the value of block.transactionCount
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public Integer getTransactioncount() {
        return transactioncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.transactionCount
     *
     * @param transactioncount the value for block.transactionCount
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setTransactioncount(Integer transactioncount) {
        this.transactioncount = transactioncount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.nonce
     *
     * @return the value of block.nonce
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public String getNonce() {
        return nonce;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.nonce
     *
     * @param nonce the value for block.nonce
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setNonce(String nonce) {
        this.nonce = nonce == null ? null : nonce.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.difficulty
     *
     * @return the value of block.difficulty
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public Long getDifficulty() {
        return difficulty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.difficulty
     *
     * @param difficulty the value for block.difficulty
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setDifficulty(Long difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.gasLimit
     *
     * @return the value of block.gasLimit
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public BigDecimal getGaslimit() {
        return gaslimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.gasLimit
     *
     * @param gaslimit the value for block.gasLimit
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setGaslimit(BigDecimal gaslimit) {
        this.gaslimit = gaslimit;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.gasUsed
     *
     * @return the value of block.gasUsed
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public BigDecimal getGasused() {
        return gasused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.gasUsed
     *
     * @param gasused the value for block.gasUsed
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setGasused(BigDecimal gasused) {
        this.gasused = gasused;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.gasPrice
     *
     * @return the value of block.gasPrice
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public BigDecimal getGasprice() {
        return gasprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.gasPrice
     *
     * @param gasprice the value for block.gasPrice
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setGasprice(BigDecimal gasprice) {
        this.gasprice = gasprice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.miner
     *
     * @return the value of block.miner
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public String getMiner() {
        return miner;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.miner
     *
     * @param miner the value for block.miner
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setMiner(String miner) {
        this.miner = miner == null ? null : miner.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.extraData
     *
     * @return the value of block.extraData
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public String getExtradata() {
        return extradata;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.extraData
     *
     * @param extradata the value for block.extraData
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setExtradata(String extradata) {
        this.extradata = extradata == null ? null : extradata.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column block.baseFeePerGas
     *
     * @return the value of block.baseFeePerGas
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public BigDecimal getBasefeepergas() {
        return basefeepergas;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column block.baseFeePerGas
     *
     * @param basefeepergas the value for block.baseFeePerGas
     *
     * @mbg.generated Thu Apr 25 11:03:14 CST 2024
     */
    public void setBasefeepergas(BigDecimal basefeepergas) {
        this.basefeepergas = basefeepergas;
    }
}