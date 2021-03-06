package com.tong.gao.walletuser.constants;

public class MyConstant {

    public static String baseUrl = "http://192.168.15.157:8010/";//测试地址
//    public static String baseUrl = "http://192.168.23.201:8000/";//测试地址
    public static String baseUrlFace = "https://api-cn.faceplusplus.com/";//faceId地址


    public static final String queryFireCoinInfo = "ug/mar/pbmts.do";           //查看火币行情信息 (首页)
    public static final String transferAccount = "ug/acc/pbats.do";    //扫码转账
    public static final String queryAssert = "ug/acc/pbads.do";        //资产查询
    public static final String login = "ug/usr/pblin.do";           //登录
    public static final String register = "ug/usr/pbrus.do";           //注册
    public static final String verify_google_code = "ug/usr/pbggc.do";           //校验谷歌验证码
    public static final String transfer_accord = "ug/acc/pbtrs.do";           //用户转账记录
    public static final String queryBuyCoinList = "ug/mer/pbadv.do";           //广告列表 买币列表
    public static final String queryMessageInform = "/ug/ord/pbmls.do";           //消息模块下 相关通知接口
    public static final String queryPersonalInfo = "/ug/usr/pbpis.do";           //个人信息查询接口
    public static final String changeNickName = "/ug/usr/pbmns.do";           //修改昵称
    public static final String exitLogin = "/ug/usr/pblou.do";           //退出登录
    public static final String queryMyAssert = "/ug/acc/pbads.do";           //资产查询
    public static final String uesrBuyOrder = "/ug/ord/pbpos.do";           //用户下单接口
    public static final String buyerHadPayMoney = "/ug/ord/pbcfs.do";           //买家确认付款接口
    public static final String cancelOrder = "/ug/ord/pbcos.do";           //取消订单接口
    public static final String queryOrderList = "/ug/ord/pbuos.do";           //查询订单列表接口
    public static final String orderAppeal = "/ug/ord/pbaos.do";           //三十一、订单申诉
    public static final String queryBtcExchangeRate = "/ug/btc/pbers.do";           //BTC汇率查询
    public static final String btcExchangeApply = "/ug/btc/pbebs.do";           //BTC兑换申请
    public static final String queryBtcExchangeAppayList = "/ug/btc/pbels.do";           //BTC兑换申请列表
    public static final String btcExchangeAppayDetail = "/ug/btc/pbeds.do";           //三十七、兑换BTC申请详细
    public static final String cancelBtcExchangeApply = "/ug/btc/pbces.do";           //三十七、撤销兑换BTC申请
    public static final String sellCoin = "/ug/mer/pbpas.do";           //三十九、商家发布广告
    public static final String queryMoneyRange = "/ug/fac/pbacs.do";           //固定金额和限额范围
    public static final String queryMerSallCoinList = "/ug/mer/pbmas.do";           //商家广告列表
    public static final String queryMerAssertChangeRecord = "/ug/acc/pbahs.do";           //用户资产变更查询
    public static final String queryMyReceiptMoneyAccountList = "/ug/mer/pbpws.do";           //十七、收款方式列表
    public static final String addReceiptMoneyAccount = "/ug/mer/pbapw.do";           //添加收款方式
    public static final String deleteReceiptMoneyAccount = "/ug/mer/pbdpw.do";           //十六、删除收款方式
    public static final String updateReceiptMoneyAccount = "/ug/mer/pbupw.do";           //十五、修改收款方式



    public static final String postOcr = "cardpp/v1/ocridcard";//ocr地址


