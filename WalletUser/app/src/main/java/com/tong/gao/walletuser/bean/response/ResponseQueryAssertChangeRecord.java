package com.tong.gao.walletuser.bean.response;

import com.tong.gao.walletuser.bean.PageBean;

import java.io.Serializable;
import java.util.List;

public class ResponseQueryAssertChangeRecord implements Serializable {

    private String errcode;
    private String msg;
    private List<AssertChangeBean> accountChange;
    private PageBean    page;


    public ResponseQueryAssertChangeRecord(String errcode, String msg, List<AssertChangeBean> accountChange, PageBean page) {
        this.errcode = errcode;
        this.msg = msg;
        this.accountChange = accountChange;
        this.page = page;
    }

    @Override
    public String toString() {
        return "ResponseQueryAssertChangeRecord{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", accountChange=" + accountChange +
                ", page=" + page +
                '}';
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

    public List<AssertChangeBean> getAccountChange() {
        return accountChange;
    }

    public void setAccountChange(List<AssertChangeBean> accountChange) {
        this.accountChange = accountChange;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public class AssertChangeBean implements Serializable{

        private String resource;
        private String createdTime;
        private String number;


        public AssertChangeBean(String resource, String createdTime, String number) {
            this.resource = resource;
            this.createdTime = createdTime;
            this.number = number;
        }

        public String getResource() {
            return resource;
        }

        public void setResource(String resource) {
            this.resource = resource;
        }

        public String getCreatedTime() {
            return createdTime;
        }

        public void setCreatedTime(String createdTime) {
            this.createdTime = createdTime;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        @Override
        public String toString() {
            return "AssertChangeBean{" +
                    "resource='" + resource + '\'' +
                    ", createdTime='" + createdTime + '\'' +
                    ", number='" + number + '\'' +
                    '}';
        }
    }

}
