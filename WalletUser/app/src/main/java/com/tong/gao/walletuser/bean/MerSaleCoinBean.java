package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class MerSaleCoinBean implements Serializable {

    private String ugOtcAdvertId;
    private String userId;
    private String type;        //1.买2.卖
    private String number;
    private String amountType;  //1.限额2.固额
    private String limitMaxAmount;  //1.限额2.固额
    private String limitMinAmount;  //1.限额2.固额
    private String fixedAmount;  //1.限额2.固额
    private String price;  //1.限额2.固额
    private String createdtime;  //1.限额2.固额
    private String modifyTime;  //1.限额2.固额
    private String paymentway;  //1.限额2.固额
    private String status;  //1-出售中2-已下架 3-售罄
    private String prompt;  //1单位:秒
    private String autoReplyContent;  //选填
    private String isSeniorCertification;  //1.是 2.否
    private String isMerchantsTrade;  //1.是 2.否 是否允许平台内的商家购买

    public MerSaleCoinBean(String ugOtcAdvertId, String userId, String type, String number, String amountType, String limitMaxAmount, String limitMinAmount, String fixedAmount, String price, String createdtime, String modifyTime, String paymentway, String status, String prompt, String autoReplyContent, String isSeniorCertification, String isMerchantsTrade) {
        this.ugOtcAdvertId = ugOtcAdvertId;
        this.userId = userId;
        this.type = type;
        this.number = number;
        this.amountType = amountType;
        this.limitMaxAmount = limitMaxAmount;
        this.limitMinAmount = limitMinAmount;
        this.fixedAmount = fixedAmount;
        this.price = price;
        this.createdtime = createdtime;
        this.modifyTime = modifyTime;
        this.paymentway = paymentway;
        this.status = status;
        this.prompt = prompt;
        this.autoReplyContent = autoReplyContent;
        this.isSeniorCertification = isSeniorCertification;
        this.isMerchantsTrade = isMerchantsTrade;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPaymentway() {
        return paymentway;
    }

    public void setPaymentway(String paymentway) {
        this.paymentway = paymentway;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getAutoReplyContent() {
        return autoReplyContent;
    }

    public void setAutoReplyContent(String autoReplyContent) {
        this.autoReplyContent = autoReplyContent;
    }

    public String getIsSeniorCertification() {
        return isSeniorCertification;
    }

    public void setIsSeniorCertification(String isSeniorCertification) {
        this.isSeniorCertification = isSeniorCertification;
    }

    public String getIsMerchantsTrade() {
        return isMerchantsTrade;
    }

    public void setIsMerchantsTrade(String isMerchantsTrade) {
        this.isMerchantsTrade = isMerchantsTrade;
    }

    @Override
    public String toString() {
        return "MerSaleCoinBean{" +
                "ugOtcAdvertId='" + ugOtcAdvertId + '\'' +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", number='" + number + '\'' +
                ", amountType='" + amountType + '\'' +
                ", limitMaxAmount='" + limitMaxAmount + '\'' +
                ", limitMinAmount='" + limitMinAmount + '\'' +
                ", fixedAmount='" + fixedAmount + '\'' +
                ", price='" + price + '\'' +
                ", createdtime='" + createdtime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", paymentway='" + paymentway + '\'' +
                ", status='" + status + '\'' +
                ", prompt='" + prompt + '\'' +
                ", autoReplyContent='" + autoReplyContent + '\'' +
                ", isSeniorCertification='" + isSeniorCertification + '\'' +
                ", isMerchantsTrade='" + isMerchantsTrade + '\'' +
                '}';
    }
}
