package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestDeleteReceiptMoneyAccount implements Serializable {

    private String paymentWayId;

    public RequestDeleteReceiptMoneyAccount(String paymentWayId) {
        this.paymentWayId = paymentWayId;
    }

    public String getPaymentWayId() {
        return paymentWayId;
    }

    public void setPaymentWayId(String paymentWayId) {
        this.paymentWayId = paymentWayId;
    }

    @Override
    public String toString() {
        return "RequestDeleteReceiptMoneyAccount{" +
                "paymentWayId='" + paymentWayId + '\'' +
                '}';
    }
}
