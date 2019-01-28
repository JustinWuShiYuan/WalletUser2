package com.tong.gao.walletuser.bean.myEnum;

import com.tong.gao.walletuser.constants.MyConstant;

import java.io.Serializable;

public enum OrderStatus implements Serializable {

    ALL(MyConstant.Status_All),
    HadNotPay(MyConstant.Status_NotPay),
    HadPay(MyConstant.Status_HadPay),
    Complete(MyConstant.Status_Complete),
    Cancel_HadCancel(MyConstant.Status_HadCancel),
    Closed_HadClosed(MyConstant.Status_HadClosed),
    Appealing(MyConstant.Status_Appeal);

    String 	state;

    OrderStatus(String state) {
        this.state = state;
    }

    public String getState()
    {
        return state;
    }

    public void setState(int String) {
        this.state = state;
    }



}
