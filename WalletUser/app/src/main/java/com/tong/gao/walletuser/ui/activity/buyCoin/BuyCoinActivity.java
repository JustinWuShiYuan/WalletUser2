package com.tong.gao.walletuser.ui.activity.buyCoin;


import android.content.Intent;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.CoinBean;
import com.tong.gao.walletuser.bean.event.CoinBeanEvent;
import com.tong.gao.walletuser.factory.ThreadPoolFactory;
import com.tong.gao.walletuser.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;


public class BuyCoinActivity extends ActivityBase {

    private CoinBean coinBean;


    @Override
    protected int getLayout() {
        return R.layout.activity_buy_coin;
    }

    @Override
    protected void initView() {

        Intent intent = getIntent();
        if(null != intent){
            coinBean = (CoinBean) intent.getSerializableExtra("coinBean");
            if(null != coinBean){
                EventBus.getDefault().post(new CoinBeanEvent(coinBean));
            }
        }

    }

}
