package com.tong.gao.walletuser.ui.activity.identity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.tong.gao.walletuser.R;
import com.tong.gao.walletuser.base.ActivityBase;
import com.tong.gao.walletuser.bean.request.RequestOcrIdcard;
import com.tong.gao.walletuser.bean.response.ResponseOcrBean;
import com.tong.gao.walletuser.constants.MyConstant;
import com.tong.gao.walletuser.net.NetWorks;
import com.tong.gao.walletuser.net.OkHttp3Utils;
import com.tong.gao.walletuser.ui.view.WheelDialog;
import com.tong.gao.walletuser.utils.BitmapUtils;
import com.tong.gao.walletuser.utils.LogUtils;
import com.tong.gao.walletuser.utils.ToastUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class IdentityInfoActivity extends ActivityBase {

    private static final int PHOTO_REQUEST_CAREMA = 1;// 拍照
    private static final int PHOTO_REQUEST_GALLERY = 2;// 从相册中选择
    private static final int PHOTO_REQUEST_CUT = 3;// 结果
    private static final String FILE_PROVIDER_AUTHORITY = "com.tong.gao.walletuser.FileProvider";

    @BindView(R.id.ll_has_auth)
    View authed_container;
    @BindView(R.id.ll_un_auth)
    View un_auth_container;

    @BindView(R.id.iv_left_show)
    ImageView iv_left_show;

    @BindView(R.id.iv_right_show)
    ImageView iv_right_show;

    boolean hasPass = false;


    @BindView(R.id.et_realname)
    public EditText et_realname;

    @BindView(R.id.et_user_card_id)
    public EditText et_user_card_id;

    @BindView(R.id.cb_option)
    public CheckBox cb_option;

    private boolean isEditLeft;


    @BindView(R.id.bt_confirm)
    public Button bt_confirm;

    @BindView(R.id.bt_left_upload)
    public Button bt_left_upload;

    @BindView(R.id.bt_right_upload)
    public Button bt_right_upload;

    /**
     * 图片资源链接
     */
    private Uri mLeftUri, mRightUri;


    private ResponseOcrBean.Card card;

    @Override
    protected int getLayout() {
        return R.layout.layout_cer_info_for_idcard;
    }

    @Override
    protected void initView() {

    }

    @OnClick(R.id.fl_back)
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkHasPass();
    }

    private void checkHasPass() {
        authed_container.setVisibility(!hasPass ? View.GONE : View.VISIBLE);
        un_auth_container.setVisibility(hasPass ? View.GONE : View.VISIBLE);
    }

    @OnClick(R.id.left_choose_idcrad)
    public void onClickLeftImage() {
        isEditLeft = true;
        chooseImage();
    }

    @OnClick(R.id.right_choose_idcrad)
    public void onClickRightImage() {
        isEditLeft = false;
        chooseImage();
    }

    private void chooseImage() {
        String[] stringList = {"拍照", "从相册选择"};

        new AlertDialog.Builder(this).setItems(stringList, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        choosePicFromCamera();
                        break;
                    case 1:
                        choosePicFromGallery();
                        break;
                }
            }
        }).create().show();
    }

    public void choosePicFromGallery() {
        //从相册找图
        LogUtils.d("从相册找图");
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
    }


    private void choosePicFromCamera() {
        LogUtils.d("行相机拍摄");
        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//打开相机的Intent
        if (takePhotoIntent.resolveActivity(getPackageManager()) != null) {//这句作用是如果没有相机则该应用不会闪退，要是不加这句则当系统没有相机应用的时候该应用会闪退
            File imageFile = createImageFile();//创建用来保存照片的文件
            Uri uri;
            if (imageFile != null) {
                if (Build.VERSION.SDK_INT >= 24) {
                    /*7.0以上要通过FileProvider将File转化为Uri*/
                    uri = FileProvider.getUriForFile(this, FILE_PROVIDER_AUTHORITY, imageFile);
                } else {
                    /*7.0以下则直接使用Uri的fromFile方法将File转化为Uri*/
                    uri = Uri.fromFile(imageFile);
                }
                if (isEditLeft) {
                    mLeftUri = uri;
                } else {
                    mRightUri = uri;
                }
                takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);//将用于输出的文件Uri传递给相机
                startActivityForResult(takePhotoIntent, PHOTO_REQUEST_CAREMA);//打开相机
            }
        }
    }


    /**
     * 创建用来存储图片的文件，以时间来命名就不会产生命名冲突
     *
     * @return 创建的图片文件
     */
    private File createImageFile() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (!storageDir.exists()) {
            storageDir.mkdirs();
        }
        File imageFile = null;
        try {
            imageFile = File.createTempFile(imageFileName, ".jpg", storageDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageFile;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PHOTO_REQUEST_CAREMA) {
                ImageView iv = isEditLeft ? iv_left_show : iv_right_show;
                Uri uri = isEditLeft ? mLeftUri : mRightUri;
                Glide.with(this).load(uri).into(iv);
                iv.setBackgroundColor(Color.WHITE);
                if (isEditLeft) doOcr();
            } else if (requestCode == PHOTO_REQUEST_GALLERY) {

                Uri uri = data.getData();
                if (isEditLeft) {
                    mLeftUri = uri;
                } else {
                    mRightUri = uri;
                }
                ImageView iv = isEditLeft ? iv_left_show : iv_right_show;
                Glide.with(this).load(uri).into(iv);
                iv.setBackgroundColor(Color.WHITE);
                if (isEditLeft) doOcr();
            }

        }
    }


    /*@OnClick(R.id.bt_left_upload)
    public void onClickLeftUpload() {
        if (TextUtils.isEmpty(leftImageUrl)) {
            ToastUtils.showNomalShortToast("请先选择需要上传的身份证照片");
            return;
        }
        showProgressDialog("");
        AliOSSUploadClient aliOSSUploadClient = new AliOSSUploadClient();
        aliOSSUploadClient.preparedUploadFile(leftImageUrl, new AliOSSUploadClient.IOSSUploadListener() {
            @Override
            public void onUploadSuccess(final String url) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        aliLeftImgUrl = url;
                        hideProgressDialog();
                        ToastUtils.showNomalShortToast("上传成功");
                    }
                });
            }

            @Override
            public void onUploadError() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        ToastUtils.showNomalShortToast("上传失败");
                    }
                });

            }
        });
    }

    @OnClick(R.id.bt_right_upload)
    public void onClickRightUpload() {
        if (TextUtils.isEmpty(rightImageUrl)) {
            ToastUtils.showNomalShortToast("请先选择需要上传的身份证信息");
            return;
        }

        showProgressDialog("");
        AliOSSUploadClient aliOSSUploadClient = new AliOSSUploadClient();
        aliOSSUploadClient.preparedUploadFile(leftImageUrl, new AliOSSUploadClient.IOSSUploadListener() {
            @Override
            public void onUploadSuccess(final String url) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        aliRightImgUrl = url;
                        hideProgressDialog();
                        ToastUtils.showNomalShortToast("上传成功");
                    }
                });

            }

            @Override
            public void onUploadError() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideProgressDialog();
                        ToastUtils.showNomalShortToast("上传失败");
                    }
                });

            }
        });
    }*/

    @OnClick(R.id.bt_confirm)
    public void onClickConfirm() {
        String realname = et_realname.getText().toString();
        String certificateno = et_user_card_id.getText().toString();

        if (TextUtils.isEmpty(realname)) {
            ToastUtils.showNomalShortToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(certificateno)) {
            ToastUtils.showNomalShortToast("请输入身份证号");
            return;
        }

        if (mLeftUri == null) {
            ToastUtils.showNomalShortToast("请上传身份证有人脸的照片");
            return;
        }
        if (mRightUri == null) {
            ToastUtils.showNomalShortToast("请上传身份证有国徽的照片");
            return;
        }

        if (!checkCard(realname, certificateno)) {
            ToastUtils.showNomalShortToast("身份证识别失败，请上传正确的身份证");
            return;
        }

    }

    public void preparedSubmit() {
        String realname = et_realname.getText().toString();
        String certificateno = et_user_card_id.getText().toString();

        if (TextUtils.isEmpty(realname)) {
            ToastUtils.showNomalShortToast("请输入姓名");
            return;
        }
        if (TextUtils.isEmpty(certificateno)) {
            ToastUtils.showNomalShortToast("请输入身份证号");
            return;
        }

        if (mLeftUri == null) {
            ToastUtils.showNomalShortToast("请上传身份证有人脸的照片");
            return;
        }
        if (mRightUri == null) {
            ToastUtils.showNomalShortToast("请上传身份证有国徽的照片");
            return;
        }
        if (!checkCard(realname, certificateno)) {
            ToastUtils.showNomalShortToast("身份证识别失败，请上传正确的身份证");
            return;
        }

    }

    int count = 0;

    private void doOcr() {
        LogUtils.d("doOcr->" + mLeftUri.toString());
        showProgressDialog("身份信息识别中");
        Observable.just(mLeftUri).observeOn(Schedulers.io())
                .map(new Function<Uri, String>() {
                    @Override
                    public String apply(Uri uri) throws Exception {
                        String base64 = getBase64(uri);
                        LogUtils.d("base64------->" + base64);
                        OkHttpClient client = OkHttp3Utils.getOkHttpClient();
                        RequestBody requestBody = new FormBody.Builder()
                                .add("api_key", MyConstant.face_AppKey)
                                .add("api_secret", MyConstant.face_AppSecret)
                                .add("image_base64", base64)
                                .add("legality", "1")
                                .build();
                        LogUtils.d("requestBody------>" + requestBody.contentLength());
                        Request request = new Request.Builder().url(MyConstant.baseUrlFace + MyConstant.postOcr).post(requestBody).build();
                        Response response = client.newCall(request).execute();
                        LogUtils.d("response has get------->");
                        return response.body().string();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.d("onSubscribe");
                    }

                    @Override
                    public void onNext(String s) {
//                        requestCheckCard(s);
                        LogUtils.d("------->" + s);
                        if (s == null || s.isEmpty()) {
                            ToastUtils.showNomalShortToast("没有得到数据");
                            return;
                        }
                        ResponseOcrBean ocr = new Gson().fromJson(s, ResponseOcrBean.class);
                        //TODO解析bean,或者上传
                        if (!ocr.isVail()) {
                            ToastUtils.showNomalShortToast("不通过");
                            return;
                        }
                        ResponseOcrBean.Card c = ocr.getCards().get(0);
                        if ("front".equals(c.getSide())) {
                            card = c;
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.d("onError:" + e.toString());
                        hideProgressDialog();
                        count++;
                        if (count < 4) doOcr();
                    }

                    @Override
                    public void onComplete() {
                        LogUtils.d(" onComplete()");
                        hideProgressDialog();
                    }
                });
    }

    String getBase64(Uri uri) {
        try {
            BitmapFactory.Options option = new BitmapFactory.Options();
            option.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(getContentResolver().openInputStream(uri), null, option);
            LogUtils.d("option.outWidth=" + option.outWidth + ", option.outHeight=" + option.outHeight);
            if (option.outWidth > 120 || option.outHeight > 120) {
                int scale = Math.max(option.outWidth / 120, option.outHeight / 120);
                option.inSampleSize = scale;
            }
            option.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(mLeftUri), null, option);
            return BitmapUtils.imgToBase64(null, bitmap, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 检查身份证是否和填入的相同
     *
     * @param name
     * @param cardId
     * @return
     */
    private boolean checkCard(String name, String cardId) {
        if (card != null) {
            ToastUtils.showNomalShortToast("身份证ocr还未完成,请重新上传身份证试试");
            return false;
        }
        if (card.getName() == null || !card.getName().equals(name)) return false;
        if (card.getId_card_number() == null || !card.getId_card_number().equals(cardId))
            return false;
        return true;
    }
}
