package com.tong.gao.walletuser.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.interfaces.DialogCallBack;


public class DialogUtils {


    public static void createAlertDialog(Activity mActivity, int resource,int cancelId,int sureId, final DialogCallBack dialogCallBack) {

        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity,R.style.Dialog);
        View v = LayoutInflater.from(mActivity).inflate(resource, null);
        final Dialog dialog = builder.create();
        dialog.show();

        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        lp.width = DensityUtil.dp2px(mActivity, 300);
        lp.height = DensityUtil.dp2px(mActivity, 390);
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
    }
}
