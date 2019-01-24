package com.tong.gao.walletuser.bean.event;

import com.tong.gao.walletuser.bean.CoinBean;

public class CoinBeanEvent {

    private CoinBean    coinBean;

    public CoinBeanEvent(CoinBean coinBean) {
        this.coinBean = coinBean;
    }

    public CoinBean getCoinBean() {
        return coinBean;
    }

    public void setCoinBean(CoinBean coinBean) {
        this.coinBean = coinBean;
    }
}
