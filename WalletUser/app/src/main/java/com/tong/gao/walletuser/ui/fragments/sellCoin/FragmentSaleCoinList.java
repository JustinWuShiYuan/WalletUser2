package com.tong.gao.walletuser.ui.fragments.sellCoin;

import android.os.Bundle;
import android.view.View;

import com.tong.gao.walletuser.bean.request.RequestMerSaleCoinList;
import com.tong.gao.walletuser.bean.response.ResponseMerSaleCoinList;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.LoadingPager;
import com.tong.gao.walletuser.utils.LogUtils;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentSaleCoinList extends BaseFragment {

    private int saleColnType = 0;

    private int pageno = 1;
    private String  pagesize = "12";

    @Override
    protected View onSuccessView() {
        return null;
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

        return null;
    }

    @Override
    protected void executeEmptyTask() {

    }


}
