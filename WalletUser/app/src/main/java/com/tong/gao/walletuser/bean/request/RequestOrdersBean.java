package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestOrdersBean implements Serializable {

    private String ugOtcAdvertId;
    private String number;

    public RequestOrdersBean(String ugOtcAdvertId, String number) {
        this.ugOtcAdvertId = ugOtcAdvertId;
        this.number = number;
    }

    public String getUgOtcAdvertId() {
        return ugOtcAdvertId;
    }

    public void setUgOtcAdvertId(String ugOtcAdvertId) {
        this.ugOtcAdvertId = ugOtcAdvertId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "RequestOrdersBean{" +
                "ugOtcAdvertId='" + ugOtcAdvertId + '\'' +
                ", number='" + number + '\'' +
                '}';
    }
}
