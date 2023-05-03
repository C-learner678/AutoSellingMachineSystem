package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import com.jlu.selling.domain.PaperMoney;
import com.jlu.selling.service.PaperMoneyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class PaperMoneyController {
    @Autowired
    PaperMoneyService paperMoneyService;
    @GetMapping("/paperMoney/getAllPaperMoney")
    public List<PaperMoney> getAllCoin(){
        return paperMoneyService.getAllPaperMoney();
    }
    @RequestMapping(value = "/paperMoney/insertPaperMoney", method = RequestMethod.POST)
    public RespBean insertPaperMoney(@RequestBody Map<String, String> params){
        Double value = Double.parseDouble(params.get("value"));
        if(paperMoneyService.insertPaperMoney(value) == 1){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
    @RequestMapping(value = "/paperMoney/setPaperMoney", method = RequestMethod.POST)
    public RespBean setCoin(@RequestBody Map<String, String> params){
        Double value = Double.parseDouble(params.get("value"));
        int num = Integer.parseInt(params.get("num"));
        if(paperMoneyService.setPaperMoney(value, num) == 1){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
}
