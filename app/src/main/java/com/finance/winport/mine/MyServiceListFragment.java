package com.finance.winport.mine;

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
import com.finance.winport.mine.adapter.MyServiceListAdapter;
import com.finance.winport.trade.TradeCircleDetailActivity;
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

public class MyServiceListFragment extends Fragment {

    Unbinder unbinder;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    XPtrFrameLayout refreshView;

    private MyServiceListAdapter mAdapter;
    private List<TradeTopic> mData = new ArrayList<>();
    private String type;
    private int pageNumber = 1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myservicelist, null);
        unbinder = ButterKnife.bind(this, view);
        init();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("TradeCircleList onResume");
    }


    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }
        mAdapter = new MyServiceListAdapter(this.getContext(), null);

        lsCircles.setAdapter(mAdapter);

        refreshView.setMode(PtrFrameLayout.Mode.LOAD_MORE);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
//                mPresenter.getMoreTradeCircles(type, pageNumber);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
//                mPresenter.getTradeCircles(type, pageNumber);
            }
        });

        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MobclickAgent.onEvent(MyServiceListFragment.this.getContext(), "circle_comment");
                TradeTopic trade = (TradeTopic) parent.getItemAtPosition(position);
                if (trade != null) {
                    Intent intent = new Intent(MyServiceListFragment.this.getContext(), TradeCircleDetailActivity.class);
                    intent.putExtra("topicId", trade.getTopicId() + "");
                    startActivity(intent);
                }
            }
        });
    }








    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

}
