package com.tong.gao.walletuser.ui.fragments.personTab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.event.MessageEvent;
import com.tong.gao.walletuser.bean.event.StartLoadDataEvent;
import com.tong.gao.walletuser.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class FragmentMyPersonal extends BaseFragment {


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_personal,container,false);
    }

    @Override
    public void initData() {
        LogUtils.d("加载数据.....");
        EventBus.getDefault().post(new StartLoadDataEvent());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage (MessageEvent event){
    }
}
