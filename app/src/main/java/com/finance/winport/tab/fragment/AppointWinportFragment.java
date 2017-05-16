package com.finance.winport.tab.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.tab.adapter.ScanWinportAdapter;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 约看过的 旺铺
 */

@Deprecated
public class AppointWinportFragment extends BaseFragment {
    private final int LIMIT = 10;
    private final int START_PAGE = 1;
    private int totalPage;
    private int pageSize = LIMIT;
    private int pageNumber = START_PAGE;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tip)
    TextView tip;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    String title;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_scan_winport, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        setTitle();
        setTip();
        initRefreshView();
        initListView();
    }

    private void setTitle() {
        tvFocusHouse.setText(title);
    }

    private void setTip() {
        String s1 = "1200";
        String s2 = "90%";
        String s = getString(R.string.list_scan_winport_tip, s1, s2);
        SpannableString sp = new SpannableString(s);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s1), s.indexOf(s1) + s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s2), s.indexOf(s2) + s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tip.setText(sp);
    }

    private void initListView() {
        mListView.setAdapter(new ScanWinportAdapter(refreshView, null, 0));
    }

    private void initRefreshView() {
        refreshView.autoLoadMore();
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                asyncData();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                asyncData();
            }
        });

    }

    private void asyncData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                refreshView.refreshComplete();
            }
        }, 2 * 1000);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }
}
