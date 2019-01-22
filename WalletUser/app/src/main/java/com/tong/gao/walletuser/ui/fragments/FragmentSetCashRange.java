package com.tong.gao.walletuser.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.ui.view.RangeSeekBar;
import com.tong.gao.walletuser.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentSetCashRange extends Fragment {


    @BindView(R.id.range_seek_bar)
    RangeSeekBar rangeSeekBar;
    Unbinder unbinder;
    @BindView(R.id.tv_min)
    TextView tvMin;
    @BindView(R.id.tv_max)
    TextView tvMax;


    private float maxCashValue;

    private String setMaxValue,setMinValue;

    public String getSetMinValue() {
        return setMinValue;
    }

    public void setSetMinValue(String setMinValue) {
        this.setMinValue = setMinValue;
    }

    public String getSetMaxValue() {
        return setMaxValue;
    }

    public void setSetMaxValue(String setMaxValue) {
        this.setMaxValue = setMaxValue;
    }

    public float getMaxCashValue() {
        return maxCashValue;
    }

    public static FragmentSetCashRange newInstance(float maxValue) {
        FragmentSetCashRange fragment = new FragmentSetCashRange();
        Bundle args = new Bundle();
        args.putFloat("FragmentSetCashRange", maxValue);
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_set_cash_range, container, false);
        unbinder = ButterKnife.bind(this, view);
        Bundle arguments = getArguments();
        if (null != arguments) {
            maxCashValue = arguments.getFloat("FragmentSetCashRange");
            rangeSeekBar.setMaxValue(maxCashValue);
            initView();
        }
        return view;
    }

    private void initView() {

        rangeSeekBar.setOnRangeChangedListener(new RangeSeekBar.OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float min, float max) {
                tvMin.setText("¥" + min);
                setMinValue = min+"";


                String value = max+"";
                if(value.length() > 2){
                    String[] split = value.split("\\.");
                    setMaxValue =""+split[0];
                    tvMax.setText("¥" + split[0]);
                }
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
