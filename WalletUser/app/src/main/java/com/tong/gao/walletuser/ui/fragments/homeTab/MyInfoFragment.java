package com.tong.gao.walletuser.ui.fragments.homeTab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoFragment extends BaseFragment {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.civ_user_head_src)
    CircleImageView civUserHeadSrc;
    @BindView(R.id.rl_my_info)
    RelativeLayout rlMyInfo;
    @BindView(R.id.rl_my_property)
    RelativeLayout rlMyProperty;
    @BindView(R.id.rl_my_account)
    RelativeLayout rlMyAccount;
    @BindView(R.id.rl_my_orders)
    RelativeLayout rlMyOrders;
    @BindView(R.id.rl_my_advertising)
    RelativeLayout rlMyAdvertising;
    @BindView(R.id.rl_identification_info)
    RelativeLayout rlIdentificationInfo;
    @BindView(R.id.rl_safe_setting)
    RelativeLayout rlSafeSetting;
    @BindView(R.id.rl_about_us)
    RelativeLayout rlAboutUs;
    Unbinder unbinder;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_my_info, container, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
