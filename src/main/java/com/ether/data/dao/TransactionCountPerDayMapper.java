package com.ether.data.dao;

import com.ether.data.entity.TransactionCountPerDay;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface TransactionCountPerDayMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    int deleteByPrimaryKey(Date date);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    int insert(TransactionCountPerDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    int insertSelective(TransactionCountPerDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    TransactionCountPerDay selectByPrimaryKey(Date date);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    int updateByPrimaryKeySelective(TransactionCountPerDay record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transaction_count_per_day
     *
     * @mbg.generated Sun Apr 28 15:23:28 CST 2024
     */
    int updateByPrimaryKey(TransactionCountPerDay record);

    TransactionCountPerDay transcationCountByDay();

    List<Map> transcationCountInfo();
}