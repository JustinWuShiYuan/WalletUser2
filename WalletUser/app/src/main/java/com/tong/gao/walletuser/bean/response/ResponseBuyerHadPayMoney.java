package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseBuyerHadPayMoney implements Serializable {

    private String errcode;
    private String msg;
    private String orderNo;
    private String orderAmount;
    private String orderPrice;
    private String orderNumber;
    private String buyUserId;
    private String sellUserId;
    private String paymentTime;
    private String pamentWay;
    private String prompt;
    private String paymentNumber;


    public ResponseBuyerHadPayMoney(String errcode, String msg, String orderNo, String orderAmount, String orderPrice, String orderNumber, String buyUserId, String sellUserId, String paymentTime, String pamentWay, String prompt, String paymentNumber) {
        this.errcode = errcode;
        this.msg = msg;
        this.orderNo = orderNo;
        this.orderAmount = orderAmount;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
        this.buyUserId = buyUserId;
        this.sellUserId = sellUserId;
        this.paymentTime = paymentTime;
        this.pamentWay = pamentWay;
        this.prompt = prompt;
        this.paymentNumber = paymentNumber;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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

    public String getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(String paymentTime) {
        this.paymentTime = paymentTime;
    }

    public String getPamentWay() {
        return pamentWay;
    }

    public void setPamentWay(String pamentWay) {
        this.pamentWay = pamentWay;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    @Override
    public String toString() {
        return "ResponseBuyerHadPayMoney{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", buyUserId='" + buyUserId + '\'' +
                ", sellUserId='" + sellUserId + '\'' +
                ", paymentTime='" + paymentTime + '\'' +
                ", pamentWay='" + pamentWay + '\'' +
                ", prompt='" + prompt + '\'' +
                ", paymentNumber='" + paymentNumber + '\'' +
                '}';
    }
}
