package com.tong.gao.walletuser.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentFindPwdIsAuth extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.rl_have_authenticatied)
    RelativeLayout rlHaveAuthenticatied;
    @BindView(R.id.rl_have_not_authentication)
    RelativeLayout rlHaveNotAuthentication;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_find_pwd_is_auth, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitleBarTitle.setVisibility(View.GONE);
        flBack.setOnClickListener(this);
        rlHaveAuthenticatied.setOnClickListener(this);
        rlHaveNotAuthentication.setOnClickListener(this);
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
                getActivity().finish();

                break;

            case R.id.rl_have_authenticatied:

                NavHostFragment
                        .findNavController(this)
                        .navigate(R.id.action_findPwdIsAuthFragment_to_realNameAuthentication);

                break;

            case R.id.rl_have_not_authentication:

                NavHostFragment
                        .findNavController(this)
                        .navigate(R.id.action_findPwdIsAuthFragment_to_fragmentHaveNotAuthentication);

                break;

        }
    }
}
