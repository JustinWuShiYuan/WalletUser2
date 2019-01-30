package com.tong.gao.walletuser.ui.fragments.sellCoin;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.response.ResponseMoneyRange;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.utils.LogUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class SingleTradeFragment extends BaseFragment {
    @BindView(R.id.et_input_min_value)
    EditText etInputMinValue;
    @BindView(R.id.et_input_max_value)
    EditText etInputMaxValue;
    @BindView(R.id.rl_trade_limit)
    RelativeLayout rlTradeLimit;
    @BindView(R.id.tfl_flow_layout)
    TagFlowLayout flfFlowLayout;
    @BindView(R.id.rl_trade_constant)
    RelativeLayout rlTradeConstant;

    @BindView(R.id.et_input_sell_coin_num)
    EditText etInputSellCoinNum;
    @BindView(R.id.rl_sale_num)
    LinearLayout rlSaleNum;

    @BindView(R.id.tv_payment_time_value)
    EditText tvPaymentTimeValue;
    @BindView(R.id.tv_payment_time_unit)
    TextView tvPaymentTimeUnit;
    @BindView(R.id.rl_payment_time)
    LinearLayout rlPaymentTime;
    Unbinder unbinder;

    public static String TAB_LAYOUT_FRAGMENT = "tab_fragment";
    private int type;
    private List<String> fixMoneyList = new ArrayList<>();
    private LayoutInflater mInflater;

    private String minValue,maxValue, sellCoinNum,tradeTime;

    private String lastSelectedValue;

    private ResponseMoneyRange moneyRange;

    public String getLastSelectedValue() {
        return lastSelectedValue;
    }


    public String getMinValue() {

        minValue = etInputMinValue.getText().toString();

        return minValue;
    }

    public String getMaxValue() {

        maxValue = etInputMaxValue.getText().toString();

        return maxValue;
    }

    public String getSellCoinNum() {

        sellCoinNum = etInputSellCoinNum.getText().toString();

        return sellCoinNum;
    }

    public String getTradeTime() {

        tradeTime = tvPaymentTimeValue.getText().toString();

        return tradeTime;
    }

    public static SingleTradeFragment newInstance(int type, ResponseMoneyRange responseMoneyRange) {
        SingleTradeFragment fragment = new SingleTradeFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TAB_LAYOUT_FRAGMENT, type);
        bundle.putSerializable("moneyRange",responseMoneyRange);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            type = (int) getArguments().getSerializable(TAB_LAYOUT_FRAGMENT);
            moneyRange = (ResponseMoneyRange) getArguments().getSerializable("moneyRange");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        initData();
        return rootView;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_sell_coin, container, false);
        return view;
    }

    @Override
    public void initData() {
        String fixedMoneyList = moneyRange.getPrice();
        String[] split = fixedMoneyList.split("\\,");
        for(int i=0;i<split.length;i++){
            fixMoneyList.add(""+split[i]);
        }

        String[] liminMoneyRange = moneyRange.getOptionPrice().split("\\,");
        etInputMinValue.setText(liminMoneyRange[0]);
        etInputMaxValue.setText(liminMoneyRange[1]);


        flfFlowLayout.setMaxSelectCount(1);

        flfFlowLayout.setAdapter(new TagAdapter<String>(fixMoneyList) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) mInflater.inflate(R.layout.item_cash, parent, false);
                tv.setTextColor(Color.parseColor("#333333"));
                tv.setText(s);
                return tv;
            }

            @Override
            public void onSelected(int position, View view) {
                view.setBackground(getResources().getDrawable(R.drawable.shape_blue_bg));
                ((TextView) view).setTextColor(Color.parseColor("#ffffff"));
                Toast.makeText(getActivity(), fixMoneyList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void unSelected(int position, View view) {
                view.setBackground(getResources().getDrawable(R.drawable.shape_gray15_round_bg));
                ((TextView) view).setTextColor(Color.parseColor("#333333"));
            }
        });


        flfFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), fixMoneyList.get(position), Toast.LENGTH_SHORT).show();
                lastSelectedValue = fixMoneyList.get(position);

                return true;
            }
        });


        switch (type) {

            case MyConstant.singleLimitTrade:       //单笔限额交易
                rlTradeConstant.setVisibility(View.GONE);
                rlTradeLimit.setVisibility(View.VISIBLE);
                break;

            case MyConstant.singleConstantTrade:       //单笔固额交易
                rlTradeConstant.setVisibility(View.VISIBLE);
                rlTradeLimit.setVisibility(View.GONE);
                break;

        }

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
