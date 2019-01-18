package com.tong.gao.walletuser.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestVerifyGoogleCodeBean;
import com.tong.gao.walletuser.bean.response.ResponseVerifyGoogleBean;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.AppUtils;
import com.tong.gao.walletuser.utils.Density;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class SecondVerifyActivity extends ActivityBase implements View.OnClickListener {
    @BindView(R.id.et_input_google_verify_code_second)
    EditText etInputGoogleVerifyCodeSecond;
    @BindView(R.id.tv_login_secondS)
    TextView tvLoginSecond;


    private String secondVerifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_verify);
        ButterKnife.bind(this);
        initView();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello(String event) {
        Toast.makeText(this, event, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_second_verify;
    }

    @Override
    protected void initView() {

        tvLoginSecond.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        secondVerifyCode = etInputGoogleVerifyCodeSecond.getText().toString();


        if (!StringUtils.isEmpty(secondVerifyCode)) {
            NetWorks.verifyGoogleCode(new RequestVerifyGoogleCodeBean(secondVerifyCode, "1"),
                    new Observer<ResponseVerifyGoogleBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            LogUtils.d("3333333333333333");
                        }

                        @Override
                        public void onNext(ResponseVerifyGoogleBean responseVerifyGoogleBean) {
                            LogUtils.d("二次校验成功:" + responseVerifyGoogleBean.toString());

                            //TODO 如果成功 回到首页，请求个人数据

                            SecondVerifyActivity.this.finish();
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.d("000000000000:" + e.toString());
                        }

                        @Override
                        public void onComplete() {
                            LogUtils.d("完成");
                        }
                    });

        } else {
            Toast.makeText(SecondVerifyActivity.this, "Google验证码不能为空", Toast.LENGTH_LONG).show();
        }

    }
}
