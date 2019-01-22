package com.tong.gao.walletuser.ui.fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.ui.adaper.ConversationListAdapterEx;
import com.tong.gao.walletuser.ui.fragments.message.FragmentMyOrderInform;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.UIUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.rong.imkit.RongContext;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imlib.model.Conversation;

public class MessageFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitle;

    @BindView(R.id.rl_order_inform)
    RelativeLayout rlOrderInform;
    @BindView(R.id.rl_connect_customer_service)
    RelativeLayout rlConnectCustomerService;
    @BindView(R.id.rl_system_inform)
    RelativeLayout rlSystemInform;

    @BindView(R.id.ll_tab_service_container)
    LinearLayout llTabServiceContainer;

    @BindView(R.id.fl_tab_message_pager_content)
    FrameLayout flTabMessagePagerContent;

    Fragment conversationList = null;

    Unbinder unbinder;

    // 主界面fragment的tag
    private final String Fragment_Tag_Message = "Fragment_Tag_Message";

    private FragmentMyOrderInform fragmentOrderInform = null;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }


    private void initView() {
        tvTitle.setText("消息列表");

        flBack.setOnClickListener(this);
        rlOrderInform.setOnClickListener(this);
        rlConnectCustomerService.setOnClickListener(this);
        rlSystemInform.setOnClickListener(this);


        if (null == conversationList) {
            conversationList = initConversationList();
        }
        changeFragment(conversationList, false, R.id.iv_title_bar_menu2);

    }

    private void changeFragment(Fragment conversationList, final boolean isHideLL, int titleTabId) {

        // 获取Fragment管理器对象
        FragmentManager fm = getActivity().getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(R.id.fl_tab_message_pager_content, conversationList, Fragment_Tag_Message);
        // 提交
        ft.commit();


        llTabServiceContainer.post(new Runnable() {
            @Override
            public void run() {
                llTabServiceContainer.setVisibility(isHideLL? View.GONE:View.VISIBLE);
                flBack.setVisibility(isHideLL? View.VISIBLE: View.GONE);
            }
        });


        switch (titleTabId){
            case  R.id.fl_back:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("消息列表");
                    }
                });
                break;
            case  R.id.rl_order_inform:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("订单通知");
                    }
                });
                break;

            case R.id.rl_connect_customer_service:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("联系客服");

                    }
                });

                break;

            case R.id.rl_system_inform:
                tvTitle.post(new Runnable() {
                    @Override
                    public void run() {
                        tvTitle.setText("系统通知");
                    }
                });

                break;
        }

    }

    private ConversationListFragment mConversationListFragment = null;
    private boolean isDebug = false;
    private Conversation.ConversationType[] mConversationsTypes = null;

    private Fragment initConversationList() {
        if (mConversationListFragment == null) {
            ConversationListFragment listFragment = new ConversationListFragment();
            listFragment.setAdapter(new ConversationListAdapterEx(RongContext.getInstance()));
            Uri uri;
            if (isDebug) {
                uri = Uri.parse("rong://" + UIUtils.getContext().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "true") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "true")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .appendQueryParameter(Conversation.ConversationType.DISCUSSION.getName(), "true")
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM,
                        Conversation.ConversationType.DISCUSSION
                };

            } else {
                uri = Uri.parse("rong://" + UIUtils.getContext().getApplicationInfo().packageName).buildUpon()
                        .appendPath("conversationlist")
                        .appendQueryParameter(Conversation.ConversationType.PRIVATE.getName(), "false") //设置私聊会话是否聚合显示
                        .appendQueryParameter(Conversation.ConversationType.GROUP.getName(), "false")//群组
                        .appendQueryParameter(Conversation.ConversationType.PUBLIC_SERVICE.getName(), "false")//公共服务号
                        .appendQueryParameter(Conversation.ConversationType.APP_PUBLIC_SERVICE.getName(), "false")//订阅号
                        .appendQueryParameter(Conversation.ConversationType.SYSTEM.getName(), "true")//系统
                        .build();
                mConversationsTypes = new Conversation.ConversationType[]{Conversation.ConversationType.PRIVATE,
                        Conversation.ConversationType.GROUP,
                        Conversation.ConversationType.PUBLIC_SERVICE,
                        Conversation.ConversationType.APP_PUBLIC_SERVICE,
                        Conversation.ConversationType.SYSTEM
                };
            }
            listFragment.setUri(uri);
            mConversationListFragment = listFragment;
            return listFragment;
        } else {
            return mConversationListFragment;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


            case R.id.fl_back:                          //返回
                fragmentOrderInform = null;

                if (null == conversationList) {
                    conversationList = initConversationList();
                }
                changeFragment(conversationList, false, R.id.iv_title_bar_menu2);

                break;

            case R.id.rl_order_inform:                  //订单通知

                if(null == fragmentOrderInform){
                    fragmentOrderInform = new FragmentMyOrderInform();
                }

                rlOrderInform.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        fragmentOrderInform.loadData();
                        rlOrderInform.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                });

                changeFragment(fragmentOrderInform, true, R.id.rl_order_inform);

                break;

            case R.id.rl_connect_customer_service:      //联系客服

                break;

            case R.id.rl_system_inform:                 //系统通知

                break;

        }
    }
}
