package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class PersonalBean implements Serializable {

    private String userid;
    private String username;
    private String nickname;
    private String realname;
    private String idnumber;
    private String valiidnumber;
    private String istrpwd;
    private String status;
    private String googleverify;
    private String valigooglesecret;
    private String userType;

    public PersonalBean() {
    }

    public PersonalBean(String userid, String username, String nickname, String realname, String idnumber, String valiidnumber, String istrpwd, String status, String googleverify, String valigooglesecret, String userType) {
        this.userid = userid;
        this.username = username;
        this.nickname = nickname;
        this.realname = realname;
        this.idnumber = idnumber;
        this.valiidnumber = valiidnumber;
        this.istrpwd = istrpwd;
        this.status = status;
        this.googleverify = googleverify;
        this.valigooglesecret = valigooglesecret;
        this.userType = userType;
    }


    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber;
    }

    public String getValiidnumber() {
        return valiidnumber;
    }

    public void setValiidnumber(String valiidnumber) {
        this.valiidnumber = valiidnumber;
    }

    public String getIstrpwd() {
        return istrpwd;
    }

    public void setIstrpwd(String istrpwd) {
        this.istrpwd = istrpwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGoogleverify() {
        return googleverify;
    }

    public void setGoogleverify(String googleverify) {
        this.googleverify = googleverify;
    }

    public String getValigooglesecret() {
        return valigooglesecret;
    }

    public void setValigooglesecret(String valigooglesecret) {
        this.valigooglesecret = valigooglesecret;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "PersonalBean{" +
                "userid='" + userid + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", realname='" + realname + '\'' +
                ", idnumber='" + idnumber + '\'' +
                ", valiidnumber='" + valiidnumber + '\'' +
                ", istrpwd='" + istrpwd + '\'' +
                ", status='" + status + '\'' +
                ", googleverify='" + googleverify + '\'' +
                ", valigooglesecret='" + valigooglesecret + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
