package com.tong.gao.walletuser.ui.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestAddReceiptMoneyAccount;
import com.tong.gao.walletuser.bean.response.ResponseBaseBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.utils.DialogUtils;
import com.tong.gao.walletuser.utils.GetPhotoFromPhotoAlbum;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.StringUtils;
import com.tong.gao.walletuser.utils.ToastUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
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
    private String[] permissions = {Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private String paymentWay,accountName, accountTypeNum,parseQrCodeFromImage,accountPWD;

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
                paymentWay = MyConstant.paymentWayZfb;
            } else {
                tvTitleBarTitle2.setText("编辑微信");
                tvAccountType.setText("微信账户");
                paymentWay = MyConstant.paymentWayWeChat;
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
                accountTypeNum =etInputAccount.getText().toString();
                accountPWD =etInputAccountPwd.getText().toString();

                if(StringUtils.isEmpty(accountName )||
                        StringUtils.isEmpty(accountTypeNum)||
                        StringUtils.isEmpty(accountPWD)){
                    Toast.makeText(this,"信息不能为空",Toast.LENGTH_LONG).show();
                }else{
                    if(StringUtils.isEmpty(parseQrCodeFromImage)){
                        Toast.makeText(this,"请换一张清晰的二维码图片 ",Toast.LENGTH_LONG).show();
                        tvNextStep.setVisibility(View.VISIBLE);
                    }else{
                        tvNextStep.setVisibility(View.VISIBLE);
                        DialogUtils.showProgressDialog(this,"添加账号中...");
                        addReceiptAccount(new RequestAddReceiptMoneyAccount(paymentWay,accountName, accountTypeNum,parseQrCodeFromImage,accountPWD));
                    }

                    finish();
                }


                break;

        }

    }

    private void addReceiptAccount(RequestAddReceiptMoneyAccount addReceiptMoneyAccount) {

        NetWorks.addReceiptMoneyAccount(addReceiptMoneyAccount, new Observer<ResponseBaseBean>() {
            @Override
            public void onSubscribe(Disposable d) {
                LogUtils.d(""+d);
            }

            @Override
            public void onNext(ResponseBaseBean responseBaseBean) {

                LogUtils.d(""+responseBaseBean.toString());

                if(null != responseBaseBean && MyConstant.resultCodeIsOK .equals(responseBaseBean.getErrcode())){
                    DialogUtils.hideProgressDialog();
                    ToastUtils.showNomalShortToast("添加收款方式成功");

                    startActivity(new Intent(EditZfbOrWeChatCodeActivity.this,MyReceiptAccountListActivity.class));
                    EditZfbOrWeChatCodeActivity.this.finish();
                }else{
                    ToastUtils.showNomalShortToast("添加失败:"+responseBaseBean.getMsg());
                }
            }

            @Override
            public void onError(Throwable e) {
                ToastUtils.showNomalShortToast("添加收款方式失败"+e.toString());
                DialogUtils.hideProgressDialog();
            }

            @Override
            public void onComplete() {
                LogUtils.d("onComplete()");
            }
        });

    }


    private void goPhotoAlbum() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


//    private Bitmap uploadingBitmap;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String photoPath;
        if (requestCode == 2 && resultCode == RESULT_OK) {
            photoPath = GetPhotoFromPhotoAlbum.getRealPathFromUri(this, data.getData());

            parseQrCodeFromImage(data,photoPath);

        }

        super.onActivityResult(requestCode, resultCode, data);

    }


    /**
     * 把二维码从图片中解析出来*/
    private void parseQrCodeFromImage(Intent data, final String photoPath) {

        if (data != null) {
            Uri uri = data.getData();
            ContentResolver cr = getContentResolver();
            try {
                Bitmap mBitmap = MediaStore.Images.Media.getBitmap(cr, uri);//显得到bitmap图片

                CodeUtils.analyzeBitmap(photoPath, new CodeUtils.AnalyzeCallback() {
                    @Override
                    public void onAnalyzeSuccess(Bitmap mBitmap, String result) {
                        ToastUtils.showNomalLongToast("解析二维码成功");
                        parseQrCodeFromImage = result;

                        ivAddQrCode.setVisibility(View.GONE);
                        ivDisplayQrCode.setVisibility(View.VISIBLE);
                        Glide.with(EditZfbOrWeChatCodeActivity.this).load(photoPath).into(ivDisplayQrCode);
                    }

                    @Override
                    public void onAnalyzeFailed() {
                        ToastUtils.showNomalLongToast("解析二维码失败");

                        ivDisplayQrCode.setVisibility(View.GONE);
                        ivAddQrCode.setVisibility(View.VISIBLE);
                    }
                });

                if (mBitmap != null) {
                    mBitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


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
