package com.tong.gao.walletuser.ui.activity.saleCoin;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.response.ResponseSellCoin;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.utils.StringUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PublishCoinActivity extends ActivityBase implements View.OnClickListener {


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


    private ResponseSellCoin sellCoin;


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

        tvTitleBarTitle2.setText("发布广告");
        flBack.setOnClickListener(this);
        btnBackToHome.setOnClickListener(this);
        btnCheckAdvertisement.setOnClickListener(this);

        Intent intent = getIntent();
        if(null != intent){
            sellCoin = (ResponseSellCoin) intent.getSerializableExtra(MyConstant.sellCoinSuccessKey);
            if(null != sellCoin){
                updateUI();
            }
        }

    }

    private void updateUI() {

        tvCoinType.setText("UG");

        tvToSellCoinNum.setText("sellCoin.getCoinNum"+" AB");
        tvCoinSinglePrice.setText("sellCoin.getSingle"+" CNY = 1 AB");
        tvReceiptMoneyType.setText("sellCoin.getPayment"+" CNY = 1 AB");

        tvTradeType.setText("单笔限额|单笔固额");

        tvCashTypeValue.setText("sellCoin.getValue");

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                this.finish();

                break;

//            @BindView(R.id.btn_back_to_home)
//            Button btnBackToHome;
//            @BindView(R.id.btn_check_advertisement)
//            Button btnCheckAdvertisement;

            case R.id.btn_back_to_home:

                this.finish();

                break;

            case R.id.btn_check_advertisement:

                if(!StringUtils.isEmpty("sellCoin.getAdverId()")){
                    //到我的广告列表


                }

                break;

        }

    }
}
