package com.tong.gao.walletuser.ui.fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.FireCoinBean;
import com.tong.gao.walletuser.bean.QueryFireCoinInfoBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.interfaces.DialogCallBack;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.view.HomeADPageView;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.iv_left_small_bell_icon)
    ImageView ivLeftSmallBellIcon;

    @BindView(R.id.iv_right_scan_icon)
    ImageView ivRightScanIcon;

    @BindView(R.id.tv_assert_ug_num)
    TextView tvAssertUgNum;

    @BindView(R.id.tv_assert_money)
    TextView tvAssertMoney;

    @BindView(R.id.tv_transfer_record)
    TextView tvTransferRecord;

    @BindView(R.id.de_recycle_img)
    HomeADPageView deRecycleImg;


    @BindView(R.id.rl_scan_and_transfer)
    RelativeLayout rlScanAndTransfer;
    @BindView(R.id.rl_exchange_coin_root)
    RelativeLayout rlExchangeCoinRoot;

    @BindView(R.id.rl_buy_coin)
    RelativeLayout rlBuyCoin;
    @BindView(R.id.rl_sale_coin_root)
    RelativeLayout rlSaleCoinRoot;

    @BindView(R.id.rl_order_container)
    RelativeLayout rlOrderContainer;
    @BindView(R.id.rl_helper_root)
    RelativeLayout rlHelperRoot;


    @BindView(R.id.rv_recycle_view)
    RecyclerView recyclerView;

    private   List<FireCoinBean>  fireCoinBeanList = new ArrayList<>();
    private   View rootView;
    private   MyFireCoinInfoAdapter myFireCoinInfoAdapter;

    private static final int REQUEST_CODE_SCAN = 10;
    private String[] permissions ={Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};


    Unbinder unbinder;
    private boolean firstExplain = true;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        LogUtils.d(" initView");
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setHasFixedSize(true);


        deRecycleImg.startAutoPlay();//开启自动播放功能


        ivLeftSmallBellIcon.setOnClickListener(this);
        ivRightScanIcon.setOnClickListener(this);
        tvTransferRecord.setOnClickListener(this);


        return rootView;
    }

    @Override
    public void initData() {

        NetWorks.queryFireCoinInfo(new Observer<QueryFireCoinInfoBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("开始订阅");
            }

            @Override
            public void onNext(QueryFireCoinInfoBean queryFireCoinInfoBean) {
                LogUtils.d(""+queryFireCoinInfoBean.toString()+"  erro:"+queryFireCoinInfoBean.getErr_code());

//                if(null != queryFireCoinInfoBean && queryFireCoinInfoBean.getErr_code() .equals(MyConstant.queryFireCoinOk)){
                if(null != queryFireCoinInfoBean ){
                    fireCoinBeanList = queryFireCoinInfoBean.getMarketList();

                    myFireCoinInfoAdapter = new MyFireCoinInfoAdapter();

                    mHandler.sendMessageDelayed(mHandler.obtainMessage(), 1000);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError"+e.toString());
            }

            @Override
            public void onComplete() {
//                LogUtils.d(" onComplete()");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_pager,container,false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(inflater,container);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            recyclerView.setAdapter(myFireCoinInfoAdapter);
        }
    };


//    @BindView(R.id.iv_left_small_bell_icon)
//    ImageView ivLeftSmallBellIcon;
//    @BindView(R.id.iv_right_scan_icon)
//    ImageView ivRightScanIcon;
//    @BindView(R.id.tv_assert_ug_num)
//    TextView tvAssertUgNum;
//    @BindView(R.id.tv_assert_money)
//    TextView tvAssertMoney;
//    @BindView(R.id.tv_transfer_record)
//    TextView tvTransferRecord;
//    @BindView(R.id.de_recycle_img)
//    HomeADPageView deRecycleImg;
//    @BindView(R.id.rl_scan_and_transfer)
//    RelativeLayout rlScanAndTransfer;
//    @BindView(R.id.rl_exchange_coin_root)
//    RelativeLayout rlExchangeCoinRoot;
//    @BindView(R.id.rl_buy_coin)
//    RelativeLayout rlBuyCoin;
//    @BindView(R.id.rl_sale_coin_root)
//    RelativeLayout rlSaleCoinRoot;
//    @BindView(R.id.rl_order_container)
//    RelativeLayout rlOrderContainer;
//    @BindView(R.id.rl_helper_root)
//    RelativeLayout rlHelperRoot;


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.iv_left_small_bell_icon:

                break;

            case R.id.iv_right_scan_icon:

                scanCode();

                break;
        }
    }

    private void scanCode() {

        if(firstExplain){
            DialogUtils.createAlertDialog(mActivity, R.layout.dialog_first_show, R.id.iv_close, R.id.tv_buy_coin_ab, new DialogCallBack() {
                @Override
                public void cancel(Dialog dialog) {
                    dialog.dismiss();
                }

                @Override
                public void sure(Dialog dialog) {
                    dialog.dismiss();

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
                                    Toast.makeText(mActivity,"ed",Toast.LENGTH_LONG).show();
                                }
                            })
                            .start();

                }
            });

        }else{

        }




    }


    private class MyFireCoinInfoAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyViewHolder(LayoutInflater.from(mActivity).inflate(R.layout.item_home_pager, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int index) {
            myViewHolder.refreshUI(fireCoinBeanList.get(index));
        }

        @Override
        public int getItemCount() {
            return fireCoinBeanList.size();
        }
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCoinIcon;
        TextView tvCoinId;
        TextView tvTradeId;
        TextView tvIncreasePercent;
        TextView tvCoinPrice;
        TextView tvChinesePrice;

        public MyViewHolder(View itemView) {
            super(itemView);

            ivCoinIcon = itemView.findViewById(R.id.iv_coin_icon);
            tvCoinId = itemView.findViewById(R.id.tv_coin_id);
            tvTradeId = itemView.findViewById(R.id.tv_trade_id);
            tvCoinPrice = itemView.findViewById(R.id.tv_coin_price);
            tvChinesePrice = itemView.findViewById(R.id.tv_exchange_china_price);
            tvIncreasePercent = itemView.findViewById(R.id.tv_increase_percent);

        }


        public void refreshUI(FireCoinBean fireCoinBean) {

            //更新火币icon
            switch (fireCoinBean.getCoinId()){

                case MyConstant.coinBTC:
                    ivCoinIcon.setImageResource(R.drawable.icon_vip);
                    break;

                case MyConstant.coinETH:
                    ivCoinIcon.setImageResource(R.drawable.icon_cir_bank);
                    break;

                case MyConstant.coinEOS:
                    ivCoinIcon.setImageResource(R.drawable.icon_cir_wechat);
                    break;

            }
            tvCoinId.setText(fireCoinBean.getCoinId());
            tvTradeId.setText(fireCoinBean.getTradeId());
            tvCoinPrice.setText(fireCoinBean.getPrice());
            tvChinesePrice.setText(fireCoinBean.getRmbPrice());

            LogUtils.d("fireCoinBean.getUpAndDown().contains(\"-\"):"+(fireCoinBean.getUpAndDown().contains("-")));

            tvIncreasePercent.setText(fireCoinBean.getUpAndDown());
            if(fireCoinBean.getUpAndDown().contains("-")){

                tvIncreasePercent.setTextColor(Color.parseColor("#006151"));
                tvIncreasePercent.setBackground(mActivity.getDrawable(R.drawable.shape_green_round6));

            }else{
                tvIncreasePercent.setTextColor(Color.parseColor("#d02a2a"));
                tvIncreasePercent.setBackground(mActivity.getDrawable(R.drawable.shape_red_round6));
            }

        }
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // 扫描二维码/条码回传
        if (requestCode == REQUEST_CODE_SCAN && resultCode == RESULT_OK) {
            if (data != null) {

                String content = data.getStringExtra(Constant.CODED_CONTENT);

                Toast.makeText(mActivity,content,Toast.LENGTH_LONG).show();
            }
        }
    }
}
