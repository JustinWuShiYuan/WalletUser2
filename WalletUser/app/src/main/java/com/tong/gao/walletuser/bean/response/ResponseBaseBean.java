package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseBaseBean implements Serializable {

    private String errcode;
    private String msg;

    public ResponseBaseBean(String errcode, String msg) {
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
        return "ResponseBaseBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
