package com.ether.data.dao;

import com.ether.data.entity.SwapTx;
import com.ether.data.entity.SwapTxKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SwapTxPriceMapper {
    List<Map> getPairPrice01(@Param("pairAddress") String pairAddress, @Param("type") Integer type);
    List<Map> getPairPrice10(@Param("pairAddress") String pairAddress, @Param("type") Integer type);

    List<Map> getPairPriceFlow01(@Param("pairAddress") String pairAddress);
    List<Map> getPairPriceFlow10(@Param("pairAddress") String pairAddress);
}