package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class FireCoinBean implements Serializable {
    private String coinId;
    private String tradeId;
    private String price;
    private String rmbPrice;
    private String upAndDown;
    private int sort;

    public FireCoinBean(String coinId, String tradeId, String price, String rmbPrice, String upAndDown, int sort) {
        this.coinId = coinId;
        this.tradeId = tradeId;
        this.price = price;
        this.rmbPrice = rmbPrice;
        this.upAndDown = upAndDown;
        this.sort = sort;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRmbPrice() {
        return rmbPrice;
    }

    public void setRmbPrice(String rmbPrice) {
        this.rmbPrice = rmbPrice;
    }

    public String getUpAndDown() {
        return upAndDown;
    }

    public void setUpAndDown(String upAndDown) {
        this.upAndDown = upAndDown;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    @Override
    public String toString() {
        return "FireCoinBean{" +
                "coinId='" + coinId + '\'' +
                ", tradeId='" + tradeId + '\'' +
                ", price='" + price + '\'' +
                ", rmbPrice='" + rmbPrice + '\'' +
                ", upAndDown='" + upAndDown + '\'' +
                ", sort=" + sort +
                '}';
    }
}
