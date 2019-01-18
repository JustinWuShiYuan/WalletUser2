package com.tong.gao.walletuser.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.ui.fragments.MainFragment;
import com.tong.gao.walletuser.utils.AppUtils;
import com.tong.gao.walletuser.utils.Density;

public class MainActivity extends ActivityBase {
    // 主界面fragment的tag
    private final String MAIN_CONTENT_FRAGMENT_TAG = "main_content";
    private long exitTime = 0;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
    }


    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    private void initFragment() {
        // 获取Fragment管理器对象
        FragmentManager fm = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(R.id.fl_main_content, new MainFragment(), MAIN_CONTENT_FRAGMENT_TAG);
        // 提交
        ft.commit();

//        connect(MyConstant.token);

    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
