package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestTransferAccountBean implements Serializable {

    private String address; //转账地址
    private String number;  //数量
    private String remark;  //备注
    private String transactionPassword;//交易密码
    private String googlecode;

    public RequestTransferAccountBean(String address, String number, String remark, String transactionPassword, String googlecode) {
        this.address = address;
        this.number = number;
        this.remark = remark;
        this.transactionPassword = transactionPassword;
        this.googlecode = googlecode;
    }

    @Override
    public String toString() {
        return "RequestTransferAccountBean{" +
                "address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", remark='" + remark + '\'' +
                ", transactionPassword='" + transactionPassword + '\'' +
                ", googlecode='" + googlecode + '\'' +
                '}';
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}
