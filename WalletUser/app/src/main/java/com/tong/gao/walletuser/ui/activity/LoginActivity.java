package com.tong.gao.walletuser.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.netease.nis.captcha.Captcha;
import com.netease.nis.captcha.CaptchaConfiguration;
import com.netease.nis.captcha.CaptchaListener;
import com.tong.gao.walletuser.AppApplication;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.event.StartLoadDataEvent;
import com.tong.gao.walletuser.bean.request.RequestLoginInfoBean;
import com.tong.gao.walletuser.bean.response.ResponseLoginInfo;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tong.gao.walletuser.constants.MyConstant.auroraPushKey;

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
    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_input_account_pwd)
    TextView tvInputAccountPwd;
    @BindView(R.id.tv_forget_pwd)
    TextView tvForgetPwd;
    @BindView(R.id.tv_have_no_account)
    TextView tvHaveNoAccount;

    private String loginName, loginPwd;
    private CaptchaListener captchaListener;
    private String noSenseCaptchaId = "af45977d53044827af6ee8968a3d550e";

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
        tvTitleBarTitle.setVisibility(View.GONE);

        tvFindPwd.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvGotoRegister.setOnClickListener(this);
        tvFindPwd.setOnClickListener(this);
        flBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fl_back:

                finish();

                break;

            case R.id.tv_find_pwd:


                startActivity(new Intent(this,FindPwdActivity.class));


                break;

            case R.id.tv_login:

                loginName = etInputAccount.getText().toString();
                loginPwd = etInputAccountPwd.getText().toString();

                if (StringUtils.isEmpty(loginName) || StringUtils.isEmpty(loginPwd)) {
                    ToastUtils.showNomalLongToast("账号和密码不能为空");

                } else {
                    goToLogin(new RequestLoginInfoBean(loginName, loginPwd,auroraPushKey));
                }


                break;

            case R.id.tv_goto_register:

                startActivity(new Intent(this,RegistActivity.class));
                finish();


                break;

        }
    }

    private void goToLogin(final RequestLoginInfoBean requestLoginInfoBean) {

        //易盾验证码 验证
        captchaListener = new CaptchaListener() {
            @Override
            public void onReady() {

            }

            @Override
            public void onValidate(String result, String validate, String msg) {
                if (!TextUtils.isEmpty(validate)) {
                    ToastUtils.showNomalLongToast("验证成功");
                    startLogin(requestLoginInfoBean);
                } else {
                    ToastUtils.showNomalLongToast("验证失败");
                }
            }

            @Override
            public void onError(String msg) {
                ToastUtils.showNomalLongToast("验证出错");
            }

            @Override
            public void onCancel() {

            }
        };


        // 创建构建验证码的配置类，可配置详细选项请参看上面SDK接口 验证码属性配置类：CaptchaConfiguration
        final CaptchaConfiguration configuration = new CaptchaConfiguration.Builder()
                .captchaId(noSenseCaptchaId)// 验证码业务id
                .mode(CaptchaConfiguration.ModeType.MODE_INTELLIGENT_NO_SENSE)  // 验证码类型，默认为常规验证码（滑块拼图、图中点选、短信上行），如果要使用智能无感知请设置该类型，否则无需设置
                .listener(captchaListener) //设置验证码回调监听器
                .build(contextActivity); // Context，请使用Activity实例的Context
        // 初始化验证码
        final Captcha captcha = Captcha.getInstance().init(configuration);
        captcha.validate();

    }

    /**开始登录*/
    private void startLogin(RequestLoginInfoBean requestLoginInfoBean) {

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                showProgressDialog("");
            }
        });


        NetWorks.login(requestLoginInfoBean, new Observer<ResponseLoginInfo>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("开始登录。。。。。");
            }

            @Override
            public void onNext(ResponseLoginInfo responseLoginInfo) {
                LogUtils.d("responseLoginInfo:" + responseLoginInfo.toString());


                if(null != responseLoginInfo.getUserinfo() && MyConstant.resultCodeIsOK .equals(responseLoginInfo.getErrcode()) ){

                    PreferenceHelper.getInstance().putStringValue(MyConstant.loginStatuesFlag,"true");

                    if (responseLoginInfo.getUserinfo().getSafeverifyswitch().equals(MyConstant.googleVerifyIsOpened)) {//开了谷歌验证
//                        .equals(MyConstant.googleVerifyIsClosed)) {//开了谷歌验证
                        Intent intent = new Intent(LoginActivity.this, SecondVerifyActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        finish();
                    }
                    EventBus.getDefault().post(new StartLoadDataEvent());

                }else{
                    LogUtils.d("responseLoginInfo:1111111111111111111111111" );
                }


            }

            @Override
            public void onError(Throwable e) {
                hideProgressDialog();
                PreferenceHelper.getInstance().putBooleanValue(MyConstant.loginStatues,false);
                ToastUtils.showNomalLongToast("登录失败");
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
                hideProgressDialog();
            }
        });
    }
}
