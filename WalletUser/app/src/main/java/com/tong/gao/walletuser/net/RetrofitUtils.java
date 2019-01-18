package com.tong.gao.walletuser.net;


import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.factory.CustomGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public abstract class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static OkHttpClient mOkHttpClient;

    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(MyConstant.baseUrl)
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }
}
