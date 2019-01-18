package com.tong.gao.walletuser.bean.response;

import java.io.Serializable;

public class ResponseMyAccountInfo implements Serializable {

    private String err_code;
    private String usableFund;//可用资产
    private String frozenFund;//冻结资产
    private String amount;//冻结资产
    private String convertRmb;//折合人民币
    private String msg;//返回信息

    public ResponseMyAccountInfo(String err_code, String usableFund, String frozenFund, String amount, String convertRmb, String msg) {
        this.err_code = err_code;
        this.usableFund = usableFund;
        this.frozenFund = frozenFund;
        this.amount = amount;
        this.convertRmb = convertRmb;
        this.msg = msg;
    }

    public String getErr_code() {
        return err_code;
    }

    public void setErr_code(String err_code) {
        this.err_code = err_code;
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
                "err_code='" + err_code + '\'' +
                ", usableFund='" + usableFund + '\'' +
                ", frozenFund='" + frozenFund + '\'' +
                ", amount='" + amount + '\'' +
                ", convertRmb='" + convertRmb + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}
