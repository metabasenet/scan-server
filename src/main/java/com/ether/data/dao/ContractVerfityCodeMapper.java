package com.ether.data.dao;

import com.ether.data.entity.ContractVerfityCode;

import java.util.List;

public interface ContractVerfityCodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    int insert(ContractVerfityCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    int insertSelective(ContractVerfityCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    ContractVerfityCode selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    int updateByPrimaryKeySelective(ContractVerfityCode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table contract_verfity_code
     *
     * @mbg.generated Fri Mar 29 10:28:44 CST 2024
     */
    int updateByPrimaryKey(ContractVerfityCode record);

    List<ContractVerfityCode> selectByContractAddress(String contractAddress);

    int deleteFileInfo(String contractAddress, String fileName);
}