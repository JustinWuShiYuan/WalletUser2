package com.tong.gao.walletuser.ui.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.factory.LoadingPagerFactory;
import com.tong.gao.walletuser.ui.fragments.FragmentMyOrderList;
import com.tong.gao.walletuser.ui.loading.BaseFragment;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyOrderListActivity extends ActivityBase implements View.OnClickListener {
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tab_layout_my_order)
    TabLayout tabLayoutMyOrder;
    @BindView(R.id.vp_my_order)
    ViewPager vpMyOrder;

    private String[]    tabTitles ={"全部","未付款","待放行","申诉中","已取消","已完成"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }


    @Override
    protected int getLayout() {
        return R.layout.activity_my_order_list;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2 = findViewById(R.id.tv_title_bar_title2);
        tvTitleBarTitle2.setText("我的订单");
        flBack = findViewById(R.id.fl_back);
        flBack.setOnClickListener(this);

        setTabTitles(tabTitles);
        tabLayoutMyOrder = findViewById(R.id.tab_layout_my_order);
        vpMyOrder = findViewById(R.id.vp_my_order);


        MyTabAdapter adapter = new MyTabAdapter(getSupportFragmentManager(),MyConstant.myOrderListKey);
        //给ViewPager设置适配器
        vpMyOrder.setAdapter(adapter);
        //将TabLayout和ViewPager关联起来。
        tabLayoutMyOrder.setupWithViewPager(vpMyOrder);
        //设置可以滑动
        tabLayoutMyOrder.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutMyOrder.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色


        vpMyOrder.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int index) {
                BaseFragment fragment = LoadingPagerFactory.getFragmentMyOrderList(index);
                fragment.loadData();
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        tabLayoutMyOrder.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(tabLayoutMyOrder.getSelectedTabPosition() == 0 ){
                    BaseFragment fragment = LoadingPagerFactory.getFragmentMyOrderList(tabLayoutMyOrder.getSelectedTabPosition());
                    fragment.loadData();

                    tabLayoutMyOrder.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.tv_title_bar_title2:

                break;


            case R.id.fl_back:

                this.finish();

                break;



        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
