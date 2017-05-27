package com.finance.winport.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/26.
 */

public class H5Activity extends BaseActivity {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.tv_close)
    TextView tvClose;

    private int type; // 0:用户协议  1:限购查询 2:关于我们 3:协议样本 4: 广告
    private String url; //广告

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_html);
        ButterKnife.bind(this);
        getBundleData();
        initView();
    }

    private void getBundleData() {
        Intent intent = getIntent();
        if (intent != null) {
            type = intent.getIntExtra("type", 0);
            url = intent.getStringExtra("url");
        }
    }

    private void initView() {
        tvFocusHouse.setText("");
        tvClose.setVisibility(View.GONE);
        String address = url;
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.loadUrl(address);
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_close})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                if (web.canGoBack() && (type != 4)) {
                    web.goBack();
                } else {
                    finish();
                }
                break;
            case R.id.tv_close:
                finish();
                break;
        }
    }

}
