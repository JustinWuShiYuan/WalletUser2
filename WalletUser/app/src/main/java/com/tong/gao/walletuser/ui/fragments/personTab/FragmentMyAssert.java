package com.tong.gao.walletuser.ui.fragments.personTab;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.response.ResponseQueryMyAssertBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentMyAssert extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_my_coin)
    TextView tvMyCoin;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.tv_can_used_money)
    TextView tvCanUsedMoney;
    @BindView(R.id.tv_margin_money)
    TextView tvMarginMoney;
    @BindView(R.id.tv_trading_money)
    TextView tvTradingMoney;
    @BindView(R.id.rv_trade_list)
    RecyclerView rvTradeList;
    Unbinder unbinder;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_assert, container, false);
    }



    @Override
    public void initData() {

        NetWorks.queryMyAssert(new Observer<ResponseQueryMyAssertBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("FragmentMyAssert ");
            }

            @Override
            public void onNext(ResponseQueryMyAssertBean responseQueryMyAssertBean) {
                LogUtils.d(""+responseQueryMyAssertBean.toString());
                if(null !=responseQueryMyAssertBean  && MyConstant.resultCodeIsOK.equals(responseQueryMyAssertBean.getErrcode())){
                    updateUI(responseQueryMyAssertBean);
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

    private void updateUI(final ResponseQueryMyAssertBean assertBean) {

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvMyCoin.setText(assertBean.getAmount());
                tvMyMoney.setText("折合人民币 "+assertBean.getConvertRmb()+" ￥");
                tvCanUsedMoney.setText(assertBean.getUsableFund());
                tvMarginMoney.setText(assertBean.getFrozenFund());

                String tradingMoney = (Float.parseFloat(assertBean.getAmount()) -Float.parseFloat(assertBean.getUsableFund()))+"";

                tvTradingMoney.setText(tradingMoney);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        tvTitleBarTitle2.setText("我的资产");
        flBack.setOnClickListener(this);

        return rootView;
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
                        .navigate(R.id.action_fragmentMyAssert_to_myInfoFragment);


                break;



        }

    }
}
