package com.tong.gao.walletuser.bean.request;

import java.io.Serializable;

public class RequestCancelExchangeApply implements Serializable {

    private String btcApplyId;

    public RequestCancelExchangeApply(String btcApplyId) {
        this.btcApplyId = btcApplyId;
    }

    public String getBtcApplyId() {
        return btcApplyId;
    }

    public void setBtcApplyId(String btcApplyId) {
        this.btcApplyId = btcApplyId;
    }

    @Override
    public String toString() {
        return "RequestCancelExchangeApply{" +
                "btcApplyId='" + btcApplyId + '\'' +
                '}';
    }
}
