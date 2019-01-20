package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;
import java.util.List;

public class ResponseMyTransferAccordBean implements Serializable {

        private String err_code;
        private String msg;
        private List<TransferInfoBean> TransferRecord;

    public ResponseMyTransferAccordBean(String err_code, String msg, List<TransferInfoBean> transferRecord) {
        this.err_code = err_code;
        this.msg = msg;
        TransferRecord = transferRecord;
    }

    public void setTransferRecord(List<TransferInfoBean> transferRecord) {
        TransferRecord = transferRecord;
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

    public List<TransferInfoBean> getTransferRecord() {
        return TransferRecord;
    }

    public class TransferInfoBean implements Serializable{

            private String transferRecordId;
            private String fromAddress;
            private String toAddress;
            private String transferTime;
            private String number;
            private String remark;
            private PageOut PageOut;

            public TransferInfoBean(String transferRecordId, String fromAddress, String toAddress, String transferTime, String number, String remark, ResponseMyTransferAccordBean.PageOut pageOut) {
                this.transferRecordId = transferRecordId;
                this.fromAddress = fromAddress;
                this.toAddress = toAddress;
                this.transferTime = transferTime;
                this.number = number;
                this.remark = remark;
                PageOut = pageOut;
            }

            @Override
            public String toString() {
                return "TransferInfoBean{" +
                        "transferRecordId='" + transferRecordId + '\'' +
                        ", fromAddress='" + fromAddress + '\'' +
                        ", toAddress='" + toAddress + '\'' +
                        ", transferTime='" + transferTime + '\'' +
                        ", number='" + number + '\'' +
                        ", remark='" + remark + '\'' +
                        ", PageOut=" + PageOut +
                        '}';
            }

            public String getTransferRecordId() {
                return transferRecordId;
            }

            public void setTransferRecordId(String transferRecordId) {
                this.transferRecordId = transferRecordId;
            }

            public String getFromAddress() {
                return fromAddress;
            }

            public void setFromAddress(String fromAddress) {
                this.fromAddress = fromAddress;
            }

            public String getToAddress() {
                return toAddress;
            }

            public void setToAddress(String toAddress) {
                this.toAddress = toAddress;
            }

            public String getTransferTime() {
                return transferTime;
            }

            public void setTransferTime(String transferTime) {
                this.transferTime = transferTime;
            }

            public String getNumber() {
                return number;
            }

            public void setNumber(String number) {
                this.number = number;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public ResponseMyTransferAccordBean.PageOut getPageOut() {
                return PageOut;
            }

            public void setPageOut(ResponseMyTransferAccordBean.PageOut pageOut) {
                PageOut = pageOut;
            }
        }


    public class PageOut implements Serializable{

            private String pageno;
            private String pagesize;
            private String sum;

            public String getPageno() {
                return pageno;
            }

            @Override
            public String toString() {
                return "PageOut{" +
                        "pageno='" + pageno + '\'' +
                        ", pagesize='" + pagesize + '\'' +
                        ", sum='" + sum + '\'' +
                        '}';
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

            public PageOut(String pageno, String pagesize, String sum) {
                this.pageno = pageno;
                this.pagesize = pagesize;
                this.sum = sum;
            }
        }
}
