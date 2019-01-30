package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseBtcExchangeDetail implements Serializable {

    private String errcode;
    private String msg;
    private String number;
    private String btcNumber;
    private String btcAddress;
    private String createdTime;
    private String status;
    private String rejectReason;
    private String txhash;

    public ResponseBtcExchangeDetail(String errcode, String msg, String number, String btcNumber, String btcAddress, String createdTime, String status, String rejectReason, String txhash) {
        this.errcode = errcode;
        this.msg = msg;
        this.number = number;
        this.btcNumber = btcNumber;
        this.btcAddress = btcAddress;
        this.createdTime = createdTime;
        this.status = status;
        this.rejectReason = rejectReason;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBtcNumber() {
        return btcNumber;
    }

    public void setBtcNumber(String btcNumber) {
        this.btcNumber = btcNumber;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    @Override
    public String toString() {
        return "ResponseBtcExchangeDetail{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", number='" + number + '\'' +
                ", btcNumber='" + btcNumber + '\'' +
                ", btcAddress='" + btcAddress + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", status='" + status + '\'' +
                ", rejectReason='" + rejectReason + '\'' +
                ", txhash='" + txhash + '\'' +
                '}';
    }
}
