package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.PageBean;
import com.tong.gao.walletuser.bean.UserOrderBean;

import java.io.Serializable;
import java.util.List;

public class ResponseQueryOrderList implements Serializable {

    private String errcode;
    private String msg;
    private List<UserOrderBean> userOrder;
    private PageBean pageOut;

    public ResponseQueryOrderList(String errcode, String msg, List<UserOrderBean> userOrder, PageBean pageOut) {
        this.errcode = errcode;
        this.msg = msg;
        this.userOrder = userOrder;
        this.pageOut = pageOut;
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

    public List<UserOrderBean> getUserOrder() {
        return userOrder;
    }

    public void setUserOrder(List<UserOrderBean> userOrder) {
        this.userOrder = userOrder;
    }

    public PageBean getPageOut() {
        return pageOut;
    }

    public void setPageOut(PageBean pageOut) {
        this.pageOut = pageOut;
    }

    @Override
    public String toString() {
        return "ResponseQueryOrderList{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", userOrder=" + userOrder +
                ", pageOut=" + pageOut +
                '}';
    }
}
