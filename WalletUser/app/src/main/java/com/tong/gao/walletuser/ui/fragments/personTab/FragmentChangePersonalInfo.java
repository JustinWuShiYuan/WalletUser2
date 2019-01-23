package com.tong.gao.walletuser.ui.fragments.personTab;

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
import com.tong.gao.walletuser.bean.response.ResponseNormalBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.PreferenceHelper;

import org.greenrobot.eventbus.EventBus;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentChangePersonalInfo extends BaseFragment implements View.OnClickListener {


    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_my_id)
    TextView tvMyId;

    @BindView(R.id.rl_change_my_nick_name)
    RelativeLayout rlChangeNickName;
    @BindView(R.id.tv_my_nick_name)
    TextView tvMyNickName;
    @BindView(R.id.tv_my_user_name)
    TextView tvMyUserName;
    @BindView(R.id.tv_exit_login)
    TextView tvExitLogin;
    Unbinder unbinder;


    PersonalBean personalBean;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        tvTitleBarTitle2.setText("个人信息");
        rlChangeNickName.setOnClickListener(this);
        tvExitLogin.setOnClickListener(this);
        initData();

        return rootView;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_change_person_info, container, false);

        return view;
    }

    public void initData() {
        Object object = PreferenceHelper.getInstance().getObject(MyConstant.personalBeanKey, null);
        if (null != object) {
            personalBean = (PersonalBean) object;
            updateUI();
        }
    }

    private void updateUI() {
        tvMyId.setText(personalBean.getUserid());
        tvMyNickName.setText(personalBean.getNickname());
        tvMyUserName.setText(personalBean.getUsername());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



    @Override
    public void onClick(View v) {
        //todo 退出登录


        switch (v.getId()) {


            case R.id.rl_change_my_nick_name:

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_fragmentChangePersonalInfo_to_fragmentChangeNickName);


                break;


            case R.id.tv_exit_login:

                NetWorks.exitLogin(new Observer<ResponseNormalBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseNormalBean responseNormalBean) {

                        if (null != responseNormalBean && MyConstant.resultCodeIsOK.equals(responseNormalBean.getErrcode())) {

                            EventBus.getDefault().post(new ExitLoginEvent());

                            PreferenceHelper.getInstance().putObject(MyConstant.personalBeanKey, null);

                            NavHostFragment.findNavController(FragmentChangePersonalInfo.this)
                                    .navigate(R.id.action_fragmentChangePersonalInfo_to_myInfoFragment);

                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


                break;


        }


    }


}
