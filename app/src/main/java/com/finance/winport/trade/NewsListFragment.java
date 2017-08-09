package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.trade.adapter.NewsListAdapter;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.trade.model.TradeTag;
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
 * 资讯列表...
 */
public class NewsListFragment extends BaseFragment {


    @BindView(R.id.mListView)
    RecyclerView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    Unbinder unbinder;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    String id;
    String title;

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
        View root = inflater.inflate(R.layout.fragment_info_list, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        tvFocusHouse.setText(title);
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.refreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.refreshComplete();
                    }
                }, 1000);
            }
        });
        mListView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        NewsListAdapter adapter = new NewsListAdapter(refreshView, getHeadData(), 0);
        mListView.setAdapter(adapter);
    }

    private List<TradeTag.Tag> getHeadInfo() {
        List<TradeTag.Tag> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            TradeTag.Tag tag = new TradeTag.Tag();
            tag.tagName = title;
            list.add(tag);
        }
        return list;
    }

    String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501843518220&di=0306ae6f9c5434136495d0c45e016b2a&imgtype=0&src=http%3A%2F%2Fpic23.photophoto.cn%2F20120530%2F0020033092420808_b.jpg";

    private List<TradeHead> getHeadData() {
        List<TradeHead> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            TradeHead item = new TradeHead();
            item.title = i == 0 ? "上海喜茶又搞事情，因黄牛得罪外卖小哥外卖小哥" : "这家店火得一发不可收拾";
            item.kind = /*i == 0 ? true :*/ false;
            item.image = img;
            item.content = title;
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
}
