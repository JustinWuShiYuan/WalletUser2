package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class UserOrderBean implements Serializable {

    private String otcOrderId;
    private String orderNo;
    private String advertId;
    private String buyUserId;
    private String sellUserId;
    private String number;
    private String price;
    private String brokerage;
    private String status;
    private String createdTime;
    private String modifyTime;
    private String paymentTime;
    private String finishTime;
    private String confirmTime;
    private String closeTime;
    private String isEvaluation;
    private String convertRmb;
    private String paymentWay;
    private String orderAmount;
    private String orderType;
    private String orderSellIsVip;
    private String restTime;//剩余时间
    private String txHash;//交易号

    public UserOrderBean(String otcOrderId, String orderNo, String advertId, String buyUserId, String sellUserId, String number, String price, String brokerage, String status, String createdTime, String modifyTime, String paymentTime, String finishTime, String confirmTime, String closeTime, String isEvaluation, String convertRmb, String paymentWay, String orderAmount, String orderType, String orderSellIsVip, String restTime, String txHash) {
        this.otcOrderId = otcOrderId;
        this.orderNo = orderNo;
        this.advertId = advertId;
        this.buyUserId = buyUserId;
        this.sellUserId = sellUserId;
        this.number = number;
        this.price = price;
        this.brokerage = brokerage;
        this.status = status;
        this.createdTime = createdTime;
        this.modifyTime = modifyTime;
        this.paymentTime = paymentTime;
        this.finishTime = finishTime;
        this.confirmTime = confirmTime;
        this.closeTime = closeTime;
        this.isEvaluation = isEvaluation;
        this.convertRmb = convertRmb;
        this.paymentWay = paymentWay;
        this.orderAmount = orderAmount;
        this.orderType = orderType;
        this.orderSellIsVip = orderSellIsVip;
        this.restTime = restTime;
        this.txHash = txHash;
    }

    public String getRestTime() {
        return restTime;
    }

    public void setRestTime(String restTime) {
        this.restTime = restTime;
    }

    public String getTxHash() {
        return txHash;
    }

    public void setTxHash(String txHash) {
        this.txHash = txHash;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getOrderSellIsVip() {
        return orderSellIsVip;
    }

    public void setOrderSellIsVip(String orderSellIsVip) {
        this.orderSellIsVip = orderSellIsVip;
    }

    public String getOtcOrderId() {
        return otcOrderId;
    }

    public void setOtcOrderId(String otcOrderId) {
        this.otcOrderId = otcOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAdvertId() {
        return advertId;
    }

    public void setAdvertId(String advertId) {
        this.advertId = advertId;
    }

    public String getBuyUserId() {
        return buyUserId;
    }

    public void setBuyUserId(String buyUserId) {
        this.buyUserId = buyUserId;
    }

    public String getSellUserId() {
        return sellUserId;
    }

    public void setSellUserId(String sellUserId) {
        this.sellUserId = sellUserId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrokerage() {
        return brokerage;
    }

    public void setBrokerage(String brokerage) {
        this.brokerage = brokerage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getConfirmTime() {
        return confirmTime;
    }

    public void setConfirmTime(String confirmTime) {
        this.confirmTime = confirmTime;
    }

    public String getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(String closeTime) {
        this.closeTime = closeTime;
    }

    public String getIsEvaluation() {
        return isEvaluation;
    }

    public void setIsEvaluation(String isEvaluation) {
        this.isEvaluation = isEvaluation;
    }

    public String getConvertRmb() {
        return convertRmb;
    }

    public void setConvertRmb(String convertRmb) {
        this.convertRmb = convertRmb;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    @Override
    public String toString() {
        return "UserOrderBean{" +
                "otcOrderId='" + otcOrderId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", advertId='" + advertId + '\'' +
                ", buyUserId='" + buyUserId + '\'' +
                ", sellUserId='" + sellUserId + '\'' +
                ", number='" + number + '\'' +
                ", price='" + price + '\'' +
                ", brokerage='" + brokerage + '\'' +
                ", status='" + status + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", modifyTime='" + modifyTime + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", confirmTime='" + confirmTime + '\'' +
                ", closeTime='" + closeTime + '\'' +
                ", isEvaluation='" + isEvaluation + '\'' +
                ", convertRmb='" + convertRmb + '\'' +
                ", paymentWay='" + paymentWay + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", orderType='" + orderType + '\'' +
                ", orderSellIsVip='" + orderSellIsVip + '\'' +
                ", restTime='" + restTime + '\'' +
                ", txHash='" + txHash + '\'' +
                '}';
    }
}
