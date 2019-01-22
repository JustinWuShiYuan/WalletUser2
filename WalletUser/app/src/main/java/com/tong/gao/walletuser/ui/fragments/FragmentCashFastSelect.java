package com.tong.gao.walletuser.ui.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.utils.Density;
import com.tong.gao.walletuser.utils.LogUtils;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentCashFastSelect extends Fragment {

    @BindView(R.id.tfl_flow_layout)
    TagFlowLayout flfFlowLayout;
    Unbinder unbinder;

    private List<String>  dataList = new ArrayList<>();

    private LayoutInflater mInflater;

    private String lastSelecteValue ;

    public String getLastSelecteValue() {
        return lastSelecteValue;
    }

    public static FragmentCashFastSelect newInstance(ArrayList<String> data) {
        FragmentCashFastSelect fragment = new FragmentCashFastSelect();
        Bundle args = new Bundle();
        args.putStringArrayList("FragmentCashFastSelect",data);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        View view = inflater.inflate(R.layout.fragment_cash_fast_select, container, false);
        unbinder = ButterKnife.bind(this, view);

        Bundle arguments = getArguments();
        if(null != arguments){
            dataList = arguments.getStringArrayList("FragmentCashFastSelect");
//            LogUtils.d("dataList.size():"+dataList.size());
        }

        initView();
        Density.setDefault(getActivity());
        return view;
    }




    private void initView() {
        flfFlowLayout.setMaxSelectCount(1);

        flfFlowLayout.setAdapter(new TagAdapter<String>(dataList){

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
                ( (TextView) view).setTextColor(Color.parseColor("#ffffff"));

                Toast.makeText(getActivity(), dataList.get(position), Toast.LENGTH_SHORT).show();
            }


            @Override
            public void unSelected(int position, View view) {
                view.setBackground(getResources().getDrawable(R.drawable.shape_gray15_round_bg));
                ( (TextView) view).setTextColor(Color.parseColor("#333333"));

            }
        });


        flfFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                Toast.makeText(getActivity(), dataList.get(position), Toast.LENGTH_SHORT).show();


                lastSelecteValue = dataList.get(position);

                return true;
            }
        });



    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
