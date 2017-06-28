package com.finance.winport.mine;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.BuildConfig;
import com.finance.winport.R;
import com.finance.winport.account.LoginActivity;
import com.finance.winport.account.event.LoginOutEvent;
import com.finance.winport.account.net.UserManager;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.dialog.NoticeDialog;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.CleanMessageUtil;
import com.finance.winport.util.Constant;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.SpUtil;
import com.finance.winport.util.ToastUtil;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingsActivity extends BaseActivity {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.version)
    TextView version;
    @BindView(R.id.login_out)
    Button loginOut;
    @BindView(R.id.debug)
    Button debug;
    LoadingDialog loading;
    @BindView(R.id.clearCache)
    TextView clearCache;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        init();
    }


    public void init() {
        loading = new LoadingDialog(context);
        tvFocusHouse.setText("系统设置");
        version.setText(Constant.NEEd_UPDATE ? "有新版本，点击更新" : "当前已是最新版 V" + getAppVersion(context));
        clearCache.setText(CleanMessageUtil.getTotalCacheSize(context));
        if (SharedPrefsUtil.getUserInfo() != null) {
            loginOut.setVisibility(View.VISIBLE);
        } else {
            loginOut.setVisibility(View.GONE);
        }
        if (BuildConfig.DEBUG) {
            debug.setVisibility(View.VISIBLE);
        } else {
            debug.setVisibility(View.GONE);
        }
    }

    @OnClick(R.id.version)
    public void onVersionClick() {
        if (Constant.NEEd_UPDATE) {
            startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(Constant.DOWNLOAD_URL)));
        }
    }

    public static String getAppVersion(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    @OnClick({R.id.imv_focus_house_back, R.id.login_out, R.id.about})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.login_out:
                MobclickAgent.onEvent(SettingsActivity.this, "system_quit");
                loginOut();
                break;
            case R.id.about:
                MobclickAgent.onEvent(SettingsActivity.this, "system_aboutus");
                startActivity(new Intent(SettingsActivity.this, AboutActivtiy.class));
                break;
        }
    }

    @OnClick(R.id.debug)
    public void onDebugClick(View v) {
//        startActivity(new Intent(context, NetServerIpSetActivity.class));
    }

    private void loginOut() {
        loading.show();
        HashMap<String, Object> params = new HashMap<>();
        UserManager.getInstance().loginOut(params, new NetworkCallback<BaseResponse>() {
            @Override
            public void success(BaseResponse response) {
                loading.dismiss();
                setLoginOutData();
                toLogin();
            }

            @Override
            public void failure(Throwable throwable) {
                loading.dismiss();
            }
        });
    }

    private void toLogin() {
        startActivity(new Intent(SettingsActivity.this, LoginActivity.class));
        SharedPrefsUtil.clearUserInfo();
        EventBus.getDefault().post(new LoginOutEvent());
        finish();
    }

    private void setLoginOutData() {
        SpUtil.getInstance().setIntData("commentNum", 0);
        SpUtil.getInstance().setStringData(SharedPrefsUtil.getUserInfo().data.userPhone, null);
    }

    @OnClick(R.id.clearCache)
    public void onViewClicked() {
        showDeleteAlert();
    }

    void showDeleteAlert() {
        NoticeDialog n = new NoticeDialog(context);
        n.setTitle("清除缓存");
        n.setMessage("确认删除所有缓存？");
        n.setPositiveBtn("确认");
        n.setOkClickListener(new NoticeDialog.OnPreClickListner() {
            @Override
            public void onClick() {
                CleanMessageUtil.clearAllCache(context);
                ToastUtil.show(context, "清除完成");
                clearCache.setText(CleanMessageUtil.getTotalCacheSize(context));
            }
        });
        n.show();
    }
}
