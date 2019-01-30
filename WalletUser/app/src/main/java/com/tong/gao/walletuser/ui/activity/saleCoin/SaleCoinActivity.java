package com.tong.gao.walletuser.ui.activity.saleCoin;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestSellCoin;
import com.tong.gao.walletuser.bean.response.ResponseMoneyRange;
import com.tong.gao.walletuser.bean.response.ResponseSellCoin;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.fragments.sellCoin.SingleTradeFragment;
import com.tong.gao.walletuser.ui.view.NoScrollViewPager;
import com.tong.gao.walletuser.utils.AppUtils;
import com.tong.gao.walletuser.utils.Density;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SaleCoinActivity extends ActivityBase implements  View.OnClickListener {

    private TextView        tvTitle;
    private FrameLayout     ivBack;
    private TextView        tvLimitTitle;
    private View            lineLimitTitle;
    private TextView        tvConstantTitle;
    private View            lineConstantTitle;
    private NoScrollViewPager vpTrade;
    private String[]        tabTitles ={"单笔交易限额","单笔交易固额"};
    private List<Fragment>  tradeFragments = new ArrayList<>();


    private SwitchButton    sbZFB;
    private SwitchButton    sbWechat;
    private SwitchButton    sbBank;
    private EditText        etFastReply;
    private CheckBox        cbHeightAuthentication;
    private CheckBox        cbNoTradeOtherPlatform;
    private CheckBox        cbAgreeProtocol;
    private TextView        tvTradeProtocol;
    private Button          btnPublishAdvertisement;

    private RelativeLayout  rlSafeRootView;
    private LinearLayout    llSafeRootView;
    private TextView        tvCancelVerify;
    private EditText        etPaymentPwd;
    private EditText        etGooglePwd;
    private EditText        etInputSinglePrice;
    private Button          btnSureSafeCode;

    private String          coinTradeType;

    @Override
    protected int getLayout() {
        return R.layout.activity_sale_coin;
    }


    @Override
    protected void initView() {

        etInputSinglePrice = findViewById(R.id.et_input_single_price);

        tvTitle = findViewById(R.id.tv_title_bar_title2);
        tvTitle.setText("发布广告");
        ivBack = findViewById(R.id.fl_back);
        ivBack.setOnClickListener(this);

        tvLimitTitle = findViewById(R.id.tv_trade_limit_title);
        tvLimitTitle.setOnClickListener(this);
        lineLimitTitle = findViewById(R.id.line_trade_limit_title);

        tvConstantTitle = findViewById(R.id.tv_trade_constant_title);
        tvConstantTitle.setOnClickListener(this);
        lineConstantTitle = findViewById(R.id.line_trade_constant_title);


        vpTrade = findViewById(R.id.view_pager_trade);
        loadData();

        sbZFB = findViewById(R.id.switch_button_zfb);
        sbZFB.setOnClickListener(this);
        sbZFB.setChecked(true);
        sbWechat = findViewById(R.id.switch_button_we_chat);
        sbWechat.setOnClickListener(this);
        sbBank = findViewById(R.id.switch_button_bank);
        sbBank.setOnClickListener(this);

        etFastReply = findViewById(R.id.et_fast_reply);

        cbHeightAuthentication = findViewById(R.id.cb_height_authentication);
        cbHeightAuthentication.setChecked(true);
        cbHeightAuthentication.setOnClickListener(this);

        cbNoTradeOtherPlatform = findViewById(R.id.cb_no_trade_other_platform);
        cbNoTradeOtherPlatform.setChecked(true);
        cbNoTradeOtherPlatform.setOnClickListener(this);

        cbAgreeProtocol = findViewById(R.id.cb_agree_protocol);
        cbAgreeProtocol.setOnClickListener(this);



        tvTradeProtocol = findViewById(R.id.tv_trade_protocol);
        tvTradeProtocol.setOnClickListener(this);

        btnPublishAdvertisement = findViewById(R.id.btn_publish_advertisement);
        btnPublishAdvertisement.setOnClickListener(this);
        btnPublishAdvertisement.setClickable(false);

        rlSafeRootView = findViewById(R.id.rl_safe_root_view);
        llSafeRootView = findViewById(R.id.ll_safe_root_view);
        tvCancelVerify = findViewById(R.id.tv_cancel_verify);
        tvCancelVerify.setOnClickListener(this);

        etPaymentPwd = findViewById(R.id.et_payment_pwd);
        etGooglePwd = findViewById(R.id.et_google_pwd);
        btnSureSafeCode = findViewById(R.id.btn_sure_safe_code);
        btnSureSafeCode.setOnClickListener(this);

        if(null != myIntent){
            coinTradeType = myIntent.getStringExtra(MyConstant.CoinLimit);
            if(!StringUtils.isEmpty(coinTradeType) && MyConstant.CoinLimit.equals(coinTradeType)){
                tvConstantTitle.setTextColor(Color.parseColor("#9B9B9B"));
                tvConstantTitle.setClickable(false);
                cbAgreeProtocol.setChecked(true);
            }else if(!StringUtils.isEmpty(coinTradeType) && MyConstant.CoinConstant.equals(coinTradeType)){
                tvLimitTitle.setTextColor(Color.parseColor("#9B9B9B"));
                tvLimitTitle.setClickable(false);
                cbAgreeProtocol.setChecked(true);
            }
        }
    }

    private void loadData() {
        //ToDO 查询金额范围

        DialogUtils.showProgressDialog(this,"加载中.....");

        NetWorks.queryMoneyRange(new Observer<ResponseMoneyRange>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseMoneyRange responseMoneyRange) {


                if(null != responseMoneyRange && MyConstant.resultCodeIsOK .equals(responseMoneyRange.getErrcode())){

                    for (int i = 0; i < tabTitles.length; i++) {
                        tradeFragments.add(SingleTradeFragment.newInstance(i+1,responseMoneyRange));
                    }
                    TabAdapter adapter = new TabAdapter(getSupportFragmentManager(), tradeFragments);
                    //给ViewPager设置适配器
                    vpTrade.setAdapter(adapter);

                    DialogUtils.hideProgressDialog();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
                DialogUtils.hideProgressDialog();
            }

            @Override
            public void onComplete() {
                DialogUtils.hideProgressDialog();
            }
        });

    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this,AppUtils.HEIGHT);
    }


    private void changePublishBg() {
        GradientDrawable drawable =(GradientDrawable)btnPublishAdvertisement.getBackground();
        if(cbAgreeProtocol.isChecked()){
            btnPublishAdvertisement.setClickable(true);
            drawable.setColor(getResources().getColor(R.color.colorBlue2));
        }else{
            btnPublishAdvertisement.setClickable(false);
            drawable.setColor(getResources().getColor(R.color.gray_2));
        }
    }



    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.fl_back:         //
                this.finish();
                break;

            case R.id.tv_trade_limit_title:         //
                if(lineLimitTitle.getVisibility() == View.INVISIBLE){
                    tvLimitTitle.setTextColor(Color.parseColor("#587BFC"));
                    lineLimitTitle.setVisibility(View.VISIBLE);

                    tvConstantTitle.setTextColor(Color.parseColor("#666666"));
                    lineConstantTitle.setVisibility(View.INVISIBLE);

                    vpTrade.setCurrentItem(0);
                }

                break;

            case R.id.tv_trade_constant_title:         //
                if(lineConstantTitle.getVisibility() == View.INVISIBLE){
                    tvConstantTitle.setTextColor(Color.parseColor("#587BFC"));
                    lineConstantTitle.setVisibility(View.VISIBLE);

                    tvLimitTitle.setTextColor(Color.parseColor("#666666"));
                    lineLimitTitle.setVisibility(View.INVISIBLE);

                    vpTrade.setCurrentItem(1);
                }

                break;

            case R.id.tv_trade_protocol:         //TODO跳转 h5 协议页面

                break;

            case R.id.btn_publish_advertisement:   //发布广告
                rlSafeRootView.setVisibility(View.VISIBLE);
                int flSafeRootViewHeight = llSafeRootView.getMeasuredHeight();
                int height = llSafeRootView.getHeight();
                LogUtils.d("flSafeRootViewHeight:"+flSafeRootViewHeight+" height:"+height);
                ObjectAnimator translation = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 532, 0);
                translation.setDuration(800);
                translation.start();
                llSafeRootView.setVisibility(View.VISIBLE);

                break;

            case R.id.tv_cancel_verify:         //取消安全验证

                int height1 = llSafeRootView.getHeight();
                ObjectAnimator translation1 = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 0, height1);
                translation1.setDuration(500);
                translation1.start();
                translation1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rlSafeRootView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
