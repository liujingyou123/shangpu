package com.finance.winport.tab;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
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
import com.finance.winport.image.Batman;
import com.finance.winport.image.BatmanCallBack;
import com.finance.winport.mine.MyNoticeActivity;
import com.finance.winport.mine.MyScheduleListActivity;
import com.finance.winport.mine.SettingsActivity;
import com.finance.winport.mine.ShopFocusActivity;
import com.finance.winport.mine.SuggestActivity;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.presenter.IPersonalInfoView;
import com.finance.winport.mine.presenter.PersonalInfoPresenter;
import com.finance.winport.mine.presenter.ShopFocusPresenter;
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.tab.event.SelectImageEvent;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportCounts;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.util.LoadingDialogUtil;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.view.StopWatchTextView;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
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
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

import static android.app.Activity.RESULT_OK;


/**
 *
 *
 */

public class MineFragment extends BaseFragment implements IPersonalInfoView {
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
    private PersonalInfoPresenter mPresenter;
    private ArrayList<Integer> selectList = new ArrayList<>();
    private String industryName,blockName,districtName,industryId,blockId,districtId;

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
        init();
        asyncRelevant();
        return root;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PersonalInfoPresenter(this);
        }
        mPresenter.getPersonalInfo();

    }

    private void init() {
        if (isLogin()) {
            setHeadImage(SharedPrefsUtil.getUserInfo().data.headPortrait);
            getData();
        }
    }

    private void setHuangLi(Lunar.DataBean data) {
        day.setText(data.day);
        solar.setText(data.year + "-" + data.month);
        lunar.setText(data.lunarmonth + data.lunarday);
        Observable.from(data.huangli.yi).take(5).subscribe(new Subscriber<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onCompleted() {
                yi.setText(sb.toString());
                Log.d("Mine", "yi-onCompleted->");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + " ");
                Log.d("Mine", "yi-onNext->" + s);
            }
        });

        Observable.from(data.huangli.ji).take(5).subscribe(new Subscriber<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onCompleted() {
                ji.setText(sb.toString());
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + " ");
            }
        });
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

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            asyncRelevant();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        asyncRelevant();
    }

    //获取个人中心相关数据
    private void asyncRelevant() {
        if (isLogin()) {
            getUnReadMsg();
            getWinportCounts();
            getLunar();
        }
    }

    //获取未读消息
    private void getUnReadMsg() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("receiveType", 0);//0:客户 1：业务员
        PersonManager.getInstance().getUnReadMsg(params, new NetworkCallback<UnReadMsg>() {
            @Override
            public void success(UnReadMsg response) {
                if (response != null && response.isSuccess()) {
                    ivFocusRight.setActivated(response.data);
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    //我的发布、约看、收藏、浏览、未来日程 总数统计接口
    private void getWinportCounts() {
        HashMap<String, Object> params = new HashMap<>();
        PersonManager.getInstance().getWinportCounts(params, new NetworkCallback<WinportCounts>() {
            @Override
            public void success(WinportCounts response) {
                if (response != null && response.isSuccess()) {
                    mineWinport.setText(response.data.issuerCount);
                    mineAppoint.setText(response.data.visitCount);
                    mineCollection.setText(response.data.collectedCount);
                    mineScan.setText(response.data.browseCount);
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    //获取农历
    private void getLunar() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("currentDate", "2017-5-22");
        PersonManager.getInstance().getLunar(params, new NetworkCallback<Lunar>() {
            @Override
            public void success(Lunar response) {
                if (response != null && response.isSuccess()) {
                    setHuangLi(response.data);
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
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
        Intent intent = new Intent(getActivity(), ShopFocusActivity.class);
        intent.putIntegerArrayListExtra("areaList",selectList);
        intent.putExtra("industryName",industryName);
        intent.putExtra("blockName",blockName);
        intent.putExtra("districtName",districtName);
        intent.putExtra("industryId",industryId);
        intent.putExtra("districtId",districtId);
        intent.putExtra("blockId",blockId);
        startActivity(intent);
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
                setHeadImage(path);
            }
        }.execute(path);
    }

    private void setHeadImage(String path) {
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


    @Override
    public void showPersonalInfo(PersonalInfoResponse response) {


        selectList.add(1);
        selectList.add(3);
        selectList.add(4);
        selectList.add(5);
        selectList.add(6);
//        selectList = response.getData().getList();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i <selectList.size() ; i++) {
            if(i==0){

                if(selectList.size()==1){

                    switch (selectList.get(i)) {
                        case 0:
                            s.append("20㎡以下");
                            break;
                        case 1:
                            s.append("20-50㎡");
                            break;
                        case 2:
                            s.append("50-100㎡");
                            break;
                        case 3:
                            s.append("100-200㎡");
                            break;
                        case 4:
                            s.append("200-500㎡");
                            break;
                        case 5:
                            s.append("500-1000㎡");
                            break;
                        case 6:
                            s.append("1000㎡以上");
                            break;
                    }

                }
                else{
                    switch (selectList.get(i)) {
                        case 0:
                            s.append("20㎡以下"+"\n");
                            break;
                        case 1:
                            s.append("20-50㎡"+"\n");
                            break;
                        case 2:
                            s.append("50-100㎡"+"\n");
                            break;
                        case 3:
                            s.append("100-200㎡"+"\n");
                            break;
                        case 4:
                            s.append("200-500㎡"+"\n");
                            break;
                        case 5:
                            s.append("500-1000㎡"+"\n");
                            break;
                        case 6:
                            s.append("1000㎡以上"+"\n");
                            break;
                    }
                }
            }
            else if(selectList.size()<5){
                switch (selectList.get(i)) {
                    case 0:
                        s.append("-"+"20㎡以下");
                        break;
                    case 1:
                        s.append("-"+"20-50㎡");
                        break;
                    case 2:
                        s.append("-"+"50-100㎡");
                        break;
                    case 3:
                        s.append("-"+"100-200㎡");
                        break;
                    case 4:
                        s.append("-"+"200-500㎡");
                        break;
                    case 5:
                        s.append("-"+"500-1000㎡");
                        break;
                    case 6:
                        s.append("-"+"1000㎡以上");
                        break;
                }
            }else if(i==3){
                switch (selectList.get(i)) {
                    case 0:
                        s.append("-"+"20㎡以下\n");
                        break;
                    case 1:
                        s.append("-"+"20-50㎡\n");
                        break;
                    case 2:
                        s.append("-"+"50-100㎡\n");
                        break;
                    case 3:
                        s.append("-"+"100-200㎡\n");
                        break;
                    case 4:
                        s.append("-"+"200-500㎡\n");
                        break;
                    case 5:
                        s.append("-"+"500-1000㎡\n");
                        break;
                    case 6:
                        s.append("-"+"1000㎡以上\n");
                        break;
                }
            }else{
                switch (selectList.get(i)) {
                    case 0:
                        s.append("-"+"20㎡以下");
                        break;
                    case 1:
                        s.append("-"+"20-50㎡");
                        break;
                    case 2:
                        s.append("-"+"50-100㎡");
                        break;
                    case 3:
                        s.append("-"+"100-200㎡");
                        break;
                    case 4:
                        s.append("-"+"200-500㎡");
                        break;
                    case 5:
                        s.append("-"+"500-1000㎡");
                        break;
                    case 6:
                        s.append("-"+"1000㎡以上");
                        break;
                }
            }
        }
        phone.setText(response.getData().getPhone());
        industryName = response.getData().getIndustryName();
        blockName = response.getData().getBlockName();
        districtName = response.getData().getDistrictName();
        industryId = response.getData().getIndustryId()+"";
        blockId = response.getData().getBlockId()+"";
        districtId = response.getData().getDistrictId()+"";
        concern.setText(response.getData().getBlockName()+"-"+response.getData().getIndustryName()+"-"+s.toString());
    }
}
