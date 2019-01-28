package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseBtcExchangeRate implements Serializable {

    private String errcode;
    private String msg;
    private String exchangeRate;


    public ResponseBtcExchangeRate(String errcode, String msg, String exchangeRate) {
        this.errcode = errcode;
        this.msg = msg;
        this.exchangeRate = exchangeRate;
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

    public String getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(String exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    @Override
    public String toString() {
        return "ResponseBtcExchangeRate{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", exchangeRate='" + exchangeRate + '\'' +
                '}';
    }
}
