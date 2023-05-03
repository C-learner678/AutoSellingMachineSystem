package com.jlu.selling.mapper;

import com.jlu.selling.domain.PaperMoney;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper

public interface PaperMoneyMapper {
    List<PaperMoney> getAllPaperMoney();
    Integer insertPaperMoney(Double value);
    Integer setPaperMoney(Double value, int num);
}
