package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import com.jlu.selling.domain.Goods;
import com.jlu.selling.service.GoodsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class GoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping("/goods/getAllGoods")
    public List<Goods> getAllGoods(){
        return goodsService.getAllGoods();
    }
    @RequestMapping(value = "/goods/insertGoods", method = RequestMethod.POST)
    public RespBean insertGoods(@RequestBody Map<String, String> params) {
        String name = params.get("name");
        Double price = Double.parseDouble(params.get("price"));
        if(goodsService.insertGoods(name,price)){
            return new RespBean("success", "添加成功!");
        }else{
            return new RespBean("error", "添加失败！");
        }
    }
    @RequestMapping(value = "/goods/modifyGoods", method = RequestMethod.POST)
    public RespBean modifyGoods(@RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        String name = params.get("name");
        Double price = Double.parseDouble(params.get("price"));
        if(goodsService.modifyGoods(id,name,price)){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败！");
        }
    }
    @RequestMapping(value = "/goods/deleteGoods", method = RequestMethod.POST)
    public RespBean deleteGoods(@RequestBody Map<String, String> params) {
        Integer id = Integer.parseInt(params.get("id"));
        if(goodsService.deleteGoods(id)){
            return new RespBean("success", "删除成功!");
        }else{
            return new RespBean("error", "删除失败！");
        }
    }
}
