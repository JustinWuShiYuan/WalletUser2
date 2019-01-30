package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.ExchangeBTCBean;
import com.tong.gao.walletuser.bean.PageBean;

import java.io.Serializable;
import java.util.List;

public class ResponseExchangeApplyList implements Serializable {

    private String errcode;
    private String msg;
    private List<ExchangeBTCBean> exchangeBTC;
    private PageBean page;


    public ResponseExchangeApplyList(String errcode, String msg, List<ExchangeBTCBean> exchangeBTC, PageBean page) {
        this.errcode = errcode;
        this.msg = msg;
        this.exchangeBTC = exchangeBTC;
        this.page = page;
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

    public List<ExchangeBTCBean> getExchangeBTC() {
        return exchangeBTC;
    }

    public void setExchangeBTC(List<ExchangeBTCBean> exchangeBTC) {
        this.exchangeBTC = exchangeBTC;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseExchangeApplyList{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", exchangeBTC=" + exchangeBTC +
                ", page=" + page +
                '}';
    }
}
