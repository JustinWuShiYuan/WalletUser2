package com.tong.gao.walletuser.bean.response;


import java.io.Serializable;
import java.util.List;

public class ResponseOrdersBean implements Serializable {

    public String errcode;
    public String msg;

    public String orderId;
    public String orderNo;
    public String orderAmount;
    public String orderPrice;
    public String orderNumber;
    public String createdTime;
    public String prompt;
    public String paymentNumber;


    public List<PaymentBean> paymentWay;

    public ResponseOrdersBean(String errcode, String msg, String orderId, String orderNo, String orderAmount, String orderPrice, String orderNumber, String createdTime, String prompt, String paymentNumber, List<PaymentBean> paymentWay) {
        this.errcode = errcode;
        this.msg = msg;
        this.orderId = orderId;
        this.orderNo = orderNo;
        this.orderAmount = orderAmount;
        this.orderPrice = orderPrice;
        this.orderNumber = orderNumber;
        this.createdTime = createdTime;
        this.prompt = prompt;
        this.paymentNumber = paymentNumber;
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

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
    }

    public List<PaymentBean> getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(List<PaymentBean> paymentWay) {
        this.paymentWay = paymentWay;
    }


    @Override
    public String toString() {
        return "ResponseOrdersBean{" +
                "errcode='" + errcode + '\'' +
                ", msg='" + msg + '\'' +
                ", orderId='" + orderId + '\'' +
                ", orderNo='" + orderNo + '\'' +
                ", orderAmount='" + orderAmount + '\'' +
                ", orderPrice='" + orderPrice + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", createdTime='" + createdTime + '\'' +
                ", prompt='" + prompt + '\'' +
                ", paymentNumber='" + paymentNumber + '\'' +
                ", paymentWay=" + paymentWay +
                '}';
    }

    public  class PaymentBean implements Serializable{
        public String paymentWayId;
        public String paymentWay;
        public String name;
        public String account;
        public String QRCode;
        public String accountOpenBank;
        public String accountOpenBranch;
        public String accountBankCard;
        public String status;


        public PaymentBean(String paymentWayId, String paymentWay, String name, String account, String QRCode, String accountOpenBank, String accountOpenBranch, String accountBankCard, String status) {
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
            return "PaymentBean{" +
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
