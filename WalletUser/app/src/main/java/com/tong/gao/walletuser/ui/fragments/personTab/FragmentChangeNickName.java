package com.tong.gao.walletuser.ui.fragments.personTab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.bean.PersonalBean;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.bean.request.RequestChangeNickNameBean;
import com.tong.gao.walletuser.bean.response.ResponseChangeNickNameBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.PreferenceHelper;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FragmentChangeNickName extends Fragment implements View.OnClickListener {


    @BindView(R.id.et_input_new_nick_name)
    EditText etInputNewNickName;
    Unbinder unbinder;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_save)
    TextView tvSave;

    PersonalBean personalBean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_change_nick_name, container, false);
        unbinder = ButterKnife.bind(this, view);
        initData();

        flBack.setOnClickListener(this);
        tvSave.setOnClickListener(this);

        return view;
    }

    private void initData() {
        Object object = PreferenceHelper.getInstance().getObject(MyConstant.personalBeanKey, null);
        if(null != object){
            personalBean = (PersonalBean) object;

            etInputNewNickName.setText(personalBean.getNickname());
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fl_back:

                NavHostFragment.findNavController(this)
                        .navigate(R.id.action_fragmentChangeNickName_to_fragmentChangePersonalInfo);


                break;


            case R.id.tv_save:  //保存

                final String newNickName = etInputNewNickName.getText().toString();
                if(StringUtils.isEmpty(newNickName)){
                    ToastUtils.showNomalShortToast("新的昵称不能为空");
                }else{
                    NetWorks.changeNickName(new RequestChangeNickNameBean(newNickName), new Observer<ResponseChangeNickNameBean>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                            LogUtils.d("开始修改............");

                        }

                        @Override
                        public void onNext(ResponseChangeNickNameBean responseChangeNickNameBean) {
                            LogUtils.d(":"+responseChangeNickNameBean.toString());
                            if(null != responseChangeNickNameBean && MyConstant.resultCodeIsOK .equals(responseChangeNickNameBean.getErrcode())){
                                personalBean.setNickname(newNickName);
                                PreferenceHelper.getInstance().putObject(MyConstant.personalBeanKey, personalBean);

                                NavHostFragment.findNavController(FragmentChangeNickName.this)
                                        .navigate(R.id.action_fragmentChangeNickName_to_fragmentChangePersonalInfo);

                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            LogUtils.d(""+e.toString());
                        }

                        @Override
                        public void onComplete() {
                            LogUtils.d(" onComplete()");
                        }
                    });
                }




                break;



        }
    }
}
