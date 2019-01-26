package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseCancelOrder implements Serializable {

    private String errcode;
    private String msg;
    private String orderNo;
    private String cancelTime;

    public ResponseCancelOrder(String errcode, String msg, String orderNo, String cancelTime) {
        this.errcode = errcode;
        this.msg = msg;
        this.orderNo = orderNo;
        this.cancelTime = cancelTime;
    }

    @Override
    public String toString() {
        return "ResponseCancelOrder{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", cancelTime='" + cancelTime + '\'' +
                '}';
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

    public String getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(String cancelTime) {
        this.cancelTime = cancelTime;
    }
}
