package com.tong.gao.walletuser.ui.activity;


import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.tong.gao.walletuser.AppApplication;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.ui.fragments.MainFragment;
import com.tong.gao.walletuser.utils.AppUtils;
import com.tong.gao.walletuser.utils.Density;
import com.tong.gao.walletuser.utils.LogUtils;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;

public class MainActivity extends ActivityBase {
    // 主界面fragment的tag
    private final String MAIN_CONTENT_FRAGMENT_TAG = "main_content";
    private long exitTime = 0;


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initFragment();
        connect(MyConstant.tokenRongCloud);
    }


    @Override
    public void setOrientation() {
        Density.setOrientation(this, AppUtils.HEIGHT);
    }

    private void initFragment() {
        // 获取Fragment管理器对象
        FragmentManager fm = getSupportFragmentManager();
        // 开启事物
        FragmentTransaction ft = fm.beginTransaction(); // 得到事物操作对象
        // 替换主界面布局
        ft.replace(R.id.fl_main_content, new MainFragment(), MAIN_CONTENT_FRAGMENT_TAG);
        // 提交
        ft.commit();
    }

    private void connect(String token) {
        if (getApplicationInfo().packageName.equals(AppApplication.getCurProcessName(getApplicationContext()))) {

            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                /**
                 * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
                 *                  2.  tokenRongCloud 对应的 appKey 和工程里设置的 appKey 是否一致
                 */
                @Override
                public void onTokenIncorrect() {
                    LogUtils.d("LoginActivity", "--onTokenIncorrect()" );
                }

                /**
                 * 连接融云成功
                 * @param userid 当前 tokenRongCloud 对应的用户 id
                 */
                @Override
                public void onSuccess(String userid) {
                    LogUtils.d("LoginActivity", "--onSuccess" + userid);
                    RongIM.getInstance().startConversation(MainActivity.this,Conversation.ConversationType.PRIVATE,"Justin2", "聊天中");
//                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
//                    finish();
                }

                /**
                 * 连接融云失败
                 * @param errorCode 错误码，可到官网 查看错误码对应的注释
                 */
                @Override
                public void onError(RongIMClient.ErrorCode errorCode) {
                    LogUtils.d("LoginActivity", "--onError()"+ errorCode.toString());
                }
            });
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    public void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
