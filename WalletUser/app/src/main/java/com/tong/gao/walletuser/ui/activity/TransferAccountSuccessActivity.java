package com.tong.gao.walletuser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferAccountSuccessActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_transfer_time)
    TextView tvTransferTime;
    @BindView(R.id.tv_transfer_address)
    TextView tvTransferAddress;
    @BindView(R.id.tv_transfer_coin_num)
    TextView tvTransferCoinNum;
    @BindView(R.id.tv_transfer_account_remark)
    TextView tvTransferAccountRemark;
    @BindView(R.id.tv_trade_id)
    TextView tvTradeId;
    @BindView(R.id.btn_back_to_home_pager)
    Button btnBackToHomePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_account_success);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_account_success;
    }

    @Override
    protected void initView() {

        tvTitleBarTitle2.setText("转账AB");
        flBack.setOnClickListener(this);
        btnBackToHomePager.setOnClickListener(this);

        loadData();

    }

    //获取转账成功的数据
    private void loadData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:
                finish();
                break;

            case R.id.btn_back_to_home_pager:
                finish();
                break;


        }
    }
}
