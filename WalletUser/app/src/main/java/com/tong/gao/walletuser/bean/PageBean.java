package com.tong.gao.walletuser.bean;

import java.io.Serializable;

public class PageBean implements Serializable {

    private String pageno;
    private String pagesize;
    private String sum;

    public PageBean() {
    }

    public PageBean(String pageno, String pagesize, String sum) {
        this.pageno = pageno;
        this.pagesize = pagesize;
        this.sum = sum;
    }


    @Override
    public String toString() {
        return "PageBean{" +
                "pageno='" + pageno + '\'' +
                ", pagesize='" + pagesize + '\'' +
                ", sum='" + sum + '\'' +
                '}';
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

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
    }
}
