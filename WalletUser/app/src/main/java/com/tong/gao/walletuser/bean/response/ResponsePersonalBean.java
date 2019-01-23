package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.PersonalBean;

import java.io.Serializable;

public class ResponsePersonalBean implements Serializable {

    private String errcode;
    private String msg;
    private PersonalBean userinfo;

    public ResponsePersonalBean() {
    }

    public ResponsePersonalBean(String errcode, String msg, PersonalBean userinfo) {
        this.errcode = errcode;
        this.msg = msg;
        this.userinfo = userinfo;
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

    public PersonalBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(PersonalBean userinfo) {
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "ResponsePersonalBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", userinfo=" + userinfo +
                '}';
    }
}
