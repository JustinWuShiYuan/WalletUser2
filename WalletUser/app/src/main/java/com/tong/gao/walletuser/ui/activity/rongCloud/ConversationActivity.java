package com.tong.gao.walletuser.ui.activity.rongCloud;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.OrderCoinBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.TextUtils;

import java.text.ParseException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ConversationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.tv_order_trade_coin_type)
    TextView tvOrderTradeCoinType;
    @BindView(R.id.tv_trade_money)
    TextView tvTradeMoney;
    @BindView(R.id.tv_order_remain_time)
    TextView tvOrderRemainTime;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_order_status_des)
    TextView tvOrderStatusDes;


    OrderCoinBean orderCoinBean;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversation);
        ButterKnife.bind(this);


        String sName = getIntent().getData().getQueryParameter("targetId");//获取昵称
        setTitle("与" + sName + "聊天中");

        initView(sName);
    }

    private void initView(String chatUserName) {

        tvTitleBarTitle2.setText("" + chatUserName + "聊天中");

        flBack.setOnClickListener(this);


        orderCoinBean = PreferenceHelper.getInstance().getObject(MyConstant.orderCoinBeanKey, null);
        if(null != orderCoinBean){
            tvOrderTradeCoinType.setText(orderCoinBean.getTradeCoinType());
            tvTradeMoney.setText(orderCoinBean.getTradeMoney());


            LogUtils.d("orderCoinBean.getRemainTime():"+orderCoinBean.getRemainTime());

//            String[] times = orderCoinBean.getRemainTime().split(":");
//            String minute = times[0];
//            if(minute.substring(0,1) .equals("0")){
//                minute = minute.substring(1,minute.length());
//            }
//            long minuteL = Long.parseLong(minute);
//
//            String second = times[1];
//            if(second.substring(0,1) .equals("0")){
//                second = second.substring(1,second.length());
//            }
//            long secondL = Long.parseLong(second);
//
//            startCountDown(minuteL*60*1000+secondL*1000);
            startCountDown(Long.parseLong(orderCoinBean.getRemainTime()));

            tvOrderRemainTime.setText(orderCoinBean.getRemainTime());
            tvOrderTime.setText(orderCoinBean.getOrderCreateTime());
            tvOrderStatusDes.setText(orderCoinBean.getOrderStatus());
        }

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }



    public void startCountDown(long continueTime) {
        if (countDownTimer == null) {
            countDownTimer = new CountDownTimer(continueTime, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    tvOrderRemainTime.setText(TextUtils.parseTime1(millisUntilFinished));
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }

    }

    private void endCountDown() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        endCountDown();
    }
}
