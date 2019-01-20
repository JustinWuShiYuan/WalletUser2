package com.tong.gao.walletuser.factory;

import android.os.Bundle;


import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.ui.loading.BaseFragment;
import com.tong.gao.walletuser.ui.loading.FragmentTransferAccord;
import com.tong.gao.walletuser.utils.LogUtils;

import java.util.LinkedHashMap;
import java.util.Map;

public class LoadingPagerFactory {
	private static Map<Integer, BaseFragment>	mCaches	= new LinkedHashMap<Integer, BaseFragment>();

	public static BaseFragment getFragment(int position) {

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
}
