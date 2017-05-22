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
import com.finance.winport.trade.adapter.TradeCircleAdapter;
import com.finance.winport.trade.model.Trade;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
    private List<Trade> mData = new ArrayList<>();
    private TradeCirclePresenter mPresenter;
    private String type;
    private int pageNumber = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tradecirclelist, null);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getData();
    }

    private void getData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getTradeCircles(type, pageNumber);
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

        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Trade trade = (Trade) parent.getItemAtPosition(position);
                if (trade != null) {
                    Intent intent = new Intent(TradeCircleListFragment.this.getContext(), TradeCircleDetailActivity.class);
                    intent.putExtra("topicId", trade.getTopicId() + "");
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void showTradeCircle(TradeCircleResponse response) {
        mData.clear();
        mData.addAll(response.getData().getPage().getData());
        mAdapter.notifyDataSetChanged();
        refreshView.refreshComplete();
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
//            view.findViewById(R.id.tv_zan).setSelected(true);

            mPresenter.getTradeCircles(type, pageNumber);
        }
    }

    @Override
    public void cancelTopic(boolean isSuccess, int position, String topId) {
        if (isSuccess) {
            mPresenter.getTradeCircles(type, pageNumber);
        }
    }

    @Override
    public void onError() {
        refreshView.refreshComplete();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
