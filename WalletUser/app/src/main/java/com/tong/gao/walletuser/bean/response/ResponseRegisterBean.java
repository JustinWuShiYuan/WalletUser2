package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseRegisterBean implements Serializable {

    private String err_code;
    private String msg;
    private String userid;


    public ResponseRegisterBean(String err_code, String msg, String userid) {
        this.err_code = err_code;
        this.msg = msg;
        this.userid = userid;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
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
                "err_code='" + err_code + '\'' +
                ", msg='" + msg + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }
}
