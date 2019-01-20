package com.tong.gao.walletuser.ui.holder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.response.ResponseMyTransferAccordBean;

public class MyTransferAccodHolder extends RecyclerView.ViewHolder {

    ImageView ivTransferIcon;
    TextView tvPaymentUserId;
    TextView tvPaymentTime;
    TextView tvTransferNum;

    public MyTransferAccodHolder(@NonNull View itemView) {
        super(itemView);
        initView(itemView);
    }

    private void initView(View itemView) {
        ivTransferIcon = itemView.findViewById(R.id.iv_transfer_icon);
        tvPaymentUserId = itemView.findViewById(R.id.tv_payment_user_id);
        tvPaymentTime = itemView.findViewById(R.id.tv_payment_time);
        tvTransferNum = itemView.findViewById(R.id.tv_transfer_num);
    }


    public void refreshUI(Activity activity,ResponseMyTransferAccordBean.TransferInfoBean transferInfoBean) {

//        RequestOptions requestOptions = new RequestOptions()
//                .placeholder(R.drawable.head_loading)
//                .error(R.drawable.head_loading)
//                .fallback(new ColorDrawable(Color.RED));

//        Glide.with(activity)
//                .load(transferInfoBean)
//                .apply(requestOptions)
//                .into(new SimpleTarget<Drawable>() {
//                    @Override
//                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
//                        Drawable current = resource.getCurrent();
//                        ivTransferIcon.setImageDrawable(current);
//                    }
//                });

        //根据是转入还是抓出 来加载不同的图片
        ivTransferIcon.setImageResource(R.drawable.icon_transfer_in);
//        ivTransferIcon.setImageResource(R.drawable.icon_transfer_out);

        tvPaymentUserId.setText(transferInfoBean.getTransferRecordId());

        tvPaymentTime.setText(transferInfoBean.getTransferTime());

        tvTransferNum.setText(transferInfoBean.getNumber());
    }
}
