package com.tong.gao.walletuser.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.ui.view.NoScrollViewPager;
import com.tong.gao.walletuser.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainFragment extends BaseFragment  implements  RadioGroup.OnCheckedChangeListener {

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


    private  View rootView;

    private List<Fragment> pagerList = null;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {
        fixBottomIcon();

        return rootView;
    }

    @Override
    public void initData() {
        if(null == pagerList){
            pagerList = new ArrayList<>();
            pagerList.add(new HomeFragment());
//            pagerList.add(new HomeFragment());
        }

//        pagerList.add(new HomeFragment());
//        pagerList.add(new HomeFragment());

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

        switch (checkedId){

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
    }
}
