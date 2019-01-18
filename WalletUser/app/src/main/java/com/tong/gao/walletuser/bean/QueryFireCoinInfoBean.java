package com.tong.gao.walletuser.bean;

import java.io.Serializable;
import java.util.List;

public class QueryFireCoinInfoBean implements Serializable {

    private String err_code;
    private String msg;
    private List<FireCoinBean>  marketList;


    public QueryFireCoinInfoBean(String err_code, String msg, List<FireCoinBean> marketList) {
        this.err_code = err_code;
        this.msg = msg;
        this.marketList = marketList;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
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
                "err_code='" + err_code + '\'' +
                ", msg='" + msg + '\'' +
                ", marketList=" + marketList +
                '}';
    }
}
