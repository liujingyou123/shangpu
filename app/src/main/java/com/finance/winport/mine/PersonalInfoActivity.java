package com.finance.winport.mine;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.mine.event.ModifyEvent;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.model.PersonalInfoResponse.DataBean.Attention.Plate;
import com.finance.winport.mine.model.PersonalInfoResponse.DataBean.Attention.Vocation;
import com.finance.winport.mine.presenter.IPersonalInfoView;
import com.finance.winport.mine.presenter.PersonalInfoPresenter;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.tab.TypeList;
import com.finance.winport.tab.WinportActivity;
import com.finance.winport.tab.event.SelectImageEvent;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.finance.winport.view.roundview.RoundedImageView;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PersonalInfoActivity extends BaseActivity {
    private static final int REQUEST_CODE_HEAD = 10;
    private int type;//image type
    private List<String> mSelected;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.headImg)
    RoundedImageView headImg;
    @BindView(R.id.headLayout)
    LinearLayout headLayout;
    @BindView(R.id.nikeName)
    TextView nikeName;
    @BindView(R.id.nikeNameLayout)
    LinearLayout nikeNameLayout;
    @BindView(R.id.sign)
    TextView sign;
    @BindView(R.id.signLayout)
    LinearLayout signLayout;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.phoneLayout)
    LinearLayout phoneLayout;
    @BindView(R.id.concern)
    TextView concern;
    @BindView(R.id.concernLayout)
    LinearLayout concernLayout;
    PersonalInfoResponse info;

    @Subscribe
    public void onSelectEvent(SelectImageEvent event) {
        if (event != null) {
            selectImage(REQUEST_CODE_HEAD);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        info = (PersonalInfoResponse) getIntent().getSerializableExtra("info");
        setContentView(R.layout.activity_personal_info);
        ButterKnife.bind(this);
        initView();
        initInfo();
    }

    private void initInfo() {
        if (info != null) {
            setHeadImage(info.data.headPortrait);
            nikeName.setText(info.data.nickName);
            sign.setText(info.data.signature);
            phone.setText(info.data.phone);
            setConcern(info.data.attention.plateList, info.data.attention.vocationList, info.data.attention.areaList);
        }
    }


    @Subscribe
    public void onModifyEvent(ModifyEvent event) {
        switch (event.type) {
            case NICK_NAME:
                if (!TextUtils.isEmpty(event.content)) {
                    nikeName.setText(event.content);
                }
                break;
            case SIGN:
                if (!TextUtils.isEmpty(event.content)) {
                    sign.setText(event.content);
                }
                break;
            case PHONE:
                if (!TextUtils.isEmpty(event.content)) {
                    phone.setText(event.content);
                }
                break;
            case CONCERN_TYPE:
                if (info != null) {
                    info.data.attention.plateList = event.plateList;
                    info.data.attention.vocationList = event.vocationList;
                    info.data.attention.areaList = event.areaList;
                }
                setConcern(event.plateList, event.vocationList, event.areaList);
                break;

        }
    }

    private void setConcern(List<Plate> plateList, List<Vocation> vocationList, List<Integer> areaList) {
        StringBuilder sb = new StringBuilder();
        if (plateList != null && plateList.size() > 0) {
            for (Plate p : plateList) {
                sb.append(p.plateName).append("、");
            }
            sb.replace(sb.length() - 1, sb.length(), "-");
        }
        if (vocationList != null && vocationList.size() > 0) {
            for (Vocation v : vocationList) {
                sb.append(v.vocationName).append("、");
            }
            sb.replace(sb.length() - 1, sb.length(), "-");
        }

        if (areaList != null && areaList.size() > 0) {
            //1：20m²以下 2：20-50m²  3：50-100m²4：100-200m²  5：200-500m²  6:500-1000m²  7:1000m²以上
            String[] area = new String[]{"20m²以下", "20-50m²", "50-100m²", "100-200m", "200-500m²", "500-1000m²", "1000m²以上"};
            for (Integer i : areaList) {
                if (i >= 0 && i < area.length) {
                    sb.append(area[i - 1]).append("、");
                }
            }
            sb.delete(sb.length() - 1, sb.length());
        }
        concern.setText(sb.toString());
    }


    private void initView() {
        tvFocusHouse.setText("个人资料");
        try {
            setHeadImage(SharedPrefsUtil.getUserInfo().data.headPortrait);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick({R.id.headLayout, R.id.nikeNameLayout, R.id.signLayout, R.id.phoneLayout, R.id.concernLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.headLayout:
                scanHeadImage();
                break;
            case R.id.nikeNameLayout:
                toNickName();
                break;
            case R.id.signLayout:
                toSign();
                break;
            case R.id.phoneLayout:
                toPhone();
                break;
            case R.id.concernLayout:
                toConcernInfo();
                break;
        }
    }

    private void toNickName() {
        startActivity(new Intent(context, InfoModifyActivity.class).putExtra("type", ModifyEvent.InfoType.NICK_NAME));
    }

    private void toSign() {
        startActivity(new Intent(context, InfoModifyActivity.class).putExtra("type", ModifyEvent.InfoType.SIGN));
    }

    private void toPhone() {
        startActivity(new Intent(context, InfoModifyActivity.class).putExtra("type", ModifyEvent.InfoType.PHONE).putExtra("data", phone.getText().toString()));
    }

    // 我关注的商铺
    private void toConcernInfo() {
        ArrayList<Integer> areaList = info == null ? null : new ArrayList<>(info.data.attention.areaList);
        ArrayList<PersonalInfoResponse.DataBean.Attention.Vocation> vocationList = info == null ? null : new ArrayList<>(info.data.attention.vocationList);
        ArrayList<PersonalInfoResponse.DataBean.Attention.Plate> plateList = info == null ? null : new ArrayList<>(info.data.attention.plateList);
        Intent intent = new Intent(context, ShopFocusActivity.class);
        intent.putIntegerArrayListExtra("areaList", areaList);
        intent.putExtra("vocationList", vocationList);
        intent.putExtra("plateList", plateList);
        startActivity(intent);
    }

    private void scanHeadImage() {
        UserInfo info = SharedPrefsUtil.getUserInfo();
        if (info != null && !TextUtils.isEmpty(info.data.headPortrait)) {
            startActivity(new Intent(context, WinportActivity.class).putExtra("type", TypeList.SCAN_HEAD));
        } else {
            selectImage(REQUEST_CODE_HEAD);
        }
    }

    String headImage = "headImg.jpg";

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //选择或拍照
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_HEAD) {
            mSelected = PicturePickerUtils.obtainResult(getContentResolver(), data);
            if (mSelected != null && mSelected.size() > 0) {
                if (requestCode == REQUEST_CODE_HEAD) {
                    type = requestCode;
                    Uri destinationUri = Uri.fromFile(new File(context.getCacheDir(), System.currentTimeMillis() + "_" + headImage));
                    startCrop(PicturePickerUtils.obtainResult(data).get(0), destinationUri);
                }
            }

        } else if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {//照片裁剪
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri == null) return;
            final String path = resultUri.getPath();
            if (TextUtils.isEmpty(path)) return;
            upLoadImage(path);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }
    }

    LoadingDialog loading;

    private void upLoadImage(final String path) {
        if (loading == null) {
            loading = new LoadingDialog(context);
        }
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                loading.show();
            }

            @Override
            protected String doInBackground(String... params) {
                return AliOss.getInstance().putObjectFromByteArray(AliOss.DIR_CUSTOMER, params[0]);
            }

            @Override
            protected void onPostExecute(final String s) {
                updateHeadInfo(path, s);
            }
        }.execute(path);
    }

    private void updateHeadInfo(final String path, final String headUrl) {
        HashMap<String, Object> params = new HashMap<>();
        params.put("headPortrait", headUrl);
        PersonManager.getInstance().updateHeadInfo(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                if (isFinishing()) return;
                loading.dismiss();
                saveHeadInfo(headUrl);
                setHeadImage(path);
            }

            @Override
            public void failure(Throwable throwable) {
                if (isFinishing()) return;
                loading.dismiss();
            }
        });
    }

    private void saveHeadInfo(String headUrl) {
        UserInfo info = SharedPrefsUtil.getUserInfo();
        if (info != null) {
            info.data.headPortrait = headUrl;
            SharedPrefsUtil.saveUserInfo(info);
        }
    }

    private void setHeadImage(String path) {
        Batman.getInstance().fromNet(path, new BatmanCallBack() {
            @Override
            public void onSuccess(Bitmap bitmap) {
                headImg.setImageBitmap(bitmap);
            }

            @Override
            public void onFailure(Exception error) {
            }
        });
    }

    private void selectImage(final int requestCode) {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission
                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        Picker.from((Activity) context)
                                .count(1)
                                .enableCamera(true)
                                .setEngine(new GlideEngine())
                                .forResult(requestCode);
                    }

                    @Override
                    public void onDenied(String permission) {
                    }


                });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions,
                grantResults);
    }

    //开启裁剪
    private void startCrop(Uri sourceUri, Uri destinationUri) {
        UCrop uCrop = UCrop.of(sourceUri, destinationUri);
        if (type == REQUEST_CODE_HEAD) {
            uCrop.withAspectRatio(1, 1);
        } else {
            uCrop.withAspectRatio(16, 9);
        }
        uCrop = advancedConfig(uCrop);
        uCrop.start(this);
    }

    private UCrop advancedConfig(@NonNull UCrop uCrop) {
        UCrop.Options options = new UCrop.Options();
        options.setCompressionQuality(60);
        options.setMaxScaleMultiplier(5);
        options.setImageToCropBoundsAnimDuration(666);
        options.setShowCropFrame(true);
        options.setCropGridStrokeWidth(2);
        options.setCropGridColumnCount(2);
        options.setCropGridRowCount(1);
        options.setCompressionFormat(Bitmap.CompressFormat.JPEG);
        // Color palette
        options.setToolbarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setStatusBarColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setActiveWidgetColor(ContextCompat.getColor(context, R.color.colorPrimary));
        options.setToolbarWidgetColor(ContextCompat.getColor(context, R.color.white));
        return uCrop.withOptions(options);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

}
