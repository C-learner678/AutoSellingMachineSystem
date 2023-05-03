package com.jlu.selling.mapper;

import com.jlu.selling.domain.Coin;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CoinMapper {
    List<Coin> getAllCoin();
    Integer insertCoin(Double value);
    Integer setCoin(Double value, int num);
}
