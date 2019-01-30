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

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentSaleCoinList extends BaseFragment {

    private int                     saleColnType = 0;
    private int                     pageno = 1;
    private String                  pagesize = "12";
    private List<MerSaleCoinBean>   merchantAdvert;

    @Override
    protected View onSuccessView() {

        SaleCoinListAdapter myTradeListAdapter = new SaleCoinListAdapter(R.layout.item_sale_coin,merchantAdvert);
        View view = View.inflate(getContext(),R.layout.sale_coin_recycleview,null);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_sale_coin);
        RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_sale_coin);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        myOrderRecycleView.setLayoutManager(mLinearLayoutManager);
        myOrderRecycleView.setAdapter(myTradeListAdapter);


        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(UIUtils.getContext(),"刷新数据完成",Toast.LENGTH_LONG).show();
                refreshLayout.setRefreshing(false);
                //TODO 刷新数据
            }
        });



        return refreshLayout;
    }

    @Override
    protected LoadingPager.LoadedResult onLoadData() {

        Bundle arguments = getArguments();
        if(null != arguments ){

            saleColnType = arguments.getInt( MyConstant.saleCoinListKey);

            NetWorks.queryMerSaleCoinList(new RequestMerSaleCoinList(pageno+"",pagesize), new Observer<ResponseMerSaleCoinList>() {
                @Override
                public void onSubscribe(Disposable d) {
                }

                @Override
                public void onNext(ResponseMerSaleCoinList responseMerSaleCoinList) {
                    LogUtils.d("responseMerSaleCoinList:"+responseMerSaleCoinList.toString());
                    if(null != responseMerSaleCoinList && MyConstant.resultCodeIsOK.equals(responseMerSaleCoinList.getErrcode())){
                        merchantAdvert = responseMerSaleCoinList.getMerchantAdvert();
                        updateUI(responseMerSaleCoinList);
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
    }


    @Override
    protected void executeEmptyTask() {

    }


    class SaleCoinListAdapter extends BaseQuickAdapter<MerSaleCoinBean,BaseViewHolder> implements View.OnClickListener {

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


            helper.getView(R.id.tv_sold_out).setOnClickListener(this);
            helper.getView(R.id.tv_sold_in).setOnClickListener(this);
            helper.getView(R.id.tv_compile).setOnClickListener(this);

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

        @Override
        public void onClick(View v) {

            switch (v.getId()){

                case R.id.tv_sold_out://下架
                    //TODO
                    ToastUtils.showNomalShortToast("下架");
                    break;

                case R.id.tv_sold_in://上架
                    ToastUtils.showNomalShortToast("上架");

                    break;

                case R.id.tv_compile://编辑
                    ToastUtils.showNomalShortToast("编辑");

                    break;


            }

        }
    }


}
