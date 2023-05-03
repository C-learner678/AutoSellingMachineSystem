package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import com.jlu.selling.domain.PaperMoney;
import com.jlu.selling.service.MoneyService;
import com.jlu.selling.service.PaperMoneyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class MoneyController {
    @Autowired
    MoneyService moneyService;
    @RequestMapping(value = "/money/returnMoney", method = RequestMethod.POST)
    public int[] returnMoney(@RequestBody Map<String, String> params){
        Double money = Double.parseDouble(params.get("money"));
        int result[] = moneyService.returnMoney(money);
        if(result != null){
            return result;
        }else{
            return new int[]{};
        }
    }
}
