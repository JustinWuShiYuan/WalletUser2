package com.tong.gao.walletuser.ui.fragments.buyCoin;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.OrderCoinBean;
import com.tong.gao.walletuser.bean.response.ResponseBuyerHadPayMoney;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.ui.activity.TransferAccountsActivity;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import static android.app.Activity.RESULT_OK;

public class FragmentBuyCoinResult extends BaseFragment implements View.OnClickListener {
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;

    @BindView(R.id.tv_order_num)
    TextView tvOrderNum;
    @BindView(R.id.iv_copy_order_num)
    ImageView ivCopyOrderNum;

    @BindView(R.id.tv_order_money_value)
    TextView tvOrderMoneyValue;
    @BindView(R.id.iv_copy_order_money)
    ImageView ivCopyOrderMoney;


    @BindView(R.id.tv_order_single_money_value)
    TextView tvOrderSingleMoneyValue;
    @BindView(R.id.tv_buy_coin_number)
    TextView tvBuyCoinNum;
    @BindView(R.id.tv_payment_type)
    TextView tvPaymentType;
    @BindView(R.id.tv_payment_reference_num)
    TextView tvPaymentReferenceNum;

    @BindView(R.id.btn_look_assert)
    Button btnLookAssert;
    @BindView(R.id.btn_scan_code_and_transfer)
    Button btnScanCodeAndTransfer;


    @BindView(R.id.rl_contact_merchant)
    RelativeLayout rlContactMerchant;

    Unbinder unbinder;


    ResponseBuyerHadPayMoney responseBuyerHadPayMoney;

    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};
    private static final int REQUEST_CODE_SCAN = 10;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_buy_coin_result, container, false);

    }

    @Override
    public void initData() {

        Bundle arguments = getArguments();
        if(null != arguments){

            responseBuyerHadPayMoney = (ResponseBuyerHadPayMoney) arguments.getSerializable(MyConstant.hadPayMoneyOrderKey);

            updateUI();

        }

    }

    private void updateUI() {

        tvOrderStatus.setText("完成");

        tvOrderNum.setText(responseBuyerHadPayMoney.getOrderNo());
        tvOrderMoneyValue.setText(responseBuyerHadPayMoney.getOrderAmount());
        tvOrderSingleMoneyValue.setText(responseBuyerHadPayMoney.getOrderPrice() +" CNY = 1 AB");
        tvBuyCoinNum.setText(responseBuyerHadPayMoney.getOrderNumber() +" AB");

        String paymentType = responseBuyerHadPayMoney.getPamentWay() .equals(MyConstant.paymentWayZfb) ?
                "支付宝" : responseBuyerHadPayMoney.getPamentWay() .equals(MyConstant.paymentWayWeChat) ?
                "微信": "银行卡";
        tvPaymentType.setText(paymentType);

        tvPaymentReferenceNum.setText(responseBuyerHadPayMoney.getPaymentNumber());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        tvTitleBarTitle2.setText("购买AB");
        initData();
        initMyView();
        return rootView;
    }

    private void initMyView() {
        flBack.setOnClickListener(this);
        btnLookAssert.setOnClickListener(this);
        btnScanCodeAndTransfer.setOnClickListener(this);
        rlContactMerchant.setOnClickListener(this);
        ivCopyOrderNum.setOnClickListener(this);
        ivCopyOrderMoney.setOnClickListener(this);


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                getActivity().finish();

                break;

            case R.id.btn_look_assert:

                NavHostFragment.findNavController(FragmentBuyCoinResult.this)
                        .navigate(R.id.action_fragmentBuyCoinResult_to_fragmentMyAssert2);

                break;

            case R.id.btn_scan_code_and_transfer:

                startScanQrCode();

                break;

            case R.id.rl_contact_merchant:

                String tradeCoinType = "AB";
                String tradeMoney = responseBuyerHadPayMoney.getOrderAmount();
                String remainTime = responseBuyerHadPayMoney.getPrompt();//获取此时的剩下时间
                String orderCreatTime = responseBuyerHadPayMoney.getPaymentTime();
                String orderStatus = "您已成功下单，请及时支付";

                StringBuffer stringBuffer = new StringBuffer();
                if(!remainTime.contains(":")){
                    stringBuffer.append(remainTime +":"+"01");
                }else{
                    stringBuffer.append(remainTime);
                }


                PreferenceHelper.getInstance().putObject(MyConstant.orderCoinBeanKey,new OrderCoinBean(tradeCoinType,tradeMoney,stringBuffer.toString(),orderCreatTime,orderStatus));

                RongIM.getInstance().startConversation(getActivity(),Conversation.ConversationType.PRIVATE,responseBuyerHadPayMoney.getSellUserId(), "聊天中");


                break;


            case R.id.iv_copy_order_num:


                break;

            case R.id.iv_copy_order_money:


                break;

        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    private void startScanQrCode() {
        final Intent intent = new Intent(mActivity, CaptureActivity.class);
        //申请权限
        AndPermission.with(mActivity)
                .runtime()
                .permission(permissions)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {

                        ZxingConfig config = new ZxingConfig();
                        config.setPlayBeep(true);//是否播放扫描声音 默认为true
                        config.setShake(true);//是否震动  默认为true
                        config.setDecodeBarCode(true);//是否扫描条形码 默认为true
//                                config.setReactColor(R.color.colorAccent);//设置扫描框四个角的颜色 默认为白色
//                                config.setFrameLineColor(R.color.colorAccent);//设置扫描框边框颜色 默认无色
//                                config.setScanLineColor(R.color.colorAccent);//设置扫描线的颜色 默认白色
                        config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
                        intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);


                        startActivityForResult(intent, REQUEST_CODE_SCAN);

                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Toast.makeText(mActivity, "ed", Toast.LENGTH_LONG).show();
                    }
                })
                .start();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Intent intent = new Intent();
                intent.putExtra(MyConstant.transferAccountAddressKey,content);
                intent.setClass(mActivity,TransferAccountsActivity.class);
                startActivity(intent);
            }
        }
    }
}
