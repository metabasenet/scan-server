package com.ether.data.dao;

import com.ether.data.entity.SwapTx;
import com.ether.data.entity.SwapTxKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SwapTxMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    int deleteByPrimaryKey(SwapTxKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    int insert(SwapTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    int insertSelective(SwapTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    SwapTx selectByPrimaryKey(SwapTxKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    int updateByPrimaryKeySelective(SwapTx record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table swap_tx
     *
     * @mbg.generated Fri May 10 11:31:21 CST 2024
     */
    int updateByPrimaryKey(SwapTx record);

    List<Map> getPairPrice01(@Param("pairAddress") String pairAddress, @Param("type") Integer type);
    List<Map> getPairPrice10(@Param("pairAddress") String pairAddress, @Param("type") Integer type);

    List<Map> getPairPriceFlow01(@Param("pairAddress") String pairAddress);
    List<Map> getPairPriceFlow10(@Param("pairAddress") String pairAddress);
}