    public static final String mySecretKeyGoogle = "THKRP5JCTPIU5CEE";  //谷歌验证 关闭
    public static final String googleVerifyIsClosed = "0";  //谷歌验证 关闭
    public static final String googleVerifyIsOpened = "1";  //谷歌验证 打开
    public static final String coinBTC = "BTC";  //BTC
    public static final String coinETH = "ETH";  //ETH
    public static final String coinEOS = "EOS";  //EOS
    public static final String resultCodeIsOK = "1";
    public static final String userId = "userId";
    public static final String transferAccordType = "transferAccordType";
    public static final String transferAccountAddressKey = "transferAccountAddressKey";
    public static final String loginStatues = "loginStatues";
    public static final String loginStatuesFlag = "loginStatuesFlag";
    public static final String personalBeanKey = "personalBeanKey";
    public static final String sellerIsVip = "1";       //为vip
    public static final String tradeFixedAmountType = "2";       //1.限额2.固额
    public static final String paymentWayWeChat = "1";       //
    public static final String paymentWayZfb = "2";       //
    public static final String paymentWayBank = "3";       //
    public static final String paymentWayIsOpen = "1";       //1-启用 2-停用
    public static final String paymentWayIsClosed = "2";       //1-启用 2-停用
    public static final String tradeFragmentCoinBeanKey = "tradeFragmentCoinBeanKey";       //
    public static final String buyCoinNoMoreNotify = "buyCoinNoMoreNotify";       //买币不在提醒对话框
    public static final String downOrderKey = "downOrderKey";                      //下单key
    public static final String hadPayMoneyOrderKey = "hadPayMoneyOrderKey";                      //下单 付完钱的key
    public static final String orderCoinBeanKey = "orderCoinBeanKey";
    public static final String valueIsNull = "*null*";
    public static final String myOrderListKey = "myOrderListKey";
    public static final String saleCoinListKey = "saleCoinListKey";
    public static final String orderItemDetailKey = "orderItemDetailKey";
    public static final String transferAccountAccordKey = "transferAccountAccordKey";
    public static final String btcExchangeApplyDetailKey = "btcExchangeApplyDetailKey";
    public static final String btcExchangeApplyIdKey = "btcExchangeApplyIdKey";
    public  static final String CoinLimit ="CoinLimit";   //
    public  static final String CoinConstant ="CoinConstant";   //
    public static final int singleLimitTrade = 1;
    public static final int singleTradeLimit = 0;
    public static final int singleConstantTrade = 2;
    public static final String sellCoinSuccessKey = "sellCoinSuccessKey";
    public static final String onOffer = "1";
    public static final String removeCoin = "2";//下架
    public static final String soldOut = "3";//售罄
    public static final String shiftIn = "1";//转入
    public static final String shiftOut = "2";//转出
    public static final String buyIn = "3";//买入
    public static final String saleOut = "4";//卖出
    public static final String exchangeBtc = "5";//兑换BTC
    public static final String Payment_ZFB ="2";
    public static final String Payment_WeChat ="1";
    public static final String Payment_Bank ="3";
    public static final String Status_All =0+"";
    public static final String Status_NotPay =1+"";      //未付款
    public static final String Status_HadPay =2+"";      //已付款
    public static final String Status_Complete =3+"";     //已完成
    public static final String Status_HadCancel =4+"";    //已取消
    public static final String Status_HadClosed =5+"";   //已关闭
    public static final String Status_Appeal =6+"";      //申诉中
    public static final String StatusDealing ="1";      //处理中
    public static final String StatusRemit ="2";      //已汇出
    public static final String StatusReject ="3";      //已驳回
    public static final String EditQrCodeType = "EditQrCodeType";



    //极光推送相关---------------------------------------------
    public static final String auroraPushKey ="50da3938830450bb899a616f";//极光推送key


    //融云相关变量---------------------------------------------
    public static String AppKey = "25wehl3u2gq4w";  //
    public static String AppSecret = "Pmy22oXFVtadI3";  //
    public static String tokenRongCloud ="cUsy5OLX7D9Pe6s0LNL/WrHvXxomzE45U9p/IHrQnICeMap1CaftA/sOi5/RNlqrLm1LSbULXCPaC7A8X1D0fv/x/uMf3v1W";//Justin1
//    public static String tokenRongCloud="oR6+G+drtptcC5CiuHIUk5435OHQjFqnb+q/H09tIVcAgHSdMTrjSNGkY/yqg7/8PZRmpbY80Z1J6lVImKYMzA==";//Justin2


    //face相关变量---------------------------------------------
    public static String face_AppKey = "QIRxfenV1UqRWLOru_hbYaij7iMt5_7F";  //
    public static String face_AppSecret = "8inAb9gFj2VU991NVWbgA6BbmH8IoxvE";  //
}
