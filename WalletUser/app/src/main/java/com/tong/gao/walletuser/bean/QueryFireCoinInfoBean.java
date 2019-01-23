package com.tong.gao.walletuser.bean;

import java.io.Serializable;
import java.util.List;

public class QueryFireCoinInfoBean implements Serializable {

    private String errcode;
    private String msg;
    private List<FireCoinBean>  marketList;

    public QueryFireCoinInfoBean() {
    }

    public QueryFireCoinInfoBean(String errcode, String msg, List<FireCoinBean> marketList) {
        this.errcode = errcode;
        this.msg = msg;
        this.marketList = marketList;
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

    public List<FireCoinBean> getMarketList() {
        return marketList;
    }

    public void setMarketList(List<FireCoinBean> marketList) {
        this.marketList = marketList;
    }

    @Override
    public String toString() {
        return "QueryFireCoinInfoBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", marketList=" + marketList +
                '}';
    }
}
