package com.tong.gao.walletuser.ui.fragments.sellCoin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.MerSaleCoinBean;
import com.tong.gao.walletuser.bean.request.RequestMerSaleCoinList;
import com.tong.gao.walletuser.bean.response.ResponseMerSaleCoinList;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.LoadingPager;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentSaleCoinList extends BaseFragment {

    private int                     saleColnType = 0;
    private int                     pageno = 1;
    private String                  pagesize = "6";
    private List<MerSaleCoinBean>   merchantAdvert = new ArrayList<>();

    private int mCurrentCounter =0;
    private int TOTAL_COUNTER   =0;

    private  SwipeRefreshLayout     refreshLayout;
    private  RecyclerView           myOrderRecycleView;
    private  SaleCoinListAdapter    saleCoinListAdapter;
    private  boolean                isLoadMoreSuccess = true;

    @Override
    protected View onSuccessView() {

        if(null == saleCoinListAdapter){


            saleCoinListAdapter = new SaleCoinListAdapter(R.layout.item_sale_coin,merchantAdvert);
            View view = View.inflate(getContext(),R.layout.sale_coin_recycleview,null);
            refreshLayout = view.findViewById(R.id.srl_refresh_sale_coin);
            myOrderRecycleView = view.findViewById(R.id.rv_sale_coin);

            LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
            myOrderRecycleView.setLayoutManager(mLinearLayoutManager);
            myOrderRecycleView.setAdapter(saleCoinListAdapter);


            refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                    android.R.color.holo_red_light, android.R.color.holo_orange_light);

            refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    refreshLayout.setRefreshing(false);
                    pageno = 0;
                    mCurrentCounter = 0;
                    TOTAL_COUNTER = 0;
                    merchantAdvert.clear();

                    onLoadData();
                    Toast.makeText(UIUtils.getContext(),"刷新数据完成",Toast.LENGTH_LONG).show();
                }
            });


            saleCoinListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, final int position) {

                    final MerSaleCoinBean  merSaleCoinBean= (MerSaleCoinBean) adapter.getData().get(position);

                    view.findViewById(R.id.tv_sold_out).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showNomalShortToast("下架"+ merSaleCoinBean.getUgOtcAdvertId());

                        }
                    });
                    view.findViewById(R.id.tv_sold_in).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showNomalShortToast("上架"+ merSaleCoinBean.getUgOtcAdvertId());
                        }
                    });
                    view.findViewById(R.id.tv_compile).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ToastUtils.showNomalShortToast("编辑"+ merSaleCoinBean.getUgOtcAdvertId());

                        }
                    });
                }
            });


            saleCoinListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
                @Override
                public void onLoadMoreRequested() {

                    myOrderRecycleView.postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            LogUtils.d("mCurrentCounter:"+mCurrentCounter +" TOTAL_COUNTER:"+TOTAL_COUNTER);
                            if (mCurrentCounter >= TOTAL_COUNTER) {
                                //数据全部加载完毕
                                saleCoinListAdapter.loadMoreEnd();
                            } else {
                                if (isLoadMoreSuccess) {
                                    //成功获取更多数据
                                    pageno++;
                                    loadMore();
                                } else {
                                    //获取更多数据失败
                                    isLoadMoreSuccess = false;
                                    Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                    saleCoinListAdapter.loadMoreFail();
                                }
                            }
                        }
                    },500);

                }
            },myOrderRecycleView);
        }


        return refreshLayout;
    }

    private void loadMore() {

        NetWorks.queryMerSaleCoinList(new RequestMerSaleCoinList(pageno+"",pagesize), new Observer<ResponseMerSaleCoinList>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(final ResponseMerSaleCoinList responseMerSaleCoinList) {
                LogUtils.d("responseMerSaleCoinList:"+responseMerSaleCoinList.toString());
                if(null != responseMerSaleCoinList && MyConstant.resultCodeIsOK.equals(responseMerSaleCoinList.getErrcode())){
                    refreshLayout.setRefreshing(false);

                    TOTAL_COUNTER =Integer.parseInt(responseMerSaleCoinList.getPage().getSum()) ;

                    if(null != responseMerSaleCoinList.getMerchantAdvert() && responseMerSaleCoinList.getMerchantAdvert().size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                saleCoinListAdapter.addData(responseMerSaleCoinList.getMerchantAdvert());
                                mCurrentCounter = saleCoinListAdapter.getData().size();
                                saleCoinListAdapter.loadMoreComplete();
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
                LogUtils.d("onComplete()");
            }
        });

    }

    @Override
    protected LoadingPager.LoadedResult onLoadData() {

        Bundle arguments = getArguments();
        if(null != arguments ){

            saleColnType = arguments.getInt( MyConstant.saleCoinListKey);//0 全部  1 出售中， 2售罄 3已下架

            NetWorks.queryMerSaleCoinList(new RequestMerSaleCoinList(pageno+"",pagesize), new Observer<ResponseMerSaleCoinList>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(ResponseMerSaleCoinList responseMerSaleCoinList) {

                    if(null != responseMerSaleCoinList && MyConstant.resultCodeIsOK.equals(responseMerSaleCoinList.getErrcode())){

                        if(null != responseMerSaleCoinList.getPage()){
                            TOTAL_COUNTER =Integer.parseInt(responseMerSaleCoinList.getPage().getSum()) ;
                        }

                        if(null != responseMerSaleCoinList.getMerchantAdvert()){
                            merchantAdvert.addAll(responseMerSaleCoinList.getMerchantAdvert());
                            mCurrentCounter = merchantAdvert.size();
                            updateUI(responseMerSaleCoinList);
                        }else{
                            getmPager().setmCurrentState(LoadingPager.LoadedResult.EMPTY.getState());
                        }


                    }
                }

                @Override
                public void onError(Throwable e) {
                    LogUtils.d("e:"+e.toString());
                }

                @Override
                public void onComplete() {
                    LogUtils.d("onComplete()");
                }
            });
//
        }

        return LoadingPager.LoadedResult.LOADING;
    }

    private void updateUI(ResponseMerSaleCoinList responseMerSaleCoinList) {

        if(null == responseMerSaleCoinList.getMerchantAdvert()){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.ERROR.getState());
        }else if(responseMerSaleCoinList.getMerchantAdvert().size() > 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.SUCCESS.getState());
        }else if(responseMerSaleCoinList.getMerchantAdvert().size() == 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.EMPTY.getState());
        }
        saleCoinListAdapter.setNewData(responseMerSaleCoinList.getMerchantAdvert());
    }


    @Override
    protected void executeEmptyTask() {

    }


    class SaleCoinListAdapter extends BaseQuickAdapter<MerSaleCoinBean,BaseViewHolder> {

        public SaleCoinListAdapter(int layoutResId, @Nullable List<MerSaleCoinBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MerSaleCoinBean item) {

            helper.setText(R.id.tv_trade_coin_id,item.getUgOtcAdvertId());

//            1-出售中2-已下架 3-售罄
            if(item.getStatus() .equals(MyConstant.onOffer)){
                helper.setText(R.id.tv_trade_coin_status,"出售中");
            }else if(item.getStatus() .equals(MyConstant.removeCoin)){
                helper.setText(R.id.tv_trade_coin_status,"已下架");
            }else if(item.getStatus() .equals(MyConstant.soldOut)){
                helper.setText(R.id.tv_trade_coin_status,"售罄");
            }


            if(item.getAmountType().equals(MyConstant.tradeFixedAmountType)){//1.限额2.固额
                helper.setText(R.id.tv_trade_coin_type,"类型:固额 "+item.getFixedAmount());
            }else{//限额
                helper.setText(R.id.tv_trade_coin_type,"类型:限额 "+item.getLimitMinAmount() +"~"+item.getLimitMaxAmount());
            }

            helper.setText(R.id.tv_coin_remain_num,"库存:"+item.getNumber());


            String paymentway = item.getPaymentway();
            if(paymentway.length() == 1){
                displayPaymentIcon(helper, paymentway);
            }else{
                String[] split = paymentway.split("\\,");
                for(int i=0;i< split.length;i++){
                    displayPaymentIcon(helper, split[i]);
                }
            }

            helper.setText(R.id.tv_publish_time,"发布时间: "+item.getCreatedtime());
            helper.setText(R.id.tv_last_change_time,"最后修改时间: "+item.getModifyTime());

        }

        private void displayPaymentIcon(BaseViewHolder helper, String s) {
            if(s.equals(MyConstant.paymentWayZfb)){
                helper.getView(R.id.iv_cir_zfb).setVisibility(View.VISIBLE);
            }
            if(s.equals(MyConstant.paymentWayWeChat)){
                helper.getView(R.id.iv_cir_wechat).setVisibility(View.VISIBLE);
            }
            if(s.equals(MyConstant.paymentWayBank)){
                helper.getView(R.id.iv_cir_bank).setVisibility(View.VISIBLE);
            }
        }

    }


}
