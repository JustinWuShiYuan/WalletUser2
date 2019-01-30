package com.tong.gao.walletuser.ui.activity.saleCoin;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.factory.LoadingPagerFactory;
import com.tong.gao.walletuser.ui.loading.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MySaleCoinListActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tab_layout_sale_coin_list)
    TabLayout tabSaleCoin;

    @BindView(R.id.vp_sale_coin)
    ViewPager vpSaleCoin;
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;

    private String[] tabTitles = {"全部", "出售中", "售罄", "已下架"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_sale_coin_list;
    }

    @Override
    protected void initView() {

        tvTitleBarTitle2.setText("我的广告");
        flBack.setOnClickListener(this);

        MyTabAdapter adapter = new MyTabAdapter(getSupportFragmentManager());
        vpSaleCoin.setAdapter(adapter);


        //将TabLayout和ViewPager关联起来。
        tabSaleCoin.setupWithViewPager(vpSaleCoin);
        //设置可以滑动
        tabSaleCoin.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabSaleCoin.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
        tabSaleCoin.setTabIndicatorFullWidth(true);


        vpSaleCoin.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int index) {
                BaseFragment fragment = LoadingPagerFactory.getFragmentSaleCoinList(index);
                fragment.loadData();
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        tabSaleCoin.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(tabSaleCoin.getSelectedTabPosition() == 0 ){
                    BaseFragment fragment = LoadingPagerFactory.getFragmentSaleCoinList(tabSaleCoin.getSelectedTabPosition());
                    fragment.loadData();

                    tabSaleCoin.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:
                this.finish();
                break;

        }
    }



    public class MyTabAdapter extends FragmentPagerAdapter {

        public MyTabAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return LoadingPagerFactory.getFragmentSaleCoinList(position);
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
}
