package com.tong.gao.walletuser.ui.loading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.event.TransferAccordEvent;
import com.tong.gao.walletuser.bean.request.RequestTransferAccordBean;
import com.tong.gao.walletuser.bean.response.ResponseMyTransferAccordBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.holder.MyTransferAccodHolder;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FragmentTransferAccord extends BaseFragment {

    private int myTransferType = 0;
    private List<ResponseMyTransferAccordBean.TransferInfoBean> dataList = new ArrayList<>();
    boolean[] isLoadMoreSuccess  = {false,false,false};
    int[] mCurrentCounter = {0,0,0};
    private int[] TOTAL_COUNTER = {1,0,0};
    private int pageSize = 7;
    private int[] pageNum = {1,1,1};

    private boolean isFirstToType1 = true;

     MyTransferAccordAdapter2 myTradeListAdapter;

    @Override
    protected View onSuccessView() {
        myTradeListAdapter = new MyTransferAccordAdapter2(R.layout.item_transfer_accord, dataList);

        myTradeListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });

        View view = View.inflate(UIUtils.getContext(), R.layout.my_transfer_recycleview, null);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_my_order);
        final RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_my_transfer);

        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        myOrderRecycleView.setLayoutManager(mLinearLayoutManager);
        myOrderRecycleView.setAdapter(myTradeListAdapter);

        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
                //TODO 刷新数据

            }
        });


        //加载更多
        myTradeListAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                myOrderRecycleView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(myTransferType == 1 && isFirstToType1){
                            isLoadMoreSuccess[myTransferType] = false;
                            isFirstToType1 = false;
                        }else{
                            isLoadMoreSuccess[myTransferType] = true;
                        }

                        if (mCurrentCounter[myTransferType] >= TOTAL_COUNTER[myTransferType]) {
                            //数据全部加载完毕
                            myTradeListAdapter.loadMoreEnd();
                        } else {
                            if (isLoadMoreSuccess[myTransferType]) {
                                //成功获取更多数据
//                                pageNum[myTransferType]++;
//                                onLoadData();
                                loadMore(pageNum[myTransferType]);
                                LogUtils.d("22222222222dataList.size():"+dataList.size());



                            } else {
                                //获取更多数据失败
                                isLoadMoreSuccess[myTransferType] = false;
                                Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                myTradeListAdapter.loadMoreFail();

                            }
                        }
                    }

                }, 500);
            }
        }, myOrderRecycleView);


        return view;
    }



    public void loadMore(int pageIndex){
        pageIndex++;

        NetWorks.queryTransferAccord(new RequestTransferAccordBean(myTransferType+"",pageIndex+"",pageSize+""),new Observer<ResponseMyTransferAccordBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseMyTransferAccordBean responseMyTransferAccordBean) {
                if (null != responseMyTransferAccordBean && MyConstant.resultCodeIsOK .equals(responseMyTransferAccordBean.getErrcode())) {


                    TOTAL_COUNTER[myTransferType] =Integer.parseInt(responseMyTransferAccordBean.getPage().getSum()) ;

                    dataList = responseMyTransferAccordBean.getTransferRecord();

//                    LogUtils.d("pageIndexPageNum:"+responseMyTransferAccordBean.getPageOut().getSum()+" pageIndex dataList.size():"+dataList.size());

                    if(null !=dataList && dataList.size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myTradeListAdapter.addData(dataList);
                                mCurrentCounter[myTransferType] = myTradeListAdapter.getData().size();
                                myTradeListAdapter.loadMoreComplete();
                            }
                        });
                    }


                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError:");
            }

            @Override
            public void onComplete() {
                LogUtils.d(" onComplete()");
            }
        });

    }




    @Override
    protected LoadingPager.LoadedResult onLoadData() {

        Bundle arguments = getArguments();
        if( null != arguments){
            myTransferType = arguments.getInt(MyConstant.transferAccordType);
        }

        NetWorks.queryTransferAccord(new RequestTransferAccordBean(myTransferType+"",pageNum[myTransferType]+"",pageSize+""),new Observer<ResponseMyTransferAccordBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("myTransferType:"+myTransferType+" pageNum:"+pageNum);
            }

            @Override
            public void onNext(ResponseMyTransferAccordBean responseMyTransferAccordBean) {
                if (null != responseMyTransferAccordBean && MyConstant.resultCodeIsOK .equals(responseMyTransferAccordBean.getErrcode())) {

                    LogUtils.d("PageNum:"+responseMyTransferAccordBean.getPage().getSum());

                    TOTAL_COUNTER[myTransferType] =Integer.parseInt(responseMyTransferAccordBean.getPage().getSum()) ;

                    dataList = responseMyTransferAccordBean.getTransferRecord();

                    LogUtils.d("PageNum:"+responseMyTransferAccordBean.getPage().getSum()+" dataList.size():"+dataList.size());

                    if(null !=dataList && dataList.size() > 0 ){
                        isLoadMoreSuccess[myTransferType] = true;
                        EventBus.getDefault().post(new TransferAccordEvent());

                    }else{
                        isLoadMoreSuccess[myTransferType] = false;
                    }


                }
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d("onError:");
            }

            @Override
            public void onComplete() {
                LogUtils.d(" onComplete()");
            }
        });


        return LoadingPager.LoadedResult.LOADING;
    }



    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventTransferAccord (TransferAccordEvent event){

        if(null != dataList && dataList.size() == 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.EMPTY.getState());
        }else if(null != dataList && dataList.size() > 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.SUCCESS.getState());
        }else{
            getmPager().setmCurrentState(LoadingPager.LoadedResult.ERROR.getState());
        }
    }


    @Override
    protected void executeEmptyTask() {

    }


    class MyTransferAccordAdapter extends RecyclerView.Adapter<MyTransferAccodHolder> {


        @NonNull
        @Override
        public MyTransferAccodHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new MyTransferAccodHolder(
                    LayoutInflater.from(getActivity()).inflate(R.layout.item_transfer_accord, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull MyTransferAccodHolder myTransferAccodHolder, int index) {
            myTransferAccodHolder.refreshUI(getActivity(),dataList.get(index));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }


    class MyTransferAccordAdapter2 extends BaseQuickAdapter<ResponseMyTransferAccordBean.TransferInfoBean,BaseViewHolder> {

        public MyTransferAccordAdapter2(int layoutResId, @Nullable List<ResponseMyTransferAccordBean.TransferInfoBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ResponseMyTransferAccordBean.TransferInfoBean item) {

            helper.setImageResource(R.id.iv_transfer_icon, R.drawable.icon_transfer_in);
            helper.setText(R.id.tv_payment_user_id, item.getTransferRecordId());
            helper.setText(R.id.tv_payment_time, item.getTransferTime());
            helper.setText(R.id.tv_transfer_num, item.getNumber());

        }
    }




}
