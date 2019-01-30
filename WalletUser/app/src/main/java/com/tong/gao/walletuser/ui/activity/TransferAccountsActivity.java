package com.tong.gao.walletuser.ui.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestTransferAccountBean;
import com.tong.gao.walletuser.bean.response.ResponseTransferAccountBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TransferAccountsActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.et_input_coin_address)
    EditText etInputCoinAddress;
    @BindView(R.id.et_input_sell_coin_num)
    EditText etInputCoinNum;
    @BindView(R.id.et_input_transfer_remark)
    EditText etInputTransferRemark;
    @BindView(R.id.tv_cancel_transfer)
    TextView tvCancelTransfer;
    @BindView(R.id.tv_sure_transfer)
    TextView tvSureTransfer;

    @BindView(R.id.tv_cancel_verify)
    TextView tvCancelVerify;
    @BindView(R.id.et_payment_pwd)
    EditText etPaymentPwd;
    @BindView(R.id.et_google_pwd)
    EditText etGooglePwd;
    @BindView(R.id.btn_sure_transfer)
    Button btnSureSafeCode;
    @BindView(R.id.ll_safe_root_view)
    LinearLayout llSafeRootView;
    @BindView(R.id.rl_safe_root_view)
    RelativeLayout rlSafeRootView;


    private String transferAccountAddress, transferAccountNum, transferRemark;
    private String paymentPwd,googleCode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_accounts);
        ButterKnife.bind(this);
        initView();
        Intent intent = getIntent();

        if(null != intent){
            transferAccountAddress  = intent.getStringExtra(MyConstant.transferAccountAddressKey);
            etInputCoinAddress.setText(transferAccountAddress);
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_accounts;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("转账AB");

        flBack.setOnClickListener(this);
        tvCancelTransfer.setOnClickListener(this);
        tvSureTransfer.setOnClickListener(this);
        tvCancelVerify.setOnClickListener(this);
        btnSureSafeCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fl_back:
                this.finish();
                break;

            case R.id.tv_cancel_transfer:
                this.finish();
                break;

            case R.id.tv_sure_transfer:

                rlSafeRootView.setVisibility(View.VISIBLE);
                int flSafeRootViewHeight = llSafeRootView.getMeasuredHeight();
                int height = llSafeRootView.getHeight();
                LogUtils.d("flSafeRootViewHeight:"+flSafeRootViewHeight+" height:"+height);
                ObjectAnimator translation = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 532, 0);
                translation.setDuration(800);
                translation.start();
                llSafeRootView.setVisibility(View.VISIBLE);

                break;


            case R.id.tv_cancel_verify:
                int height1 = llSafeRootView.getHeight();
                ObjectAnimator translation1 = ObjectAnimator.ofFloat(llSafeRootView, "translationY", 0, height1);
                translation1.setDuration(500);
                translation1.start();
                translation1.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        rlSafeRootView.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {
                    }
                });
                break;


            case R.id.btn_sure_transfer:        //确认转账

                transferAccountAddress = etInputCoinAddress.getText().toString();
                transferAccountNum = etInputCoinNum.getText().toString();
                transferRemark = etInputTransferRemark.getText().toString();
                paymentPwd = etPaymentPwd.getText().toString();
                googleCode = etGooglePwd.getText().toString();

                if(StringUtils.isEmpty(transferAccountAddress) || StringUtils.isEmpty(transferAccountNum) ||
                        StringUtils.isEmpty(transferRemark) || StringUtils.isEmpty(paymentPwd) ||
                        StringUtils.isEmpty(googleCode)){
                    ToastUtils.showNomalLongToast("信息不能有空");
                }else{
                    startTransfer();
                }


                break;



        }
    }

    private void startTransfer() {

        NetWorks.transferAccount(new RequestTransferAccountBean(transferAccountAddress, transferAccountNum, transferRemark
                , paymentPwd, googleCode), new Observer<ResponseTransferAccountBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("开始订阅");
            }

            @Override
            public void onNext(ResponseTransferAccountBean responseTransferAccountBean) {

                if(null != responseTransferAccountBean && MyConstant.resultCodeIsOK .equals(responseTransferAccountBean.getErrcode())){
                    startActivity(new Intent(TransferAccountsActivity.this,TransferAccountSuccessActivity.class));
                    finish();
                }else{
                    LogUtils.d("转账失败");
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(":"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });
    }
}
