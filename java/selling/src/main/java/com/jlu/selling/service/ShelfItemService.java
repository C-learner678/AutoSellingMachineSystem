package com.jlu.selling.service;

import com.jlu.selling.domain.ShelfItem;
import com.jlu.selling.mapper.ShelfItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfItemService {
    @Autowired
    ShelfItemMapper shelfItemMapper;
    public List<ShelfItem> getAllShelfItem(){
        return shelfItemMapper.getAllShelfItem();
    }
    public int buyShelfItem(int position){
        if(shelfItemMapper.checkShelfItem(position) > 0){
            return shelfItemMapper.buyShelfItem(position);
        }else{
            return 0;
        }
    }
    public boolean setShelfItem(int position, int goodsid, int num){
        if(shelfItemMapper.setShelfItemGoodsId(position, goodsid)==1){
            if(shelfItemMapper.setShelfItemNum(position, num)==1){
                return true;
            }else{
                return false;
            }
        }else{
            return false;
        }
    }
}