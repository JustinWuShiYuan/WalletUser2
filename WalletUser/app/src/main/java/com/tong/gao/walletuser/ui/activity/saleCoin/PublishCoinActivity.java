package com.tong.gao.walletuser.ui.activity.saleCoin;


import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishCoinActivity extends ActivityBase {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.iv_complete)
    ImageView ivComplete;
    @BindView(R.id.tv_coin_type)
    TextView tvCoinType;
    @BindView(R.id.tv_to_sell_coin_num)
    TextView tvToSellCoinNum;
    @BindView(R.id.tv_coin_single_price)
    TextView tvCoinSinglePrice;
    @BindView(R.id.tv_receipt_money_type)
    TextView tvReceiptMoneyType;
    @BindView(R.id.tv_trade_type)
    TextView tvTradeType;
    @BindView(R.id.tv_cash_type_value)
    TextView tvCashTypeValue;
    @BindView(R.id.btn_back_to_home)
    Button btnBackToHome;
    @BindView(R.id.btn_check_advertisement)
    Button btnCheckAdvertisement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_publish_coin;
    }

    @Override
    protected void initView() {

    }


}
