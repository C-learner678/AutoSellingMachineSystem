package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import com.jlu.selling.domain.Coin;
import com.jlu.selling.service.CoinService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class CoinController {
    @Autowired
    CoinService coinService;
    @GetMapping("/coin/getAllCoin")
    public List<Coin> getAllCoin(){
        return coinService.getAllCoin();
    }
    @RequestMapping(value = "/coin/insertCoin", method = RequestMethod.POST)
    public RespBean insertCoin(@RequestBody Map<String, String> params){
        Double value = Double.parseDouble(params.get("value"));
        if(coinService.insertCoin(value) == 1){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
    @RequestMapping(value = "/coin/setCoin", method = RequestMethod.POST)
    public RespBean setCoin(@RequestBody Map<String, String> params){
        Double value = Double.parseDouble(params.get("value"));
        int num = Integer.parseInt(params.get("num"));
        if(coinService.setCoin(value, num) == 1){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
}
