package com.tong.gao.walletuser.ui.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tong.gao.walletuser.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FragmentRegisterSuccess extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_ug_id)
    TextView tvUgId;
    @BindView(R.id.tv_download_google_code)
    TextView tvDownloadGoogleCode;
    @BindView(R.id.tv_skip_not_download)
    TextView tvSkipNotDownload;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register_success, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        tvTitleBarTitle.setVisibility(View.GONE);
        flBack.setVisibility(View.GONE);

        tvDownloadGoogleCode.setOnClickListener(this);
        tvSkipNotDownload.setOnClickListener(this);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.tv_download_google_code:

                break;


            case R.id.tv_skip_not_download:


                getActivity().finish();

                break;


        }
    }
}
