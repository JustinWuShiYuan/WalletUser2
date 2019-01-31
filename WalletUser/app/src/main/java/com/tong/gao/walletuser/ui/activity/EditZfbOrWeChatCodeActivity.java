package com.tong.gao.walletuser.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.utils.GetPhotoFromPhotoAlbum;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pub.devrel.easypermissions.EasyPermissions;

public class EditZfbOrWeChatCodeActivity extends ActivityBase implements View.OnClickListener,EasyPermissions.PermissionCallbacks {

    @BindView(R.id.tv_title_bar_title2)
    TextView tvTitleBarTitle2;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.fl_add)
    FrameLayout flAdd;
    @BindView(R.id.cl_root_view)
    ConstraintLayout clRootView;
    @BindView(R.id.iv_display_qr_code)
    ImageView ivDisplayQrCode;
    @BindView(R.id.iv_add_qr_code)
    ImageView ivAddQrCode;
    @BindView(R.id.tv_name_des)
    TextView tvNameDes;
    @BindView(R.id.et_input_name)
    EditText etInputName;
    @BindView(R.id.tv_account_type)
    TextView tvAccountType;
    @BindView(R.id.et_input_account)
    EditText etInputAccount;
    @BindView(R.id.tv_account_pwd)
    TextView tvAccountPwd;
    @BindView(R.id.et_input_account_pwd)
    EditText etInputAccountPwd;
    @BindView(R.id.tv_next_step)
    TextView tvNextStep;

    private String qrCodeType = null;
    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果


    private File cameraSavePath;//拍照照片路径
    private Uri uri;
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String accountName,accountType,accountPWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_zfb_or_we_chat_code);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (null != intent) {
            qrCodeType = intent.getStringExtra(MyConstant.EditQrCodeType);
            initView();
            getPermission();
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_edit_zfb_or_we_chat_code;
    }

    @Override
    protected void initView() {

        if (null != qrCodeType) {

            if (qrCodeType.equals("ZFB")) {
                tvTitleBarTitle2.setText("编辑支付宝");
                tvAccountType.setText("支付宝账户");
            } else {
                tvTitleBarTitle2.setText("编辑微信");
                tvAccountType.setText("微信账户");
            }

        }

        flAdd.setVisibility(View.GONE);

        flBack.setOnClickListener(this);
        ivAddQrCode.setOnClickListener(this);
        tvNextStep.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.fl_back:
                finish();
                break;


            case R.id.iv_add_qr_code:
                goPhotoAlbum();

                break;

            case R.id.tv_next_step:     //点击下一步

                accountName =etInputName.getText().toString();
                accountType =etInputAccount.getText().toString();
                accountPWD =etInputAccountPwd.getText().toString();

                if(StringUtils.isEmpty(accountName )||
                        StringUtils.isEmpty(accountType)||
                        StringUtils.isEmpty(accountPWD)){
                    Toast.makeText(this,"信息不能为空",Toast.LENGTH_LONG).show();
                }else{
                    //将+ 显示成完成 上传信息
                    //TODO 触发上传动作
                    Toast.makeText(this,"上传中成功 ",Toast.LENGTH_LONG).show();
//                    tvNextStep.setVisibility(View.GONE);
                    finish();
                }


                break;

        }

    }


    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    private Bitmap uploadingBitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 1 && resultCode == RESULT_OK) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoPath = String.valueOf(cameraSavePath);
            } else {
                photoPath = uri.getEncodedPath();
            }
            LogUtils.d("拍照返回图片路径:", photoPath);
            ivAddQrCode.setVisibility(View.GONE);
            Glide.with(this).load(photoPath).into(ivDisplayQrCode);
        } else if (requestCode == 2 && resultCode == RESULT_OK) {
            ivAddQrCode.setVisibility(View.GONE);
            photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());
            uploadingBitmap = BitmapFactory.decodeFile(photoPath);
            LogUtils.d("uploadingBitmap.getConfig():"+ uploadingBitmap.getConfig()+" photoPath:"+photoPath);
            Glide.with(this).load(photoPath).into(ivDisplayQrCode);
        }

        super.onActivityResult(requestCode, resultCode, data);

    }


    //获取权限
    private void getPermission() {
        if (EasyPermissions.hasPermissions(this, permissions)) {
            //已经打开权限
            Toast.makeText(this, "已经申请相关权限", Toast.LENGTH_SHORT).show();
        } else {
            //没有打开相关权限、申请权限
            EasyPermissions.requestPermissions(this, "需要获取您的相册、照相使用权限", 1, permissions);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //框架要求必须这么写
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }


    //成功打开权限
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {

        Toast.makeText(this, "相关权限获取成功", Toast.LENGTH_SHORT).show();
    }

    //用户未同意权限
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        Toast.makeText(this, "请同意相关权限，否则功能无法使用", Toast.LENGTH_SHORT).show();
    }
}
