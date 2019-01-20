package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseMyAccountInfo implements Serializable {

    private String errcode;
    private String usableFund;//可用资产
    private String frozenFund;//冻结资产
    private String amount;//冻结资产
    private String convertRmb;//折合人民币
    private String msg;//返回信息

    public ResponseMyAccountInfo(String errcode, String usableFund, String frozenFund, String amount, String convertRmb, String msg) {
        this.errcode = errcode;
        this.usableFund = usableFund;
        this.frozenFund = frozenFund;
        this.amount = amount;
        this.convertRmb = convertRmb;
        this.msg = msg;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getUsableFund() {
        return usableFund;
    }

    public void setUsableFund(String usableFund) {
        this.usableFund = usableFund;
    }

    public String getFrozenFund() {
        return frozenFund;
    }

    public void setFrozenFund(String frozenFund) {
        this.frozenFund = frozenFund;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getConvertRmb() {
        return convertRmb;
    }

    public void setConvertRmb(String convertRmb) {
        this.convertRmb = convertRmb;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "ResponseMyAccountInfo{" +
                "errcode='" + errcode + '\'' +
                ", usableFund='" + usableFund + '\'' +
                ", frozenFund='" + frozenFund + '\'' +
                ", amount='" + amount + '\'' +
                ", convertRmb='" + convertRmb + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
