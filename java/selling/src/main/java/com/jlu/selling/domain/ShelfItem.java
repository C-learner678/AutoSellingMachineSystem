package com.jlu.selling.domain;

public class ShelfItem {
    private Integer goodsid;
    private Integer position;
    private String name;
    private Integer num;
    private Double price;
    public ShelfItem(){}
    public Integer getGoodsid() {
        return goodsid;
    }
    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
}
