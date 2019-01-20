package com.tong.gao.walletuser.net;

import com.tong.gao.walletuser.bean.QueryFireCoinInfoBean;
import com.tong.gao.walletuser.bean.request.RequestLoginInfoBean;
import com.tong.gao.walletuser.bean.request.RequestRegisterBean;
import com.tong.gao.walletuser.bean.request.RequestTransferAccountBean;
import com.tong.gao.walletuser.bean.request.RequestVerifyGoogleCodeBean;
import com.tong.gao.walletuser.bean.response.ReponseTransferAccountBean;
import com.tong.gao.walletuser.bean.response.ResponseLoginInfo;
import com.tong.gao.walletuser.bean.response.ResponseMyAccountInfo;
import com.tong.gao.walletuser.bean.response.ResponseMyTransferAccordBean;
import com.tong.gao.walletuser.bean.response.ResponseRegisterBean;
import com.tong.gao.walletuser.bean.response.ResponseVerifyGoogleBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.ui.loading.LoadingPager;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public class NetWorks extends RetrofitUtils {

    //创建实现接口调用
    protected static final NetService service = getRetrofit().create(NetService.class);

    //设缓存有效期为1天
    protected static final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
    //查询缓存的Cache-Control设置，使用缓存
    protected static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    //查询网络的Cache-Control设置。不使用缓存
    protected static final String CACHE_CONTROL_NETWORK = "max-age=0";

    private interface NetService {

        //POST请求
//        @FormUrlEncoded
//        @POST(MyConstant.uploadQrData)
//        Observable<ResultBean> uploadJsonToServer(@Field("qrData") String qrData, @Field("token") String token);
//
//        //POST请求
//        @FormUrlEncoded
//        @POST(MyConstant.uploadQrDataAgain)
//        Observable<ResultBean> uploadJsonToServerAgain(@Field("qrData") String qrData, @Field("token") String token);

//        //POST请求
//        @FormUrlEncoded
//        @POST("bjws/app.user/login")
//        Observable<Verification> getVerfcationCodePostMap(@FieldMap Map<String, String> map);
//
//        //GET请求
//        @GET(MyConstant.isHaveTheAccount)
//        Observable<String> checkAccountIsExist(@Query("account") String account);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.login)
        Observable<ResponseLoginInfo> login(@Body RequestLoginInfoBean loginInfoBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.register)
        Observable<ResponseRegisterBean> register(@Body RequestRegisterBean registerBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.queryFireCoinInfo)
        Observable<QueryFireCoinInfoBean> queryFireCoinInfo();


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.transferAccount)
        Observable<ReponseTransferAccountBean> transferAccounts(@Body RequestTransferAccountBean requestTransferAccountBean);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.verify_google_code)
        Observable<ResponseVerifyGoogleBean> verifyGoogleCode(@Body RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.queryAssert)
        Observable<ResponseMyAccountInfo> queryMyAccountInfo();


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.transfer_accord)
        Observable<ResponseMyTransferAccordBean> queryTransferAccord();


//        @Headers({"Content-type:application/json;charset=UTF-8"})
//        @POST(MyConstant.verify_google_code)
//        Observable<ResponseVerifyGoogleBean> verifyGoogleCode(@Body RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean);
////
//        //GET请求，设置缓存
//        @Headers("Cache-Control: public," + CACHE_CONTROL_CACHE)
//        @GET("bjws/app.user/login")
//        Observable<Verification> getVerfcationGetCache(@Query("tel") String tel, @Query("password") String pass);
//
//
//        @Headers("Cache-Control: public," + CACHE_CONTROL_NETWORK)
//        @GET("bjws/app.menu/getMenu")
//        Observable<MenuBean> getMainMenu();

    }

//    //POST请求
//    public static void uploadJson(String qrData, String token, Observer<ResultBean> observer){
//        setSubscribe(service.uploadJsonToServer(qrData, token),observer);
//    }
//    //POST请求
//    public static void uploadErrorJson(String qrData, String token, Observer<ResultBean> observer){
//        setSubscribe(service.uploadJsonToServerAgain(qrData, token),observer);
//    }
//
//
//    public static void checkAccount(String account, Observer<String> observer){
//        setSubscribe(service.checkAccountIsExist(account),observer);
//    }


    public static void login(RequestLoginInfoBean requestLoginInfoBean, Observer<ResponseLoginInfo> observer){
        setSubscribe(service.login(requestLoginInfoBean),observer);
    }

    public static void register(RequestRegisterBean requestRegisterBean, Observer<ResponseRegisterBean> observer){
        setSubscribe(service.register(requestRegisterBean),observer);
    }

    public static void queryFireCoinInfo(Observer<QueryFireCoinInfoBean> observer){
        setSubscribe(service.queryFireCoinInfo(),observer);
    }


    public static void transferAccount(RequestTransferAccountBean  requestTransferAccountBean,Observer<ReponseTransferAccountBean> observer){
        setSubscribe(service.transferAccounts(requestTransferAccountBean),observer);
    }


    public static void verifyGoogleCode(RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean, Observer<ResponseVerifyGoogleBean> observer){
        setSubscribe(service.verifyGoogleCode(requestVerifyGoogleCodeBean),observer);
    }


    public static void queryMyAccountInfo(Observer<ResponseMyAccountInfo> observer){
        setSubscribe(service.queryMyAccountInfo(),observer);
    }

    public static void queryTransferAccord(Observer<ResponseMyTransferAccordBean> observer){
        setSubscribe(service.queryTransferAccord(),observer);
    }



//
//    public static void login(RequestLoginInfoBean requestLoginInfoBean, Observer<ResponseLoginInfo> observer){
//        setSubscribe(service.login(requestLoginInfoBean),observer);
//    }
//
//
//    public static void verifyGoogleCode(RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean,Observer<ResponseVerifyGoogleBean> observer){
//        setSubscribe(service.verifyGoogleCode(requestVerifyGoogleCodeBean),observer);
//    }


//
//    //POST请求参数以map传入
//    public static void verfacationCodePostMap(Map<String, String> map, Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationCodePostMap(map),observer);
//    }
//
//    //Get请求设置缓存
//    public static void verfacationCodeGetCache(String tel, String pass,Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationGetCache(tel, pass),observer);
//    }
//
//    //Get请求
//    public static void verfacationCodeGet(String tel, String pass,Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationGet(tel, pass),observer);
//    }
//
//    //Get请求
//    public static void verfacationCodeGetsub(String tel, String pass, Observer<Verification> observer) {
//        setSubscribe(service.getVerfcationGet(tel, pass),observer);
//    }
//
//    //Get请求
//    public static void Getcache( Observer<MenuBean> observer) {
//        setSubscribe(service.getMainMenu(),observer);
//    }

    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
