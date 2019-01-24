package com.tong.gao.walletuser.constants;

public class MyConstant {

    public static String baseUrl = "http://47.52.45.85:8010/";//测试地址
//    public static String baseUrl = "http://192.168.23.201:8000/";//测试地址

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
    public static final String paymentWayZfb = "1";       //
    public static final String paymentWayWeChat = "2";       //
    public static final String paymentWayBank = "3";       //
    public static final String tradeFragmentCoinBeanKey = "tradeFragmentCoinBeanKey";       //




    //融云相关变量---------------------------------------------
    public static String AppKey = "25wehl3u2gq4w";  //
    public static String AppSecret = "Pmy22oXFVtadI3";  //
    public static String tokenRongCloud ="cUsy5OLX7D9Pe6s0LNL/WrHvXxomzE45U9p/IHrQnICeMap1CaftA/sOi5/RNlqrLm1LSbULXCPaC7A8X1D0fv/x/uMf3v1W";//Justin1
//    public static String tokenRongCloud="oR6+G+drtptcC5CiuHIUk5435OHQjFqnb+q/H09tIVcAgHSdMTrjSNGkY/yqg7/8PZRmpbY80Z1J6lVImKYMzA==";//Justin2
}
