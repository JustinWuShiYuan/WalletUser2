package com.tong.gao.walletuser.ui.fragments.homeTab;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.FireCoinBean;
import com.tong.gao.walletuser.bean.QueryFireCoinInfoBean;
import com.tong.gao.walletuser.bean.event.ExitLoginEvent;
import com.tong.gao.walletuser.bean.event.MessageEvent;
import com.tong.gao.walletuser.bean.event.StartLoadDataEvent;
import com.tong.gao.walletuser.bean.response.ResponseMyAccountInfo;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.factory.ThreadPoolFactory;
import com.tong.gao.walletuser.interfaces.DialogCallBack;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.activity.LoginActivity;
import com.tong.gao.walletuser.ui.activity.MyOrderListActivity;
import com.tong.gao.walletuser.ui.activity.TransferAccountsActivity;
import com.tong.gao.walletuser.ui.activity.TransferRecordActivity;
import com.tong.gao.walletuser.ui.view.HomeADPageView;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends BaseFragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.iv_left_small_bell_icon)
    ImageView ivLeftSmallBellIcon;

    @BindView(R.id.view_message_hint)
    View hintMessageView;

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
    @BindView(R.id.rl_user_info)
    RelativeLayout rlUserInfo;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.rl_un_login)
    RelativeLayout rlUnLogin;
    @BindView(R.id.iv_scan_coin)
    ImageView ivScanCoin;
    @BindView(R.id.iv_exchange_coin1)
    ImageView ivExchangeCoin1;
    @BindView(R.id.rl_sale_and_exchange_coin_container)
    LinearLayout rlSaleAndExchangeCoinContainer;
    @BindView(R.id.iv_buy_coin)
    ImageView ivBuyCoin;
    @BindView(R.id.iv_sale_coin)
    ImageView ivSaleCoin;
    @BindView(R.id.rl_buy_and_sale_coin_container)
    LinearLayout rlBuyAndSaleCoinContainer;
    @BindView(R.id.iv_order)
    ImageView ivOrder;
    @BindView(R.id.iv_helper)
    ImageView ivHelper;
    @BindView(R.id.rl_order_and_helper_container)
    LinearLayout rlOrderAndHelperContainer;


    @BindView(R.id.srl_refresh_root_view)
    SwipeRefreshLayout refreshLayout;

    private List<FireCoinBean> fireCoinBeanList = new ArrayList<>();
    private View rootView;
    private MyFireCoinInfoAdapter myFireCoinInfoAdapter;

    private static final int REQUEST_CODE_SCAN = 10;
    private String[] permissions = {Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE};


    Unbinder unbinder;
    private boolean firstExplain = true;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(mActivity);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(mLinearLayoutManager);
        recyclerView.setHasFixedSize(true);

        deRecycleImg.startAutoPlay();//开启自动播放功能

        ivLeftSmallBellIcon.setOnClickListener(this);
        ivRightScanIcon.setOnClickListener(this);
        tvTransferRecord.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        rlScanAndTransfer.setOnClickListener(this);
        rlBuyCoin.setOnClickListener(this);
        rlSaleCoinRoot.setOnClickListener(this);
        rlOrderContainer.setOnClickListener(this);
        rlHelperRoot.setOnClickListener(this);
        rlExchangeCoinRoot.setOnClickListener(this);

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(this);

        return rootView;
    }

    private void changeDistance() {

        ViewTreeObserver vto = refreshLayout.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                Float mDistanceToTriggerSync = 2000.f;

                try {
                    Field field = SwipeRefreshLayout.class.getDeclaredField("mDistanceToTriggerSync");
                    field.setAccessible(true);
                    field.setFloat(refreshLayout, mDistanceToTriggerSync);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                ViewTreeObserver obs = refreshLayout.getViewTreeObserver();
                obs.removeOnGlobalLayoutListener(this);
            }
        });
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

                if (null != queryFireCoinInfoBean && MyConstant.resultCodeIsOK .equals(queryFireCoinInfoBean.getErrcode())) {
                    fireCoinBeanList = queryFireCoinInfoBean.getMarketList();

                    myFireCoinInfoAdapter = new MyFireCoinInfoAdapter();

                    mHandler.sendMessageDelayed(mHandler.obtainMessage(), 1000);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError" + e.toString());
            }

            @Override
            public void onComplete() {
//                LogUtils.d(" onComplete()");
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home_pager, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(inflater, container);
        changeDistance();
        EventBus.getDefault().register(this);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventExitLogin(ExitLoginEvent exitLoginEvent){
        rlUserInfo.setVisibility(View.GONE);
        rlUnLogin.setVisibility(View.VISIBLE);
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_login:

                startActivity(new Intent(mActivity, LoginActivity.class));

                break;

            case R.id.iv_left_small_bell_icon:

                EventBus.getDefault().post(new MessageEvent("3"));

                break;

            case R.id.iv_right_scan_icon:

                scanCode();

                break;

            case R.id.tv_transfer_record:   //转账记录

                startActivity(new Intent(getActivity(), TransferRecordActivity.class));

                break;


            case R.id.cb_no_longer_remind:  //首次转账 不再提醒说明的 cb

                if (cbNoLongerRemind0AB.isChecked()) {
                    PreferenceHelper.getInstance().putBooleanValue(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN_0AB, true);
                } else {
                    PreferenceHelper.getInstance().putBooleanValue(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN_0AB, false);
                }

                break;


            case R.id.tv_cancel_transfer:      //0AB 使用帮助

                ToastUtils.showNomalShortToast("0AB 使用帮助");

                break;


            case R.id.cb_no_longer_remind_transfer:   //扫码转账 对话框

                if (cbScanQrCodeTransfer.isChecked()) {
                    PreferenceHelper.getInstance().putBooleanValue(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN, true);
                } else {
                    PreferenceHelper.getInstance().putBooleanValue(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN, false);
                }

                break;

            case R.id.rl_scan_and_transfer:         //中间6个条目扫码转账

                scanCode();

                break;

            case R.id.rl_exchange_coin_root:        //中间6个条目 兑换比特币

                ToastUtils.showNomalShortToast("兑换比特币");

                break;


            case R.id.rl_buy_coin:        //中间6个条目 买币

                EventBus.getDefault().post(new MessageEvent("2"));

                break;

            case R.id.rl_sale_coin_root:        //中间6个条目 卖币

                ToastUtils.showNomalShortToast("  卖币");

                break;

            case R.id.rl_order_container:        //中间6个条目 我的订单

                startActivity(new Intent(getActivity(),MyOrderListActivity.class));

                break;

            case R.id.rl_helper_root:        //中间6个条目 帮助中心

                ToastUtils.showNomalShortToast("  帮助中心");

                break;

        }
    }

    CheckBox cbNoLongerRemind0AB, cbScanQrCodeTransfer;

    private void scanCode() {

        if (firstExplain) {//当资产为 0AB

            if (!PreferenceHelper.getInstance().getBooleanShareData(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN_0AB, false)) {
                View firstScanView = DialogUtils.createAlertDialog(mActivity, R.layout.dialog_first_transfer_accounts, R.id.iv_close, R.id.tv_sure_transfer, new DialogCallBack() {
                    @Override
                    public void cancel(Dialog dialog) {
                        dialog.dismiss();
                    }

                    @Override
                    public void sure(Dialog dialog) {
                        dialog.dismiss();

                        startScanQrCode();

                    }
                });

                View useHelp0AB = firstScanView.findViewById(R.id.tv_cancel_transfer);

                cbNoLongerRemind0AB = firstScanView.findViewById(R.id.cb_no_longer_remind);
                cbNoLongerRemind0AB.setOnClickListener(this);
                useHelp0AB.setOnClickListener(this);
            }


        } else {  //当资产不为0AB


            if (!PreferenceHelper.getInstance().getBooleanShareData(PreferenceHelper.PreferenceKey.KEY_N0_REMAIN, false)) {

                View view = DialogUtils.createAlertDialog(mActivity, R.layout.dialog_transfer_accounts, R.id.iv_close, R.id.tv_sure, new DialogCallBack() {
                    @Override
                    public void cancel(Dialog dialog) {
                        dialog.dismiss();

                    }

                    @Override
                    public void sure(Dialog dialog) {
                        dialog.dismiss();

                        startScanQrCode();
                    }
                });
                cbScanQrCodeTransfer = view.findViewById(R.id.cb_no_longer_remind_transfer);
                cbScanQrCodeTransfer.setOnClickListener(this);

            }


        }


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
    public void onRefresh() {

        //TODO 重新加载 接口 刷新数据

        ThreadPoolFactory.getScheduledExecutor().scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                refreshLayout.setRefreshing(false);
            }
        },1,4,TimeUnit.SECONDS);
    }


    private class MyFireCoinInfoAdapter extends RecyclerView.Adapter<MyViewHolder> {

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
            switch (fireCoinBean.getCoinId()) {

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

//            LogUtils.d("fireCoinBean.getUpAndDown().contains(\"-\"):" + (fireCoinBean.getUpAndDown().contains("-")));

            tvIncreasePercent.setText(fireCoinBean.getUpAndDown());
            if (fireCoinBean.getUpAndDown().contains("-")) {

                tvIncreasePercent.setTextColor(Color.parseColor("#006151"));
                tvIncreasePercent.setBackground(mActivity.getDrawable(R.drawable.shape_green_round6));

            } else {
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
                Intent intent = new Intent();
                intent.putExtra(MyConstant.transferAccountAddressKey,content);
                intent.setClass(mActivity,TransferAccountsActivity.class);
                startActivity(intent);
            }
        }
    }


    private boolean isQueryMyAccountSuccess = false;
    @Override
    public void onResume() {
        super.onResume();
        if(!isQueryMyAccountSuccess){
            loadMyAccountInfo();
        }
    }


    private void loadMyAccountInfo() {

        NetWorks.queryMyAccountInfo(new Observer<ResponseMyAccountInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("查询我的账号");
            }

            @Override
            public void onNext(ResponseMyAccountInfo responseMyAccountInfo) {
                LogUtils.d("responseMyAccountInfo:"+responseMyAccountInfo.toString());

                if(null != responseMyAccountInfo && MyConstant.resultCodeIsOK.equals(responseMyAccountInfo.getErrcode()) ){
                    refreshTitleUI(responseMyAccountInfo);
                    isQueryMyAccountSuccess = true;
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
                isQueryMyAccountSuccess = false;
            }

            @Override
            public void onComplete() {
                LogUtils.d("e:onComplete()");
            }
        });

    }

    private void refreshTitleUI(final ResponseMyAccountInfo responseMyAccountInfo) {

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rlUnLogin.setVisibility(View.GONE);
                rlUserInfo.setVisibility(View.VISIBLE);

                tvAssertUgNum.setText(responseMyAccountInfo.getUsableFund());
                tvAssertMoney.setText("折合人民币  "+responseMyAccountInfo.getConvertRmb());

            }
        });

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage (MessageEvent event){
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventStartLoadData(StartLoadDataEvent event){
        loadMyAccountInfo();
    }
}
