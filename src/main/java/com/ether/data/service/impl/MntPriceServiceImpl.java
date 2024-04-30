package com.ether.data.service.impl;

import com.ether.data.dao.MntPriceMapper;
import com.ether.data.entity.MntPrice;
import com.ether.data.service.MntPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MntPriceServiceImpl implements MntPriceService {
    @Autowired
    private MntPriceMapper mntPriceMapper;
    @Override
    public MntPrice getPrice(){
       return mntPriceMapper.selectByPrimaryKey(1);
    }
}
