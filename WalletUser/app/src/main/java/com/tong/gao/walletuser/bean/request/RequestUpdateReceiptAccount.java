package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestUpdateReceiptAccount implements Serializable {

    private String paymentWayId;
    private String paymentWay;
    private String name;
    private String account;
    private String QRCode;
    private String accountOpenBank;
    private String accountOpenBranch;
    private String accountBankCard;
    private String accountBankCardRepeat;
    private String tradePwd;
    private String status;  //1-启用 2-停用


    public RequestUpdateReceiptAccount(String paymentWayId, String status) {
        this.paymentWayId = paymentWayId;
        this.status = status;
    }

    public RequestUpdateReceiptAccount(String paymentWayId, String paymentWay, String name, String account, String QRCode, String accountOpenBank, String accountOpenBranch, String accountBankCard, String accountBankCardRepeat, String tradePwd, String status) {
        this.paymentWayId = paymentWayId;
        this.paymentWay = paymentWay;
        this.name = name;
        this.account = account;
        this.QRCode = QRCode;
        this.accountOpenBank = accountOpenBank;
        this.accountOpenBranch = accountOpenBranch;
        this.accountBankCard = accountBankCard;
        this.accountBankCardRepeat = accountBankCardRepeat;
        this.tradePwd = tradePwd;
        this.status = status;
    }

    public String getPaymentWayId() {
        return paymentWayId;
    }

    public void setPaymentWayId(String paymentWayId) {
        this.paymentWayId = paymentWayId;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getQRCode() {
        return QRCode;
    }

    public void setQRCode(String QRCode) {
        this.QRCode = QRCode;
    }

    public String getAccountOpenBank() {
        return accountOpenBank;
    }

    public void setAccountOpenBank(String accountOpenBank) {
        this.accountOpenBank = accountOpenBank;
    }

    public String getAccountOpenBranch() {
        return accountOpenBranch;
    }

    public void setAccountOpenBranch(String accountOpenBranch) {
        this.accountOpenBranch = accountOpenBranch;
    }

    public String getAccountBankCard() {
        return accountBankCard;
    }

    public void setAccountBankCard(String accountBankCard) {
        this.accountBankCard = accountBankCard;
    }

    public String getAccountBankCardRepeat() {
        return accountBankCardRepeat;
    }

    public void setAccountBankCardRepeat(String accountBankCardRepeat) {
        this.accountBankCardRepeat = accountBankCardRepeat;
    }

    public String getTradePwd() {
        return tradePwd;
    }

    public void setTradePwd(String tradePwd) {
        this.tradePwd = tradePwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestUpdateReceiptAccount{" +
                "paymentWayId='" + paymentWayId + '\'' +
                ", paymentWay='" + paymentWay + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", QRCode='" + QRCode + '\'' +
                ", accountOpenBank='" + accountOpenBank + '\'' +
                ", accountOpenBranch='" + accountOpenBranch + '\'' +
                ", accountBankCard='" + accountBankCard + '\'' +
                ", accountBankCardRepeat='" + accountBankCardRepeat + '\'' +
                ", tradePwd='" + tradePwd + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
