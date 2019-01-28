package com.tong.gao.walletuser.ui.fragments;

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
import com.tong.gao.walletuser.bean.PageBean;
import com.tong.gao.walletuser.bean.UserOrderBean;
import com.tong.gao.walletuser.bean.event.MyOrderListEvent;
import com.tong.gao.walletuser.bean.myEnum.OrderStatus;
import com.tong.gao.walletuser.bean.request.RequestQueryOrderList;
import com.tong.gao.walletuser.bean.response.ResponseQueryOrderList;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.LoadingPager;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentMyOrderList extends BaseFragment {

    private int mCurrentCounter =0;
    private int TOTAL_COUNTER   =0;
    private int type = 0;
    private int pageno =1;
    private String pagesize ="12";
    private boolean isLoadMoreSuccess = true;
    private List<UserOrderBean> userOrderBeanList;
    private PageBean pageOut;

    private MyOrderListAdapter myOrderListAdapter;

    @Override
    protected View onSuccessView() {

        myOrderListAdapter = new MyOrderListAdapter(R.layout.item_my_order_list,userOrderBeanList);

        View view = View.inflate(UIUtils.getContext(),R.layout.fragment_my_order_list,null);

        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_my_order_list);
        final RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_my_order_list);


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        myOrderRecycleView.setLayoutManager(mLinearLayoutManager);

        myOrderRecycleView.setAdapter(myOrderListAdapter);

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Toast.makeText(UIUtils.getContext(),"2",Toast.LENGTH_LONG).show();

                refreshLayout.setRefreshing(false);

                //TODO 刷新数据
            }
        });

        myOrderListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ToastUtils.showNomalShortToast("1111111111调往详情"+position);
            }
        });

        //加载更多
        myOrderListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                myOrderRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            myOrderListAdapter.loadMoreEnd();
                        } else {
                            if (isLoadMoreSuccess) {
                                //成功获取更多数据
                                loadMore(pageno);
                            } else {
                                //获取更多数据失败
                                isLoadMoreSuccess = false;
                                Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                myOrderListAdapter.loadMoreFail();

                            }
                        }


                    }
                },500);

            }
        },myOrderRecycleView);


        return refreshLayout;
    }

    private void loadMore(int pageno) {
        pageno++;


        NetWorks.queryOrderList(new RequestQueryOrderList(type+"",pageno+"",pagesize), new Observer<ResponseQueryOrderList>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(""+d+"开始订阅.................");
            }

            @Override
            public void onNext(ResponseQueryOrderList responseQueryOrderList) {
                if(null != responseQueryOrderList && MyConstant.resultCodeIsOK .equals(responseQueryOrderList.getErrcode())){

                    TOTAL_COUNTER = Integer.parseInt(responseQueryOrderList.getPageOut().getSum()) ;
                    userOrderBeanList = responseQueryOrderList.getUserOrder();

                    if(null !=userOrderBeanList && userOrderBeanList.size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myOrderListAdapter.addData(userOrderBeanList);
                                mCurrentCounter = myOrderListAdapter.getData().size();
                                myOrderListAdapter.loadMoreComplete();
                            }
                        });
                    }
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
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
        if(null != arguments){
            type = arguments.getInt(MyConstant.myOrderListKey);
        }

        NetWorks.queryOrderList(new RequestQueryOrderList(type+"",pageno+"",pagesize), new Observer<ResponseQueryOrderList>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(""+d+"开始订阅.................");
            }

            @Override
            public void onNext(ResponseQueryOrderList responseQueryOrderList) {
                if(null != responseQueryOrderList && MyConstant.resultCodeIsOK .equals(responseQueryOrderList.getErrcode())){
                    userOrderBeanList = responseQueryOrderList.getUserOrder();
                    pageOut = responseQueryOrderList.getPageOut();
                    if(null != pageOut ){
                        TOTAL_COUNTER =Integer.parseInt(pageOut.getSum()) ;
                    }
                    updateUI();
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });


        return LoadingPager.LoadedResult.LOADING;
    }

    private void updateUI() {
        if(null != userOrderBeanList && userOrderBeanList.size()>0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.SUCCESS.getState());
        }else if(null == userOrderBeanList || userOrderBeanList.size() == 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.EMPTY.getState());
        }else{
            getmPager().setmCurrentState(LoadingPager.LoadedResult.ERROR.getState());
        }
    }

    @Override
    protected void executeEmptyTask() {

    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    class MyOrderListAdapter extends BaseQuickAdapter<UserOrderBean,BaseViewHolder>{

        public MyOrderListAdapter(int layoutResId, @Nullable List<UserOrderBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, UserOrderBean item) {
            refreshUI(helper,item);
        }

        private void refreshUI(BaseViewHolder helper, UserOrderBean item) {

            if(null != item){
                helper.setText(R.id.tv_name, ""+item.getBuyUserId());  //昵称 目前用的用户id
                if(!item.getOrderSellIsVip().equals(MyConstant.sellerIsVip)){
                    helper.itemView.findViewById(R.id.iv_is_vip).setVisibility(View.VISIBLE);
                }else{
                    helper.itemView.findViewById(R.id.iv_is_vip).setVisibility(View.GONE);
                }


                // 1.未付款
                if(item.getStatus() .equals(OrderStatus.HadNotPay.getState())){
                    helper.setText(R.id.tv_order_status, "未付款");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.yellow_ff9238));
                }
                //2.已付款
                if(item.getStatus() .equals(OrderStatus.HadPay.getState())){
                    helper.setText(R.id.tv_order_status, "已付款");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.colorBlue));
                }
                //3.已完成
                if(item.getStatus() .equals(OrderStatus.Complete.getState())){
                    helper.setText(R.id.tv_order_status, "已完成");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.colorBlue));
                }
                //4.已取消
                if(item.getStatus() .equals(OrderStatus.Cancel_HadCancel.getState())){
                    helper.setText(R.id.tv_order_status, "已取消");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.gray_8));
                }
                //5.已关闭(自动 )
                if(item.getStatus() .equals(OrderStatus.Closed_HadClosed.getState())){
                    helper.setText(R.id.tv_order_status, "已关闭");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.gray_8));
                }
                //6.申诉中
                if(item.getStatus() .equals(OrderStatus.Appealing.getState())){
                    helper.setText(R.id.tv_order_status, "申诉中");
                    helper.setTextColor(R.id.tv_order_status, UIUtils.getColor(R.color.colorRead));
                }

                helper.setText(R.id.tv_order_type, "购买AB");  //后续需要增加 接口字段 来判断是买 还是卖 006151
                helper.setText(R.id.tv_order_number, "数量："+item.getNumber());  //
                helper.setText(R.id.tv_order_time, item.getPaymentTime());  //
                helper.setText(R.id.tv_order_cny, item.getConvertRmb());  //

            }
        }
    }


}
