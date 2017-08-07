package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.trade.adapter.HomeTradeCircleAdapter;
import com.finance.winport.trade.model.TradeCanon;
import com.finance.winport.trade.model.TradeCommunity;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 生意圈首页
 */
public class HomeTradeCircleFragment extends BaseFragment {


    @BindView(R.id.mListView)
    ExpandableListView mListView;
    @BindView(R.id.refresh_view)
    PtrFrameLayout refreshView;
    Unbinder unbinder;
    private Map<String, List<BaseResponse>> data;
    private String[] titles;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_trade_circle, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
        return root;
    }

    private void initView() {
        refreshView.setMode(PtrFrameLayout.Mode.REFRESH);
        mListView.addFooterView(LayoutInflater.from(context).inflate(R.layout.trade_list_footer, null));
        data = new LinkedHashMap<>();
        titles = new String[]{"行业头条", "生意宝典", "生意社区"};
        data.put(titles[0], getHeadData());
        data.put(titles[1], getCanonData());
        data.put(titles[2], getCommunityData());
        HomeTradeCircleAdapter adapter = new HomeTradeCircleAdapter(context, data);
        mListView.setAdapter(adapter);
        mListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                return true;
            }
        });
        for (int i = 0; i < adapter.getGroupCount(); i++) {
            mListView.expandGroup(i);
        }
    }

    String img = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501843518220&di=0306ae6f9c5434136495d0c45e016b2a&imgtype=0&src=http%3A%2F%2Fpic23.photophoto.cn%2F20120530%2F0020033092420808_b.jpg";

    private List<BaseResponse> getHeadData() {
        List<BaseResponse> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TradeHead item = new TradeHead();
            item.title = i == 0 ? "上海喜茶又搞事情，因黄牛得罪外卖小哥外卖小哥" : "这家店火得一发不可收拾";
            item.kind = i == 0 ? true : false;
            item.image = img;
            item.content = "新闻";
            item.dateTime = "2017-07-17";
            item.viewCount = "10000";
            item.source = "今日头条";
            list.add(item);
        }
        return list;
    }

    private List<BaseResponse> getCanonData() {
        List<BaseResponse> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            TradeCanon item = new TradeCanon();
            item.title = "全上海最好的铺子都在这里";
            item.image = img;
            item.content = "生意测评";
            item.dateTime = "2017-07-17";
            item.viewCount = "10000";
            list.add(item);
        }
        return list;
    }

    private List<BaseResponse> getCommunityData() {
        List<BaseResponse> list = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            TradeCommunity item = new TradeCommunity();
            item.title = "行业头条";
            item.imgList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                TradeCommunity.ImageList imageItem = new TradeCommunity.ImageList();
                imageItem.imgUrl = img;
                item.imgList.add(imageItem);
            }
            item.commentNumber = "5000";
            item.content = "金铺多成立于2017年4月，是金铺多集团旗下的国内首家专业商业地产网站，专注于商业地产信息服务专注于商业地产信息服务";
            item.dateTime = "6分钟前发布";
            item.praiseNumber = "10000";
            list.add(item);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
