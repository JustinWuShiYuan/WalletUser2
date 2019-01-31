package com.tong.gao.walletuser.bean.myEnum;

import com.tong.gao.walletuser.constants.MyConstant;

import java.io.Serializable;

public enum PaymentWay implements Serializable {


    ZFB(MyConstant.Payment_ZFB),WeChat(MyConstant.Payment_WeChat),Bank(MyConstant.Payment_Bank);
    String  way;

    PaymentWay(String way) {
        this.way = way;
    }

    public String getWay() {
        return way;
    }

}