package com.finance.winport.trade;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.log.XLog;
import com.finance.winport.mine.NoticeListActivity;
import com.finance.winport.trade.adapter.TradeCircleAdapter;
import com.finance.winport.trade.model.CommentNumResponse;
import com.finance.winport.trade.model.EventBusCircleData;
import com.finance.winport.trade.model.EventBusCommentNum;
import com.finance.winport.trade.model.EventBustTag;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by liuworkmac on 17/5/10.
 * 生意圈
 */

public class TradeCircleListFragment extends Fragment implements ITradeCircleView {

    Unbinder unbinder;
    @BindView(R.id.tv_comments_num)
    TextView tvCommentsNum;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    XPtrFrameLayout refreshView;

    private TradeCircleAdapter mAdapter;
    private List<TradeTopic> mData = new ArrayList<>();
    private TradeCirclePresenter mPresenter;
    private String type;
    private int pageNumber = 1;
    private boolean isCanGetData = true;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tradecirclelist, null);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        unbinder = ButterKnife.bind(this, view);
        init();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("TradeCircleList onResume");
        if (isCanGetData) {
            getData();
        }
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                isCanGetData = false;
                pageNumber = 1;
                mPresenter.getTradeCircles(type, pageNumber);
                mPresenter.getCommentsNum();
            }
        }, 100);
    }

    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        if (mPresenter == null) {
            mPresenter = new TradeCirclePresenter();
            mPresenter.setmITradeCircleView(this);
        }
        mAdapter = new TradeCircleAdapter(this.getContext(), mData, mPresenter);

        lsCircles.setAdapter(mAdapter);

        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                mPresenter.getMoreTradeCircles(type, pageNumber);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                mPresenter.getTradeCircles(type, pageNumber);
            }
        });

//        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MobclickAgent.onEvent(TradeCircleListFragment.this.getContext(), "circle_comment");
//                TradeTopic trade = (TradeTopic) parent.getItemAtPosition(position);
//                if (trade != null) {
//                    Intent intent = new Intent(TradeCircleListFragment.this.getContext(), TradeCircleDetailActivity.class);
//                    intent.putExtra("topicId", trade.getTopicId() + "");
//                    startActivity(intent);
//                }
//            }
//        });
    }

    @Override
    public void showTradeCircle(TradeCircleResponse response) {
        if (response.getData() != null && response.getData().getPage() != null && response.getData().getPage().getData() != null) {
            mData.clear();
            mData.addAll(response.getData().getPage().getData());
            mAdapter.notifyDataSetChanged();
            refreshView.refreshComplete();
//            response.getData().setCreateTopicOpen("0");
            if ("1".equals(response.getData().getCreateTopicOpen())) {
                EventBustTag eventBustTag = new EventBustTag();
                eventBustTag.isOpenCreateTopic = false;
                EventBus.getDefault().post(eventBustTag);
            } else {
                EventBustTag eventBustTag = new EventBustTag();
                EventBus.getDefault().post(eventBustTag);
            }

            if (pageNumber == 1) {
                lsCircles.smoothScrollToPosition(0);
            }
        }

    }

    @Override
    public void showMoreTradeCircle(TradeCircleResponse response) {
        mData.addAll(response.getData().getPage().getData());
        mAdapter.notifyDataSetChanged();
        refreshView.refreshComplete();
    }

    @Override
    public void zanTopic(boolean isSuccess, int position, String topId) {
        if (isSuccess) {
//            View view = lsCircles.getChildAt(position-lsCircles.getFirstVisiblePosition());
//            TextView textView = (TextView) view.findViewById(R.id.tv_zan);
//            if (textView != null) {
//                textView.setSelected(true);
//
//            }
//            mPresenter.getTradeCircles(type, pageNumber);

            for (int i = 0; i < mData.size(); i++) {
                TradeTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    trade.setLikeStatus("1");
                    trade.setPraiseNumber(trade.getPraiseNumber() + 1);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }

            ToastUtil.show(this.getContext(), "点赞成功");
        } else {
            ToastUtil.show(this.getContext(), "点赞失败");
        }
    }

    @Override
    public void cancelTopic(boolean isSuccess, int position, String topId) {
        if (isSuccess) {
//            mPresenter.getTradeCircles(type, pageNumber);
            for (int i = 0; i < mData.size(); i++) {
                TradeTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    trade.setLikeStatus("0");
                    trade.setPraiseNumber(trade.getPraiseNumber() - 1);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }

            ToastUtil.show(this.getContext(), "取消成功");

        } else {
            ToastUtil.show(this.getContext(), "取消失败");

        }
    }

    @Override
    public void deleteTopic(boolean isSuccess, String topId) {
        if (isSuccess) {

            for (int i = 0; i < mData.size(); i++) {
                TradeTopic trade = mData.get(i);
                if (topId.equals(trade.getTopicId() + "")) {
                    mData.remove(trade);
                    mAdapter.notifyDataSetChanged();
                    break;
                }
            }
//            mPresenter.getTradeCircles(type, pageNumber);
        }
    }

    @Override
    public void showCommentsNum(CommentNumResponse response) {
        if (response != null && response.getData() > 0) {
            tvCommentsNum.setVisibility(View.VISIBLE);
            tvCommentsNum.setText("老板，您有" + response.getData() + "条新的评论");
        } else {
            tvCommentsNum.setVisibility(View.GONE);
        }
    }

    @Subscribe
    public void getListData(EventBusCircleData param) {
        XLog.e("param = " + param.canGetData);
        if (param != null && param.canGetData) {
            isCanGetData = true;
        }
    }

    @Subscribe
    public void getCommentNum(EventBusCommentNum num) {
        if (mPresenter != null) {
            mPresenter.getCommentsNum();
        }
//        commentNum();
    }

//    private void commentNum() {
//        int commentNum = SpUtil.getInstance().getIntData("commentNum", 0);
//
//    }

    @Override
    public void onError() {
        refreshView.refreshComplete();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        if (mPresenter != null) {
            mPresenter.clearData();
        }
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    @OnClick(R.id.tv_comments_num)
    public void onViewClicked() {
        MobclickAgent.onEvent(this.getContext(), "circle_comment");
        Intent intent = new Intent(this.getContext(), NoticeListActivity.class);
        intent.putExtra("type", 2);
        intent.putExtra("title", "生意圈");
        startActivity(intent);
//        SpUtil.getInstance().setIntData("commentNum", 0);
        tvCommentsNum.setVisibility(View.GONE);
//        EventBus.getDefault().post(new EventBusCommentNum());

    }
}
