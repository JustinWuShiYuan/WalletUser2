package com.tong.gao.walletuser.ui.fragments;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.utils.GetPhotoFromPhotoAlbum;
import com.tong.gao.walletuser.utils.LogUtils;

import java.io.File;

import androidx.navigation.fragment.NavHostFragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;

public class FragmentRealNameAuthentication extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_title_bar_title)
    TextView tvTitleBarTitle;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_welcome_login)
    TextView tvWelcomeLogin;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.et_input_identity_num)
    EditText etInputIdentityNum;
    @BindView(R.id.et_input_mailbox)
    EditText etInputMailbox;


    @BindView(R.id.fl_upload_identity_card_container)
    FrameLayout flDisplayCard;

    @BindView(R.id.iv_display_identity_card)
    ImageView ivDisplayIdentityCard;

    @BindView(R.id.iv_add_identity_card)
    ImageView ivAddIdentityCard;


    @BindView(R.id.tv_next_step)
    TextView tvNextStep;

    Unbinder unbinder;


    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private File cameraSavePath;//拍照照片路径
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private Bitmap uploadingBitmap;
    private Uri uri;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_real_name_authenthication, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        getPermission();
        return view;
    }


    private void initView() {
        tvTitleBarTitle.setVisibility(View.GONE);
        tvWelcomeLogin.setText("实名认证");

        flBack.setOnClickListener(this);
        flDisplayCard.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);
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
                
                NavHostFragment
                        .findNavController(this)
                        .navigate(R.id.action_realNameAuthentication_to_findPwdIsAuthFragment);


                break;

            case R.id.fl_upload_identity_card_container:        //调用相机上传图片
                //TODO 提交身份信息到接口 成功走下面的逻辑
                goPhotoAlbum();


                break;


            case R.id.tv_next_step:

                NavHostFragment
                        .findNavController(this)
                        .navigate(R.id.action_realNameAuthentication_to_fragmentSubmitSuccess);


                break;

        }
    }

    private void goPhotoAlbum() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }

    private void getPermission() {

        if (EasyPermissions.hasPermissions(getActivity(), permissions)) {
            //已经打开权限
            Toast.makeText(getActivity(), "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            LogUtils.d("拍照返回图片路径:", photoPath);
            ivAddIdentityCard.setVisibility(View.GONE);
            Glide.with(getActivity()).load(photoPath).into(ivDisplayIdentityCard);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            ivAddIdentityCard.setVisibility(View.GONE);
            photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(getActivity(), data.getData());
            uploadingBitmap = BitmapFactory.decodeFile(photoPath);
//            LogUtils.d("uploadingBitmap.getConfig():"+ uploadingBitmap.getConfig());
            Glide.with(getActivity()).load(photoPath).into(ivDisplayIdentityCard);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }
}
