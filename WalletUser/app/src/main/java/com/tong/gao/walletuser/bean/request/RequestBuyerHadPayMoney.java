package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestBuyerHadPayMoney implements Serializable {

    private String orderNo;
    private String paymentWay;

    public RequestBuyerHadPayMoney(String orderNo, String paymentWay) {
        this.orderNo = orderNo;
        this.paymentWay = paymentWay;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    @Override
    public String toString() {
        return "RequestBuyerHadPayMoney{" +
                "orderNo='" + orderNo + '\'' +
                ", paymentWay='" + paymentWay + '\'' +
                '}';
    }
}
