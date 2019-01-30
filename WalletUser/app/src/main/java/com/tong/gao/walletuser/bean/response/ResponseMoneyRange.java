package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseMoneyRange implements Serializable {

    private String price;//价格(金额快选 )
    private String optionPrice;//
    private String errcode;//
    private String msg;//

    public ResponseMoneyRange(String price, String optionPrice, String errcode, String msg) {
        this.price = price;
        this.optionPrice = optionPrice;
        this.errcode = errcode;
        this.msg = msg;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(String optionPrice) {
        this.optionPrice = optionPrice;
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

    @Override
    public String toString() {
        return "ResponseMoneyRange{" +
                "price='" + price + '\'' +
                ", optionPrice='" + optionPrice + '\'' +
                ", errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
