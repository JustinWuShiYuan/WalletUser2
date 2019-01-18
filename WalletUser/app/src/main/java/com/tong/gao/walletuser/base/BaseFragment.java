package com.tong.gao.walletuser.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public abstract class BaseFragment extends Fragment {


    public FragmentActivity mActivity; // 把fragment绑定到哪个Activity上, 上下文对象就是那个Activity.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return initView(inflater,container);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 子类必须实现此方法, 返回一个View对象, 作为当前Fragment的布局来展示.
     * @return
     */
    public abstract View initView(LayoutInflater inflater,ViewGroup container);


    public abstract void initData();
}
