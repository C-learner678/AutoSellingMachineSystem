package com.jlu.selling.mapper;

import com.jlu.selling.domain.ShelfItem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ShelfItemMapper {
    List<ShelfItem> getAllShelfItem();
    int checkShelfItem(int position);
    int buyShelfItem(int position);//num - 1
    int setShelfItemGoodsId(int position, int goodsid);
    int setShelfItemNum(int position, int num);
}