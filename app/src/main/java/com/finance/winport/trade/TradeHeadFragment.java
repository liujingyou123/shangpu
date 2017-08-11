package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.trade.adapter.TradeBibleAdapter;
import com.finance.winport.trade.adapter.TradeHeadAdapter;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeSubList;
import com.finance.winport.trade.model.TradeTag;
import com.finance.winport.trade.view.ITradeSubView;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 行业头条、生意宝典
 */
public class TradeHeadFragment extends BaseFragment implements ITradeSubView {
    private final int LIMIT = 10;
    private final int START_PAGE = 1;
    private boolean isPull = true;
    private int pageSize = LIMIT;
    private int pageNumber = START_PAGE;

    @BindView(R.id.mListView)
    RecyclerView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    Unbinder unbinder;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    TradeHeadAdapter adapter;
    String type;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_trade_head, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText("行业头条");
        initRefreshView();
        mListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        adapter = new TradeHeadAdapter(refreshView, null, 0);
        mListView.setAdapter(adapter);
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.updateHeader(getHeadInfo());
                adapter.refreshData(getData());
            }
        }, 300);

    }

    private void initRefreshView() {
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
//                presenter.getSubList(pageNumber, pageSize, type, false);
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.refreshData(getData());
                        mListView.scrollToPosition(0);
                        refreshView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
//                presenter.getSubList(pageNumber, pageSize, type, false);
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        adapter.updateData(getData());
                        refreshView.refreshComplete();
                    }
                }, 1000);
            }
        });
    }

    private List<TradeTag.Tag> getHeadInfo() {
        List<TradeTag.Tag> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TradeTag.Tag tag = new TradeTag.Tag();
            tag.tagName = "资讯";
            list.add(tag);
        }
        return list;
    }

    String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501843518220&di=0306ae6f9c5434136495d0c45e016b2a&imgtype=0&src=http%3A%2F%2Fpic23.photophoto.cn%2F20120530%2F0020033092420808_b.jpg";

    private List<TradeSub> getData() {
        List<TradeSub> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            TradeSub item = new TradeSub();
            item.title = i == 0 ? i + "上海喜茶又搞事情，因黄牛得罪外卖小哥外卖小哥" : i + "这家店火得一发不可收拾";
            item.kind = /*i == 0 ? true :*/ false;
            item.image = img;
            item.content = "新闻";
            item.dateTime = "2017-07-17";
            item.viewCount = "10000";
            item.source = "今日头条";
            list.add(item);
        }
        return list;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.imv_focus_house_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                handleBack();
                break;
        }
    }

    @Override
    public void setAdapter(TradeSubList data) {
        List<TradeSub> list = data.data.data;
        int totalCount = data.data.totalSize;
        if (adapter == null) {
            adapter = new TradeHeadAdapter(refreshView, list, totalCount);
            mListView.setAdapter(adapter);
        } else {
            if (pageNumber == 1) {
                adapter.refreshData(list, totalCount);
            } else {
                adapter.updateData(list, totalCount);
            }
        }
    }

    @Override
    public void setHeadInfo(TradeTag data) {
        if (adapter != null) {
            adapter.updateHeader(data.data);
        }
    }

    @Override
    public void showError(String errorMsg) {
        ToastUtil.show(context, errorMsg);
    }
}
