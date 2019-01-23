package com.tong.gao.walletuser.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.event.MessageEvent;
import com.tong.gao.walletuser.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public abstract class BaseFragment extends Fragment {


    public FragmentActivity mActivity; // 把fragment绑定到哪个Activity上, 上下文对象就是那个Activity.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage (MessageEvent event){
    }
}
