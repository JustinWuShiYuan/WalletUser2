package com.tong.gao.walletuser.ui.activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestAddReceiptMoneyAccount;
import com.tong.gao.walletuser.bean.response.ResponseBaseBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class EditBankCardActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_input_bank_type)
    EditText etInputBankType;
    @BindView(R.id.et_input_bank_type_branch)
    EditText etInputBankTypeBranch;
    @BindView(R.id.et_input_bank_num)
    EditText etInputBankNum;
    @BindView(R.id.et_input_bank_num_again)
    EditText etInputBankNumAgain;
    @BindView(R.id.et_input_bank_pwd)
    EditText etInputBankPwd;

    @BindView(R.id.tv_next_step)
    TextView tvNextStep;


    private String paymentWay = MyConstant.paymentWayBank;
    private String userName,openAccountBank,openAccountBranchBank,bankNum,sureBankNum,cashPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_edit_bank_card;
    }

    @Override
    protected void initView() {

        tvTitleBarTitle2.setText("编辑银行卡");

        flBack.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                this.finish();

                break;

            case R.id.tv_next_step:

                userName = etInputName.getText().toString();
                openAccountBank = etInputBankType.getText().toString();
                openAccountBranchBank = etInputBankTypeBranch.getText().toString();
                bankNum = etInputBankNum.getText().toString();
                sureBankNum = etInputBankNumAgain.getText().toString();
                cashPwd = etInputBankPwd.getText().toString();


                if(StringUtils.isEmpty(userName) || StringUtils.isEmpty(openAccountBank) || StringUtils.isEmpty(openAccountBranchBank)
                        || StringUtils.isEmpty(bankNum) || StringUtils.isEmpty(sureBankNum) || StringUtils.isEmpty(cashPwd)){
                    ToastUtils.showNomalLongToast("信息不能有空");
                }else{
                    if(!bankNum .equals(sureBankNum)){
                        ToastUtils.showNomalLongToast("确认银行卡号和银行卡号不一致");
                    }else{
                        addAccount(new RequestAddReceiptMoneyAccount(paymentWay,userName,openAccountBank,openAccountBranchBank,bankNum,sureBankNum,cashPwd));
                    }
                }


                break;

        }

    }

    private void addAccount(RequestAddReceiptMoneyAccount receiptMoneyAccount) {

        NetWorks.addReceiptMoneyAccount(receiptMoneyAccount, new Observer<ResponseBaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseBaseBean responseBaseBean) {
                LogUtils.d("添加银行卡成功："+responseBaseBean.toString());
                if(null != responseBaseBean && MyConstant.resultCodeIsOK .equals(responseBaseBean.getErrcode())){
                    startActivity(new Intent(EditBankCardActivity.this,MyReceiptAccountListActivity.class));
                    EditBankCardActivity.this.finish();
                }else{
                    ToastUtils.showNomalLongToast("添加失败："+responseBaseBean.getMsg());
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
                ToastUtils.showNomalLongToast("添加失败："+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }
}
