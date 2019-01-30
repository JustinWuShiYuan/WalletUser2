package com.tong.gao.walletuser.ui.fragments.exchangeCoin;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.request.RequestBtcExchangeDetail;
import com.tong.gao.walletuser.bean.request.RequestCancelExchangeApply;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeDetail;
import com.tong.gao.walletuser.bean.response.ResponseCancelExchangeApply;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tong.gao.walletuser.constants.MyConstant.StatusDealing;
import static com.tong.gao.walletuser.constants.MyConstant.StatusRemit;

public class FragmentExchangeCoinDetail extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_exchange_coin_type)
    TextView tvExchangeCoinType;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.tv_exchanged_num)
    TextView tvExchangedNum;
    @BindView(R.id.tv_get_coin_num)
    TextView tvGetCoinNum;
    @BindView(R.id.tv_order_submit_time)
    TextView tvOrderSubmitTime;
    @BindView(R.id.tv_order_status_exchanged)
    TextView tvOrderStatusExchanged;
    @BindView(R.id.tv_btc_address)
    TextView tvBtcAddress;

    @BindView(R.id.rl_txid_container)
    RelativeLayout rlTxidContainer;
    @BindView(R.id.tv_txid)
    TextView tvTxid;

    @BindView(R.id.rl_reject_container)
    RelativeLayout rlRejectContainer;
    @BindView(R.id.tv_reject_reason_info)
    TextView tvRejectReasonInfo;

    @BindView(R.id.tv_cancel_exchange_or_check_txid)
    TextView tvCancelExchangeOrCheckTxid;
    Unbinder unbinder;


    private String exchangeApplyId;
    private ResponseBtcExchangeDetail btcExchangeDetailBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_coin_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initData();
        return view;
    }

    private void initView() {
        tvTitleBarTitle2.setText("兑换详情");
        flBack.setOnClickListener(this);
        tvCancelExchangeOrCheckTxid.setOnClickListener(this);
    }

    private void initData() {

        Bundle arguments = getArguments();
        if(null != arguments){
            exchangeApplyId = arguments.getString(MyConstant.btcExchangeApplyDetailKey);
            loadData(exchangeApplyId);
        }
    }

    private void loadData(String exchangeApplyId) {

        NetWorks.btcExchangeApplyDetail(new RequestBtcExchangeDetail(exchangeApplyId), new Observer<ResponseBtcExchangeDetail>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseBtcExchangeDetail responseBtcExchangeDetail) {
                LogUtils.d("responseBtcExchangeDetail:"+responseBtcExchangeDetail.toString());
                if(null != responseBtcExchangeDetail && MyConstant.resultCodeIsOK.equals(responseBtcExchangeDetail.getErrcode())){
                    btcExchangeDetailBean = responseBtcExchangeDetail;
                    updateUI();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });


    }

    private void updateUI() {

        tvRate.setText("1 AB = "+btcExchangeDetailBean.getBtcNumber()+" BTC");
        tvExchangedNum.setText(btcExchangeDetailBean.getNumber());
        tvGetCoinNum.setText(btcExchangeDetailBean.getBtcNumber());
        tvOrderSubmitTime.setText(btcExchangeDetailBean.getCreatedTime());


        if(btcExchangeDetailBean.getStatus() .equals(StatusDealing)){
            tvOrderStatusExchanged.setText("处理中");
            tvOrderStatusExchanged.setTextColor(Color.parseColor("#ff9238"));

            rlTxidContainer.setVisibility(View.GONE);
            tvCancelExchangeOrCheckTxid.setText("撤销兑换");
            tvCancelExchangeOrCheckTxid.setVisibility(View.VISIBLE);

        }else if(btcExchangeDetailBean.getStatus() .equals(StatusRemit)){
            tvOrderStatusExchanged.setText("已汇出");
            tvOrderStatusExchanged.setTextColor(Color.parseColor("#4c7fff"));

            rlTxidContainer.setVisibility(View.VISIBLE);
            tvCancelExchangeOrCheckTxid.setText("查看Txid");
            tvCancelExchangeOrCheckTxid.setVisibility(View.VISIBLE);

        }else{
            tvOrderStatusExchanged.setText("已驳回");
            tvOrderStatusExchanged.setTextColor(Color.parseColor("#d02a2a"));

            rlTxidContainer.setVisibility(View.GONE);
            tvCancelExchangeOrCheckTxid.setVisibility(View.GONE);
        }


        tvBtcAddress.setText(btcExchangeDetailBean.getBtcAddress());
        tvTxid.setText(btcExchangeDetailBean.getTxhash());
        tvRejectReasonInfo.setText(btcExchangeDetailBean.getRejectReason());

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){


            case R.id.fl_back:

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_fragmentExchangeCoinDetail_to_fragmentExchangeCoin);

                break;

            case R.id.tv_cancel_exchange_or_check_txid:

                if(btcExchangeDetailBean.getStatus() .equals(StatusDealing)){ //撤销兑换
                    cancelExchange();
                }else if(btcExchangeDetailBean.getStatus() .equals(StatusRemit)){//查看Txid

                }


                break;

        }

    }

    private void cancelExchange() {
        NetWorks.cancelExchangeApply(new RequestCancelExchangeApply(exchangeApplyId), new Observer<ResponseCancelExchangeApply>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseCancelExchangeApply responseCancelExchangeApply) {

                if(null != responseCancelExchangeApply && MyConstant.resultCodeIsOK .equals(responseCancelExchangeApply.getErrcode())){
                    ToastUtils.showNomalShortToast("撤销成功");
                    NavHostFragment.findNavController(FragmentExchangeCoinDetail.this)
                            .navigate(R.id.action_fragmentExchangeCoinDetail_to_fragmentExchangeCoin);
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
    }
}
