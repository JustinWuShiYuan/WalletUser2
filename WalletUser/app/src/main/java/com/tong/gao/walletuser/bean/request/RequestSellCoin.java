package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestSellCoin implements Serializable {

    private String number;
    private String amountType;
    private String limitMaxAmount;
    private String limitMinAmount;
    private String fixedAmount;
    private String price;
    private String type;
    private String pamentWay;
    private String coinId;
    private String coinType;
    private String prompt;
    private String autoReplyContent;
    private String isSeniorCertification;
    private String isMerchantsTrade;
    private String transactionPassword;
    private String googlecode;



    public RequestSellCoin(String number, String amountType, String limitMaxAmount, String limitMinAmount, String fixedAmount, String price, String type, String pamentWay, String coinId, String coinType, String prompt, String autoReplyContent, String isSeniorCertification, String isMerchantsTrade, String transactionPassword, String googlecode) {
        this.number = number;
        this.amountType = amountType;
        this.limitMaxAmount = limitMaxAmount;
        this.limitMinAmount = limitMinAmount;
        this.fixedAmount = fixedAmount;
        this.price = price;
        this.type = type;
        this.pamentWay = pamentWay;
        this.coinId = coinId;
        this.coinType = coinType;
        this.prompt = prompt;
        this.autoReplyContent = autoReplyContent;
        this.isSeniorCertification = isSeniorCertification;
        this.isMerchantsTrade = isMerchantsTrade;
        this.transactionPassword = transactionPassword;
        this.googlecode = googlecode;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPamentWay() {
        return pamentWay;
    }

    public void setPamentWay(String pamentWay) {
        this.pamentWay = pamentWay;
    }

    public String getCoinId() {
        return coinId;
    }

    public void setCoinId(String coinId) {
        this.coinId = coinId;
    }

    public String getCoinType() {
        return coinType;
    }

    public void setCoinType(String coinType) {
        this.coinType = coinType;
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

    public String getTransactionPassword() {
        return transactionPassword;
    }

    public void setTransactionPassword(String transactionPassword) {
        this.transactionPassword = transactionPassword;
    }

    public String getGooglecode() {
        return googlecode;
    }

    public void setGooglecode(String googlecode) {
        this.googlecode = googlecode;
    }

    @Override
    public String toString() {
        return "RequestSellCoin{" +
                "number='" + number + '\'' +
                ", amountType='" + amountType + '\'' +
                ", limitMaxAmount='" + limitMaxAmount + '\'' +
                ", limitMinAmount='" + limitMinAmount + '\'' +
                ", fixedAmount='" + fixedAmount + '\'' +
                ", price='" + price + '\'' +
                ", type='" + type + '\'' +
                ", pamentWay='" + pamentWay + '\'' +
                ", coinId='" + coinId + '\'' +
                ", coinType='" + coinType + '\'' +
                ", prompt='" + prompt + '\'' +
                ", autoReplyContent='" + autoReplyContent + '\'' +
                ", isSeniorCertification='" + isSeniorCertification + '\'' +
                ", isMerchantsTrade='" + isMerchantsTrade + '\'' +
                ", transactionPassword='" + transactionPassword + '\'' +
                ", googlecode='" + googlecode + '\'' +
                '}';
    }
}
