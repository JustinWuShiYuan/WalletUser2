package com.tong.gao.walletuser.ui.fragments.buyCoin;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.response.ResponseOrdersBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.interfaces.DialogCallBack;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;


import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentBuyCoinDetail extends BaseFragment implements View.OnClickListener{

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.iv_copy_order_id)
    ImageView ivCopyOrderId;
    @BindView(R.id.tv_order_money)
    TextView tvOrderMoney;
    @BindView(R.id.iv_copy_order_money)
    ImageView ivCopyOrderMoney;
    @BindView(R.id.tv_single_price)
    TextView tvSinglePrice;
    @BindView(R.id.tv_buy_coin_number)
    TextView tvBuyCoinNumber;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;

    @BindView(R.id.tv_zfb)
    TextView tvZfb;
    @BindView(R.id.rl_payment_zfb)
    RelativeLayout rlPaymentZfb;
    @BindView(R.id.tv_we_chat_pay)
    TextView tvWeChatPay;
    @BindView(R.id.rl_payment_we_chat)
    RelativeLayout rlPaymentWeChat;
    @BindView(R.id.tv_bank_card)
    TextView tvBankCard;
    @BindView(R.id.rl_payment_bank)
    RelativeLayout rlPaymentBank;
    @BindView(R.id.iv_ali_qr_code_zfb)
    ImageView ivAliQrCodeZfb;
    @BindView(R.id.tv_notice_pay_amont2)
    TextView tvNoticePayAmont2;
    @BindView(R.id.ll_payment_zfb)
    LinearLayout llPaymentZfb;
    @BindView(R.id.tv_we_chat_reference_num)
    TextView tvWechatReferenceId;
    @BindView(R.id.iv_ali_qr_code_we_chat)
    ImageView ivAliQrCodeWeChat;
    @BindView(R.id.tv_notice_pay_amont3)
    TextView tvNoticePayAmont3;
    @BindView(R.id.ll_payment_we_chat)
    LinearLayout llPaymentWeChat;
    @BindView(R.id.tv_bank_name)
    TextView tvBankName;
    @BindView(R.id.iv_copy_bank_name)
    ImageView ivCopyBankName;
    @BindView(R.id.tv_bank_account)
    TextView tvBankAccount;
    @BindView(R.id.iv_copy_bank_account)
    ImageView ivCopyBankAccount;
    @BindView(R.id.tv_bank_username)
    TextView tvBankUsername;
    @BindView(R.id.tv_payment_reference_num_bank)
    TextView tvPaymentReferenceNum;
    @BindView(R.id.iv_copy_payment_reference_num)
    ImageView ivCopyPaymentReferenceNum;
    @BindView(R.id.tv_notice_pay_amont1)
    TextView tvNoticePayAmont1;
    @BindView(R.id.ll_payment_bank_card)
    LinearLayout llPaymentBankCard;
    @BindView(R.id.tv_time_count_down)
    TextView tvTimeCountDown;
    @BindView(R.id.ll_con_seller)
    LinearLayout llConSeller;
    @BindView(R.id.btn_cancel)
    Button btnCancel;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;
    Unbinder unbinder;
    @BindView(R.id.tv_payment_reference_num_zfb)
    TextView tvPaymentReferenceNumZfb;
    @BindView(R.id.iv_copy_payment_reference_num_zfb)
    ImageView ivCopyPaymentReferenceNumZfb;
    @BindView(R.id.iv_copy_payment_reference_num_we_chat)
    ImageView ivCopyPaymentReferenceNumWeChat;


    private ResponseOrdersBean responseOrdersBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        initData();
        initMyView();
        return rootView;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_buy_coin_detail, container, false);
    }

    @Override
    public void initData() {
        //TODO 获取从上层 请求好的数据  然后刷新页面
        Bundle bundle = getArguments();
        if( null != bundle){
            responseOrdersBean = (ResponseOrdersBean) bundle.getSerializable(MyConstant.downOrderKey);
            updateUI(responseOrdersBean);
        }

    }

    private void updateUI(ResponseOrdersBean responseOrdersBean) {

        tvOrderId.setText(responseOrdersBean.getOrderId());
        tvOrderMoney.setText(responseOrdersBean.getOrderAmount());
        tvSinglePrice.setText(responseOrdersBean.getOrderPrice());
        tvBuyCoinNumber.setText(responseOrdersBean.getOrderNumber());
        tvOrderTime.setText(responseOrdersBean.getCreatedTime());


        List<ResponseOrdersBean.PaymentBean> paymentWay = responseOrdersBean.getPaymentWay();

        if(null != paymentWay){

            for(int i = 0;i<paymentWay.size();i++){

                switch (paymentWay.get(i).getPaymentWay()){

                    case MyConstant.paymentWayZfb:
                        rlPaymentZfb.setVisibility(View.VISIBLE);

                        break;

                    case MyConstant.paymentWayWeChat:
                        rlPaymentWeChat.setVisibility(View.VISIBLE);

                        break;

                    case MyConstant.paymentWayBank:
                        rlPaymentBank.setVisibility(View.VISIBLE);

                        break;
                }
            }
        }

        //根据数据 展示默认选中的tab 已经对应的页面
        if(rlPaymentZfb.getVisibility() == View.VISIBLE){
            showPaymentTabAndView(R.id.rl_payment_zfb);
        }else{
            if(rlPaymentWeChat.getVisibility() == View.VISIBLE){
                showPaymentTabAndView(R.id.rl_payment_we_chat);
            }else{
                //走到这里说明 只有银行卡了
                showPaymentTabAndView(R.id.rl_payment_bank);
            }
        }

    }



    private void initMyView() {
        tvTitleBarTitle2.setText("购买AB");
        flBack.setOnClickListener(this);

        rlPaymentZfb.setOnClickListener(this);
        rlPaymentWeChat.setOnClickListener(this);
        rlPaymentBank.setOnClickListener(this);
        ivCopyOrderId.setOnClickListener(this);
        ivCopyOrderMoney.setOnClickListener(this);


        btnCancel.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    CheckBox cbHaveNoPay,cbZfb,cbWechat,cbBank;
    TextView tvHaveNoPayDes,tvZFB,tvWechat,tvBank ;
    Button btnCancelOrder ;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.rl_payment_zfb:
                showPaymentTabAndView(R.id.rl_payment_zfb);
                break;


            case R.id.rl_payment_we_chat:
                showPaymentTabAndView(R.id.rl_payment_we_chat);
                break;

            case R.id.rl_payment_bank:
                showPaymentTabAndView(R.id.rl_payment_bank);
                break;


            case R.id.iv_copy_order_id:     //复制订单号
                UIUtils.copyTextToClipboard(tvOrderId.getText().toString(),getContext());
                break;

            case R.id.iv_copy_order_money:     //复制订单金额
                UIUtils.copyTextToClipboard(tvOrderMoney.getText().toString(),getContext());
                break;



            case R.id.btn_cancel:       //点击取消订单

                View view = DialogUtils.createAlertDialog(getActivity(), R.layout.dialog_cancel_order, R.id.btn_cancel_order,
                        R.id.btn_later_on, 300, 300, new DialogCallBack() {
                            @Override
                            public void cancel(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void sure(Dialog dialog) {
                                dialog.dismiss();
                                if(cbHaveNoPay.isChecked()){
                                    ToastUtils.showNomalShortToast("执行取消订单逻辑");
                                }
                            }
                        });

                cbHaveNoPay = view.findViewById(R.id.cb_have_no_pay);
                cbHaveNoPay.setOnClickListener(this);

                tvHaveNoPayDes = view.findViewById(R.id.tv_have_no_pay_des);
                btnCancelOrder = view.findViewById(R.id.btn_cancel_order);
                break;


            case R.id.cb_have_no_pay:

                tvHaveNoPayDes.setTextColor(cbHaveNoPay.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));
                btnCancelOrder.setBackground(cbHaveNoPay.isChecked() ? getActivity().getDrawable( R.drawable.shape_gray_cornor4):
                        getActivity().getDrawable(R.drawable.shape_content_gray_bg));

                btnCancelOrder.setClickable(cbHaveNoPay.isChecked());
                break;


            case R.id.btn_confirm:      //点击完成付款

                View alertDialog = DialogUtils.createAlertDialog(getActivity(), R.layout.dialog_havd_pay, R.id.btn_have_transfer,
                        R.id.btn_later_on, 300, 300, new DialogCallBack() {
                            @Override
                            public void cancel(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void sure(Dialog dialog) {
                                dialog.dismiss();
                                if(cbZfb.isChecked() || cbWechat.isChecked() || cbBank.isChecked()){
                                    //TODO  提交接口 如果成功 走下面
                                    NavHostFragment.findNavController(FragmentBuyCoinDetail.this)
                                            .navigate(R.id.action_fragmentBuyCoinoDetail_to_fragmentBuyCoinResult);
                                }
                            }
                        });

                cbZfb = alertDialog.findViewById(R.id.cb_zfb);
                cbZfb.setOnClickListener(this);

                cbWechat = alertDialog.findViewById(R.id.cb_we_chat_pay);
                cbWechat.setOnClickListener(this);

                cbBank = alertDialog.findViewById(R.id.cb_bank_card);
                cbBank.setOnClickListener(this);


                tvZFB = alertDialog.findViewById(R.id.tv_zfb);
                tvWechat = alertDialog.findViewById(R.id.tv_we_chat);
                tvBank = alertDialog.findViewById(R.id.tv_bank_card);

                break;

            case R.id.cb_zfb:

                tvZFB.setTextColor(cbZfb.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                cbWechat.setChecked(false);
                cbBank.setChecked(false);

                tvWechat.setTextColor(cbWechat.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));
                tvBank.setTextColor(cbBank.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                break;

            case R.id.cb_we_chat_pay:

                tvWechat.setTextColor(cbWechat.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                cbZfb.setChecked(false);
                cbBank.setChecked(false);

                tvZFB.setTextColor(cbZfb.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));
                tvBank.setTextColor(cbBank.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                break;

            case R.id.cb_bank_card:

                tvBank.setTextColor(cbBank.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                cbZfb.setChecked(false);
                cbWechat.setChecked(false);

                tvZFB.setTextColor(cbZfb.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));
                tvWechat.setTextColor(cbWechat.isChecked() ? Color.parseColor("#4c7fff") : Color.parseColor("#4a4a4a"));

                break;



        }

    }

    public void showPaymentTabAndView(int id){

        switch (id){

            case R.id.rl_payment_zfb:


                tvZfb.setTextColor(Color.parseColor("#2595c9"));
                tvWeChatPay.setTextColor(Color.parseColor("#717171"));
                tvBankCard.setTextColor(Color.parseColor("#717171"));

                rlPaymentZfb.setBackgroundResource(R.drawable.shape_white_bg_blue_line);
                rlPaymentWeChat.setBackgroundResource(R.drawable.shape_white_bg_gray_line);
                rlPaymentBank.setBackgroundResource(R.drawable.shape_white_bg_gray_line);

                llPaymentZfb.setVisibility(View.VISIBLE);
                llPaymentWeChat.setVisibility(View.GONE);
                llPaymentBankCard.setVisibility(View.GONE);


                break;


            case R.id.rl_payment_we_chat:

                tvZfb.setTextColor(Color.parseColor("#717171"));
                tvWeChatPay.setTextColor(Color.parseColor("#2595c9"));
                tvBankCard.setTextColor(Color.parseColor("#717171"));

                rlPaymentZfb.setBackgroundResource(R.drawable.shape_white_bg_gray_line);
                rlPaymentWeChat.setBackgroundResource(R.drawable.shape_white_bg_blue_line);
                rlPaymentBank.setBackgroundResource(R.drawable.shape_white_bg_gray_line);

                llPaymentZfb.setVisibility(View.GONE);
                llPaymentWeChat.setVisibility(View.VISIBLE);
                llPaymentBankCard.setVisibility(View.GONE);


                break;

            case R.id.rl_payment_bank:

                tvZfb.setTextColor(Color.parseColor("#717171"));
                tvWeChatPay.setTextColor(Color.parseColor("#717171"));
                tvBankCard.setTextColor(Color.parseColor("#2595c9"));

                rlPaymentZfb.setBackgroundResource(R.drawable.shape_white_bg_gray_line);
                rlPaymentWeChat.setBackgroundResource(R.drawable.shape_white_bg_gray_line);
                rlPaymentBank.setBackgroundResource(R.drawable.shape_white_bg_blue_line);

                llPaymentZfb.setVisibility(View.GONE);
                llPaymentWeChat.setVisibility(View.GONE);
                llPaymentBankCard.setVisibility(View.VISIBLE);

                break;



        }



    }


}
