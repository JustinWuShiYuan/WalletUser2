package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestMessageInformBean implements Serializable {

    private String type;
    private String pageno;
    private String pagesize;

    public RequestMessageInformBean(String type, String pageno, String pagesize) {
        this.type = type;
        this.pageno = pageno;
        this.pagesize = pagesize;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPageno() {
        return pageno;
    }

    public void setPageno(String pageno) {
        this.pageno = pageno;
    }

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    @Override
    public String toString() {
        return "RequestMessageInformBean{" +
                "type='" + type + '\'' +
                ", pageno='" + pageno + '\'' +
                ", pagesize='" + pagesize + '\'' +
                '}';
    }
}
