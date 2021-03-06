package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.UserInfo;

import java.io.Serializable;

public class ResponseLoginInfo implements Serializable {

    private String errcode;

    private String msg;

    private UserInfo userinfo;

    public ResponseLoginInfo() {
    }

    public ResponseLoginInfo(String errcode, String msg, UserInfo userinfo) {
        this.errcode = errcode;
        this.msg = msg;
        this.userinfo = userinfo;
    }

    @Override
    public String toString() {
        return "ResponseLoginInfo{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", userinfo=" + userinfo +
                '}';
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

    public UserInfo getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserInfo userinfo) {
        this.userinfo = userinfo;
    }
}
