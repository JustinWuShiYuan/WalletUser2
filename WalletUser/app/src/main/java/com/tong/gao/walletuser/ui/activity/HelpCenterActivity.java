package com.tong.gao.walletuser.ui.activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HelpCenterActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.ll_helper_support)
    LinearLayout llHelperSupport;
    @BindView(R.id.ll_service_on_line)
    LinearLayout llServiceOnLine;
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_center);
        ButterKnife.bind(this);

        tvTitleBarTitle2.setText("帮助支持");
        flBack.setOnClickListener(this);
        llHelperSupport.setOnClickListener(this);
        llServiceOnLine.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.fl_back:

                this.finish();

                break;

            case R.id.ll_helper_support:

                ToastUtils.showNomalShortToast("帮助中心支持");

                break;

            case R.id.ll_service_on_line:

                ToastUtils.showNomalShortToast("在线客服");

                break;

        }
    }
}
