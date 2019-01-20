package com.tong.gao.walletuser.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.request.RequestRegisterBean;
import com.tong.gao.walletuser.bean.response.ResponseRegisterBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.activity.LoginActivity;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentRegister extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.et_input_account)
    EditText etInputAccount;
    @BindView(R.id.et_input_account_pwd)
    EditText etInputAccountPwd;
    @BindView(R.id.et_input_account_pwd_again)
    EditText etInputAccountPwdAgain;

    @BindView(R.id.tv_skip_not_download)
    TextView tvRegister;
    @BindView(R.id.tv_goto_login)
    TextView tvGotoLogin;


    private String accountName,accountPwd,accountPwdAgain;

    Unbinder unbinder;
    View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView();
        return rootView;
    }

    private void initView() {
        tvTitleBarTitle.setVisibility(View.GONE);
        flBack.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvGotoLogin.setOnClickListener(this);

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
//                accountName,accountPwd,accountPwdAgain;
                break;

            case R.id.tv_skip_not_download:

                accountName = etInputAccount.getText().toString();
                accountPwd = etInputAccountPwd.getText().toString();
                accountPwdAgain = etInputAccountPwdAgain.getText().toString();

                if(StringUtils.isEmpty(accountName) || StringUtils.isEmpty(accountPwd)
                        || StringUtils.isEmpty(accountPwdAgain)){

                    ToastUtils.showNomalLongToast("账号和密码不能为空");

                }else if(accountPwd.equals(accountPwdAgain)){

                    //TODO 去注册
                    toRegister(new RequestRegisterBean(accountName,accountPwd,accountPwdAgain));


                }

                break;

            case R.id.tv_goto_login:

                startActivity(new Intent(getActivity(),LoginActivity.class));

                break;

        }
    }

    private void toRegister(RequestRegisterBean requestRegisterBean) {

        NetWorks.register(requestRegisterBean, new Observer<ResponseRegisterBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("注册订阅");
            }

            @Override
            public void onNext(ResponseRegisterBean responseRegisterBean) {
                LogUtils.d("注册:"+responseRegisterBean.toString());

//                NavController.navigate(int, bundle)


//                NavHostFragment.n
                Bundle bundle = new Bundle();
                bundle.putString(MyConstant.userId,responseRegisterBean.getUserid());

                Navigation.findNavController(rootView).navigate(R.id.fragmentRegisterSuccess,bundle);

//                NavHostFragment
//                        .findNavController(FragmentRegister.this)
//                        .navigate(R.id.action_fragmentRegister_to_fragmentRegisterSuccess);

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("注册 onComplete() ");
            }
        });
    }
}
