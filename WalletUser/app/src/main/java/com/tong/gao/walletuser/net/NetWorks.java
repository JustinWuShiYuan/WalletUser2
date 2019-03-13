package com.tong.gao.walletuser.net;

import com.tong.gao.walletuser.bean.QueryFireCoinInfoBean;
import com.tong.gao.walletuser.bean.request.RequestAddReceiptMoneyAccount;
import com.tong.gao.walletuser.bean.request.RequestBtcExchangeApply;
import com.tong.gao.walletuser.bean.request.RequestBtcExchangeDetail;
import com.tong.gao.walletuser.bean.request.RequestBuyerHadPayMoney;
import com.tong.gao.walletuser.bean.request.RequestCancelExchangeApply;
import com.tong.gao.walletuser.bean.request.RequestCancelOrder;
import com.tong.gao.walletuser.bean.request.RequestChangeNickNameBean;
import com.tong.gao.walletuser.bean.request.RequestDeleteReceiptMoneyAccount;
import com.tong.gao.walletuser.bean.request.RequestExchangeApplyList;
import com.tong.gao.walletuser.bean.request.RequestLoginInfoBean;
import com.tong.gao.walletuser.bean.request.RequestMerSaleCoinList;
import com.tong.gao.walletuser.bean.request.RequestMessageInformBean;
import com.tong.gao.walletuser.bean.request.RequestOcrIdcard;
import com.tong.gao.walletuser.bean.request.RequestOrderApply;
import com.tong.gao.walletuser.bean.request.RequestOrdersBean;
import com.tong.gao.walletuser.bean.request.RequestQueryAssertChangeRecord;
import com.tong.gao.walletuser.bean.request.RequestQueryBuyCoinBean;
import com.tong.gao.walletuser.bean.request.RequestQueryOrderList;
import com.tong.gao.walletuser.bean.request.RequestRegisterBean;
import com.tong.gao.walletuser.bean.request.RequestSellCoin;
import com.tong.gao.walletuser.bean.request.RequestTransferAccordBean;
import com.tong.gao.walletuser.bean.request.RequestTransferAccountBean;
import com.tong.gao.walletuser.bean.request.RequestUpdateReceiptAccount;
import com.tong.gao.walletuser.bean.request.RequestVerifyGoogleCodeBean;
import com.tong.gao.walletuser.bean.response.ResponseBaseBean;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeApply;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeDetail;
import com.tong.gao.walletuser.bean.response.ResponseBtcExchangeRate;
import com.tong.gao.walletuser.bean.response.ResponseCancelExchangeApply;
import com.tong.gao.walletuser.bean.response.ResponseExchangeApplyList;
import com.tong.gao.walletuser.bean.response.ResponseMerSaleCoinList;
import com.tong.gao.walletuser.bean.response.ResponseMoneyRange;
import com.tong.gao.walletuser.bean.response.ResponseOcrBean;
import com.tong.gao.walletuser.bean.response.ResponseQueryAssertChangeRecord;
import com.tong.gao.walletuser.bean.response.ResponseQueryMyReceiptMoneyAccountList;
import com.tong.gao.walletuser.bean.response.ResponseSellCoin;
import com.tong.gao.walletuser.bean.response.ResponseTransferAccountBean;
import com.tong.gao.walletuser.bean.response.ResponseBuyerHadPayMoney;
import com.tong.gao.walletuser.bean.response.ResponseCancelOrder;
import com.tong.gao.walletuser.bean.response.ResponseChangeNickNameBean;
import com.tong.gao.walletuser.bean.response.ResponseLoginInfo;
import com.tong.gao.walletuser.bean.response.ResponseMessageInformBean;
import com.tong.gao.walletuser.bean.response.ResponseMyAccountInfo;
import com.tong.gao.walletuser.bean.response.ResponseMyTransferAccordBean;
import com.tong.gao.walletuser.bean.response.ResponseNormalBean;
import com.tong.gao.walletuser.bean.response.ResponseOrderAppeal;
import com.tong.gao.walletuser.bean.response.ResponseOrdersBean;
import com.tong.gao.walletuser.bean.response.ResponsePersonalBean;
import com.tong.gao.walletuser.bean.response.ResponseQueryBuyCoinBean;
import com.tong.gao.walletuser.bean.response.ResponseQueryMyAssertBean;
import com.tong.gao.walletuser.bean.response.ResponseQueryOrderList;
import com.tong.gao.walletuser.bean.response.ResponseRegisterBean;
import com.tong.gao.walletuser.bean.response.ResponseVerifyGoogleBean;
import com.tong.gao.walletuser.constants.MyConstant;

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
    protected static final NetService faceService = getRetrofitFace().create(NetService.class);

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
//        Observable<ResultBean> uploadJsonToServer(@Field("qrData") String qrData, @Field("tokenRongCloud") String tokenRongCloud);
//
//        //POST请求
//        @FormUrlEncoded
//        @POST(MyConstant.uploadQrDataAgain)
//        Observable<ResultBean> uploadJsonToServerAgain(@Field("qrData") String qrData, @Field("tokenRongCloud") String tokenRongCloud);

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
        Observable<ResponseTransferAccountBean> transferAccounts(@Body RequestTransferAccountBean requestTransferAccountBean);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.verify_google_code)
        Observable<ResponseVerifyGoogleBean> verifyGoogleCode(@Body RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.queryAssert)
        Observable<ResponseMyAccountInfo> queryMyAccountInfo();


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.transfer_accord)
        Observable<ResponseMyTransferAccordBean> queryTransferAccord(@Body RequestTransferAccordBean requestTransferAccordBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryBuyCoinList)
        Observable<ResponseQueryBuyCoinBean> queryBuyCoinList(@Body RequestQueryBuyCoinBean requestQueryBuyCoinBean);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryMessageInform)
        Observable<ResponseMessageInformBean> queryMessageInformList(@Body RequestMessageInformBean requestMessageInformBean);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryPersonalInfo)
        Observable<ResponsePersonalBean> queryPersonalInfo();

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.changeNickName)
        Observable<ResponseChangeNickNameBean> changeNickName(@Body RequestChangeNickNameBean requestChangeNickNameBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.exitLogin)
        Observable<ResponseNormalBean> exitLogin();

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @GET(MyConstant.queryAssert)
        Observable<ResponseQueryMyAssertBean> queryAssert();



        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.uesrBuyOrder)
        Observable<ResponseOrdersBean> userBuyOrders(@Body RequestOrdersBean requestOrdersBean);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.buyerHadPayMoney)
        Observable<ResponseBuyerHadPayMoney> buyerHadPayMoney(@Body RequestBuyerHadPayMoney requestBuyerHadPayMoney);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.cancelOrder)
        Observable<ResponseCancelOrder> cancelOrder(@Body RequestCancelOrder requestCancelOrder);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryOrderList)
        Observable<ResponseQueryOrderList> queryOrderList(@Body RequestQueryOrderList requestQueryOrderList);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.orderAppeal)
        Observable<ResponseOrderAppeal> orderAppeal(@Body RequestOrderApply requestOrderApply);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.btcExchangeApply)
        Observable<ResponseBtcExchangeApply> btcExchangeApply(@Body RequestBtcExchangeApply requestBtcExchangeApply);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryBtcExchangeRate)
        Observable<ResponseBtcExchangeRate> queryBtcExchangeRate();


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryBtcExchangeAppayList)
        Observable<ResponseExchangeApplyList> queryBtcExchangeApplyList(@Body RequestExchangeApplyList requestExchangeApplyList);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.btcExchangeAppayDetail)
        Observable<ResponseBtcExchangeDetail> btcExchangeApplyDetail(@Body RequestBtcExchangeDetail requestBtcExchangeDetail);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.cancelBtcExchangeApply)
        Observable<ResponseCancelExchangeApply> cancelBtcExchangeApply(@Body RequestCancelExchangeApply requestCancelExchangeApply);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.sellCoin)
        Observable<ResponseSellCoin> sellCoin(@Body RequestSellCoin requestSellCoin);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryMoneyRange)
        Observable<ResponseMoneyRange> queryMoneyRange();

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryMerSallCoinList)
        Observable<ResponseMerSaleCoinList> queryMerSaleCoinList(@Body RequestMerSaleCoinList requestMerSaleCoinList);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryMerAssertChangeRecord)
        Observable<ResponseQueryAssertChangeRecord> queryAssertChangeRecord(@Body RequestQueryAssertChangeRecord requestQueryAssertChangeRecord);


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.queryMyReceiptMoneyAccountList)
        Observable<ResponseQueryMyReceiptMoneyAccountList> queryMyReceiptMoneyAccountList();


        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.addReceiptMoneyAccount)
        Observable<ResponseBaseBean> addReceiptMoneyAccount(@Body RequestAddReceiptMoneyAccount requestAddReceiptMoneyAccount);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.deleteReceiptMoneyAccount)
        Observable<ResponseBaseBean> deleteReceiptMoneyAccount(@Body RequestDeleteReceiptMoneyAccount requestDeleteReceiptMoneyAccount);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.updateReceiptMoneyAccount)
        Observable<ResponseBaseBean> updateReceiptMoneyAccount(@Body RequestUpdateReceiptAccount requestUpdateReceiptAccount);

        @Headers({"Content-type:application/json;charset=UTF-8"})
        @POST(MyConstant.postOcr)
        Observable<ResponseOcrBean> postOcrIdcard(@Body RequestOcrIdcard requestOcrIdcard);


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



    public static void login(RequestLoginInfoBean requestLoginInfoBean, Observer<ResponseLoginInfo> observer){
        setSubscribe(service.login(requestLoginInfoBean),observer);
    }

    public static void register(RequestRegisterBean requestRegisterBean, Observer<ResponseRegisterBean> observer){
        setSubscribe(service.register(requestRegisterBean),observer);
    }

    public static void queryFireCoinInfo(Observer<QueryFireCoinInfoBean> observer){
        setSubscribe(service.queryFireCoinInfo(),observer);
    }


    public static void transferAccount(RequestTransferAccountBean  requestTransferAccountBean,Observer<ResponseTransferAccountBean> observer){
        setSubscribe(service.transferAccounts(requestTransferAccountBean),observer);
    }


    public static void verifyGoogleCode(RequestVerifyGoogleCodeBean requestVerifyGoogleCodeBean, Observer<ResponseVerifyGoogleBean> observer){
        setSubscribe(service.verifyGoogleCode(requestVerifyGoogleCodeBean),observer);
    }


    public static void queryMyAccountInfo(Observer<ResponseMyAccountInfo> observer){
        setSubscribe(service.queryMyAccountInfo(),observer);
    }

    public static void queryTransferAccord(RequestTransferAccordBean requestTransferAccordBean,Observer<ResponseMyTransferAccordBean> observer){
        setSubscribe(service.queryTransferAccord(requestTransferAccordBean),observer);
    }

    public static void queryBuyCoins(RequestQueryBuyCoinBean requestQueryBuyCoinBean,Observer<ResponseQueryBuyCoinBean> observer){
        setSubscribe(service.queryBuyCoinList(requestQueryBuyCoinBean),observer);
    }

    public static void queryMessageInformList(RequestMessageInformBean requestMessageInformBean,Observer<ResponseMessageInformBean> observer){
        setSubscribe(service.queryMessageInformList(requestMessageInformBean),observer);
    }


    public static void queryPersonalInfo(Observer<ResponsePersonalBean> observer){
        setSubscribe(service.queryPersonalInfo(),observer);
    }

    public static void changeNickName(RequestChangeNickNameBean requestChangeNickNameBean,Observer<ResponseChangeNickNameBean> observer){
        setSubscribe(service.changeNickName(requestChangeNickNameBean),observer);
    }


    public static void exitLogin(Observer<ResponseNormalBean> observer){
        setSubscribe(service.exitLogin(),observer);
    }


    public static void queryMyAssert(Observer<ResponseQueryMyAssertBean> observer){
        setSubscribe(service.queryAssert(),observer);
    }


    public static void buyOrders(RequestOrdersBean requestOrdersBean,Observer<ResponseOrdersBean> observer){
        setSubscribe(service.userBuyOrders(requestOrdersBean),observer);
    }


    public static void buyerHadPay(RequestBuyerHadPayMoney requestBuyerHadPayMoney,Observer<ResponseBuyerHadPayMoney> observer){
        setSubscribe(service.buyerHadPayMoney(requestBuyerHadPayMoney),observer);
    }

    public static void cancelOrder(RequestCancelOrder requestCancelOrder, Observer<ResponseCancelOrder> observer){
        setSubscribe(service.cancelOrder(requestCancelOrder),observer);
    }

    public static void queryOrderList(RequestQueryOrderList requestQueryOrderList, Observer<ResponseQueryOrderList> observer){
        setSubscribe(service.queryOrderList(requestQueryOrderList),observer);
    }


    public static void orderAppeal(RequestOrderApply requestOrderApply, Observer<ResponseOrderAppeal> observer){
        setSubscribe(service.orderAppeal(requestOrderApply),observer);
    }


    public static void queryBtcExchangeRate(Observer<ResponseBtcExchangeRate> observer){
        setSubscribe(service.queryBtcExchangeRate(),observer);
    }


    public static void queryBtcExchangeApplyList(RequestExchangeApplyList requestExchangeApplyList, Observer<ResponseExchangeApplyList> observer){
        setSubscribe(service.queryBtcExchangeApplyList(requestExchangeApplyList),observer);
    }

    public static void btcExchangeApply(RequestBtcExchangeApply requestBtcExchangeApply, Observer<ResponseBtcExchangeApply> observer){
        setSubscribe(service.btcExchangeApply(requestBtcExchangeApply),observer);
    }

    public static void btcExchangeApplyDetail(RequestBtcExchangeDetail requestBtcExchangeDetail,Observer<ResponseBtcExchangeDetail> observer){
        setSubscribe(service.btcExchangeApplyDetail(requestBtcExchangeDetail),observer);
    }

    public static void cancelExchangeApply(RequestCancelExchangeApply requestCancelExchangeApply,Observer<ResponseCancelExchangeApply> observer){
        setSubscribe(service.cancelBtcExchangeApply(requestCancelExchangeApply),observer);
    }

    public static void sellCoin(RequestSellCoin requestSellCoin,Observer<ResponseSellCoin> observer){
        setSubscribe(service.sellCoin(requestSellCoin),observer);
    }

    public static void queryMoneyRange(Observer<ResponseMoneyRange> observer){
        setSubscribe(service.queryMoneyRange(),observer);
    }


    public static void queryMerSaleCoinList(RequestMerSaleCoinList requestMerSaleCoinList,Observer<ResponseMerSaleCoinList> observer){
        setSubscribe(service.queryMerSaleCoinList(requestMerSaleCoinList),observer);
    }


    public static void queryAssertChangeRecord(RequestQueryAssertChangeRecord requestQueryAssertChangeRecord,Observer<ResponseQueryAssertChangeRecord> observer){
        setSubscribe(service.queryAssertChangeRecord(requestQueryAssertChangeRecord),observer);
    }



    public static void queryMyReceiptAccountList(Observer<ResponseQueryMyReceiptMoneyAccountList> observer){
        setSubscribe(service.queryMyReceiptMoneyAccountList(),observer);
    }


    public static void addReceiptMoneyAccount(RequestAddReceiptMoneyAccount requestAddReceiptMoneyAccount,Observer<ResponseBaseBean> observer){
        setSubscribe(service.addReceiptMoneyAccount(requestAddReceiptMoneyAccount),observer);
    }

    public static void deleteReceiptMoneyAccount(RequestDeleteReceiptMoneyAccount requestDeleteReceiptMoneyAccount,Observer<ResponseBaseBean> observer){
        setSubscribe(service.deleteReceiptMoneyAccount(requestDeleteReceiptMoneyAccount),observer);
    }


    public static void updateReceiptMoneyAccount(RequestUpdateReceiptAccount requestUpdateReceiptAccount,Observer<ResponseBaseBean> observer){
        setSubscribe(service.updateReceiptMoneyAccount(requestUpdateReceiptAccount),observer);
    }


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

    public static void postOcrIdcard(RequestOcrIdcard requestOcrIdcard,Observer<ResponseOcrBean> observer) {
        setSubscribe(faceService.postOcrIdcard(requestOcrIdcard), observer);
    }

    public static <T> void setSubscribe(Observable<T> observable, Observer<T> observer) {
        observable.subscribeOn(Schedulers.newThread())//子线程访问网络
                .observeOn(AndroidSchedulers.mainThread())//回调到主线程
                .subscribe(observer);
    }
}
