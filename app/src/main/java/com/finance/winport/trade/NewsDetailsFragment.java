package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.view.CustomWebView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 资讯详情...
 */
public class NewsDetailsFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    String id;
    String title;
    @BindView(R.id.title)
    TextView tvTitle;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.from)
    TextView from;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.scan_count)
    TextView scanCount;
    @BindView(R.id.web)
    CustomWebView web;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.praise)
    TextView praise;
    @BindView(R.id.down_praise)
    TextView downPraise;
    @BindView(R.id.bottom)
    LinearLayout bottom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            id = getArguments().getString("id");
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_news_details, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        setDetails(null);
        return root;
    }

    private void setDetails(TradeDetails info) {
        if (info != null) {
            web.loadUrl("https://m.10010.com/queen/icbc/e-card.html");
            tvTitle.setText(info.data.title);
            type.setText(info.data.content);
            if (TextUtils.isEmpty(info.data.source)) {
                from.setVisibility(View.GONE);
            } else {
                from.setText(info.data.source);
                from.setVisibility(View.VISIBLE);
            }
            scanCount.setText(info.data.viewCount + "浏览");
            date.setText(info.data.dateTime);
            praise.setText(info.data.goodCount + "");
            downPraise.setText(info.data.badCount + "");
        }

    }

    private void initView() {
        tvFocusHouse.setText(title + "详情");
        web.setOnScrollListener(new CustomWebView.OnScrollListener() {
            @Override
            public void onScroll(int scrollY) {
                hideBottomView();
            }

            @Override
            public void onScrollIdle(int scrollY) {
                showBottomView();
            }
        });
    }

    private void showBottomView() {
        bottom.setVisibility(View.VISIBLE);
    }

    private void hideBottomView() {
        bottom.setVisibility(View.GONE);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.imv_focus_house_back, R.id.share, R.id.praise, R.id.down_praise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
            case R.id.share:

                break;
            case R.id.praise:

                break;
            case R.id.down_praise:

                break;
        }
    }
}
