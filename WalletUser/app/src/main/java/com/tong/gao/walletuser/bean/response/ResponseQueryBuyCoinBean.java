package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.CoinBean;
import com.tong.gao.walletuser.bean.PageBean;

import java.io.Serializable;
import java.util.List;

public class ResponseQueryBuyCoinBean implements Serializable {

    private String errcode;
    private String msg;
    private List<CoinBean> advert;
    private PageBean page;


    public ResponseQueryBuyCoinBean(String errcode, String msg, List<CoinBean> advert, PageBean page) {
        this.errcode = errcode;
        this.msg = msg;
        this.advert = advert;
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

    public List<CoinBean> getAdvert() {
        return advert;
    }

    public void setAdvert(List<CoinBean> advert) {
        this.advert = advert;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseQueryBuyCoinBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", advert=" + advert +
                ", page=" + page +
                '}';
    }
}
