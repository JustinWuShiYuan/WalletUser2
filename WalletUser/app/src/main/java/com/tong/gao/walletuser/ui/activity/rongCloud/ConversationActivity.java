package com.tong.gao.walletuser.ui.activity.rongCloud;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);



        String sName = getIntent().getData().getQueryParameter("targetId");//获取昵称
        setTitle("与" + sName + "聊天中");

//        LogUtils.d("sName:" + sName);
        initView(sName);
    }

    private void initView(String chatUserName) {

        tvTitleBarTitle2.setText("" + chatUserName + "聊天中");

        flBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
