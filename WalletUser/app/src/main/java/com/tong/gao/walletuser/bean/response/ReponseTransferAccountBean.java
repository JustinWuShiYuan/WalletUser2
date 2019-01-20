package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ReponseTransferAccountBean implements Serializable {

    private String errcode;
    private String msg;
    private String accountTransferId;
    private String address;
    private String number;
    private String remark;
    private String transferTime;
    private String txhash;


    public ReponseTransferAccountBean(String errcode, String msg, String accountTransferId, String address, String number, String remark, String transferTime, String txhash) {
        this.errcode = errcode;
        this.msg = msg;
        this.accountTransferId = accountTransferId;
        this.address = address;
        this.number = number;
        this.remark = remark;
        this.transferTime = transferTime;
        this.txhash = txhash;
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

    public String getAccountTransferId() {
        return accountTransferId;
    }

    public void setAccountTransferId(String accountTransferId) {
        this.accountTransferId = accountTransferId;
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

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    @Override
    public String toString() {
        return "ReponseTransferAccountBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", accountTransferId='" + accountTransferId + '\'' +
                ", address='" + address + '\'' +
                ", number='" + number + '\'' +
                ", remark='" + remark + '\'' +
                ", transferTime='" + transferTime + '\'' +
                ", txhash='" + txhash + '\'' +
                '}';
    }
}
