package com.tong.gao.walletuser.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.factory.LoadingPagerFactory;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TransferRecordActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tab_layout_transfer_accord)
    TabLayout tabLayoutTransferAccord;
    @BindView(R.id.vp_my_transfer_accord)
    ViewPager vpMyTransferAccord;

    private String[]    tabTitles ={"全部","转入","转出"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transfer_record);
        ButterKnife.bind(this);
        initView();
        initData();
    }



    @Override
    protected int getLayout() {
        return R.layout.activity_transfer_record;
    }

    @Override
    protected void initView() {
        tvTitleBarTitle2.setText("转账记录");

        flBack.setOnClickListener(this);

        setTabTitles(tabTitles);
        MyTabAdapter adapter = new MyTabAdapter(getSupportFragmentManager());
        vpMyTransferAccord.setAdapter(adapter);

        //将TabLayout和ViewPager关联起来。
        tabLayoutTransferAccord.setupWithViewPager(vpMyTransferAccord);
        //设置可以滑动
        tabLayoutTransferAccord.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayoutTransferAccord.setSelectedTabIndicatorColor(Color.parseColor("#587BFC"));//设置选中时的指示器的颜色
        tabLayoutTransferAccord.setTabIndicatorFullWidth(true);


        vpMyTransferAccord.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int index) {
//                LogUtils.d("onPageSelected" + index );
                BaseFragment fragment = LoadingPagerFactory.getFragment(index);
                fragment.loadData();
            }
            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });


        tabLayoutTransferAccord.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if(tabLayoutTransferAccord.getSelectedTabPosition() == 0 ){
                    BaseFragment fragment = LoadingPagerFactory.getFragment(tabLayoutTransferAccord.getSelectedTabPosition());
                    fragment.loadData();

                    tabLayoutTransferAccord.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });


    }


    private void initData() {

    }

    @Override
    public void onClick(View v) {
        this.finish();
    }
}
