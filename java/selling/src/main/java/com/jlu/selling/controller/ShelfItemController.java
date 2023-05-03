package com.jlu.selling.controller;

import com.jlu.selling.bean.RespBean;
import com.jlu.selling.domain.ShelfItem;
import com.jlu.selling.service.ShelfItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ShelfItemController {
    @Autowired
    ShelfItemService shelfItemService;
    @GetMapping("/shelf/getAllShelfItem")
    public List<ShelfItem> getAllShelfItem(){
        return shelfItemService.getAllShelfItem();
    }
    @RequestMapping(value = "/shelf/buyShelfItem", method = RequestMethod.POST)
    public RespBean buyShelfItem(@RequestBody Map<String, String> params){
        int position = Integer.parseInt(params.get("position"));
        if(shelfItemService.buyShelfItem(position) == 1){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
    @RequestMapping(value = "shelf/setShelfItem", method = RequestMethod.POST)
    public RespBean setShelfItem(@RequestBody Map<String, String> params){
        int position = Integer.parseInt(params.get("position"));
        int goodsid = Integer.parseInt(params.get("goodsid"));
        int num = Integer.parseInt(params.get("num"));
        if(shelfItemService.setShelfItem(position, goodsid, num)){
            return new RespBean("success", "修改成功!");
        }else{
            return new RespBean("error", "修改失败!");
        }
    }
}

