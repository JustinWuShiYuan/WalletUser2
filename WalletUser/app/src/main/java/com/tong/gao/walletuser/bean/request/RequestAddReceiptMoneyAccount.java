package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestAddReceiptMoneyAccount implements Serializable {

    private String paymentWay;//1.微信2.支付宝3.银行卡
    private String name;
    private String account;
    private String QRCode;
    private String accountOpenBank;
    private String accountOpenBranch;
    private String accountBankCard;
    private String accountBankCardRepeat;
    private String tradePwd;


    public RequestAddReceiptMoneyAccount(String paymentWay, String name, String accountOpenBank, String accountOpenBranch, String accountBankCard, String accountBankCardRepeat, String tradePwd) {
        this.paymentWay = paymentWay;
        this.name = name;
        this.accountOpenBank = accountOpenBank;
        this.accountOpenBranch = accountOpenBranch;
        this.accountBankCard = accountBankCard;
        this.accountBankCardRepeat = accountBankCardRepeat;
        this.tradePwd = tradePwd;
    }

    public RequestAddReceiptMoneyAccount(String paymentWay, String name, String account, String QRCode, String tradePwd) {
        this.paymentWay = paymentWay;
        this.name = name;
        this.account = account;
        this.QRCode = QRCode;
        this.tradePwd = tradePwd;
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

    @Override
    public String toString() {
        return "RequestAddReceiptMoneyAccount{" +
                "paymentWay='" + paymentWay + '\'' +
                ", name='" + name + '\'' +
                ", account='" + account + '\'' +
                ", QRCode='" + QRCode + '\'' +
                ", accountOpenBank='" + accountOpenBank + '\'' +
                ", accountOpenBranch='" + accountOpenBranch + '\'' +
                ", accountBankCard='" + accountBankCard + '\'' +
                ", accountBankCardRepeat='" + accountBankCardRepeat + '\'' +
                ", tradePwd='" + tradePwd + '\'' +
                '}';
    }
}
