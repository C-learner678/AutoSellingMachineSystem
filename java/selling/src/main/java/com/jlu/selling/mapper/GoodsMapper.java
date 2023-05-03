package com.jlu.selling.mapper;

import com.jlu.selling.domain.Goods;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {
    List<Goods> getAllGoods();
    int insertGoods(String name, Double price);
    int modifyGoodsName(int id, String name);
    int modifyGoodsPrice(int id, Double price);
    int deleteGoods(int id);
}