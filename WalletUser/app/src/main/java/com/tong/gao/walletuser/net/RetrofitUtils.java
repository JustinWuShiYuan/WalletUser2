package com.tong.gao.walletuser.net;


import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.factory.CustomGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public abstract class RetrofitUtils {

    private static Retrofit mRetrofit;
    private static Retrofit mRetrofitFace;
    private static OkHttpClient mOkHttpClient;

    protected static Retrofit getRetrofit() {

        if (null == mRetrofit) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(MyConstant.baseUrl)
                    .addConverterFactory(CustomGsonConverterFactory.create())
//                    .addConverterFactory(FastJsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofit;
    }
    protected static Retrofit getRetrofitFace() {

        if (null == mRetrofitFace) {

            if (null == mOkHttpClient) {
                mOkHttpClient = OkHttp3Utils.getOkHttpClient();
            }
            mRetrofitFace = new Retrofit.Builder()
                    .baseUrl(MyConstant.baseUrlFace)
                    .addConverterFactory(CustomGsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(mOkHttpClient)
                    .build();

        }

        return mRetrofitFace;
    }
}
