package com.tong.gao.walletuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestLoginInfoBean;
import com.tong.gao.walletuser.bean.response.ResponseLoginInfo;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.et_input_account)
    EditText etInputAccount;
    @BindView(R.id.et_input_account_pwd)
    EditText etInputAccountPwd;
    @BindView(R.id.tv_find_pwd)
    TextView tvFindPwd;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_goto_register)
    TextView tvGotoRegister;

    private String loginName,loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        tvFindPwd.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvGotoRegister.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_find_pwd:


                break;

            case R.id.tv_login:

                loginName = etInputAccount.getText().toString();
                loginPwd = etInputAccountPwd.getText().toString();

                if(StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPwd)){
                    ToastUtils.showNomalLongToast("账号和密码不能为空");

                }else{
                    goToLogin(new RequestLoginInfoBean(loginName,loginPwd));
                }


                break;

            case R.id.tv_goto_register:


                break;

        }
    }

    private void goToLogin(RequestLoginInfoBean requestLoginInfoBean) {

        NetWorks.login(requestLoginInfoBean, new Observer<ResponseLoginInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("开始登录。。。。。");
            }

            @Override
            public void onNext(ResponseLoginInfo responseLoginInfo) {
                LogUtils.d("responseLoginInfo:"+responseLoginInfo.toString());


                if (null != responseLoginInfo.getUserinfo() && responseLoginInfo.getUserinfo().getSafeverifyswitch()
                        .equals(MyConstant.googleVerifyIsOpened)) {//开了谷歌验证
//                        .equals(MyConstant.googleVerifyIsClosed)) {//开了谷歌验证

                    Intent intent = new Intent(LoginActivity.this, SecondVerifyActivity.class);
                    startActivity(intent);
                    finish();


                } else {

                    finish();
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });
    }
}
