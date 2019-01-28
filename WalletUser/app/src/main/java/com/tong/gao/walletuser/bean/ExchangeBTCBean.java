package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class ExchangeBTCBean implements Serializable {

    private String btcApplyId;
    private String userId;
    private String number;
    private String btcNumber;
    private String btcAddress;
    private String createdTime;
    private String status;

    public ExchangeBTCBean(String btcApplyId, String userId, String number, String btcNumber, String btcAddress, String createdTime, String status) {
        this.btcApplyId = btcApplyId;
        this.userId = userId;
        this.number = number;
        this.btcNumber = btcNumber;
        this.btcAddress = btcAddress;
        this.createdTime = createdTime;
        this.status = status;
    }

    public String getBtcApplyId() {
        return btcApplyId;
    }

    public void setBtcApplyId(String btcApplyId) {
        this.btcApplyId = btcApplyId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
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

    @Override
    public String toString() {
        return "ExchangeBTCBean{" +
                "btcApplyId='" + btcApplyId + '\'' +
                ", userId='" + userId + '\'' +
                ", number='" + number + '\'' +
                ", btcNumber='" + btcNumber + '\'' +
                ", btcAddress='" + btcAddress + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
