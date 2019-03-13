package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestLoginInfoBean implements Serializable {

    private String loginname;
    private String pwd;
    private String registrationId;//极光推送id

    public RequestLoginInfoBean(String loginname, String pwd) {
        this.loginname = loginname;
        this.pwd = pwd;
    }

    public RequestLoginInfoBean(String loginname, String pwd, String registrationId) {
        this.loginname = loginname;
        this.pwd = pwd;
        this.registrationId = registrationId;
    }


    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Override
    public String toString() {
        return "RequestLoginInfoBean{" +
                "loginname='" + loginname + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
