package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestQueryBuyCoinBean implements Serializable {

    private String pageNum;
    private String pageSize;
    private String paymentWay;
    private String type;
    private String price;
    private String limitMaxPrice;
    private String limitMinPrice;


    public RequestQueryBuyCoinBean(String pageNum, String pageSize, String paymentWay, String type, String price, String limitMaxPrice, String limitMinPrice) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.paymentWay = paymentWay;
        this.type = type;
        this.price = price;
        this.limitMaxPrice = limitMaxPrice;
        this.limitMinPrice = limitMinPrice;
    }



    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getPageSize() {
        return pageSize;
    }

    public void setPageSize(String pageSize) {
        this.pageSize = pageSize;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLimitMaxPrice() {
        return limitMaxPrice;
    }

    public void setLimitMaxPrice(String limitMaxPrice) {
        this.limitMaxPrice = limitMaxPrice;
    }

    public String getLimitMinPrice() {
        return limitMinPrice;
    }

    public void setLimitMinPrice(String limitMinPrice) {
        this.limitMinPrice = limitMinPrice;
    }

    @Override
    public String toString() {
        return "RequestQueryBuyCoinBean{" +
                "pageNum='" + pageNum + '\'' +
                ", pageSize='" + pageSize + '\'' +
                ", paymentWay='" + paymentWay + '\'' +
                ", type='" + type + '\'' +
                ", price='" + price + '\'' +
                ", limitMaxPrice='" + limitMaxPrice + '\'' +
                ", limitMinPrice='" + limitMinPrice + '\'' +
                '}';
    }
}
