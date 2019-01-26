package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestCancelOrder implements Serializable {

    private String orderNo;

    public RequestCancelOrder(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }


    @Override
    public String toString() {
        return "RequestCancelOrder{" +
                "orderNo='" + orderNo + '\'' +
                '}';
    }
}
