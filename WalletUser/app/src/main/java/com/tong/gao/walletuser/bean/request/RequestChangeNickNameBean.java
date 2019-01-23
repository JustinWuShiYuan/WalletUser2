package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestChangeNickNameBean implements Serializable {
    private String nickname;

    public RequestChangeNickNameBean(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "RequestChangeNickNameBean{" +
                "nickname='" + nickname + '\'' +
                '}';
    }
}
