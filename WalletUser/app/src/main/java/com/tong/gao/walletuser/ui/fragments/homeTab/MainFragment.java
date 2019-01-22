package com.tong.gao.walletuser.ui.fragments.homeTab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.event.MessageEvent;
import com.tong.gao.walletuser.ui.view.NoScrollViewPager;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    @BindView(R.id.rb_fragment_home)
    RadioButton rbFragmentHome;
    @BindView(R.id.rb_fragment_trade)
    RadioButton rbFragmentTrade;
    @BindView(R.id.rb_fragment_message)
    RadioButton rbFragmentMessage;
    @BindView(R.id.rb_fragment_myInfo)
    RadioButton rbFragmentMyInfo;

    @BindView(R.id.rg_fragment_container)
    RadioGroup rgFragmentContainer;
    Unbinder unbinder;

    @BindView(R.id.vp_container)
    NoScrollViewPager vpContainer;

    @BindView(R.id.rl_scan_guide)
    RelativeLayout rlScanGuide;
    @BindView(R.id.btn_next_scan)
    ImageView btnNextScan;


    @BindView(R.id.rl_buy_coin_guide)
    RelativeLayout rlBuyCoinGuide;
    @BindView(R.id.btn_next_buy_coin)
    ImageView btnNextBuyCoin;


    @BindView(R.id.rl_my_order_guide)
    RelativeLayout rlMyOrderGuide;
    @BindView(R.id.btn_i_know)
    ImageView btnIKnow;


    @BindView(R.id.rl_sale_coin_guide)
    RelativeLayout rlSaleCoinGuide;
    @BindView(R.id.btn_next_sell_coin)
    ImageView btnNextSellCoin;


    private View rootView;

    private List<Fragment> pagerList = null;



    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        fixBottomIcon();

        btnNextScan.setOnClickListener(this);
        btnNextBuyCoin.setOnClickListener(this);
        btnIKnow.setOnClickListener(this);
        btnNextSellCoin.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void initData() {
        if (null == pagerList) {
            pagerList = new ArrayList<>();
            pagerList.add(new HomeFragment());
            pagerList.add(new TradeFragment());
            pagerList.add(new MessageFragment());
            pagerList.add(new MyInfoFragment());
        }


        FragmentPagerAdapter fragmentPagerAdapter = new FragmentPagerAdapter(getActivity().getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return pagerList.get(position);
            }

            @Override
            public int getCount() {
                return pagerList.size();
            }
        };

        vpContainer.setAdapter(fragmentPagerAdapter);
        vpContainer.setOffscreenPageLimit(4);

        rgFragmentContainer.check(R.id.rb_fragment_home); // 设置默认选中的是home页签
//        ((HomeFragment)pagerList.get(0)).initData();
        rgFragmentContainer.setOnCheckedChangeListener(this);

    }

    private void fixBottomIcon() {
        //定义底部标签图片大小和位置
        Drawable drawable_news = getResources().getDrawable(R.drawable.tab_home_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rbFragmentHome.setCompoundDrawables(null, drawable_news, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_live = getResources().getDrawable(R.drawable.tab_trade_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_live.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rbFragmentTrade.setCompoundDrawables(null, drawable_live, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_tuijian = getResources().getDrawable(R.drawable.tab_message_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_tuijian.setBounds(0, 0, UIUtils.dip2px(25), UIUtils.dip2px(25));
        //设置图片在文字的哪个方向
        rbFragmentMessage.setCompoundDrawables(null, drawable_tuijian, null, null);

        //定义底部标签图片大小和位置
        Drawable drawable_me = getResources().getDrawable(R.drawable.tab_myinfo_bg);
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_me.setBounds(0, 0, UIUtils.dip2px(30), UIUtils.dip2px(30));
        //设置图片在文字的哪个方向
        rbFragmentMyInfo.setCompoundDrawables(null, drawable_me, null, null);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        EventBus.getDefault().register(this);
        initView(inflater,container);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        int pageIndex = -1;

        switch (checkedId) {

            case R.id.rb_fragment_home:
                pageIndex = 0;
                break;

            case R.id.rb_fragment_trade:
                pageIndex = 1;

                break;

            case R.id.rb_fragment_message:
                pageIndex = 2;
                break;

            case R.id.rb_fragment_myInfo:
                pageIndex = 3;
                break;

        }
        vpContainer.setCurrentItem(pageIndex);

        if(pageIndex != 2){
            ((BaseFragment)pagerList.get(pageIndex)).initData();
        }

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage(MessageEvent event) {
        rgFragmentContainer.check(R.id.rb_fragment_message);
    }



    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.btn_next_scan:

                rlScanGuide.setVisibility(View.GONE);
                rlBuyCoinGuide.setVisibility(View.VISIBLE);

                break;


            case R.id.btn_next_buy_coin:

                rlBuyCoinGuide.setVisibility(View.GONE);
                rlMyOrderGuide.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_i_know:

                rlMyOrderGuide.setVisibility(View.GONE);
                rlSaleCoinGuide.setVisibility(View.VISIBLE);

                break;


            case R.id.btn_next_sell_coin:

                rlSaleCoinGuide.setVisibility(View.GONE);

                break;

        }
    }
}
