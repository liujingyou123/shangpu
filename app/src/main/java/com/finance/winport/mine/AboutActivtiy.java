package com.finance.winport.mine;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;


import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.net.NetUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by jge
 */
public class AboutActivtiy extends BaseActivity {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.web)
    WebView web;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_aboutus);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        tvFocusHouse.setText("关于我们");
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setDomStorageEnabled(true);
        web.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view, url);
            }
        });
        web.loadUrl(NetUtils.webUrl() + "aboutUs.html");
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onClick() {
        handleBack();
    }
}
