package com.jlu.selling.domain;

public class Coin {
    private Double value;
    private Integer num;
    public Coin(){}
    public Integer getNum() {
        return num;
    }
    public void setNum(Integer num) {
        this.num = num;
    }
    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
}
