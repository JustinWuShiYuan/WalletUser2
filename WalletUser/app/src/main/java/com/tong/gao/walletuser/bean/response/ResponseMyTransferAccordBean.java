package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;
import java.util.List;

public class ResponseMyTransferAccordBean implements Serializable {

    private String errcode;
    private String msg;
    private List<TransferInfoBean> transferRecord;
    private TransferAccordPageInfo page;

    public ResponseMyTransferAccordBean(String errcode, String msg, List<TransferInfoBean> transferRecord, TransferAccordPageInfo page) {
        this.errcode = errcode;
        this.msg = msg;
        this.transferRecord = transferRecord;
        this.page = page;
    }

    public TransferAccordPageInfo getPage() {
        return page;
    }

    public void setPage(TransferAccordPageInfo page) {
        this.page = page;
    }

    public void setTransferRecord(List<TransferInfoBean> transferRecord) {
        this.transferRecord = transferRecord;
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

    public List<TransferInfoBean> getTransferRecord() {
        return transferRecord;
    }

    @Override
    public String toString() {
        return "ResponseMyTransferAccordBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", transferRecord=" + transferRecord +
                '}';
    }

    public class TransferInfoBean implements Serializable{

            private String transferRecordId;
            private String fromAddress;
            private String toAddress;
            private String transferTime;
            private String number;
            private String remark;


        public TransferInfoBean(String transferRecordId, String fromAddress, String toAddress, String transferTime, String number, String remark) {
            this.transferRecordId = transferRecordId;
            this.fromAddress = fromAddress;
            this.toAddress = toAddress;
            this.transferTime = transferTime;
            this.number = number;
            this.remark = remark;
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

        }


    public class TransferAccordPageInfo implements Serializable{

            private String pageno;
            private String pagesize;
            private String sum;

            public String getPageno() {
                return pageno;
            }

            @Override
            public String toString() {
                return "TransferAccordPageInfo{" +
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

            public TransferAccordPageInfo(String pageno, String pagesize, String sum) {
                this.pageno = pageno;
                this.pagesize = pagesize;
                this.sum = sum;
            }
        }
}
