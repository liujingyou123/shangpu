package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.trade.adapter.TradeHomeCircleAdapter;
import com.finance.winport.trade.model.TradeBible;
import com.finance.winport.trade.model.TradeHead;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.trade.presenter.TradeHomePresenter;
import com.finance.winport.trade.view.ITradeHomeView;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
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
public class TradeHomeCircleFragment extends BaseFragment implements ITradeHomeView {


    @BindView(R.id.mListView)
    ExpandableListView mListView;
    @BindView(R.id.refresh_view)
    PtrFrameLayout refreshView;
    Unbinder unbinder;
    private Map<String, List<BaseResponse>> mData;
    private String[] titles;
    private TradeHomeCircleAdapter adapter;
    private TradeHomePresenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TradeHomePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home_trade_circle, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();
//        asyncData();
        return root;
    }

    private void initView() {
        initRefreshView();
        mListView.addFooterView(LayoutInflater.from(context).inflate(R.layout.trade_list_footer, null));
        mData = new LinkedHashMap<>();
        titles = new String[]{"行业头条", "生意宝典", "生意社区"};
        initData();
        adapter = new TradeHomeCircleAdapter(context, presenter, mData);
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

    private void asyncData() {
        presenter.getTradeHome(true);
    }

    private void initData() {
        mData.put(titles[0], getHeadData());
        mData.put(titles[1], getCanonData());
        mData.put(titles[2], getCommunityData());
    }

    private void initRefreshView() {
        refreshView.setMode(PtrFrameLayout.Mode.REFRESH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                presenter.getTradeHome(false);
                refreshView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshView.refreshComplete();
                    }
                }, 500);
            }
        });
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
            TradeBible item = new TradeBible();
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
            TradeTopic item = new TradeTopic();
            item.title = "行业头条";
            item.imgList = new ArrayList<>();
            if (i % 2 != 0) {
                for (int j = 0; j <= i; j++) {
                    TradeTopic.imgBean imageItem = new TradeTopic.imgBean();
                    imageItem.imgUrl = img;
                    item.imgList.add(imageItem);
                }
            } else {
                item.h5obj = new TradeTopic.Href();
                item.h5obj.title = "万安路104号";
                item.h5obj.content = "一周内890位老板浏览了此商铺";
                item.h5obj.imageUrl = img;
            }
            item.nickName = "稻草人Kevin";
            item.headPicture = img;
            item.signature = "我是一个低调的老板";
            item.commentNumber = 5000;
            item.content = "金铺多成立于2017年4月，是金铺多集团旗下的国内首家专业商业地产网站，专注于商业地产信息服务专注于商业地产信息服务";
            item.dateTime = "6分钟前发布";
            item.praiseNumber = 10000;
            list.add(item);
        }
        return list;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    int groupPosition, childPosition;

    @Override
    public void setAdapter(TradeHome data) {
        if (data != null) {
            // add head
            if (!mData.containsKey(titles[0])) {
                List<BaseResponse> list = new ArrayList<>();
                list.addAll(data.data.headlineList);
                mData.put(titles[0], list);
            } else {
                List<BaseResponse> list = mData.get(titles[0]);
                list.clear();
                list.addAll(data.data.headlineList);
            }
            //add bible
            if (!mData.containsKey(titles[1])) {
                List<BaseResponse> list = new ArrayList<>();
                list.addAll(data.data.bibleList);
                mData.put(titles[1], list);
            } else {
                List<BaseResponse> list = mData.get(titles[1]);
                list.clear();
                list.addAll(data.data.bibleList);
            }
            // add topic
            if (!mData.containsKey(titles[2])) {
                List<BaseResponse> list = new ArrayList<>();
                list.addAll(data.data.topicList);
                mData.put(titles[2], list);
            } else {
                List<BaseResponse> list = mData.get(titles[2]);
                list.clear();
                list.addAll(data.data.topicList);
            }

            if (adapter == null) {
                adapter = new TradeHomeCircleAdapter(context, presenter, mData);
                mListView.setAdapter(adapter);
            } else {
                adapter.notifyDataSetChanged();
            }
            mListView.setSelectedChild(groupPosition, childPosition, true);
            groupPosition = childPosition = 0;
        }
    }

    @Override
    public void showError(String errorMsg) {

    }

    @Override
    public void zanTopic(boolean success, String topicId, int groupPosition, int position) {
        if (success) {
            if (mData != null) {
                List<BaseResponse> list = mData.get(titles[groupPosition]);
                BaseResponse find = null;
                if (list != null) {
                    if (list.size() > position) {
                        TradeTopic item = (TradeTopic) list.get(position);
                        if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                            item.praiseNumber += 1;
                            item.likeStatus = "1";
                            find = item;
                        }
                    }
                    if (find == null) {
                        for (int i = 0; i < list.size(); i++) {
                            TradeTopic item = (TradeTopic) list.get(i);
                            if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                                item.praiseNumber += 1;
                                item.likeStatus = "1";
                                find = item;
                                break;
                            }
                        }
                    }
                    if (find != null && adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }

    @Override
    public void cancelZanTopic(boolean success, String topicId, int groupPosition, int position) {
        if (success) {
            if (mData != null) {
                List<BaseResponse> list = mData.get(titles[groupPosition]);
                BaseResponse find = null;
                if (list != null) {
                    if (list.size() > position) {
                        TradeTopic item = (TradeTopic) list.get(position);
                        if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                            item.praiseNumber -= 1;
                            item.likeStatus = "0";
                            find = item;
                        }
                    }
                    if (find == null) {
                        for (int i = 0; i < list.size(); i++) {
                            TradeTopic item = (TradeTopic) list.get(i);
                            if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                                item.praiseNumber -= 1;
                                item.likeStatus = "0";
                                find = item;
                                break;
                            }
                        }
                    }
                    if (find != null && adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }


    @Override
    public void deleteTopic(boolean success, String topicId, int groupPosition, int childPosition) {
        if (success) {
            if (mData != null) {
                BaseResponse remove = null;
                List<BaseResponse> list = mData.get(titles[groupPosition]);
                if (list != null) {
                    if (list.size() > childPosition) {
                        TradeTopic item = (TradeTopic) list.get(childPosition);
                        if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                            remove = list.remove(childPosition);
                        }
                    }
                    if (remove == null) {
                        for (int i = 0; i < list.size(); i++) {
                            TradeTopic item = (TradeTopic) list.get(i);
                            if (item != null && TextUtils.equals(item.topicId + "", topicId)) {
                                list.remove(item);
                                remove = item;
                                break;
                            }
                        }
                    }
                    if (remove != null && adapter != null) {
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }
}
