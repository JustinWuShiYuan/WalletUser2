package com.tong.gao.walletuser.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.interfaces.DialogCallBack;
import com.tong.gao.walletuser.ui.view.WheelDialog;


public class DialogUtils {
    private static WheelDialog mWheelDialog;


    public static View createAlertDialog(Activity mActivity, int resource,int cancelId,int sureId, final DialogCallBack dialogCallBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
        View v = LayoutInflater.from(mActivity).inflate(resource, null);
        final Dialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = DensityUtil.dp2px(mActivity, 310);
        lp.height = DensityUtil.dp2px(mActivity, 440);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setContentView(v);


        v.findViewById(cancelId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.cancel(dialog);
            }
        });


        v.findViewById(sureId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.sure(dialog);
            }
        });


        return v;
    }


    public static View createAlertDialog(Activity mActivity, int resource,int sureId, final DialogCallBack dialogCallBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
        View v = LayoutInflater.from(mActivity).inflate(resource, null);
        final Dialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = DensityUtil.dp2px(mActivity, 300);
        lp.height = DensityUtil.dp2px(mActivity, 430);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setContentView(v);



        v.findViewById(sureId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.sure(dialog);
            }
        });


        return v;
    }



    public static View createAlertDialog(Activity mActivity, int resource,int sureId, int height,int width,final DialogCallBack dialogCallBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
        View v = LayoutInflater.from(mActivity).inflate(resource, null);
        final Dialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = DensityUtil.dp2px(mActivity, width);
        lp.height = DensityUtil.dp2px(mActivity, height);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setContentView(v);



        v.findViewById(sureId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.sure(dialog);
            }
        });


        return v;
    }


    public static View createAlertDialog(Activity mActivity, int resource,int sureId,int cancelId, int height,int width,final DialogCallBack dialogCallBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
        View v = LayoutInflater.from(mActivity).inflate(resource, null);
        final Dialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = DensityUtil.dp2px(mActivity, width);
        lp.height = DensityUtil.dp2px(mActivity, height);
        window.setGravity(Gravity.CENTER);
        window.setAttributes(lp);
        window.setContentView(v);



        v.findViewById(cancelId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.cancel(dialog);
            }
        });
        v.findViewById(sureId).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogCallBack.sure(dialog);
            }
        });


        return v;
    }



    public static void showProgressDialog(Context context,String message) {
        if (mWheelDialog == null) {
            mWheelDialog = new WheelDialog(context, R.style.WheelDialog);
        }
        if (mWheelDialog.isShowing()) {
            return;
        }
        if (TextUtils.isEmpty(message)) {
            mWheelDialog.setMessage("请稍后..");
        } else {
            mWheelDialog.setMessage(message);
        }
        mWheelDialog.show();
    }

    public static  void hideProgressDialog() {
        if (mWheelDialog != null && mWheelDialog.isShowing()) {
            mWheelDialog.dismiss();
        }
    }



}
