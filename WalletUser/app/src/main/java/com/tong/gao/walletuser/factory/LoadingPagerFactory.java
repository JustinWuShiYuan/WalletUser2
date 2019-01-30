package com.tong.gao.walletuser.factory;

import android.os.Bundle;


import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.ui.fragments.FragmentMyOrderList;
import com.tong.gao.walletuser.ui.fragments.sellCoin.FragmentSaleCoinList;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.FragmentTransferAccord;
import com.tong.gao.walletuser.utils.LogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoadingPagerFactory {
	private static Map<Integer, BaseFragment>	mCaches	= new LinkedHashMap<>();
	private static Map<Integer, BaseFragment>	mCachesMyOrderList	= new LinkedHashMap<>();
	private static Map<Integer, BaseFragment>	mCachesSaleCoinList	= new LinkedHashMap<>();

	public static BaseFragment getFragmentTransferAccord(int position) {

		BaseFragment fragment = mCaches.get(position);
		// 判断缓存中是否有
		if (fragment != null) {
			LogUtils.d("读取缓存 : " + position);
			return fragment;
		}

		fragment = new FragmentTransferAccord();

        Bundle bundle = new Bundle();
        bundle.putInt(MyConstant.transferAccordType,position);
        fragment.setArguments(bundle);
		// 存储到缓存
		mCaches.put(position, fragment);
		LogUtils.d("新建缓存 : " + position);

		return fragment;
	}


	public static BaseFragment getFragmentMyOrderList(int position) {

		BaseFragment fragment = mCachesMyOrderList.get(position);
		// 判断缓存中是否有
		if (fragment != null) {
			LogUtils.d("读取缓存订单列表 : " + position);
			return fragment;
		}

		fragment = new FragmentMyOrderList();

		Bundle bundle = new Bundle();
		bundle.putInt(MyConstant.myOrderListKey,position);
		fragment.setArguments(bundle);
		// 存储到缓存
		mCachesMyOrderList.put(position, fragment);
		LogUtils.d("新建缓存订单列表 : " + position);

		return fragment;
	}

//	MyTradeFragment
	public static BaseFragment getFragmentSaleCoinList(int position) {

		BaseFragment fragment = mCachesSaleCoinList.get(position);
		// 判断缓存中是否有
		if (fragment != null) {
			LogUtils.d("读取缓存订单列表 : " + position);
			return fragment;
		}

		fragment = new FragmentSaleCoinList();

		Bundle bundle = new Bundle();
		bundle.putInt(MyConstant.saleCoinListKey,position);
		fragment.setArguments(bundle);
		// 存储到缓存
		mCachesSaleCoinList.put(position, fragment);
		LogUtils.d("新建缓存订单列表 : " + position);

		return fragment;
	}



}
