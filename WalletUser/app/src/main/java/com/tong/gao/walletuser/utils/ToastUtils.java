package com.tong.gao.walletuser.utils;

import android.widget.Toast;

import com.tong.gao.walletuser.AppApplication;


/**
 * Created by terry on 11/11/16.
 */

public class ToastUtils {

    public static void showNomalShortToast(String message) {
        Toast.makeText(AppApplication.getMyContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static void showNomalLongToast(String message) {
        Toast.makeText(AppApplication.getMyContext(), message, Toast.LENGTH_LONG).show();
    }

}
