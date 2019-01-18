package com.tong.gao.walletuser.ui.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.factory.ThreadPoolFactory;
import com.tong.gao.walletuser.ui.adaper.BannerPageAdapter;
import com.tong.gao.walletuser.utils.AppUtils;
import com.tong.gao.walletuser.utils.UIUtils;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomeADPageView extends LinearLayout implements ViewPager.OnPageChangeListener {

    private Context mContext;

    private ViewPager viewPager;

    private ImageView[] points;

    private BannerPageAdapter pagerAdapter;

    private LinearLayout ll_points;

    private FragmentManager fragmentManager;

    // 自动轮播启用开关
    private boolean isAutoPlay = false;
    /** 当前页数 */
    private int currentPage;

    private int screenWidth;

    private List<ImageView> views;

    public HomeADPageView(Context context) {
        this(context, null);
    }

    public HomeADPageView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HomeADPageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        initScreenInfo();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_show_header, this, true);

        viewPager = (ViewPager) findViewById(R.id.pager);
        viewPager.addOnPageChangeListener(this);
        ll_points = (LinearLayout) findViewById(R.id.ll_points);


        requestBannerData();

    }

    List<Drawable> bannerData;
    private void requestBannerData() {
        //TODO 模拟请求网络数据获取 图片url 列表

        if(null == bannerData){
            bannerData = new ArrayList<>();
        }
        for(int i = 0;i<4;i++){
            if(i %2 == 0){
                bannerData.add(mContext.getDrawable(R.drawable.banner));
            }else{
                bannerData.add(mContext.getDrawable(R.drawable.home_top_img));
            }

        }

        viewPager.setAdapter(new BannerPageAdapter(mContext, bannerData));

        addPoints(bannerData.size());

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private int viewPagerIndex = 0;

    public void startAutoPlay(){

        ThreadPoolFactory.getScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {

                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewPagerIndex++;

                        viewPager.setCurrentItem(viewPagerIndex % bannerData.size());
                    }
                });



            }
        },1,4,TimeUnit.SECONDS);
    }


    @Override
    public void onPageSelected(int position) {
        currentPage = position;
        if (null != points) {
            int posi = position % points.length;
            setImageBackground(posi);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setImageBackground(int selectItems) {
        if (points != null) {
            for (int i = 0; i < points.length; i++) {
                if (i == selectItems) {
                    points[i].setBackgroundResource(R.drawable.page_dot_focused);
                } else {
                    points[i].setBackgroundResource(R.drawable.page_dot_un_focused);
                }
            }
        }
    }

    private void addPoints(int size) {
        ll_points.removeAllViews();
        points = new ImageView[size];
        for (int i = 0; i < size; i++) {
            points[i] = new ImageView(mContext);
            LayoutParams layoutParams = new LayoutParams(new ViewGroup.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = AppUtils.dp2px(mContext, 3);
            layoutParams.rightMargin = AppUtils.dp2px(mContext, 3);
            if (i == 0) {
                points[i].setBackgroundResource(R.drawable.page_dot_focused);
            } else {
                points[i].setBackgroundResource(R.drawable.page_dot_un_focused);
            }
            ll_points.addView(points[i], layoutParams);
        }
    }

    private void initScreenInfo() {
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        screenWidth = outMetrics.widthPixels;
    }
}
