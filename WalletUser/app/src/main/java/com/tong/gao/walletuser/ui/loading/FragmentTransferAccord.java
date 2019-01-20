package com.tong.gao.walletuser.ui.loading;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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

    @Override
    protected View onSuccessView() {
        MyTransferAccordAdapter myTradeListAdapter = new MyTransferAccordAdapter();
        View view = View.inflate(UIUtils.getContext(),R.layout.my_transfer_recycleview,null);
        final SwipeRefreshLayout refreshLayout = view.findViewById(R.id.srl_refresh_my_order);
        RecyclerView myOrderRecycleView = view.findViewById(R.id.rv_my_transfer);

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

        return view;
    }



    @Override
    protected LoadingPager.LoadedResult onLoadData() {

        Bundle arguments = getArguments();
        if( null != arguments){
            myTransferType = arguments.getInt(MyConstant.transferAccordType);
        }


        LogUtils.d("myTransferType:"+myTransferType);

        NetWorks.queryTransferAccord(new RequestTransferAccordBean(myTransferType+"","1","15"),new Observer<ResponseMyTransferAccordBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseMyTransferAccordBean responseMyTransferAccordBean) {
                if (null != responseMyTransferAccordBean) {

                    dataList = responseMyTransferAccordBean.getTransferRecord();

                    EventBus.getDefault().post(new TransferAccordEvent());
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

}
