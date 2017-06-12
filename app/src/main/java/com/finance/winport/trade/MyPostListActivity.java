package com.finance.winport.trade;

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
import com.finance.winport.trade.adapter.MyTradeCircleAdapter;
import com.finance.winport.trade.adapter.TradeCircleAdapter;
import com.finance.winport.trade.model.CommentNumResponse;
import com.finance.winport.trade.model.MyTopicResponse;
import com.finance.winport.trade.model.Trade;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.trade.view.IMyTopicListView;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/12.
 * 我的帖子
 */

public class MyPostListActivity extends BaseActivity implements IMyTopicListView, ITradeCircleView {
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private MyTradeCircleAdapter mAdapter;
    private List<MyTopicResponse.DataBean.MyTopic> mData = new ArrayList<>();
    private TradeCirclePresenter mPresenter;
    private int pageNumber = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_post_list);
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        mPresenter.getMyTopics(pageNumber);
    }

    private void init() {
        tvFocusHouse.setText("我发布的帖子");
        if (mPresenter == null) {
            mPresenter = new TradeCirclePresenter();
            mPresenter.setmITradeCircleView(this);
            mPresenter.setmIMyTopicListView(this);
        }
        if (mAdapter == null) {
            mAdapter = new MyTradeCircleAdapter(this, mData, mPresenter);
        }
        lsCircles.setAdapter(mAdapter);

        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                mPresenter.getMyMoreTopics(pageNumber);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                mPresenter.getMyTopics(pageNumber);
            }
        });

        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MobclickAgent.onEvent(context, "mypost_post");
                MyTopicResponse.DataBean.MyTopic trade = (MyTopicResponse.DataBean.MyTopic) parent.getItemAtPosition(position);
                if (trade != null) {
                    Intent intent = new Intent(MyPostListActivity.this, TradeCircleDetailActivity.class);
                    intent.putExtra("topicId", trade.getTopicId() + "");
                    startActivity(intent);
                }
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
        if (isSuccess) {

            for (int i = 0; i < mData.size(); i++) {
                MyTopicResponse.DataBean.MyTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    trade.setLikeStatus("1");
                    trade.setPraiseNumber(trade.getPraiseNumber() + 1);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }

            ToastUtil.show(this, "点赞成功");
        } else {
            ToastUtil.show(this, "点赞失败");
        }
    }

    @Override
    public void cancelTopic(boolean isSuccess, int position, String topId) {
        if (isSuccess) {
//            mPresenter.getTradeCircles(type, pageNumber);
            for (int i = 0; i < mData.size(); i++) {
                MyTopicResponse.DataBean.MyTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    trade.setLikeStatus("0");
                    trade.setPraiseNumber(trade.getPraiseNumber() - 1);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }

            ToastUtil.show(this, "取消成功");

        } else {
            ToastUtil.show(this, "取消失败");

        }
    }

    @Override
    public void deleteTopic(boolean isSuccess, String topId) {
        if (isSuccess) {

            for (int i = 0; i < mData.size(); i++) {
                MyTopicResponse.DataBean.MyTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    mData.remove(trade);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }

            if (mData == null || mData.size() == 0) {
                llEmpty.setVisibility(View.VISIBLE);
                refreshView.setVisibility(View.GONE);
            } else {
                llEmpty.setVisibility(View.GONE);
                refreshView.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void showCommentsNum(CommentNumResponse response) {

    }

    @Override
    public void showTopics(MyTopicResponse response) {
        if (response.getData() == null || response.getData().getData() == null || response.getData().getData().size() == 0) {
            llEmpty.setVisibility(View.VISIBLE);
            refreshView.setVisibility(View.GONE);
        } else {
            mData.clear();
            mData.addAll(response.getData().getData());
            llEmpty.setVisibility(View.GONE);
            refreshView.setVisibility(View.VISIBLE);
            mAdapter.notifyDataSetChanged();
        }
        refreshView.refreshComplete();
    }

    @Override
    public void showMoreTopics(MyTopicResponse response) {
        mData.addAll(response.getData().getData());
        mAdapter.notifyDataSetChanged();
        refreshView.refreshComplete();
    }

    @Override
    public void onError() {
        refreshView.refreshComplete();
    }
}
