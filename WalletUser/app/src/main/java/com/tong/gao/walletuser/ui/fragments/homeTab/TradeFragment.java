package com.tong.gao.walletuser.ui.fragments.homeTab;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.event.MessageEvent;
import com.tong.gao.walletuser.bean.request.RequestQueryBuyCoinBean;
import com.tong.gao.walletuser.bean.response.ResponseQueryBuyCoinBean;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.fragments.FragmentCashFastSelect;
import com.tong.gao.walletuser.ui.fragments.FragmentSetCashRange;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class TradeFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.rl_all)
    RelativeLayout rlAllBuyCoinType;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    @BindView(R.id.tv_all_buy_coin_type)
    TextView tvAllBuyCoinType;
    @BindView(R.id.rv_my_trade)
    RecyclerView rvMyTrade;
    @BindView(R.id.srl_refresh_my_trade)
    SwipeRefreshLayout srlRefreshMyTrade;
    @BindView(R.id.ll_select_type_view)
    LinearLayout llSelectTypeView;
    @BindView(R.id.rl_select_type_container)
    RelativeLayout rlSelectTypeContainer;

    @BindView(R.id.tv_payment_type)
    TextView tvPaymentType;
    @BindView(R.id.rb_all)
    RadioButton rbAll;
    @BindView(R.id.rb_bank)
    RadioButton rbBank;
    @BindView(R.id.rb_zhifubao)
    RadioButton rbZhifubao;
    @BindView(R.id.rb_wechat)
    RadioButton rbWechat;
    @BindView(R.id.rb_cash_fast_selected)
    RadioButton rbCashFastSelected;
    @BindView(R.id.rb_set_selected_cash_round)
    RadioButton rbSetSelectedCashRound;
    @BindView(R.id.rg_selected_cash_container)
    RadioGroup rgSelectedCashContainer;
    @BindView(R.id.fl_cash_fragment)
    FrameLayout flCashFragment;
    @BindView(R.id.btn_confirm)
    Button btnConfirm;


    private ArrayList<String>  cashList = new ArrayList<>();
    private float maxCashValue =100;

    private FragmentCashFastSelect fragmentCashFastSelect;
    private FragmentSetCashRange fragmentSetCashRange;


    Unbinder unbinder;

    private final String Fragment_Tag_Fixed = "Fragment_Tag_Fixed";     //固定金额的tag
    private final String Fragment_Tag_Can_Selected = "Fragment_Tag_Can_Selected";//设置选择金额范围的tag.
    private boolean fragmentSelectedIndex = true;

    private String pageNum = "1";
    private String pageSize = "12";
    private String pageWay = "1";
    private String type = "1";
    private String price ="100";
    private String limitMaxPrice ="10000" ;
    private String limitMinPrice ="1";


    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_trade, container, false);
        unbinder = ButterKnife.bind(this, rootView);
        initView(inflater,container);
        return rootView;
    }

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        tvTitleBarTitle.setText("买币");
        flBack.setVisibility(View.GONE);

        rlAllBuyCoinType.setOnClickListener(this);
        rbCashFastSelected.setOnClickListener(this);
        rbSetSelectedCashRound.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);

        return rootView;
    }


    public void initData() {
        //todo 模拟 从网络获取了 那些固定的金额数据集合
        for(int i =100;i<110;i++){
            cashList.add(""+i);
        }
        maxCashValue = 4000;

        //TODO  启动默认 会加载2个fragment 需要处理。

        RequestQueryBuyCoinBean requestQueryBuyCoinBean
                = new RequestQueryBuyCoinBean(pageNum,pageSize,pageWay,type,price,limitMaxPrice,limitMinPrice);


        NetWorks.queryBuyCoins(requestQueryBuyCoinBean, new Observer<ResponseQueryBuyCoinBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("Justin开始订阅");
            }

            @Override
            public void onNext(ResponseQueryBuyCoinBean responseQueryBuyCoinBean) {
                LogUtils.d("Justin responseQueryBuyCoinBean:"+responseQueryBuyCoinBean.toString());
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("Justin e："+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("Justin onComplete()");
            }
        });


        if(null == fragmentCashFastSelect ){
            fragmentCashFastSelect = FragmentCashFastSelect.newInstance(cashList);
            fragmentSetCashRange = FragmentSetCashRange.newInstance(maxCashValue);
        }

        changeFragment(R.id.fl_cash_fragment,fragmentCashFastSelect,Fragment_Tag_Fixed);//

    }




    private void changeFragment(int willReplaceLayoutId,Fragment fragment,String tag) {

        fragmentSelectedIndex = tag.equals(Fragment_Tag_Fixed);

        // 获取Fragment管理器对象
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(willReplaceLayoutId, fragment, tag);
        // 提交
        ft.commit();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.rl_all:

                if (rlSelectTypeContainer.getVisibility() == View.VISIBLE) {
                    startAllContainerAnimation(false);
                } else {
                    //展开选择页面
                    startAllContainerAnimation(true);
                }

                break;


            case R.id.rb_cash_fast_selected:

                changeFragment(R.id.fl_cash_fragment,fragmentCashFastSelect,Fragment_Tag_Fixed);//

                break;


            case R.id.rb_set_selected_cash_round:

                changeFragment(R.id.fl_cash_fragment,fragmentSetCashRange,Fragment_Tag_Can_Selected);//

                break;


            case R.id.btn_confirm:


                if(fragmentSelectedIndex){
                    //取 fragmentCashFastSelect 选中的值

                    ToastUtils.showNomalLongToast(""+fragmentCashFastSelect.getLastSelecteValue());

                }else{

                    //取 fragmentSetCashRange 选中的值

                    ToastUtils.showNomalLongToast(""+fragmentSetCashRange.getSetMaxValue()+" : "+fragmentSetCashRange.getSetMinValue());

                }
                closeSelectTypeAnimation();


                break;


        }

    }

    private void startAllContainerAnimation(boolean openOrCloseView) {

        if (openOrCloseView) {//如果打开
            //先执行箭头的旋转动画
            executeArrowAnimation(0f, 180f);
            openSelectTypeAnimation();

        } else {
            executeArrowAnimation(180f, 360f);
            closeSelectTypeAnimation();
        }

    }


    /**
     * 关闭 全部 内容view
     */
    private void closeSelectTypeAnimation() {
        int height = llSelectTypeView.getHeight();
        ObjectAnimator translation = ObjectAnimator.ofFloat(llSelectTypeView, "translationY", 0 - height);
        translation.setDuration(400);
        translation.start();
        translation.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rlSelectTypeContainer.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }


    /**
     * 打开 全部 内容view
     */
    private void openSelectTypeAnimation() {

        rlSelectTypeContainer.setVisibility(View.VISIBLE);
        int flSafeRootViewHeight = llSelectTypeView.getMeasuredHeight();
        int height = llSelectTypeView.getHeight();
        LogUtils.d("flSafeRootViewHeight:" + flSafeRootViewHeight + " height:" + height);
        ObjectAnimator translation = ObjectAnimator.ofFloat(llSelectTypeView, "translationY", -460, 0);
        translation.setDuration(400);
        translation.start();
        llSelectTypeView.setVisibility(View.VISIBLE);

    }

    private void executeArrowAnimation(float startAngle, float endAngle) {
        ObjectAnimator translation = ObjectAnimator.ofFloat(ivArrow, "rotation", startAngle, endAngle);
        translation.setDuration(500);
        translation.start();
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMessage (MessageEvent event){
    }
}
