package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class CoinBean implements Serializable {

    private String ugOtcAdvertId;
    private String userId;
    private String nickName;
    private String isVip;
    private String orderAllNumber;
    private String orderTotle;
    private String successRate;
    private String number;
    private String amountType;
    private String limitMaxAmount;
    private String limitMinAmount;
    private String fixedAmount;
    private String price;
    private String createdtime;
    private String paymentway;


    public CoinBean(String ugOtcAdvertId, String userId, String nickName, String isVip, String orderAllNumber, String orderTotle, String successRate, String number, String amountType, String fixedAmount, String price, String createdtime, String paymentway) {
        this.ugOtcAdvertId = ugOtcAdvertId;
        this.userId = userId;
        this.nickName = nickName;
        this.isVip = isVip;
        this.orderAllNumber = orderAllNumber;
        this.orderTotle = orderTotle;
        this.successRate = successRate;
        this.number = number;
        this.amountType = amountType;
        this.fixedAmount = fixedAmount;
        this.price = price;
        this.createdtime = createdtime;
        this.paymentway = paymentway;
    }

    public CoinBean(String ugOtcAdvertId, String userId, String nickName, String isVip, String orderAllNumber, String orderTotle, String successRate, String number, String amountType, String limitMaxAmount, String limitMinAmount, String fixedAmount, String price, String createdtime, String paymentway) {
        this.ugOtcAdvertId = ugOtcAdvertId;
        this.userId = userId;
        this.nickName = nickName;
        this.isVip = isVip;
        this.orderAllNumber = orderAllNumber;
        this.orderTotle = orderTotle;
        this.successRate = successRate;
        this.number = number;
        this.amountType = amountType;
        this.limitMaxAmount = limitMaxAmount;
        this.limitMinAmount = limitMinAmount;
        this.fixedAmount = fixedAmount;
        this.price = price;
        this.createdtime = createdtime;
        this.paymentway = paymentway;
    }

    public String getIsVip() {
        return isVip;
    }

    public void setIsVip(String isVip) {
        this.isVip = isVip;
    }

    public String getLimitMaxAmount() {
        return limitMaxAmount;
    }

    public void setLimitMaxAmount(String limitMaxAmount) {
        this.limitMaxAmount = limitMaxAmount;
    }

    public String getLimitMinAmount() {
        return limitMinAmount;
    }

    public void setLimitMinAmount(String limitMinAmount) {
        this.limitMinAmount = limitMinAmount;
    }

    public String getUgOtcAdvertId() {
        return ugOtcAdvertId;
    }

    public void setUgOtcAdvertId(String ugOtcAdvertId) {
        this.ugOtcAdvertId = ugOtcAdvertId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getOrderAllNumber() {
        return orderAllNumber;
    }

    public void setOrderAllNumber(String orderAllNumber) {
        this.orderAllNumber = orderAllNumber;
    }

    public String getOrderTotle() {
        return orderTotle;
    }

    public void setOrderTotle(String orderTotle) {
        this.orderTotle = orderTotle;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType;
    }

    public String getFixedAmount() {
        return fixedAmount;
    }

    public void setFixedAmount(String fixedAmount) {
        this.fixedAmount = fixedAmount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedtime() {
        return createdtime;
    }

    public void setCreatedtime(String createdtime) {
        this.createdtime = createdtime;
    }

    public String getPaymentway() {
        return paymentway;
    }

    public void setPaymentway(String paymentway) {
        this.paymentway = paymentway;
    }

    @Override
    public String toString() {
        return "CoinBean{" +
                "ugOtcAdvertId='" + ugOtcAdvertId + '\'' +
                ", userId='" + userId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", isVip='" + isVip + '\'' +
                ", orderAllNumber='" + orderAllNumber + '\'' +
                ", orderTotle='" + orderTotle + '\'' +
                ", successRate='" + successRate + '\'' +
                ", number='" + number + '\'' +
                ", amountType='" + amountType + '\'' +
                ", limitMaxAmount='" + limitMaxAmount + '\'' +
                ", limitMinAmount='" + limitMinAmount + '\'' +
                ", fixedAmount='" + fixedAmount + '\'' +
                ", price='" + price + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", paymentway='" + paymentway + '\'' +
                '}';
    }
}
