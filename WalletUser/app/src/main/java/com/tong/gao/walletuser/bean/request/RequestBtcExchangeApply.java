package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestBtcExchangeApply implements Serializable {

    private String rate;
    private String number;
    private String btcnumber;
    private String btcaddress;

    public RequestBtcExchangeApply(String rate, String number, String btcnumber, String btcaddress) {
        this.rate = rate;
        this.number = number;
        this.btcnumber = btcnumber;
        this.btcaddress = btcaddress;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBtcnumber() {
        return btcnumber;
    }

    public void setBtcnumber(String btcnumber) {
        this.btcnumber = btcnumber;
    }

    public String getBtcaddress() {
        return btcaddress;
    }

    public void setBtcaddress(String btcaddress) {
        this.btcaddress = btcaddress;
    }

    @Override
    public String toString() {
        return "RequestBtcExchangeApply{" +
                "rate='" + rate + '\'' +
                ", number='" + number + '\'' +
                ", btcnumber='" + btcnumber + '\'' +
                ", btcaddress='" + btcaddress + '\'' +
                '}';
    }
}
