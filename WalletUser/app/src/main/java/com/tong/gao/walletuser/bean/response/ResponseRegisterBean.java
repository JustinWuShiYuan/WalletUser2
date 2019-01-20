package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseRegisterBean implements Serializable {

    private String errcode;
    private String msg;
    private String userid;


    public ResponseRegisterBean(String errcode, String msg, String userid) {
        this.errcode = errcode;
        this.msg = msg;
        this.userid = userid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public String toString() {
        return "ResponseRegisterBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
