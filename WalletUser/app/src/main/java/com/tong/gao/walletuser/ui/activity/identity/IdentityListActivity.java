package com.tong.gao.walletuser.ui.activity.identity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class IdentityListActivity extends ActivityBase {
    @Override
    protected int getLayout() {
        return R.layout.activity_identity_list;
    }

    @Override
    protected void initView() {

    }


    @BindView(R.id.iv_right_image)
    public ImageView iv_right_image;

    @BindView(R.id.iv_right_image2)
    public ImageView iv_right_image2;

    @BindView(R.id.tv_statu)
    public TextView tv_statu;

    @BindView(R.id.tv_statu_hight)
    public TextView tv_statu_hight;

    @BindView(R.id.tv_title_bar_title2)
    TextView title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRes();
        title.setText(getTitle());
    }

    private void initRes() {
        if (false) {//用户已经认证成功

            tv_statu.setText("认证成功");
            Drawable drawable = getResources().getDrawable(R.drawable.icon_succ_3);// 找到资源图片
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
            tv_statu.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
            iv_right_image.setVisibility(View.GONE);
        } else if (true) {


        } else if ("审核中" != "审核中") {
            tv_statu.setText("审核中");
            tv_statu.setTextColor(getResources().getColor(R.color.c_text6));
            Drawable drawable = getResources().getDrawable(R.drawable.icon_unfinish_3);// 找到资源图片
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
            tv_statu.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
            iv_right_image.setVisibility(View.GONE);
        }

        if (false) {

            tv_statu_hight.setText("认证成功");
            Drawable drawable = getResources().getDrawable(R.drawable.icon_succ_3);// 找到资源图片
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
            tv_statu_hight.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
            iv_right_image2.setVisibility(View.GONE);
        }
        else if (true) {


        } else if ("审核中" != "审核中") {
            tv_statu_hight.setText("审核中");
            tv_statu_hight.setTextColor(getResources().getColor(R.color.c_text8));
            Drawable drawable = getResources().getDrawable(R.drawable.icon_unfinish_3);// 找到资源图片
            // 这一步必须要做，否则不会显示。
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置图片宽高
            tv_statu_hight.setCompoundDrawables(drawable, null, null, null);// 设置到控件中
            iv_right_image2.setVisibility(View.GONE);
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


    @OnClick(R.id.cr_auth)
    public void onClickAuth() {
        startActivity(new Intent(this, IdentityInfoActivity.class));
        Log.d("px","asdasdad");
    }

    @OnClick(R.id.cr_auth_hight)
    public void onClickHightAuth() {
        Log.d("px","gaoji");
    }

    @OnClick(R.id.fl_back)
    public void onBackPressed() {
        super.onBackPressed();
    }
}
