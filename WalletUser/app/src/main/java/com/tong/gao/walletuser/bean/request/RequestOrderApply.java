package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestOrderApply implements Serializable {

    private String orderNo;
    private String contactWay;
    private String appealReason;
    private String remark;


    public RequestOrderApply(String orderNo, String contactWay, String appealReason, String remark) {
        this.orderNo = orderNo;
        this.contactWay = contactWay;
        this.appealReason = appealReason;
        this.remark = remark;
    }

    public RequestOrderApply(String orderNo, String appealReason) {
        this.orderNo = orderNo;
        this.appealReason = appealReason;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getAppealReason() {
        return appealReason;
    }

    public void setAppealReason(String appealReason) {
        this.appealReason = appealReason;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "RequestOrderApply{" +
                "orderNo='" + orderNo + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", appealReason='" + appealReason + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
