package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestRegisterBean implements Serializable {

    private String userName;
    private String password;
    private String confirmpassword;


    public RequestRegisterBean(String userName, String password, String confirmpassword) {
        this.userName = userName;
        this.password = password;
        this.confirmpassword = confirmpassword;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmpassword() {
        return confirmpassword;
    }

    public void setConfirmpassword(String confirmpassword) {
        this.confirmpassword = confirmpassword;
    }

    @Override
    public String toString() {
        return "RequestRegisterBean{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", confirmpassword='" + confirmpassword + '\'' +
                '}';
    }
}
