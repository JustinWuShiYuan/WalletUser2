package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.MerSaleCoinBean;
import com.tong.gao.walletuser.bean.PageBean;

import java.io.Serializable;
import java.util.List;

public class ResponseMerSaleCoinList implements Serializable {

    private String errcode;
    private String msg;
    private List<MerSaleCoinBean> merchantAdvert;
    private PageBean page;


    public ResponseMerSaleCoinList(String errcode, String msg, List<MerSaleCoinBean> merchantAdvert, PageBean page) {
        this.errcode = errcode;
        this.msg = msg;
        this.merchantAdvert = merchantAdvert;
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

    public List<MerSaleCoinBean> getMerchantAdvert() {
        return merchantAdvert;
    }

    public void setMerchantAdvert(List<MerSaleCoinBean> merchantAdvert) {
        this.merchantAdvert = merchantAdvert;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseMerSaleCoinList{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", merchantAdvert=" + merchantAdvert +
                ", page=" + page +
                '}';
    }
}
