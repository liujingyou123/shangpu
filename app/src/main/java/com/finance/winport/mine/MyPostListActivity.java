package com.finance.winport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.trade.adapter.TradeCircleAdapter;
import com.finance.winport.trade.model.Trade;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/12.
 * 我的帖子
 */

public class MyPostListActivity extends BaseActivity implements ITradeCircleView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private TradeCircleAdapter mAdapter;
    private List<Trade> mData = new ArrayList<>();
    private TradeCirclePresenter mPresenter;
    private int pageNumber = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_list);
        ButterKnife.bind(this);
        init();
        getData();
    }
    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mPresenter == null) {
                    mPresenter = new TradeCirclePresenter();
                }
                mPresenter.getMyTopics(pageNumber);
            }
        }, 100);
    }

    private void init() {
        tvFocusHouse.setText("我发布的帖子");
        mAdapter = new TradeCircleAdapter(this, mData, mPresenter);

        lsCircles.setAdapter(mAdapter);

        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {

            }
        });

        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyPostListActivity.this, TradeCircleDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showTradeCircle(TradeCircleResponse response) {

    }

    @Override
    public void showMoreTradeCircle(TradeCircleResponse response) {

    }

    @Override
    public void zanTopic(boolean isSuccess, int position, String topId) {

    }

    @Override
    public void cancelTopic(boolean isSuccess, int position, String topId) {

    }

    @Override
    public void onError() {

    }
}
