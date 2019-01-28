package com.tong.gao.walletuser.ui.fragments.message;

import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.MessageBean;
import com.tong.gao.walletuser.bean.event.MyOrderInformEvent;
import com.tong.gao.walletuser.bean.request.RequestMessageInformBean;
import com.tong.gao.walletuser.bean.response.ResponseMessageInformBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.LoadingPager;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FragmentCustomerInform extends BaseFragment {
    private View rootView;
    private RecyclerView rvMyOrderInform;
    private SwipeRefreshLayout srlRefreshMyOrderInform;


    private List<MessageBean> dataList = new ArrayList<>();
    private MyOrderInformAdapter myOrderInformAdapter;

    private int mCurrentCounter =0;
    private int TOTAL_COUNTER   =0;
    private String pageSize = "12";
    private int pageNum = 1;
    private String type = "3";//订单消息通知
    private boolean isLoadMoreSuccess = true;

    @Override
    protected View onSuccessView() {
        rootView = View.inflate(UIUtils.getContext(), R.layout.fragment_my_order_inform, null);
        initView();

        return rootView;
    }

    private void initView() {
        rvMyOrderInform = rootView.findViewById(R.id.rv_my_order_inform);
        srlRefreshMyOrderInform = rootView.findViewById(R.id.srl_refresh_my_order_inform);

        if(null == myOrderInformAdapter){
            myOrderInformAdapter = new MyOrderInformAdapter(R.layout.item_my_message, dataList);
        }

        myOrderInformAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "onItemClick" + position, Toast.LENGTH_SHORT).show();
            }
        });


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(UIUtils.getContext());
        rvMyOrderInform.setLayoutManager(mLinearLayoutManager);
        rvMyOrderInform.setAdapter(myOrderInformAdapter);

        srlRefreshMyOrderInform.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light, android.R.color.holo_orange_light);

        srlRefreshMyOrderInform.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlRefreshMyOrderInform.setRefreshing(false);
                //TODO 刷新数据

            }
        });


        myOrderInformAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {

                rvMyOrderInform.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (mCurrentCounter >= TOTAL_COUNTER) {
                            //数据全部加载完毕
                            myOrderInformAdapter.loadMoreEnd();
                        } else {
                            if (isLoadMoreSuccess) {
                                //成功获取更多数据
                                loadMore(pageNum);

                            } else {
                                //获取更多数据失败
                                isLoadMoreSuccess = false;
                                Toast.makeText(getActivity(), "加载更多失败", Toast.LENGTH_LONG).show();
                                myOrderInformAdapter.loadMoreFail();

                            }
                        }


                    }
                },500);

            }
        },rvMyOrderInform);


    }


    @Override
    protected LoadingPager.LoadedResult onLoadData() {

        NetWorks.queryMessageInformList(new RequestMessageInformBean(type, pageNum+"", pageSize), new Observer<ResponseMessageInformBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ResponseMessageInformBean responseMessageInformBean) {

                if (null != responseMessageInformBean && MyConstant.resultCodeIsOK .equals(responseMessageInformBean.getErrcode())) {
                    dataList = responseMessageInformBean.getAllMessage();

                    TOTAL_COUNTER =Integer.parseInt(responseMessageInformBean.getPage().getSum()) ;

                    if(null != dataList){
                        LogUtils.d("PageNum:"+responseMessageInformBean.getPage().getSum()+" dataList.size():"+dataList.size());
                    }
//                    EventBus.getDefault().post(new MyOrderInformEvent());

                    updateUI();

                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        return LoadingPager.LoadedResult.LOADING;
    }

    private void updateUI() {

        if(null == dataList ){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.EMPTY.getState());
        }else if(null != dataList && dataList.size() > 0){
            getmPager().setmCurrentState(LoadingPager.LoadedResult.SUCCESS.getState());
        }else{
            getmPager().setmCurrentState(LoadingPager.LoadedResult.ERROR.getState());
        }
    }


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onEventOrderInform (MyOrderInformEvent event){
//    }

    @Override
    protected void executeEmptyTask() {

    }



    public void loadMore(int pageIndex){
        pageIndex++;


        NetWorks.queryMessageInformList(new RequestMessageInformBean(type, pageIndex + "", pageSize), new Observer<ResponseMessageInformBean>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(ResponseMessageInformBean responseMessageInformBean) {

                if (null != responseMessageInformBean && MyConstant.resultCodeIsOK .equals(responseMessageInformBean.getErrcode())) {

                    TOTAL_COUNTER = Integer.parseInt(responseMessageInformBean.getPage().getSum()) ;

                    dataList = responseMessageInformBean.getAllMessage();

                    if(null !=dataList && dataList.size() > 0 ){

                        UIUtils.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                myOrderInformAdapter.addData(dataList);
                                mCurrentCounter = myOrderInformAdapter.getData().size();
                                myOrderInformAdapter.loadMoreComplete();
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





    class MyOrderInformAdapter extends BaseQuickAdapter<MessageBean,BaseViewHolder>{

        public MyOrderInformAdapter(int layoutResId, @Nullable List<MessageBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, MessageBean item) {

            helper.setImageResource(R.id.civ_user_head_icon, R.drawable.img_service_head);
            helper.setText(R.id.tv_user_name, item.getMessageId());
            helper.setText(R.id.tv_message_info, item.getContent());
            helper.setText(R.id.tv_message_time, item.getCreatedTime());

        }
    }

}
