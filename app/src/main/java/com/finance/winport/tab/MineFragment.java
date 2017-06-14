package com.finance.winport.tab;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.account.event.LoginEvent;
import com.finance.winport.account.event.LoginOutEvent;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.aliyunoss.AliOss;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
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
import com.finance.winport.permission.PermissionsManager;
import com.finance.winport.permission.PermissionsResultAction;
import com.finance.winport.tab.event.SelectImageEvent;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportCounts;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.trade.MyPostListActivity;
import com.finance.winport.util.LoadingDialogUtil;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.StringUtil;
import com.finance.winport.util.ToolsUtil;
import com.finance.winport.view.StopWatchTextView;
import com.finance.winport.view.picker.Picker;
import com.finance.winport.view.picker.engine.GlideEngine;
import com.finance.winport.view.picker.utils.PicturePickerUtils;
import com.umeng.analytics.MobclickAgent;
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
    @BindView(R.id.mine_schedule)
    TextView mineSchedule;
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
    private String industryName, blockName, districtName, industryId, blockId, districtId, cityName;

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

    @Subscribe
    public void onLoginOutEvent(LoginOutEvent event) {
        if (event != null) {
            init();
        }
    }

    @Subscribe
    public void onLoginEvent(LoginEvent event) {
        if (event != null) {
            init();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        init();
        asyncRelevant();
        return root;
    }

    private void initView() {
        yi.setVisibility(View.GONE);
        ji.setVisibility(View.GONE);
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new PersonalInfoPresenter(this);
        }
        mPresenter.getPersonalInfo();

    }

    private void init() {
        if (isLogin()) {
            String number = SharedPrefsUtil.getUserInfo().data.userPhone;
            if (StringUtil.isCellPhone(number)) {
                phone.setText(number.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2"));
            } else {
                phone.setText("");
            }
            setHeadImage(SharedPrefsUtil.getUserInfo().data.headPortrait);
            getData();
            concern.setText("去设置关注的旺铺");
        } else {
            phone.setText("未登录");
            concern.setText("点击登录帐号");
            modify.setVisibility(View.GONE);
            shopImg.setImageResource(R.mipmap.icon_default_head);
            mineWinport.setText("--");
            mineAppoint.setText("--");
            mineCollection.setText("--");
            mineScan.setText("--");
            mineSchedule.setText("");
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
                yi.setVisibility(View.VISIBLE);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                sb.append(s + " ");
            }
        });

        Observable.from(data.huangli.ji).take(5).subscribe(new Subscriber<String>() {
            StringBuilder sb = new StringBuilder();

            @Override
            public void onCompleted() {
                ji.setText(sb.toString());
                ji.setVisibility(View.VISIBLE);
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
            retryRelevant();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        asyncRelevant();
    }

    private boolean isMsgOk;
    private boolean isLunarOk;
    private boolean isWinportCountsOk;
    private boolean isDataOk;

    private void retryRelevant() {
        if (!isMsgOk && isLogin()) {
            getUnReadMsg();
        }
        if (!isLunarOk) {
            getLunar();
        }
        if (!isWinportCountsOk && isLogin()) {
            getWinportCounts();
        }
        if (!isDataOk && isLogin()) {
            getData();
        }

    }

    //获取个人中心相关数据
    private void asyncRelevant() {
        if (isLogin()) {
            getUnReadMsg();
            getWinportCounts();
            getData();
            modify.setVisibility(View.VISIBLE);
        }
        getLunar();
    }

    //获取未读消息
    private void getUnReadMsg() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("receiveType", 0);//0:客户 1：业务员
        PersonManager.getInstance().getUnReadMsg(params, new NetworkCallback<UnReadMsg>() {
            @Override
            public void success(UnReadMsg response) {
                if (getView() == null) return;
                if (response != null && response.isSuccess()) {
                    ivFocusRight.setActivated(response.data);
                    isMsgOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isMsgOk = false;
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
                    setWinportCounts(response);
                    isWinportCountsOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isWinportCountsOk = false;
            }
        });
    }

    //获取农历
    private void getLunar() {
        HashMap<String, Object> params = new HashMap<>();
        PersonManager.getInstance().getLunar(params, new NetworkCallback<Lunar>() {
            @Override
            public void success(Lunar response) {
                if (response != null && response.isSuccess()) {
                    setHuangLi(response.data);
                    isLunarOk = true;
                }
            }

            @Override
            public void failure(Throwable throwable) {
                isLunarOk = true;
            }
        });
    }

    //设置发布、约看、收藏、浏览、日程数
    private void setWinportCounts(WinportCounts response) {
        if (response == null || response.data == null) return;
        if (!TextUtils.equals(mineWinport.getText().toString().trim(), response.data.issuerCount + "")) {
            mineWinport.setShowNumber(response.data.issuerCount);
        }
        if (!TextUtils.equals(mineAppoint.getText().toString().trim(), response.data.visitCount + "")) {
            mineAppoint.setShowNumber(response.data.visitCount);
        }
        if (!TextUtils.equals(mineCollection.getText().toString().trim(), response.data.collectedCount + "")) {
            mineCollection.setShowNumber(response.data.collectedCount);
        }
        if (!TextUtils.equals(mineScan.getText().toString().trim(), response.data.browseCount + "")) {
            mineScan.setShowNumber(response.data.browseCount);
        }
        if (response.data.scheduleCount > 0) {
            String sc = getString(R.string.mine_schedule, response.data.scheduleCount + "");
            SpannableString sp = new SpannableString(sc);
            int start = sc.indexOf(response.data.scheduleCount + "");
            int end = start + (response.data.scheduleCount + "").length();
            sp.setSpan(new ForegroundColorSpan(Color.parseColor("#333333")), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            mineSchedule.setText(sp);
        }

    }


    @OnClick({R.id.tv_focus_right, R.id.modify, R.id.schedule_list, R.id.phone
            , R.id.concern, R.id.shop_img, R.id.ll_mine_winport, R.id.ll_mine_collection
            , R.id.ll_mine_appoint, R.id.ll_mine_scan, R.id.post, R.id.suggest})
    public void onRequiredLoginClicked(View view) {
//        if (!isLogin()) {// not login
//            toLogin();
//            return;
//        }
        switch (view.getId()) {
            case R.id.tv_focus_right:
                MobclickAgent.onEvent(context, "message");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(getActivity(), MyNoticeActivity.class));
                break;
            case R.id.modify:
                MobclickAgent.onEvent(context, "my_shopfollow");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                toConcern();
                break;
            case R.id.schedule_list:
                MobclickAgent.onEvent(context, "my_date");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(getActivity(), MyScheduleListActivity.class));
                break;
            case R.id.phone:
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                break;
            case R.id.concern:
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                toConcern();
                break;
            case R.id.shop_img:
                MobclickAgent.onEvent(context, "my_head");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                scanHeadImage();
                break;
            case R.id.ll_mine_winport:
                MobclickAgent.onEvent(context, "my_shop");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent release = new Intent(context, WinportActivity.class);
                release.putExtra("type", TypeList.RELEASE);
                release.putExtra("title", "我发布的旺铺");
                startActivity(release);
                break;
            case R.id.ll_mine_appoint:
                MobclickAgent.onEvent(context, "my_shoporder");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent appoint = new Intent(context, WinportActivity.class);
                appoint.putExtra("type", TypeList.APPOINT);
                appoint.putExtra("title", "约看过的旺铺");
                startActivity(appoint);
                break;
            case R.id.ll_mine_collection:
                MobclickAgent.onEvent(context, "my_shopcollect");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent collection = new Intent(context, WinportActivity.class);
                collection.putExtra("type", TypeList.COLLECTION);
                collection.putExtra("title", "我的收藏");
                startActivity(collection);
                break;
            case R.id.ll_mine_scan:
                MobclickAgent.onEvent(context, "my_shopbrowse");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                Intent scan = new Intent(context, WinportActivity.class);
                scan.putExtra("type", TypeList.SCAN);
                scan.putExtra("title", "最近浏览");
                startActivity(scan);
                break;
            case R.id.post:// 我的帖子
                MobclickAgent.onEvent(context, "my_post");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(context, MyPostListActivity.class));
                break;
            case R.id.suggest:
                MobclickAgent.onEvent(context, "my_suggest");
                if (!isLogin()) {// not login
                    toLogin();
                    return;
                }
                startActivity(new Intent(context, SuggestActivity.class));
                break;
        }
    }

    @OnClick({R.id.setting, R.id.fierce_prediction})
    public void onViewClick(View v) {
        switch (v.getId()) {
            case R.id.setting:
                MobclickAgent.onEvent(context, "my_system");
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
            case R.id.fierce_prediction:
                MobclickAgent.onEvent(context, "my_luckyshopname");
                startActivity(new Intent(context, FiercePredictionActivity.class));
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
        intent.putIntegerArrayListExtra("areaList", selectList);
        intent.putExtra("industryName", industryName);
        intent.putExtra("blockName", blockName);
        intent.putExtra("districtName", districtName);
        intent.putExtra("industryId", industryId);
        intent.putExtra("districtId", districtId);
        intent.putExtra("blockId", blockId);
        intent.putExtra("cityName", cityName);
        startActivity(intent);
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
                if (getView() == null) return;
                loading.dismiss();
                saveHeadInfo(headUrl);
                setHeadImage(path);
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
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
        isDataOk = true;

//        selectList.add(1);
//        selectList.add(3);
//        selectList.add(4);
//        selectList.add(5);
//        selectList.add(6);
        selectList = (ArrayList<Integer>) response.getData().getList();
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < selectList.size(); i++) {
            if (i == 0) {

                if (selectList.size() == 1) {

                    switch (selectList.get(i)) {
                        case 1:
                            s.append("20㎡以下");
                            break;
                        case 2:
                            s.append("20-50㎡");
                            break;
                        case 3:
                            s.append("50-100㎡");
                            break;
                        case 4:
                            s.append("100-200㎡");
                            break;
                        case 5:
                            s.append("200-500㎡");
                            break;
                        case 6:
                            s.append("500-1000㎡");
                            break;
                        case 7:
                            s.append("1000㎡以上");
                            break;
                    }

                } else {
                    switch (selectList.get(i)) {
                        case 1:
                            s.append("20㎡以下");
                            break;
                        case 2:
                            s.append("20-50㎡");
                            break;
                        case 3:
                            s.append("50-100㎡");
                            break;
                        case 4:
                            s.append("100-200㎡");
                            break;
                        case 5:
                            s.append("200-500㎡");
                            break;
                        case 6:
                            s.append("500-1000㎡");
                            break;
                        case 7:
                            s.append("1000㎡以上");
                            break;
                    }
                }
            } else if (selectList.size() < 5) {
                switch (selectList.get(i)) {
                    case 1:
                        s.append("-" + "20㎡以下");
                        break;
                    case 2:
                        s.append("-" + "20-50㎡");
                        break;
                    case 3:
                        s.append("-" + "50-100㎡");
                        break;
                    case 4:
                        s.append("-" + "100-200㎡");
                        break;
                    case 5:
                        s.append("-" + "200-500㎡");
                        break;
                    case 6:
                        s.append("-" + "500-1000㎡");
                        break;
                    case 7:
                        s.append("-" + "1000㎡以上");
                        break;
                }
            } else if (i == 3) {
                switch (selectList.get(i)) {
                    case 1:
                        s.append("-" + "20㎡以下");
                        break;
                    case 2:
                        s.append("-" + "20-50㎡");
                        break;
                    case 3:
                        s.append("-" + "50-100㎡");
                        break;
                    case 4:
                        s.append("-" + "100-200㎡");
                        break;
                    case 5:
                        s.append("-" + "200-500㎡");
                        break;
                    case 6:
                        s.append("-" + "500-1000㎡");
                        break;
                    case 7:
                        s.append("-" + "1000㎡以上");
                        break;
                }
            } else {
                switch (selectList.get(i)) {
                    case 1:
                        s.append("-" + "20㎡以下");
                        break;
                    case 2:
                        s.append("-" + "20-50㎡");
                        break;
                    case 3:
                        s.append("-" + "50-100㎡");
                        break;
                    case 4:
                        s.append("-" + "100-200㎡");
                        break;
                    case 5:
                        s.append("-" + "200-500㎡");
                        break;
                    case 6:
                        s.append("-" + "500-1000㎡");
                        break;
                    case 7:
                        s.append("-" + "1000㎡以上");
                        break;
                }
            }
        }
        phone.setText(response.getData().getPhone());
        industryName = response.getData().getIndustryName();
        blockName = response.getData().getBlockName();
        districtName = response.getData().getDistrictName();
        industryId = response.getData().getIndustryId();
        blockId = response.getData().getBlockId();
        districtId = response.getData().getDistrictId();
        cityName = response.getData().getCityName();
        String headPortrait = response.getData().getHeadPortrait();
        saveHeadInfo(headPortrait);
        setHeadImage(headPortrait);
        if (TextUtils.isEmpty(response.getData().getBlockName())) {

            if (TextUtils.isEmpty(response.getData().getDistrictName())) {

                if (TextUtils.isEmpty(response.getData().getCityName())) {
                    if (TextUtils.isEmpty(response.getData().getIndustryName())) {

                        if (!TextUtils.isEmpty(s.toString())) {

                            concern.setText("关注 " + s.toString() + " 的旺铺");
                        }
                    } else {

                        if (TextUtils.isEmpty(s.toString())) {

                            concern.setText("关注 " + response.getData().getIndustryName() + " 的旺铺");
                        } else {

                            concern.setText("关注 " + response.getData().getIndustryName() + "-" + s.toString() + " 的旺铺");
                        }
                    }
                } else {

                    if (TextUtils.isEmpty(response.getData().getIndustryName())) {

                        if (!TextUtils.isEmpty(s.toString())) {

                            concern.setText("关注 " + response.getData().getCityName() + "-" + s.toString() + " 的旺铺");
                        } else {
                            concern.setText("关注 " + response.getData().getCityName() + " 的旺铺");
                        }
                    } else {

                        if (TextUtils.isEmpty(s.toString())) {

                            concern.setText("关注 " + response.getData().getCityName() + "-" + response.getData().getIndustryName() + " 的旺铺");
                        } else {

                            concern.setText("关注 " + response.getData().getCityName() + "-" + response.getData().getIndustryName() + "-" + s.toString() + " 的旺铺");
                        }
                    }
                }
            } else {
                if (TextUtils.isEmpty(response.getData().getIndustryName())) {

                    if (!TextUtils.isEmpty(s.toString())) {

                        concern.setText("关注 " + response.getData().getDistrictName() + "-" + s.toString() + " 的旺铺");
                    } else {
                        concern.setText("关注 " + response.getData().getDistrictName() + " 的旺铺");
                    }
                } else {

                    if (TextUtils.isEmpty(s.toString())) {

                        concern.setText("关注 " + response.getData().getDistrictName() + "-" + response.getData().getIndustryName() + " 的旺铺");
                    } else {

                        concern.setText("关注 " + response.getData().getDistrictName() + "-" + response.getData().getIndustryName() + "-" + s.toString() + " 的旺铺");
                    }
                }
            }
        } else if (TextUtils.isEmpty(response.getData().getIndustryName())) {
            if (!TextUtils.isEmpty(s.toString())) {

                concern.setText("关注 " + response.getData().getBlockName() + "-" + s.toString() + " 的旺铺");
            } else {
                concern.setText("关注 " + response.getData().getBlockName() + " 的旺铺");

            }


        } else if (!TextUtils.isEmpty(s.toString())) {

            concern.setText("关注 " + response.getData().getBlockName() + "-" + response.getData().getIndustryName() + "-" + s.toString() + " 的旺铺");
        } else {
            concern.setText("关注 " + response.getData().getBlockName() + "-" + response.getData().getIndustryName() + " 的旺铺");

        }


    }
}
