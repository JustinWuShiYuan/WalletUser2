package com.tong.gao.walletuser.ui.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;

public class WelcomeActivity extends ActivityBase {


    @Override
    protected int getLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                enterMain();
            }
        }, 2000);

    }


    public void enterMain() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(mActivity, MainActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        });
    }
}
