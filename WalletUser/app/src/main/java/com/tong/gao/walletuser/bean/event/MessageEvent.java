package com.tong.gao.walletuser.bean.event;

import java.io.Serializable;

public class MessageEvent implements Serializable {

    private String  bottomTabIndex ;

    public MessageEvent(String bottomTabIndex) {
        this.bottomTabIndex = bottomTabIndex;
    }

    public String getBottomTabIndex() {
        return bottomTabIndex;
    }

    public void setBottomTabIndex(String bottomTabIndex) {
        this.bottomTabIndex = bottomTabIndex;
    }
}
