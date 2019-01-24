package com.tong.gao.walletuser.ui.fragments.buyCoin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.CoinBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.utils.CalculateUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentBuyCoin extends BaseFragment {


    @BindView(R.id.ll_limit_container)
    LinearLayout llLimitContainer;

    @BindView(R.id.ll_fixed_container)
    LinearLayout llFixedContainer;


    @BindView(R.id.tv_username)
    TextView tvUsername;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_limit_value)
    TextView tvLimitValue;
    @BindView(R.id.tv_amont_type)
    TextView tvAmountType;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_can_buy_max_num)
    TextView tvCanBuyMaxNum;

    @BindView(R.id.et_input_money)
    EditText etInputMoney;

    @BindView(R.id.et_input_buy_num)
    EditText etInputBuyNum;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;
    @BindView(R.id.tv_fixed_coin_num)
    TextView tvFixedCoinNum;
    @BindView(R.id.tv_sell_price)
    TextView tvSellPrice;
    @BindView(R.id.tv_single_price)
    TextView tvSinglePrice;
    @BindView(R.id.tv_receipt_account)
    TextView tvReceiptAccount;
    @BindView(R.id.rl_cancel_and_submit)
    LinearLayout rlCancelAndSubmit;

    private View rootView;

    private boolean flag = true;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_buy_coin, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(inflater, container);
        return rootView;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        Object object = PreferenceHelper.getInstance().getObject(MyConstant.tradeFragmentCoinBeanKey, null);
        CoinBean coinBean = null;
        if (null != object) {
            coinBean = (CoinBean) object;

            LogUtils.d("coinBean.getAmountType():"+coinBean.getAmountType());
            if (coinBean.getAmountType().equals(MyConstant.tradeFixedAmountType)) {
                llLimitContainer.setVisibility(View.GONE);
                llFixedContainer.setVisibility(View.VISIBLE);
            } else {
                llLimitContainer.setVisibility(View.VISIBLE);
                llFixedContainer.setVisibility(View.GONE);
            }

            updateUI(coinBean);
        }


        final CoinBean finalCoinBean = coinBean;
        etInputMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flag) {
                    flag = false;
                    String text = s.toString().trim();
                    if (TextUtils.isEmpty(text)) {
                        etInputBuyNum.setText("");
                        return;
                    }
                    String result = CalculateUtils.div(text, finalCoinBean.getPrice(), 2);
                    etInputBuyNum.setText(result);
                } else {
                    flag = true;
                }
            }
        });

        etInputBuyNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (flag) {
                    flag = false;
                    String text = s.toString().trim();
                    if (TextUtils.isEmpty(text)) {
                        etInputMoney.setText("");
                        return;
                    }
                    String result = CalculateUtils.mul(text, finalCoinBean.getPrice(), 2);
                    etInputMoney.setText(result);
                } else {
                    flag = true;
                }
            }
        });


        return rootView;

    }

    @Override
    public void initData() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventCoinBean(CoinBeanEvent event){
//
//        LogUtils.d("收到 event 事件 1111111111111111");
//
//        if(null != event){
//
//            updateUI(event.getCoinBean());
//
//        }
//
//    }


    private void updateUI(CoinBean coinBean) {
        //更新 限额 相关部分---------------------------------------------开始
        String nickName = coinBean.getNickName();
        tvUsername.setText(nickName.charAt(0) + "****" + nickName.charAt(nickName.length() - 1));

        tvPrice.setText(coinBean.getPrice() + " CNY = 1 AB");

        if (MyConstant.tradeFixedAmountType.equals(coinBean.getAmountType())) { //限额
            tvAmountType.setText("限额");
            tvLimitValue.setText(coinBean.getLimitMinAmount() + "~" + coinBean.getLimitMaxAmount() + "CNY");
        } else { //固额
            tvAmountType.setText("固额");
            tvLimitValue.setText(coinBean.getFixedAmount() + "CNY");
        }

        //支付方式 有哪些
        String paymentway = coinBean.getPaymentway();
        String paymentValue = "";
        if (paymentway.length() > 1) {

            String[] paymentWayList = paymentway.split(",");
            for (int i = 0; i < paymentWayList.length; i++) {

                if (i == paymentWayList.length - 1) {
                    paymentValue.concat(getPaymentTypeValue(paymentWayList[i]));
                } else {
                    paymentValue.concat(getPaymentTypeValue(paymentWayList[i]) + ",");
                }
            }

        }else{
            getPaymentTypeValue(paymentValue);
        }

        tvPayType.setText(paymentValue);

        tvCanBuyMaxNum.setText(coinBean.getLimitMaxAmount());

        //更新 限额 相关部分---------------------------------------------结束


        //更新 固额 相关部分---------------------------------------------开始
        tvFixedCoinNum.setText(coinBean.getFixedAmount() +" AB");

        LogUtils.d("coinBean.getFixedAmount():"+coinBean.getFixedAmount());
//        String result = CalculateUtils.mul(coinBean.getFixedAmount(), coinBean.getPrice(), 2);
//        tvSellPrice.setText(result);

        tvSinglePrice.setText(coinBean.getPrice());

        tvReceiptAccount.setText(paymentValue);

//        @BindView(R.id.tv_receipt_account)
//        TextView tvReceiptAccount;
//        @BindView(R.id.rl_cancel_and_submit)
//        LinearLayout rlCancelAndSubmit;


        //更新 固额 相关部分---------------------------------------------结束


    }


    private String getPaymentTypeValue(String paymentType) {

        String paymentValue = "";
        if (paymentType.equals(MyConstant.paymentWayZfb)) {
            paymentValue.concat("支付宝");
        }

        if (paymentType.equals(MyConstant.paymentWayWeChat)) {
            paymentValue.concat("微信");
        }

        if (paymentType.equals(MyConstant.paymentWayBank)) {
            paymentValue.concat("银行卡");
        }

        return paymentValue;

    }

}
