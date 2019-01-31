package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;
import java.util.List;

public class ResponseQueryMyReceiptMoneyAccountList implements Serializable {

    private String errcode;
    private String msg;
    private List<ReceiptMoneyBean> paymentWay;

    public ResponseQueryMyReceiptMoneyAccountList(String errcode, String msg, List<ReceiptMoneyBean> paymentWay) {
        this.errcode = errcode;
        this.msg = msg;
        this.paymentWay = paymentWay;
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

    public List<ReceiptMoneyBean> getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(List<ReceiptMoneyBean> paymentWay) {
        this.paymentWay = paymentWay;
    }

    @Override
    public String toString() {
        return "ResponseQueryMyReceiptMoneyAccountList{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", paymentWay=" + paymentWay +
                '}';
    }

    public class ReceiptMoneyBean implements Serializable{

        private String paymentWayId;
        private String paymentWay;
        private String name;
        private String account;
        private String QRCode;
        private String accountOpenBank;
        private String accountOpenBranch;
        private String accountBankCard;
        private String status;

        public ReceiptMoneyBean(String paymentWayId, String paymentWay, String name, String account, String QRCode, String accountOpenBank, String accountOpenBranch, String accountBankCard, String status) {
            this.paymentWayId = paymentWayId;
            this.paymentWay = paymentWay;
            this.name = name;
            this.account = account;
            this.QRCode = QRCode;
            this.accountOpenBank = accountOpenBank;
            this.accountOpenBranch = accountOpenBranch;
            this.accountBankCard = accountBankCard;
            this.status = status;
        }

        public String getPaymentWayId() {
            return paymentWayId;
        }

        public void setPaymentWayId(String paymentWayId) {
            this.paymentWayId = paymentWayId;
        }

        public String getPaymentWay() {
            return paymentWay;
        }

        public void setPaymentWay(String paymentWay) {
            this.paymentWay = paymentWay;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getQRCode() {
            return QRCode;
        }

        public void setQRCode(String QRCode) {
            this.QRCode = QRCode;
        }

        public String getAccountOpenBank() {
            return accountOpenBank;
        }

        public void setAccountOpenBank(String accountOpenBank) {
            this.accountOpenBank = accountOpenBank;
        }

        public String getAccountOpenBranch() {
            return accountOpenBranch;
        }

        public void setAccountOpenBranch(String accountOpenBranch) {
            this.accountOpenBranch = accountOpenBranch;
        }

        public String getAccountBankCard() {
            return accountBankCard;
        }

        public void setAccountBankCard(String accountBankCard) {
            this.accountBankCard = accountBankCard;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "ReceiptMoneyBean{" +
                    "paymentWayId='" + paymentWayId + '\'' +
                    ", paymentWay='" + paymentWay + '\'' +
                    ", name='" + name + '\'' +
                    ", account='" + account + '\'' +
                    ", QRCode='" + QRCode + '\'' +
                    ", accountOpenBank='" + accountOpenBank + '\'' +
                    ", accountOpenBranch='" + accountOpenBranch + '\'' +
                    ", accountBankCard='" + accountBankCard + '\'' +
                    ", status='" + status + '\'' +
                    '}';
        }
    }
}