//
                break;


            case R.id.btn_sure_safe_code:       //提交安全验证
                String singlePrice = etInputSinglePrice.getText().toString();
                String paymentPwd = etPaymentPwd.getText().toString();
                String googleCode = etGooglePwd.getText().toString();
                String type = "2";//卖

                StringBuffer stringBuffer = new StringBuffer();
                String paymentWay ="";
                String coinId = "AB";
                String coinType = "rmb";

                String fastReply = etFastReply.getText().toString();

                if(sbZFB.isChecked()){
                    stringBuffer.append("1,");
                }else if(sbWechat.isChecked()){
                    stringBuffer.append("2,");
                }else if(sbBank.isChecked()){
                    stringBuffer.append("3,");
                }

                if(StringUtils.isEmpty(stringBuffer.toString())){
                    ToastUtils.showNomalShortToast("请至少选择一种支付方式");
                    return;
                }else{
                    paymentWay = stringBuffer.substring(0, stringBuffer.length() - 1);
                }

                String isSeniorCertification = cbHeightAuthentication.isChecked() ? "1":"0";
                String isMerchantsTrade = cbNoTradeOtherPlatform.isChecked() ? "1":"0";

                //根据 选择的是限额 还是固额 来判断参数输入
                int currentItem = vpTrade.getCurrentItem();
                if(currentItem == MyConstant.singleTradeLimit){//选择的是单笔交易限额
                    SingleTradeFragment  singleTradeFragment = (SingleTradeFragment) tradeFragments.get(0);
                    String minValue = singleTradeFragment.getMinValue();
                    String maxValue = singleTradeFragment.getMaxValue();
                    String sellCoinNum = singleTradeFragment.getSellCoinNum();
                    String tradeTime = singleTradeFragment.getTradeTime();
                    String amountType = "1";//限额


                    if(StringUtils.isEmpty(minValue) || StringUtils.isEmpty(maxValue)
                            || StringUtils.isEmpty(sellCoinNum)|| StringUtils.isEmpty(tradeTime)){
                        ToastUtils.showNomalShortToast("输入的值不能有空");
                    }else{
                        RequestSellCoin requestSellCoin = new RequestSellCoin(sellCoinNum,amountType,maxValue,minValue
                                ,"null",singlePrice,type,paymentWay,coinId,coinType,tradeTime,fastReply,isSeniorCertification,isMerchantsTrade,paymentPwd,googleCode);

                        publishSellCoin(requestSellCoin);
                    }

                }else{//处理固额的情况

                    SingleTradeFragment  fixedTradeFragment = (SingleTradeFragment) tradeFragments.get(1);

                    String fixedSelectedValue = fixedTradeFragment.getLastSelectedValue();

                    String sellCoinNum = fixedTradeFragment.getSellCoinNum();
                    String tradeTime = fixedTradeFragment.getTradeTime();
                    String amountType = "2";//固额

                    if(StringUtils.isEmpty(fixedSelectedValue)){
                        ToastUtils.showNomalShortToast("请至少选择一个固定额度");
                    }else{
                        RequestSellCoin requestSellCoin = new RequestSellCoin(sellCoinNum,amountType,"",""
                                ,fixedSelectedValue,singlePrice,type,paymentWay,coinId,coinType,tradeTime,fastReply,isSeniorCertification,isMerchantsTrade,paymentPwd,googleCode);

                        publishSellCoin(requestSellCoin);

                    }

                }


                break;

            case R.id.cb_height_authentication:

//                changePublishBg();
                break;

            case R.id.cb_no_trade_other_platform:
//                changePublishBg();
                break;

            case R.id.cb_agree_protocol:
                changePublishBg();
                break;

            case R.id.switch_button_zfb:

                break;

            case R.id.switch_button_we_chat:

                break;

            case R.id.switch_button_bank:

                break;

//
        }
    }




    private void publishSellCoin(final RequestSellCoin requestSellCoin) {

        NetWorks.sellCoin(requestSellCoin, new Observer<ResponseSellCoin>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseSellCoin responseSellCoin) {
                LogUtils.d("responseSellCoin:"+responseSellCoin.toString());
                if(null != responseSellCoin && MyConstant.resultCodeIsOK .equals(responseSellCoin.getErrcode())){
                    Intent intent = new Intent();
                    intent.putExtra(MyConstant.sellCoinSuccessKey,responseSellCoin);
                    intent.setClass(SaleCoinActivity.this,PublishCoinActivity.class);
                    startActivity(intent);

                    SaleCoinActivity.this.finish();

                }else {
                    ToastUtils.showNomalShortToast("发布广告失败:"+responseSellCoin.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showNomalShortToast("发布广告失败:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }
}
