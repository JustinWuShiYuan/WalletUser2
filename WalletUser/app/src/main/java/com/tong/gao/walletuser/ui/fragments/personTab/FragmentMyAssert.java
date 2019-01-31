package com.tong.gao.walletuser.ui.fragments.personTab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.BaseFragment;
import com.tong.gao.walletuser.bean.request.RequestQueryAssertChangeRecord;
import com.tong.gao.walletuser.bean.response.ResponseQueryAssertChangeRecord;
import com.tong.gao.walletuser.bean.response.ResponseQueryMyAssertBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentMyAssert extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_my_coin)
    TextView tvMyCoin;
    @BindView(R.id.tv_my_money)
    TextView tvMyMoney;
    @BindView(R.id.tv_can_used_money)
    TextView tvCanUsedMoney;
    @BindView(R.id.tv_margin_money)
    TextView tvMarginMoney;
    @BindView(R.id.tv_trading_money)
    TextView tvTradingMoney;

    Unbinder unbinder;
    @BindView(R.id.srl_refresh_assert_change_record)
    SwipeRefreshLayout srlRefreshAssertChangeRecord;
    @BindView(R.id.rv_assert_change_record)
    RecyclerView rvAssertChangeRecord;


    private String type = "0";
    private int pageNum = 1;
    private String pageSize = "10";
    private int                     mCurrentCounter =0;
    private int                     TOTAL_COUNTER   =0;
    private boolean                 isLoadMoreSuccess = true;
    private List<ResponseQueryAssertChangeRecord.AssertChangeBean> assertChangeBeanList = new ArrayList<>();
    private MyAssertChangeRecordAdapter  myAssertChangeRecordAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);

        tvTitleBarTitle2.setText("我的资产");
        flBack.setOnClickListener(this);
        initMyView();
        initData();

        return rootView;
    }


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        return inflater.inflate(R.layout.fragment_my_assert, container, false);
    }


    @Override
    public void initData() {

        //查询我的资产
        NetWorks.queryMyAssert(new Observer<ResponseQueryMyAssertBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("FragmentMyAssert ");
            }

            @Override
            public void onNext(ResponseQueryMyAssertBean responseQueryMyAssertBean) {
                LogUtils.d("" + responseQueryMyAssertBean.toString());
                if (null != responseQueryMyAssertBean && MyConstant.resultCodeIsOK.equals(responseQueryMyAssertBean.getErrcode())) {
                    updateUI(responseQueryMyAssertBean);
                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("" + e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });


        //查询我的资产变更记录
        queryAssertChangeRecord();


    }

    private void queryAssertChangeRecord() {
        NetWorks.queryAssertChangeRecord(new RequestQueryAssertChangeRecord(type, pageNum + "", pageSize), new Observer<ResponseQueryAssertChangeRecord>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseQueryAssertChangeRecord responseQueryAssertChangeRecord) {

                if(null != responseQueryAssertChangeRecord && MyConstant.resultCodeIsOK.equals(responseQueryAssertChangeRecord.getErrcode())){
                    assertChangeBeanList = responseQueryAssertChangeRecord.getAccountChange();

                    if(null != responseQueryAssertChangeRecord.getPage()){
                        TOTAL_COUNTER = Integer.parseInt(responseQueryAssertChangeRecord.getPage().getSum());
                    }
                    updateAssertChangeRecordUI(assertChangeBeanList);
                    mCurrentCounter = assertChangeBeanList.size();
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d(" onComplete()");
            }
        });
    }

    private void updateAssertChangeRecordUI(List<ResponseQueryAssertChangeRecord.AssertChangeBean> datas) {
        myAssertChangeRecordAdapter.setNewData(datas);
    }

    private void updateUI(final ResponseQueryMyAssertBean assertBean) {

        UIUtils.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvMyCoin.setText(assertBean.getAmount());
                tvMyMoney.setText("折合人民币 " + assertBean.getConvertRmb() + " ￥");
                tvCanUsedMoney.setText(assertBean.getUsableFund());
                tvMarginMoney.setText(assertBean.getFrozenFund());

                String tradingMoney = (Float.parseFloat(assertBean.getAmount()) - Float.parseFloat(assertBean.getUsableFund())) + "";

                tvTradingMoney.setText(tradingMoney);
            }
        });

    }



    private void initMyView() {

        myAssertChangeRecordAdapter = new MyAssertChangeRecordAdapter(R.layout.item_assert_change_record_list,assertChangeBeanList);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        rvAssertChangeRecord.setLayoutManager(mLinearLayoutManager);
        rvAssertChangeRecord.setAdapter(myAssertChangeRecordAdapter);


        srlRefreshAssertChangeRecord.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        srlRefreshAssertChangeRecord.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlRefreshAssertChangeRecord.setRefreshing(false);
                pageNum = 0;
                mCurrentCounter = 0;
                TOTAL_COUNTER = 0;
                assertChangeBeanList.clear();

                queryAssertChangeRecord();
                Toast.makeText(UIUtils.getContext(),"刷新数据完成",Toast.LENGTH_LONG).show();
            }
        });



        myAssertChangeRecordAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                rvAssertChangeRecord.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            myAssertChangeRecordAdapter.loadMoreEnd();
                        } else {
                            if (isLoadMoreSuccess) {
                                //成功获取更多数据
                                pageNum++;
                                loadMore();
                            } else {
                                //获取更多数据失败
                                isLoadMoreSuccess = false;
                                Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                myAssertChangeRecordAdapter.loadMoreFail();
                            }
                        }

                    }
                },500);

            }
        },rvAssertChangeRecord);



    }

    private void loadMore() {

        NetWorks.queryAssertChangeRecord(new RequestQueryAssertChangeRecord(type, pageNum + "", pageSize), new Observer<ResponseQueryAssertChangeRecord>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(final ResponseQueryAssertChangeRecord responseQueryAssertChangeRecord) {

                if(null != responseQueryAssertChangeRecord && MyConstant.resultCodeIsOK.equals(responseQueryAssertChangeRecord.getErrcode())){
                    srlRefreshAssertChangeRecord.setRefreshing(false);

                    TOTAL_COUNTER =Integer.parseInt(responseQueryAssertChangeRecord.getPage().getSum()) ;

                    if(null != responseQueryAssertChangeRecord.getAccountChange() && responseQueryAssertChangeRecord.getAccountChange().size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myAssertChangeRecordAdapter.addData(responseQueryAssertChangeRecord.getAccountChange());
                                mCurrentCounter = myAssertChangeRecordAdapter.getData().size();
                                myAssertChangeRecordAdapter.loadMoreComplete();
                            }
                        });
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("e:"+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d(" onComplete()");
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fl_back:

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_fragmentMyAssert_to_myInfoFragment);
                break;

        }

    }


    class MyAssertChangeRecordAdapter extends BaseQuickAdapter<ResponseQueryAssertChangeRecord.AssertChangeBean,BaseViewHolder>{

        public MyAssertChangeRecordAdapter(int layoutResId, @Nullable List<ResponseQueryAssertChangeRecord.AssertChangeBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ResponseQueryAssertChangeRecord.AssertChangeBean item) {

            if(item.getResource() .equals(MyConstant.shiftIn)){//转入
                helper.setText(R.id.tv_assert_change_title,"转入");
                helper.setTextColor(R.id.tv_assert_change_num,Color.parseColor("#006151"));
                helper.setText(R.id.tv_assert_change_num,"+"+item.getNumber());

            }else if(item.getResource() .equals(MyConstant.shiftOut)){//转出
                helper.setText(R.id.tv_assert_change_title,"转出");
                helper.setTextColor(R.id.tv_assert_change_num,Color.parseColor("#d02a2a"));
                helper.setText(R.id.tv_assert_change_num,"-"+item.getNumber());

            }else if(item.getResource() .equals(MyConstant.buyIn)){//买入
                helper.setText(R.id.tv_assert_change_title,"买入");

                helper.setTextColor(R.id.tv_assert_change_num,Color.parseColor("#006151"));
                helper.setText(R.id.tv_assert_change_num,"+"+item.getNumber());

            }else if(item.getResource() .equals(MyConstant.saleOut)){//卖出
                helper.setText(R.id.tv_assert_change_title,"卖出");
                helper.setTextColor(R.id.tv_assert_change_num,Color.parseColor("#d02a2a"));
                helper.setText(R.id.tv_assert_change_num,"-"+item.getNumber());

            }else if(item.getResource() .equals(MyConstant.exchangeBtc)){//兑换BTC
                helper.setText(R.id.tv_assert_change_title,"兑换BTC");
                helper.setTextColor(R.id.tv_assert_change_num,Color.parseColor("#d02a2a"));
                helper.setText(R.id.tv_assert_change_num,"-"+item.getNumber());
            }

            helper.setText(R.id.tv_assert_change_time,item.getCreatedTime());

        }
    }

}
