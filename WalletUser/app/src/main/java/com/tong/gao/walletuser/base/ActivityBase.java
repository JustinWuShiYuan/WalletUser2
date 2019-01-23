package com.tong.gao.walletuser.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.factory.LoadingPagerFactory;
import com.tong.gao.walletuser.manager.AppActivityManager;
import com.tong.gao.walletuser.ui.view.WheelDialog;
import com.tong.gao.walletuser.utils.Density;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.ButterKnife;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;


public abstract class ActivityBase extends AppCompatActivity {

    protected Activity mActivity;

    private WheelDialog mWheelDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = this;
        AppActivityManager.getInstance().pushActivity(this);
        setOrientation();
        setContentView(getLayout());
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
        initView();
    }


    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        StatusBarUtil.setStatuBarAndFontColor(this, getResources().getColor(R.color.white), true);
    }

    @Override
    public void finish() {
        super.finish();
        AppActivityManager.getInstance().popActivity(this);
        overridePendingTransition(R.anim.fragment_slide_left_enter,
                R.anim.fragment_slide_right_exit);// default animation for
    }


    protected void setSysteStatuBarColor(int statusColor) {
        Window window = getWindow();
        //取消状态栏透明
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //添加Flag把状态栏设为可绘制模式
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(statusColor);
        }
        //设置系统状态栏处于可见状态
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
        //让view不根据系统窗口来调整自己的布局
        ViewGroup mContentView = (ViewGroup) window.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);
        if (mChildView != null) {
            ViewCompat.setFitsSystemWindows(mChildView, false);
            ViewCompat.requestApplyInsets(mChildView);
        }
    }



    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PreferenceHelper.getInstance().putBooleanValue(MyConstant.loginStatues,false);
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));
    }

    protected void enterActivityWithoutFinish(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.fragment_slide_right_enter,
                R.anim.fragment_slide_left_exit);
    }

    protected void jumpToActivity(Context context, Class cls) {
        Intent intent = new Intent(context, cls);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public void setOrientation() {
        Density.setDefault(mActivity);
    }

    protected abstract int getLayout();

    protected abstract void initView();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void hello ( String event){
        Toast.makeText( this , event , Toast.LENGTH_SHORT).show();
    }




    protected String[] tabTitles;

    public void setTabTitles(String[] tabTitles) {
        this.tabTitles = tabTitles;
    }

    public class MyTabAdapter extends FragmentPagerAdapter {

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return LoadingPagerFactory.getFragment(position);
        }

        @Override
        public int getCount() {
            return tabTitles.length;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (tabTitles != null) { return tabTitles[position]; }
            return super.getPageTitle(position);
        }
    }

    public void showProgressDialog(String message) {
        if (mWheelDialog == null) {
            mWheelDialog = new WheelDialog(this, R.style.WheelDialog);
        }
        if (mWheelDialog.isShowing()) {
            return;
        }
        if (TextUtils.isEmpty(message)) {
            mWheelDialog.setMessage("请稍后..");
        } else {
            mWheelDialog.setMessage(message);
        }
        mWheelDialog.show();
    }

    protected void hideProgressDialog() {
        if (mWheelDialog != null && mWheelDialog.isShowing()) {
            mWheelDialog.dismiss();
        }
    }


}
