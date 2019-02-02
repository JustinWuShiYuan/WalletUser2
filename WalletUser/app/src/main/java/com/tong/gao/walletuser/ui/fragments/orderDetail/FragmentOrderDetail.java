package com.tong.gao.walletuser.ui.fragments.orderDetail;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.OrderCoinBean;
import com.tong.gao.walletuser.bean.UserOrderBean;
import com.tong.gao.walletuser.bean.myEnum.OrderStatus;
import com.tong.gao.walletuser.bean.request.RequestBuyerHadPayMoney;
import com.tong.gao.walletuser.bean.request.RequestCancelOrder;
import com.tong.gao.walletuser.bean.response.ResponseBuyerHadPayMoney;
import com.tong.gao.walletuser.bean.response.ResponseCancelOrder;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.interfaces.CountDownCallBack;
import com.tong.gao.walletuser.interfaces.DialogCallBack;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

public class FragmentOrderDetail extends Fragment implements View.OnClickListener {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.cl_root_view)
    ConstraintLayout clRootView;
    @BindView(R.id.iv_order_status_icon)
    ImageView ivOrderStatusIcon;
    @BindView(R.id.tv_order_detail_status_des)
    TextView tvOrderDetailStatusDes;
    @BindView(R.id.rl_not_willGreenLight)
    RelativeLayout rlNotWillGreenLight;
    @BindView(R.id.tv_order_detail_num)
    TextView tvOrderDetailNum;
    @BindView(R.id.iv_copy_order_detail_num)
    ImageView ivCopyOrderDetailNum;
    @BindView(R.id.tv_order_detail_money)
    TextView tvOrderDetailMoney;
    @BindView(R.id.iv_copy_order_detail_money)
    ImageView ivCopyOrderDetailMoney;
    @BindView(R.id.tv_order_detail_single_price)
    TextView tvOrderDetailSinglePrice;
    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.tv_order_detail_time)
    TextView tvOrderDetailTime;
    @BindView(R.id.tv_trade_no)
    TextView tvTradeNo;
    @BindView(R.id.rl_trade_code)
    RelativeLayout rlTradeCode;
    @BindView(R.id.tv_block_num)
    TextView tvBlockNum;
    @BindView(R.id.rl_block_code)
    RelativeLayout rlBlockCode;
    @BindView(R.id.rl_complete_bottom_root)
    RelativeLayout rlCompleteBottomRoot;
    @BindView(R.id.rl_order_had_canceled)
    RelativeLayout rlOrderHadCanceled;
    @BindView(R.id.tv_order_remain_time_waiting)
    TextView tvOrderRemainTimeWaiting;
    @BindView(R.id.tv_go_to_pay_hint)
    TextView tvGoToPayHint;
    @BindView(R.id.tv_had_pay)
    TextView tvHadPay;
    @BindView(R.id.tv_cancel_order)
    TextView tvCancelOrder;
    @BindView(R.id.rl_have_not_pay)
    RelativeLayout rlHaveNotPay;
    @BindView(R.id.tv_order_remain_time)
    TextView tvOrderRemainTime;
    @BindView(R.id.tv_give_user_hint_info)
    TextView tvGiveUserHintInfo;
    @BindView(R.id.tv_will_appeal)
    TextView tvWillAppeal;
    @BindView(R.id.rl_had_pay)
    RelativeLayout rlHadPay;
    @BindView(R.id.tv_cancel_appealing)
    TextView tvCancelAppealing;
    @BindView(R.id.iv_contact_buyer_icon)
    ImageView ivContactBuyerIcon;
    @BindView(R.id.tv_contact_buyer)
    TextView tvContactBuyer;
    @BindView(R.id.rl_contact_buyer)
    RelativeLayout rlContactBuyer;
    Unbinder unbinder;


    private UserOrderBean userOrderBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitleBarTitle2.setText("购买AB");

        flBack.setOnClickListener(this);

        Intent intent = getActivity().getIntent();
        if (null != intent) {
            userOrderBean = (UserOrderBean) intent.getSerializableExtra(MyConstant.orderItemDetailKey);

            if (null != userOrderBean) {
                updateUI();
            }
        }

        ivCopyOrderDetailNum.setOnClickListener(this);
        ivCopyOrderDetailMoney.setOnClickListener(this);
        tvCancelAppealing.setOnClickListener(this);
        tvWillAppeal.setOnClickListener(this);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void updateUI() {

        String userOrderStatus = userOrderBean.getStatus();

        if (userOrderStatus.equals(OrderStatus.HadNotPay.getState())) {//未付款

            ivOrderStatusIcon.setImageResource(R.drawable.icon_have_not_pay);
            tvOrderDetailStatusDes.setText("未付款");

            rlHaveNotPay.setVisibility(View.VISIBLE);


            UIUtils.startCountDown(Long.parseLong(userOrderBean.getRestTime()), tvOrderRemainTimeWaiting, new CountDownCallBack() {
                @Override
                public void countDownFinish() {
                    //TODO 倒计时结束要做的事
                }
            });

            tvHadPay.setOnClickListener(this);
            tvCancelOrder.setOnClickListener(this);

            rlContactBuyer.setOnClickListener(this);


        } else if (userOrderStatus.equals(OrderStatus.HadPay.getState())) {//已付款

            rlHaveNotPay.setVisibility(View.GONE);
            rlHadPay.setVisibility(View.VISIBLE);

            long remainTime = Long.parseLong(userOrderBean.getRestTime());
            if(remainTime > 0){
                ivOrderStatusIcon.setImageResource(R.drawable.icon_complete);
                tvOrderDetailStatusDes.setText("已付款，等待对方放行");
                tvWillAppeal.setBackground(getActivity().getDrawable(R.drawable.shape_gray3_round_bg));
                tvWillAppeal.setClickable(false);

                UIUtils.startCountDown(remainTime, tvOrderRemainTime, new CountDownCallBack() {
                    @Override
                    public void countDownFinish() {
                        hadPayNotLetGo();
                    }
                });

            }else{
                hadPayNotLetGo();
            }

            rlContactBuyer.setOnClickListener(this);

        } else if (userOrderStatus.equals(OrderStatus.Complete.getState())) {//已完成

            ivOrderStatusIcon.setImageResource(R.drawable.icon_complete);
            tvOrderDetailStatusDes.setText("已完成");

            rlCompleteBottomRoot.setVisibility(View.VISIBLE);
            tvTradeNo.setText(userOrderBean.getOrderNo());
            tvBlockNum.setText(userOrderBean.getOrderNo());

            rlContactBuyer.setOnClickListener(this);

        } else if (userOrderStatus.equals(OrderStatus.Cancel_HadCancel.getState())) {//已取消

            ivOrderStatusIcon.setImageResource(R.drawable.icon_closed);
            tvOrderDetailStatusDes.setText("已取消");

            hideBottomContainer();

        } else if (userOrderStatus.equals(OrderStatus.Closed_HadClosed.getState())) {//已关闭

            ivOrderStatusIcon.setImageResource(R.drawable.icon_closed);
            tvOrderDetailStatusDes.setText("已关闭");

            hideBottomContainer();

        } else if (userOrderStatus.equals(OrderStatus.Appealing.getState())) {//申诉中

            ivOrderStatusIcon.setImageResource(R.drawable.icon_appeal);
            tvOrderDetailStatusDes.setText("申诉中");

            tvCancelAppealing.setOnClickListener(this);

        }


        tvOrderDetailNum.setText(userOrderBean.getOrderNo());
        tvOrderDetailMoney.setText(userOrderBean.getConvertRmb());
        tvOrderDetailSinglePrice.setText(userOrderBean.getPrice() +" CNY = 1 AB");
        tvOrderNum.setText(userOrderBean.getNumber() +"  AB");
        tvOrderDetailTime.setText(userOrderBean.getRestTime() +"  AB");


    }

    private void hideBottomContainer() {
        rlCompleteBottomRoot.setVisibility(View.GONE);
        rlHaveNotPay.setVisibility(View.GONE);
        rlHadPay.setVisibility(View.GONE);
        tvCancelAppealing.setVisibility(View.GONE);

        rlContactBuyer.setClickable(false);
        rlContactBuyer.setBackgroundColor(Color.parseColor("#e9e8f0"));

        ivContactBuyerIcon.setImageResource(R.drawable.icon_cont_gray_3);
        tvContactBuyer.setTextColor(Color.parseColor("#888888"));
    }

    private void hadPayNotLetGo() {
        ivOrderStatusIcon.setImageResource(R.drawable.icon_closed);
        tvOrderDetailStatusDes.setText("已付款，对方未放行");
        tvWillAppeal.setBackground(getActivity().getDrawable(R.drawable.shape_gray6_round_bg));
        tvWillAppeal.setClickable(true);
        tvWillAppeal.setOnClickListener(this);

        rlContactBuyer.setClickable(true);
        rlContactBuyer.setBackgroundColor(Color.parseColor("#ffffff"));
        ivContactBuyerIcon.setImageResource(R.drawable.icon_cont_3);
        tvContactBuyer.setTextColor(Color.parseColor("#ff9238"));

    }


    CheckBox cbHaveNoPay, cbZfb, cbWechat, cbBank;
    TextView tvHaveNoPayDes, tvZFB, tvWechat, tvBank;
    Button btnCancelOrder;

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                getActivity().finish();

                break;

            case R.id.iv_copy_order_detail_num:

                UIUtils.copyTextToClipboard(tvOrderDetailNum.getText().toString(),getActivity());

                break;


            case R.id.iv_copy_order_detail_money:

                UIUtils.copyTextToClipboard(tvOrderDetailMoney.getText().toString(),getActivity());

                break;


            case R.id.tv_had_pay: //已完成付款

                View alertDialog = DialogUtils.createAlertDialog(getActivity(), R.layout.dialog_havd_pay, R.id.btn_have_transfer,
                        R.id.btn_later_on, 300, 300, new DialogCallBack() {
                            @Override
                            public void cancel(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void sure(Dialog dialog) {
                                dialog.dismiss();
                                hadPayMoney();
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

            case R.id.tv_cancel_order: //取消订单


                View view = DialogUtils.createAlertDialog(getActivity(), R.layout.dialog_cancel_order, R.id.btn_cancel_order,
                        R.id.btn_later_on, 300, 300, new DialogCallBack() {
                            @Override
                            public void cancel(Dialog dialog) {
                                dialog.dismiss();
                            }

                            @Override
                            public void sure(Dialog dialog) {

                                if (cbHaveNoPay.isChecked()) {
                                    dialog.dismiss();
                                    cancelOrder();
                                }else{
                                    ToastUtils.showNomalShortToast("请勾选确认还未付款");
                                }
                            }
                        });

                cbHaveNoPay = view.findViewById(R.id.cb_have_no_pay);
                cbHaveNoPay.setOnClickListener(this);

                tvHaveNoPayDes = view.findViewById(R.id.tv_have_no_pay_des);
                btnCancelOrder = view.findViewById(R.id.btn_cancel_order);


                break;

            case R.id.tv_will_appeal: //我要申诉

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_orderDetailFragment_to_fragmentOrderAppeal);

                break;

            case R.id.tv_cancel_appealing: //取消申诉



                break;

            case R.id.rl_contact_buyer: //联系卖家

                String tradeCoinType = "AB";
                String tradeMoney = userOrderBean.getOrderAmount();
                String remainTime = userOrderBean.getRestTime();//获取此时的剩下时间
                String orderCreatTime = userOrderBean.getCreatedTime();
                String orderStatus = "您还未下单";

                PreferenceHelper.getInstance().putObject(MyConstant.orderCoinBeanKey,new OrderCoinBean(tradeCoinType,tradeMoney,remainTime,orderCreatTime,orderStatus));

                RongIM.getInstance().startConversation(getActivity(),Conversation.ConversationType.PRIVATE,userOrderBean.getSellUserId(), "聊天中");


                break;


        }
    }

    private void hadPayMoney() {

        String paymentWay = MyConstant.paymentWayZfb;

        if(cbZfb.isChecked()){
            paymentWay = MyConstant.paymentWayZfb;
        }

        if(cbWechat.isChecked()){
            paymentWay = MyConstant.paymentWayWeChat;
        }

        if(cbBank.isChecked()){
            paymentWay = MyConstant.paymentWayBank;
        }



        if (cbZfb.isChecked() || cbWechat.isChecked() || cbBank.isChecked()) {
            NetWorks.buyerHadPay(new RequestBuyerHadPayMoney(userOrderBean.getOrderNo(), paymentWay), new Observer<ResponseBuyerHadPayMoney>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(ResponseBuyerHadPayMoney responseBuyerHadPayMoney) {
                    LogUtils.d("responseBuyerHadPayMoney："+responseBuyerHadPayMoney.toString());

                    if(null != responseBuyerHadPayMoney && MyConstant.resultCodeIsOK .equals(responseBuyerHadPayMoney.getErrcode())){
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(MyConstant.hadPayMoneyOrderKey,responseBuyerHadPayMoney);
                        ToastUtils.showNomalLongToast("已完成付款成功");

                        FragmentOrderDetail.this.getActivity().finish();

                    }else{
                        ToastUtils.showNomalShortToast(""+responseBuyerHadPayMoney.getMsg());
                    }

                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.d(""+e.toString());
                }

                @Override
                public void onComplete() {
                    LogUtils.d("onComplete()");
                }
            });

        }else{
            ToastUtils.showNomalLongToast("请至少勾选一种付款的方式");
        }


    }

    private void cancelOrder() {

        NetWorks.cancelOrder(new RequestCancelOrder(userOrderBean.getOrderNo()), new Observer<ResponseCancelOrder>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(""+d);
            }

            @Override
            public void onNext(ResponseCancelOrder responseCancelOrder) {

                if(null != responseCancelOrder && MyConstant.resultCodeIsOK.equals(responseCancelOrder.getErrcode())){
                    ToastUtils.showNomalShortToast("取消订单成功");
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UIUtils.endCountDown();
    }
}
