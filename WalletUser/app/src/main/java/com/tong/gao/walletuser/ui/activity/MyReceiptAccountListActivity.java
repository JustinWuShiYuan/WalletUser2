package com.tong.gao.walletuser.ui.activity;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.suke.widget.SwitchButton;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.response.ResponseQueryMyReceiptMoneyAccountList;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.ui.adaper.BaseAdapter;
import com.tong.gao.walletuser.utils.LogUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import static com.tong.gao.walletuser.bean.myEnum.PaymentWay.Bank;
import static com.tong.gao.walletuser.bean.myEnum.PaymentWay.WeChat;
import static com.tong.gao.walletuser.bean.myEnum.PaymentWay.ZFB;

public class MyReceiptAccountListActivity extends ActivityBase implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.fl_add)
    FrameLayout flAdd;
    @BindView(R.id.rv_my_receipt_account_list)
    SwipeMenuRecyclerView rvMyReceiptAccountList;
    @BindView(R.id.rl_cancel)
    RelativeLayout rlCancel;
    @BindView(R.id.ib_zfb_switch)
    ImageButton ibZfbSwitch;
    @BindView(R.id.ib_we_chat_switch)
    ImageButton ibWeChatSwitch;
    @BindView(R.id.ib_bank_switch)
    ImageButton ibBankSwitch;
    @BindView(R.id.btn_sure_safe_code)
    Button btnSureSafeCode;
    @BindView(R.id.ll_add_account_root_view)
    LinearLayout llAddAccountRootView;
    @BindView(R.id.rl_add_account_root_view)
    RelativeLayout rlAddAccountRootView;


    public static final String TOP_DECORATION = "top_decoration";
    public static final String BOTTOM_DECORATION = "bottom_decoration";
    public static final String LEFT_DECORATION = "left_decoration";
    public static final String RIGHT_DECORATION = "right_decoration";


    private  List<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean> receiptMoneyBeanList = new ArrayList<>();

    private MyReceiptAccountListAdapter myAccountAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_my_receipt_account_list;
    }

    @Override
    protected void initView() {

        tvTitleBarTitle2.setText("收款账户");
        flBack.setOnClickListener(this);
        flAdd.setOnClickListener(this);

        rlCancel.setOnClickListener(this);
        ibZfbSwitch.setOnClickListener(this);
        ibWeChatSwitch.setOnClickListener(this);
        ibBankSwitch.setOnClickListener(this);
        btnSureSafeCode.setOnClickListener(this);


        loadMyReceiptAccountList();
    }

    private void loadMyReceiptAccountList() {

        NetWorks.queryMyReceiptAccountList(new Observer<ResponseQueryMyReceiptMoneyAccountList>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d("d");
            }

            @Override
            public void onNext(ResponseQueryMyReceiptMoneyAccountList responseQueryMyReceiptMoneyAccountList) {

                LogUtils.d("responseQueryMyReceiptMoneyAccountList:"+responseQueryMyReceiptMoneyAccountList.toString());
                if(null != responseQueryMyReceiptMoneyAccountList && MyConstant.resultCodeIsOK.equals(responseQueryMyReceiptMoneyAccountList.getErrcode())){
                    List<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean> paymentWayList = responseQueryMyReceiptMoneyAccountList.getPaymentWay();
                    receiptMoneyBeanList.addAll(paymentWayList);
                    updateUI(paymentWayList);
                }

            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(""+e.toString());
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });
    }

    private void updateUI(List<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean> paymentWayList) {


        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mLinearLayoutManager.setSmoothScrollbarEnabled(true);
        rvMyReceiptAccountList.setLayoutManager(mLinearLayoutManager);

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        stringIntegerHashMap.put(TOP_DECORATION, 20);//top间距
        stringIntegerHashMap.put(LEFT_DECORATION, 10);//top间距
        stringIntegerHashMap.put(RIGHT_DECORATION, 10);//top间距


        rvMyReceiptAccountList.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        rvMyReceiptAccountList.setSwipeMenuCreator(swipeMenuCreator);
        rvMyReceiptAccountList.setSwipeMenuItemClickListener(mMenuItemClickListener);


        if (null == myAccountAdapter) {
            myAccountAdapter = new MyReceiptAccountListAdapter(this);
        }
        rvMyReceiptAccountList.setAdapter(myAccountAdapter);

        myAccountAdapter.notifyDataSetChanged(paymentWayList);

    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){

            case R.id.fl_back://返回

                this.finish();

                break;

            case R.id.fl_add://添加新的账户


                rlAddAccountRootView.setVisibility(View.VISIBLE);
                int flSafeRootViewHeight = llAddAccountRootView.getMeasuredHeight();
                int height = llAddAccountRootView.getHeight();
                LogUtils.d("flSafeRootViewHeight:" + flSafeRootViewHeight + " height:" + height);
                ObjectAnimator translation = ObjectAnimator.ofFloat(llAddAccountRootView, "translationY", 532, 0);
                translation.setDuration(800);
                translation.start();
                llAddAccountRootView.setVisibility(View.VISIBLE);


                break;



            case R.id.rl_cancel:

                hideAddAccountRootView();

                break;


            case R.id.ib_zfb_switch:

                changeSwitchBg(R.id.ib_zfb_switch);

                break;


            case R.id.ib_we_chat_switch:
                changeSwitchBg(R.id.ib_we_chat_switch);

                break;


            case R.id.ib_bank_switch:
                changeSwitchBg(R.id.ib_bank_switch);

                break;


            case R.id.btn_sure_safe_code:
                hideAddAccountRootView();
                startActivityById(paymentTypeId);

                break;


        }

    }


    private int paymentTypeId;
    private void changeSwitchBg(int id) {
        paymentTypeId = id;
        switch (id){

            case R.id.ib_zfb_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_selected);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_unselect);

                break;


            case R.id.ib_we_chat_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_selected);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_unselect);

                break;


            case R.id.ib_bank_switch:
                ibZfbSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibWeChatSwitch.setBackgroundResource(R.drawable.trick_unselect);
                ibBankSwitch.setBackgroundResource(R.drawable.trick_selected);

                break;
        }

    }



    private void hideAddAccountRootView() {
        int height1 = rlAddAccountRootView.getHeight();
        ObjectAnimator translation1 = ObjectAnimator.ofFloat(llAddAccountRootView, "translationY", 0, height1);
        translation1.setDuration(500);
        translation1.start();
        translation1.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                rlAddAccountRootView.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
    }

    private void startActivityById(int paymentTypeId) {
        switch (paymentTypeId){

            case R.id.ib_zfb_switch:
                Intent intent = new Intent();
                intent.putExtra(MyConstant.EditQrCodeType,"ZFB");
                intent.setClass(this,EditZfbOrWeChatCodeActivity.class);
                startActivity(intent);
                break;


            case R.id.ib_we_chat_switch:

                Intent intent1 = new Intent();
                intent1.putExtra(MyConstant.EditQrCodeType,"WeChat");
                intent1.setClass(this,EditZfbOrWeChatCodeActivity.class);
                startActivity(intent1);

                break;


            case R.id.ib_bank_switch:
//                startActivity(new Intent(this,EditBankCardActivity.class));

                break;
        }

    }


    class MyReceiptAccountListAdapter extends BaseAdapter<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean,MyReceiptAccountListAdapter.ViewHolder> {


        private List<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean> mDataList;

        public MyReceiptAccountListAdapter(Context context) {
            super(context);
        }

        @Override
        public void notifyDataSetChanged(List<ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean> dataList) {
            this.mDataList = dataList;
            super.notifyDataSetChanged();
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
            return new ViewHolder(getInflater().inflate(R.layout.item_receipt_account_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
            viewHolder.setData(mDataList.get(position));
        }

        @Override
        public int getItemCount() {
            return mDataList == null ? 0 : mDataList.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            SwitchButton switchButtonZfb;
            ImageView ivPaymentIcon;
            TextView tvPaymentType;
            TextView tvAccountNum;
            public ViewHolder(View itemView) {
                super(itemView);
                switchButtonZfb = itemView.findViewById(R.id.switch_button_zfb);
                ivPaymentIcon = itemView.findViewById(R.id.iv_payment_icon);
                tvPaymentType = itemView.findViewById(R.id.tv_payment_type);
                tvAccountNum = itemView.findViewById(R.id.tv_account_num);
            }

            public void setData(ResponseQueryMyReceiptMoneyAccountList.ReceiptMoneyBean accountItemBean) {

                switchButtonZfb.setChecked(accountItemBean.getStatus().equals(MyConstant.paymentWayIsOpen));

                if(accountItemBean.getPaymentWay() .equals(ZFB.getWay())){
                    ivPaymentIcon.setImageResource(R.drawable.icon_zfb);
                    tvPaymentType.setText("支付宝");
                    tvAccountNum.setText(accountItemBean.getName());

                }else if(accountItemBean.getPaymentWay() .equals(Bank.getWay())){
                    ivPaymentIcon.setImageResource(R.drawable.icon_bank);
                    tvPaymentType.setText("银行");
                    tvAccountNum.setText(accountItemBean.getAccountBankCard());

                }else if(accountItemBean.getPaymentWay() .equals(WeChat.getWay())){
                    ivPaymentIcon.setImageResource(R.drawable.icon_wechat);
                    tvPaymentType.setText("微信");
                    tvAccountNum.setText(accountItemBean.getName());
                }



            }
        }

    }


    class RecyclerViewSpacesItemDecoration extends RecyclerView.ItemDecoration {

        HashMap<String, Integer> mSpaceValueMap;

        public RecyclerViewSpacesItemDecoration(HashMap<String, Integer> mSpaceValueMap) {
            this.mSpaceValueMap = mSpaceValueMap;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view,
                                   RecyclerView parent, RecyclerView.State state) {
            if (mSpaceValueMap.get(TOP_DECORATION) != null)
                outRect.top = mSpaceValueMap.get(TOP_DECORATION);
            if (mSpaceValueMap.get(LEFT_DECORATION) != null)

                outRect.left = mSpaceValueMap.get(LEFT_DECORATION);
            if (mSpaceValueMap.get(RIGHT_DECORATION) != null)
                outRect.right = mSpaceValueMap.get(RIGHT_DECORATION);
            if (mSpaceValueMap.get(BOTTOM_DECORATION) != null)
                outRect.bottom = mSpaceValueMap.get(BOTTOM_DECORATION);

        }


    }

    /**
     * 菜单创建器，在Item要创建菜单的时候调用。
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int position) {
            int width = getResources().getDimensionPixelSize(R.dimen.dp_120);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            SwipeMenuItem deleteItem = new SwipeMenuItem(MyReceiptAccountListActivity.this).setBackground(R.color.colorWhite)
//                        .setImage(R.mipmap.ic_action_delete)
                    .setText("删除")
                    .setTextColor(Color.RED)
                    .setWidth(width)
                    .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
        }
    };


    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge, int position) {
            menuBridge.closeMenu();

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                showMyDialog(position);

            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                Toast.makeText(MyReceiptAccountListActivity.this, "list第" + position + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    };


    private void showMyDialog(final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MyReceiptAccountListActivity.this);
        View view = View.inflate(MyReceiptAccountListActivity.this,R.layout.normal_dialog,null);
        builder.setView(view);
        builder.setCancelable(false);
        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.tv_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        view.findViewById(R.id.tv_sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

                //TODO 执行删除 收款账号的接口

//                receiptMoneyBeanList.remove(position);
//                myAccountAdapter.notifyDataSetChanged();
            }
        });

    }


}
