package com.tong.gao.walletuser.bean.event;

import java.io.Serializable;
import java.util.List;

public class CashEvent implements Serializable {

   private List<String> cashList;

    public CashEvent(List<String> cashList) {
        this.cashList = cashList;
    }

    public List<String> getCashList() {
        return cashList;
    }

    public void setCashList(List<String> cashList) {
        this.cashList = cashList;
    }

    @Override
    public String toString() {
        return "CashEvent{" +
                "cashList=" + cashList +
                '}';
    }
}
