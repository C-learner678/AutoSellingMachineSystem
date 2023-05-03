package com.jlu.selling.service;

import com.jlu.selling.domain.Coin;
import com.jlu.selling.mapper.CoinMapper;
import com.jlu.selling.domain.PaperMoney;
import com.jlu.selling.mapper.PaperMoneyMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MoneyService {
    @Autowired
    CoinMapper coinMapper;
    @Autowired
    PaperMoneyMapper paperMoneyMapper;
    public int[] returnMoney(Double money){
        //退钱，返回（钞票面值：数量）
        //100元纸币：7 50元纸币：6 20元纸币：5 10元纸币：4 5元纸币：3 1元纸币：2 1元硬币：1 0.5元硬币：0
        List<Coin> coinList = coinMapper.getAllCoin();
        List<PaperMoney> paperMoneyList = paperMoneyMapper.getAllPaperMoney();
        Map<Double, Integer> coinMap = new HashMap<Double, Integer>();
        Map<Double, Integer> paperMoneyMap = new HashMap<Double, Integer>();
        for(int i=0;i<coinList.size();i++){
            coinMap.put(coinList.get(i).getValue(), coinList.get(i).getNum());
        }
        for(int i=0;i<paperMoneyList.size();i++){
            paperMoneyMap.put(paperMoneyList.get(i).getValue(), paperMoneyList.get(i).getNum());
        }
        Double userMoney = money;
        if(userMoney > 0){
            int moneyList[] = {0,0,0,0,0,0,0,0};
            int label = 7;
            while(userMoney > 0 || label >= 0){
                if(label == 7){
                    while((userMoney >= 100) && (paperMoneyMap.get(100.0) > 0)){
                        userMoney -= 100;
                        paperMoneyMap.put(100.0, paperMoneyMap.get(100.0)-1);
                        moneyList[7] += 1;
                    }
                }else if(label == 6){
                    while((userMoney >= 50) && (paperMoneyMap.get(50.0) > 0)){
                        userMoney -= 50;
                        paperMoneyMap.put(50.0, paperMoneyMap.get(50.0)-1);
                        moneyList[6] += 1;
                    }
                }else if(label == 5){
                    while((userMoney >= 20) && (paperMoneyMap.get(20.0) > 0)){
                        userMoney -= 20;
                        paperMoneyMap.put(20.0, paperMoneyMap.get(20.0)-1);
                        moneyList[5] += 1;
                    }
                }else if(label == 4){
                    while((userMoney >= 10) && (paperMoneyMap.get(10.0) > 0)){
                        userMoney -= 10;
                        paperMoneyMap.put(10.0, paperMoneyMap.get(10.0)-1);
                        moneyList[4] += 1;
                    }
                }else if(label == 3){
                    while((userMoney >= 5) && (paperMoneyMap.get(5.0) > 0)){
                        userMoney -= 5;
                        paperMoneyMap.put(5.0, paperMoneyMap.get(5.0)-1);
                        moneyList[3] += 1;
                    }
                }else if(label == 2){
                    while((userMoney >= 1) && (paperMoneyMap.get(1.0) > 0)){
                        userMoney -= 1;
                        paperMoneyMap.put(1.0, paperMoneyMap.get(1.0)-1);
                        moneyList[2] += 1;
                    }
                }else if(label == 1){
                    while((userMoney >= 1) && (coinMap.get(1.0) > 0)){
                        userMoney -= 1;
                        coinMap.put(1.0, coinMap.get(1.0)-1);
                        moneyList[1] += 1;
                    }
                }else{
                    while((userMoney >= 0.5) && (coinMap.get(0.5) > 0)){
                        userMoney -= 0.5;
                        coinMap.put(0.5, coinMap.get(0.5)-1);
                        moneyList[0] += 1;
                    }
                }
                label -= 1;
            }
            if(userMoney == 0){
                coinMapper.setCoin(0.5,coinMap.get(0.5));
                coinMapper.setCoin(1.0,coinMap.get(1.0));
                paperMoneyMapper.setPaperMoney(1.0,paperMoneyMap.get(1.0));
                paperMoneyMapper.setPaperMoney(5.0,paperMoneyMap.get(5.0));
                paperMoneyMapper.setPaperMoney(10.0,paperMoneyMap.get(10.0));
                paperMoneyMapper.setPaperMoney(20.0,paperMoneyMap.get(20.0));
                paperMoneyMapper.setPaperMoney(50.0,paperMoneyMap.get(50.0));
                paperMoneyMapper.setPaperMoney(100.0,paperMoneyMap.get(100.0));
                return moneyList;
            }else{
                return null;
            }
        }
        return null;
    }
}
