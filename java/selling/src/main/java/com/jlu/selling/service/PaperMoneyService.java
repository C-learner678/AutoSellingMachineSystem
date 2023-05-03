package com.jlu.selling.service;

import com.jlu.selling.domain.PaperMoney;
import com.jlu.selling.mapper.PaperMoneyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaperMoneyService {
    @Autowired
    PaperMoneyMapper paperMoneyMapper;
    public List<PaperMoney> getAllPaperMoney(){ return paperMoneyMapper.getAllPaperMoney(); }
    public Integer insertPaperMoney(Double value){ return paperMoneyMapper.insertPaperMoney(value);}
    public Integer setPaperMoney(Double value,int num){return paperMoneyMapper.setPaperMoney(value,num);}
}
