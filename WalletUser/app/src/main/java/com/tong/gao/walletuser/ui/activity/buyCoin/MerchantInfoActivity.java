package com.tong.gao.walletuser.ui.activity.buyCoin;


import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MerchantInfoActivity extends ActivityBase {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_user_nick_name)
    TextView tvUserNickName;
    @BindView(R.id.cb_real_name_authentication)
    CheckBox cbRealNameAuthentication;
    @BindView(R.id.cb_identity_authentication)
    CheckBox cbIdentityAuthentication;
    @BindView(R.id.cb_compensate_guarantee)
    CheckBox cbCompensateGuarantee;

    @Override
    protected int getLayout() {
        return R.layout.activity_merchant_info;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
