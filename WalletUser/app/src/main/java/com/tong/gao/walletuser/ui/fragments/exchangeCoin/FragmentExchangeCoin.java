package com.tong.gao.walletuser.ui.fragments.exchangeCoin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeRate;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.CalculateUtils;
import com.tong.gao.walletuser.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentExchangeCoin extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_rate)
    TextView tvRate;
    @BindView(R.id.et_input_ab_number)
    EditText etInputAbNumber;
    @BindView(R.id.et_input_btc_number)
    EditText etInputBtcNumber;
    @BindView(R.id.et_input_btc_address)
    EditText etInputBtcAddress;
    @BindView(R.id.btn_submit_apply)
    Button btnSubmitApply;
    @BindView(R.id.rl_recycler_my_apply)
    RecyclerView rlRecyclerMyApply;
    Unbinder unbinder;



    private boolean flag = true;

    private ResponseBtcExchangeRate btcExchangeRate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exchange_coin, container, false);
        unbinder = ButterKnife.bind(this, view);
        requestData();
        initView();
        return view;
    }

    private void requestData() {

        NetWorks.queryBtcExchangeRate(new Observer<ResponseBtcExchangeRate>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("d");
            }

            @Override
            public void onNext(ResponseBtcExchangeRate responseBtcExchangeRate) {
                if(null != responseBtcExchangeRate && MyConstant.resultCodeIsOK .equals(responseBtcExchangeRate.getErrcode())){
                    btcExchangeRate = responseBtcExchangeRate;
                    updateUI();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }

    private void updateUI() {
        LogUtils.d("ExchangeCoin","btcExchangeRate.getExchangeRate():"+btcExchangeRate.getExchangeRate());
        tvRate.setText("汇率 "+btcExchangeRate.getExchangeRate());
    }

    private void initView() {
        tvTitleBarTitle2.setText("兑换比特币");
        flBack.setOnClickListener(this);


        etInputAbNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                if (flag) {
                    flag = false;
                    if (TextUtils.isEmpty(text) || btcExchangeRate == null) {
                        etInputBtcNumber.setText("");
                        return;
                    }
                    String result = CalculateUtils.mul(text, btcExchangeRate.getExchangeRate(), 4);
                    etInputBtcNumber.setText(result);
                } else {
                    flag = true;
                }
            }
        });

        etInputBtcNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String text = s.toString().trim();
                if (flag) {
                    flag = false;
                    if (TextUtils.isEmpty(text) || btcExchangeRate == null || "0".equals(text)) {
                        etInputAbNumber.setText("");
                        return;
                    }
                    String result = CalculateUtils.div(text, btcExchangeRate.getExchangeRate(), 4);
                    etInputAbNumber.setText(result);
                } else {
                    flag = true;
                }
            }
        });


//        rlRecyclerMyApply


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

                getActivity().finish();

                break;

        }
    }


    public class ExChangeAdapter extends BaseQuickAdapter<ResponseBtcExchangeRate, BaseViewHolder> {

        public ExChangeAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, ResponseBtcExchangeRate item) {
//            if ("1".equals(item.status)) {
//                helper.setText(R.id.tv_statu, "处理中");
//            } else {
//                helper.setText(R.id.tv_statu, "已处理");
//            }
//
//            helper.setText(R.id.tv_price, CalculateUtils.div(item.number, "1", 2) + " AB = "+ CalculateUtils.div(item.btcNumber, "1", 2) +" BTC");

        }


    }
}
