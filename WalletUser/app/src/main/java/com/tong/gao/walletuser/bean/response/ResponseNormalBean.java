package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseNormalBean implements Serializable {

    private String errcode;
    private String msg;

    public ResponseNormalBean() {
    }

    public ResponseNormalBean(String errcode, String msg) {
        this.errcode = errcode;
        this.msg = msg;
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
        return "ResponseChangeNickNameBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
