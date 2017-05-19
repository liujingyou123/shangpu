package com.finance.winport.tab;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.sax.RootElement;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.mine.MyNoticeActivity;
import com.finance.winport.mine.MyScheduleListActivity;
import com.finance.winport.mine.SettingsActivity;
import com.finance.winport.mine.ShopFocusActivity;
import com.finance.winport.mine.SuggestActivity;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.tab.event.SelectImageEvent;
import com.finance.winport.util.LoadingDialogUtil;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.view.StopWatchTextView;
import com.finance.winport.view.imagepreview.ImagePreviewActivity;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.yalantis.ucrop.UCrop;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.app.Activity.RESULT_OK;


/**
 *
 *
 */

public class MineFragment extends BaseFragment {
    private static final int REQUEST_CODE_HEAD = 10;
    @BindView(R.id.mine_winport)
    StopWatchTextView mineWinport;
    @BindView(R.id.mine_appoint)
    StopWatchTextView mineAppoint;
    @BindView(R.id.mine_collection)
    StopWatchTextView mineCollection;
    @BindView(R.id.mine_scan)
    StopWatchTextView mineScan;
    @BindView(R.id.day)
    TextView day;
    @BindView(R.id.solar)
    TextView solar;
    @BindView(R.id.lunar)
    TextView lunar;
    @BindView(R.id.yi)
    TextView yi;
    @BindView(R.id.ji)
    TextView ji;
    private int type;//image type
    private List<String> mSelected;
    @BindView(R.id.tv_focus_right)
    ImageView ivFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.schedule_list)
    RelativeLayout scheduleList;
    @BindView(R.id.setting)
    RelativeLayout setting;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.concern)
    TextView concern;
    Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSelectEvent(SelectImageEvent event) {
        if (event != null) {
            selectImage(REQUEST_CODE_HEAD);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    private void setHuangLi() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private boolean isLogin() {
        UserInfo info = SharedPrefsUtil.getUserInfo();
        return info != null;
    }


    @OnClick({R.id.tv_focus_right, R.id.modify, R.id.schedule_list, R.id.setting, R.id.phone
            , R.id.concern, R.id.shop_img, R.id.ll_mine_winport, R.id.ll_mine_collection
            , R.id.ll_mine_appoint, R.id.ll_mine_scan, R.id.fierce_prediction, R.id.suggest})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_focus_right:
                startActivity(new Intent(getActivity(), MyNoticeActivity.class));
                break;
            case R.id.modify:
                toConcern();
                break;
            case R.id.schedule_list:
                startActivity(new Intent(getActivity(), MyScheduleListActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.phone:
                if (!isLogin()) {// not login
                    toLogin();
                }
                break;
            case R.id.concern:
                if (isLogin()) {// already login
                    toConcern();
                } else {
                    toLogin();
                }
                break;
            case R.id.shop_img:
                if (isLogin()) {// already login
                    scanHeadImage();
                } else {//not login
                    toLogin();
                }
                break;
            case R.id.ll_mine_winport:
                if (!isLogin()) {
                    toLogin();
                    return;
                }
                Intent release = new Intent(context, WinportActivity.class);
                release.putExtra("type", TypeList.RELEASE);
                release.putExtra("title", "我发布的旺铺");
                startActivity(release);
                break;
            case R.id.ll_mine_appoint:
                if (!isLogin()) {
                    toLogin();
                    return;
                }
                Intent appoint = new Intent(context, WinportActivity.class);
                appoint.putExtra("type", TypeList.APPOINT);
                appoint.putExtra("title", "我的约看");
                startActivity(appoint);
                break;
            case R.id.ll_mine_collection:
                if (!isLogin()) {
                    toLogin();
                    return;
                }
                Intent collection = new Intent(context, WinportActivity.class);
                collection.putExtra("type", TypeList.COLLECTION);
                collection.putExtra("title", "我的收藏");
                startActivity(collection);
                break;
            case R.id.ll_mine_scan:
                if (!isLogin()) {
                    toLogin();
                    return;
                }
                Intent scan = new Intent(context, WinportActivity.class);
                scan.putExtra("type", TypeList.SCAN);
                scan.putExtra("title", "最近浏览");
                startActivity(scan);
                break;
            case R.id.fierce_prediction:
                startActivity(new Intent(context, FiercePredictionActivity.class));
                break;
            case R.id.suggest:
                if (!isLogin()) {
                    toLogin();
                    return;
                }
                startActivity(new Intent(context, SuggestActivity.class));
                break;
        }
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
            mSelected = PicturePickerUtils.obtainResult(getActivity().getContentResolver(), data);
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

    // 登录
    private void toLogin() {
        startActivity(new Intent(context, LoginActivity.class));
    }

    // 关注的旺铺页面
    private void toConcern() {
        startActivity(new Intent(getActivity(), ShopFocusActivity.class));
    }

    private void upLoadImage(final String path) {
        new AsyncTask<String, Integer, String>() {
            @Override
            protected void onPreExecute() {
                LoadingDialogUtil.getInstance().showLoading("上传中...");
            }

            @Override
            protected String doInBackground(String... params) {
                return AliOss.getInstance().putObjectFromByteArray(params[0]);
            }

            @Override
            protected void onPostExecute(final String s) {
                LoadingDialogUtil.getInstance().hideLoading();
                UserInfo info = SharedPrefsUtil.getUserInfo();
                if (info != null) {
                    info.data.headPortrait = s;
                    SharedPrefsUtil.saveUserInfo(info);
                }
                Batman.getInstance().fromNet(path, new BatmanCallBack() {
                    @Override
                    public void onSuccess(Bitmap bitmap) {
                        shopImg.setImageBitmap(bitmap);
                    }

                    @Override
                    public void onFailure(Exception error) {
                    }
                });
            }
        }.execute(path);
    }

    private void selectImage(final int requestCode) {
        String[] permissions = new String[]{Manifest.permission.CAMERA, Manifest.permission
                .WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        PermissionsManager.getInstance().requestPermissionsIfNecessaryForResult(this, permissions,
                new PermissionsResultAction() {
                    @Override
                    public void onGranted() {
                        Picker.from(MineFragment.this)
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
            uCrop.withAspectRatio(4, 3);
        } else {
            uCrop.withAspectRatio(16, 9);
        }
        uCrop = advancedConfig(uCrop);
        uCrop.start(context, this);
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


}
