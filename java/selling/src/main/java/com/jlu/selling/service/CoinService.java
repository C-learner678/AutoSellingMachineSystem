package com.jlu.selling.service;

import com.jlu.selling.domain.Coin;
import com.jlu.selling.mapper.CoinMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoinService {
    @Autowired
    CoinMapper coinMapper;
    public List<Coin> getAllCoin(){
        return coinMapper.getAllCoin();
    }
    public Integer insertCoin(Double value){ return coinMapper.insertCoin(value);}
    public Integer setCoin(Double value,int num){return coinMapper.setCoin(value,num);}
}
