package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.MessageBean;
import com.tong.gao.walletuser.bean.PageBean;

import java.io.Serializable;
import java.util.List;

public class ResponseMessageInformBean implements Serializable {

    private String errcode;
    private String msg;
    private List<MessageBean> allMessage;
    private PageBean page;


    public ResponseMessageInformBean(String errcode, String msg, List<MessageBean> allMessage, PageBean page) {
        this.errcode = errcode;
        this.msg = msg;
        this.allMessage = allMessage;
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

    public List<MessageBean> getAllMessage() {
        return allMessage;
    }

    public void setAllMessage(List<MessageBean> allMessage) {
        this.allMessage = allMessage;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseMessageInformBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", allMessage=" + allMessage +
                ", page=" + page +
                '}';
    }
}
