package com.jlu.selling.service;

import com.jlu.selling.domain.Goods;
import com.jlu.selling.mapper.GoodsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {
    @Autowired
    GoodsMapper goodsMapper;
    public List<Goods> getAllGoods(){
        return goodsMapper.getAllGoods();
    }
    public boolean insertGoods(String name, Double price){
        if(goodsMapper.insertGoods(name, price) == 1){
            return true;
        }else{
            return false;
        }
    }
    public boolean modifyGoods(int id, String name, Double price){
        if(goodsMapper.modifyGoodsName(id, name) == 1){
            if(goodsMapper.modifyGoodsPrice(id, price) == 1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
    public boolean deleteGoods(int id){
        if(goodsMapper.deleteGoods(id) == 1){
            return true;
        }else{
            return false;
        }
    }
}
