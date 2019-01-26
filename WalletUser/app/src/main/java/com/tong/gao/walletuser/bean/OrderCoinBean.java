package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class OrderCoinBean implements Serializable {

    private String tradeCoinType;
    private String tradeMoney;
    private String remainTime;
    private String orderCreateTime;
    private String orderStatus;

    public OrderCoinBean(String tradeCoinType, String tradeMoney, String remainTime, String orderCreateTime, String orderStatus) {
        this.tradeCoinType = tradeCoinType;
        this.tradeMoney = tradeMoney;
        this.remainTime = remainTime;
        this.orderCreateTime = orderCreateTime;
        this.orderStatus = orderStatus;
    }

    public String getTradeCoinType() {
        return tradeCoinType;
    }

    public void setTradeCoinType(String tradeCoinType) {
        this.tradeCoinType = tradeCoinType;
    }

    public String getTradeMoney() {
        return tradeMoney;
    }

    public void setTradeMoney(String tradeMoney) {
        this.tradeMoney = tradeMoney;
    }

    public String getRemainTime() {
        return remainTime;
    }

    public void setRemainTime(String remainTime) {
        this.remainTime = remainTime;
    }

    public String getOrderCreateTime() {
        return orderCreateTime;
    }

    public void setOrderCreateTime(String orderCreateTime) {
        this.orderCreateTime = orderCreateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    @Override
    public String toString() {
        return "OrderCoinBean{" +
                "tradeCoinType='" + tradeCoinType + '\'' +
                ", tradeMoney='" + tradeMoney + '\'' +
                ", remainTime='" + remainTime + '\'' +
                ", orderCreateTime='" + orderCreateTime + '\'' +
                ", orderStatus='" + orderStatus + '\'' +
                '}';
    }

}
