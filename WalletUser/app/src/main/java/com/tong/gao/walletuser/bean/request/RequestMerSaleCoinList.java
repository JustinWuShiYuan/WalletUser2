package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestMerSaleCoinList implements Serializable {

    private String pageno;
    private String pagesize;

    public RequestMerSaleCoinList(String pageno, String pagesize) {
        this.pageno = pageno;
        this.pagesize = pagesize;
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
        return "RequestMerSaleCoinList{" +
                "pageno='" + pageno + '\'' +
                ", pagesize='" + pagesize + '\'' +
                '}';
    }
}
