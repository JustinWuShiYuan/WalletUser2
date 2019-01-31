package com.tong.gao.walletuser.ui.fragments.homeTab;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.bean.PersonalBean;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.event.ExitLoginEvent;
import com.tong.gao.walletuser.bean.event.StartLoadDataEvent;
import com.tong.gao.walletuser.bean.response.ResponsePersonalBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.activity.LoginActivity;
import com.tong.gao.walletuser.ui.activity.MyOrderListActivity;
import com.tong.gao.walletuser.ui.activity.MyReceiptAccountListActivity;
import com.tong.gao.walletuser.ui.activity.saleCoin.MySaleCoinListActivity;
import com.tong.gao.walletuser.ui.activity.saleCoin.PublishCoinActivity;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MyInfoFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.tv_first_to_login)
    TextView tvFirstToLogin;

    @BindView(R.id.rl_my_info)
    RelativeLayout rlMyInfo;
    @BindView(R.id.civ_user_head_src)
    CircleImageView civUserHeadSrc;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_id)
    TextView tvUserId;


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

        NetWorks.queryPersonalInfo(new Observer<ResponsePersonalBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("查询个人信息");
            }

            @Override
            public void onNext(ResponsePersonalBean responsePersonalBean) {

                if (null != responsePersonalBean && MyConstant.resultCodeIsOK.equals(responsePersonalBean.getErrcode())) {
                    PersonalBean personalBean = responsePersonalBean.getUserinfo();
                    updateUI(personalBean);
                    PreferenceHelper.getInstance().putObject(MyConstant.personalBeanKey,personalBean);
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("查询个人信息" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });


    }

    private void updateUI(final PersonalBean personalBean) {

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                LogUtils.d("null != tvUserName:"+(null != tvUserName));
                if(null != personalBean && null != tvUserName){
                    tvUserName.setText(personalBean.getNickname());
                    tvUserId.setText("ID:" + personalBean.getUserid());
                }


            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        tvTitleBarTitle.setText("我的");
        tvFirstToLogin.setOnClickListener(this);

        rlMyInfo.setOnClickListener(this);

        rlMyProperty.setOnClickListener(this);
        rlMyAccount.setOnClickListener(this);
        rlMyOrders.setOnClickListener(this);
        rlMyAdvertising.setOnClickListener(this);
        rlIdentificationInfo.setOnClickListener(this);
        rlSafeSetting.setOnClickListener(this);
        rlAboutUs.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onResume() {
        super.onResume();

        if (PreferenceHelper.getInstance().getStringShareData(MyConstant.loginStatuesFlag, "false").equals("true")) {
            rlMyInfo.setVisibility(View.VISIBLE);
            tvFirstToLogin.setVisibility(View.GONE);
            initData();
        }

    }




    //    @BindView(R.id.rl_my_property)
//    RelativeLayout rlMyProperty;
//    @BindView(R.id.rl_my_account)
//    RelativeLayout rlMyAccount;
//    @BindView(R.id.rl_my_orders)
//    RelativeLayout rlMyOrders;
//    @BindView(R.id.rl_my_advertising)
//    RelativeLayout rlMyAdvertising;
//    @BindView(R.id.rl_identification_info)
//    RelativeLayout rlIdentificationInfo;
//    @BindView(R.id.rl_safe_setting)
//    RelativeLayout rlSafeSetting;
//    @BindView(R.id.rl_about_us)
//    RelativeLayout rlAboutUs;

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_first_to_login:

                startActivity(new Intent(getActivity(), LoginActivity.class));

                break;

            case R.id.rl_my_info:   //修改个人信息

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_myInfoFragment_to_fragmentChangePersonalInfo);

                break;

            case R.id.rl_my_property:   //

                LogUtils.d("hadLogin():"+hadLogin());
                if(hadLogin()){
                    NavHostFragment.findNavController(this)
                            .navigate(R.id.action_myInfoFragment_to_fragmentMyAssert);

                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_my_account:   //

                if(hadLogin()){
                    startActivity(new Intent(getActivity(), MyReceiptAccountListActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_my_orders:   //

                if(hadLogin()){
                    startActivity(new Intent(getActivity(),MyOrderListActivity.class));
                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_my_advertising:   //

                if(hadLogin()){
                    startActivity(new Intent(getActivity(),MySaleCoinListActivity.class));

                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }

                break;

            case R.id.rl_identification_info:   //

                if(hadLogin()){

                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_safe_setting:   //

                if(hadLogin()){

                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

            case R.id.rl_about_us:   //

                if(hadLogin()){

                }else{
                    startActivity(new Intent(getActivity(), LoginActivity.class));
                }


                break;

        }
    }

    private boolean hadLogin() {
        String aFalse = PreferenceHelper.getInstance().getStringShareData(MyConstant.loginStatuesFlag, "false");

        return aFalse.equals("true");
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventStartLoadData(StartLoadDataEvent event){
        initData();
    }
}
