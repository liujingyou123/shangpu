package com.finance.winport.tab.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.tab.adapter.WinportAdapter;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * （我的、约看过的、我的收藏、最近浏览） 旺铺
 */

public class MineWinportFragment extends BaseFragment {
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_winport, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        setTip();
        initRefreshView();
        initListView();
    }

    private void setTip() {
//        tip.setText(Html.fromHtml(getString(R.string.list_winport_tip, "19802", "92%")));
    }

    private void initListView() {
        mListView.setAdapter(new WinportAdapter(refreshView, null, 0));
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
