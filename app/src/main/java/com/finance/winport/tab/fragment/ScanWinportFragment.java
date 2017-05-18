package com.finance.winport.tab.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.home.event.HomeEvent;
import com.finance.winport.map.MyLocation;
import com.finance.winport.tab.TypeList;
import com.finance.winport.tab.adapter.AppointWinportAdapter;
import com.finance.winport.tab.adapter.CollectionWinportAdapter;
import com.finance.winport.tab.adapter.ScanWinportAdapter;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.ScanShopList;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * （我的收藏、最近浏览、我看过的） 旺铺
 */

public class ScanWinportFragment extends BaseFragment {
    private final int LIMIT = 10;
    private final int START_PAGE = 1;
    private int pageSize = LIMIT;
    private int pageNumber = START_PAGE;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.empty)
    RelativeLayout empty;
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
    String title;
    TypeList type;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            type = (TypeList) getArguments().getSerializable("type");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_scan_winport, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getCurrentLocation();
    }

    private void initView() {
        location = new MyLocation(context);
        setTitle();
        initRefreshView();
        initListView();
    }

    private void setTitle() {
        tvFocusHouse.setText(title);
    }

    private void setTip(String count, String rank) {
        String s1 = count;
        String s2 = rank;
        String s = "";
        switch (type) {
            case SCAN:
                s = getString(R.string.list_browser_tip, s1, s2);
                break;
            case COLLECTION:
                s = getString(R.string.list_collection_tip, s1, s2);
                break;
            case APPOINT:
                s = getString(R.string.list_appoint_tip, s1, s2);
                break;
        }
        if (TextUtils.isEmpty(s)) return;
        SpannableString sp = new SpannableString(s);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s1), s.indexOf(s1) + s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s2), s.indexOf(s2) + s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tip.setText(sp);
        tip.setVisibility(View.VISIBLE);
    }

    private void initListView() {
//        mListView.setAdapter(new ScanWinportAdapter(refreshView, null, 0));
    }

    private void initRefreshView() {
        refreshView.autoLoadMore();
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                getCurrentLocation();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                getCurrentLocation();
            }
        });

    }

    MyLocation location;
    String longitude;
    String latitude;

    private void getCurrentLocation() {
        location.start(new MyLocation.XLocationListener() {
            @Override
            public void result(boolean result, BDLocation location) {
                longitude = location.getLongitude() + "";
                latitude = location.getLatitude() + "";
                asyncData();
            }
        });
    }

    // 约看次数和排名
    private void getAppointRanking() {
        PersonManager.getInstance().getAppointRanking(new HashMap<String, Object>(), new NetworkCallback<AppointRanking>() {
            @Override
            public void success(AppointRanking response) {
                setTip(response.data.total, response.data.rate);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    // 最近浏览次数和排名
    private void queryBrowserCount() {
        PersonManager.getInstance().queryBrowserCount(new HashMap<String, Object>(), new NetworkCallback<AppointRanking>() {
            @Override
            public void success(AppointRanking response) {
                setTip(response.data.total, response.data.rate);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    // 收藏次数和排名
    private void queryCollectionCount() {
        PersonManager.getInstance().queryCollectionCount(new HashMap<String, Object>(), new NetworkCallback<AppointRanking>() {
            @Override
            public void success(AppointRanking response) {
                setTip(response.data.total, response.data.rate);
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }


    // 获取约看列表
    private void getAppointList() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        params.put("longitude", longitude);
        params.put("latitude", latitude);
        PersonManager.getInstance().getAppointList(params, new NetworkCallback<AppointShopList>() {
            @Override
            public void success(AppointShopList response) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                if (response != null && response.isSuccess() && response.data.totalSize > 0) {
                    setAppointAdapter(response.data.data, response.data.totalSize);
                } else {
                    showEmptyView(true);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                showEmptyView(true);
            }
        });
    }

    //获取收藏列表
    private void getCollectionList() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        PersonManager.getInstance().getCollectionList(params, new NetworkCallback<ScanShopList>() {
            @Override
            public void success(ScanShopList response) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                if (response != null && response.isSuccess() && response.data.totalSize > 0) {
                    setCollectionAdapter(response.data.data, response.data.totalSize);
                } else {
                    showEmptyView(true);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                showEmptyView(true);
            }
        });
    }

    // 获取浏览列表
    private void getScanList() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        PersonManager.getInstance().getScanList(params, new NetworkCallback<ScanShopList>() {
            @Override
            public void success(ScanShopList response) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                if (response != null && response.isSuccess() && response.data.totalSize > 0) {
                    setScanAdapter(response.data.data, response.data.totalSize);
                } else {
                    showEmptyView(true);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                showEmptyView(true);
            }
        });
    }

    // 约看
    private AppointWinportAdapter appointWinportAdapter;

    private void setAppointAdapter(List<AppointShopList.DataBeanX.DataBean> list, int totalCount) {
        if (appointWinportAdapter == null) {
            appointWinportAdapter = new AppointWinportAdapter(refreshView, list, totalCount);
            mListView.setAdapter(appointWinportAdapter);
        } else {
            if (pageNumber == 1) {
                appointWinportAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                appointWinportAdapter.updateData(list, totalCount);
            }
        }
    }

    // 收藏
    private CollectionWinportAdapter collectionWinportAdapter;

    private void setCollectionAdapter(List<ScanShopList.DataBeanX.DataBean> list, int totalCount) {
        if (collectionWinportAdapter == null) {
            collectionWinportAdapter = new CollectionWinportAdapter(refreshView, list, totalCount);
            mListView.setAdapter(collectionWinportAdapter);
        } else {
            if (pageNumber == 1) {
                collectionWinportAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                collectionWinportAdapter.updateData(list, totalCount);
            }
        }
    }

    // 最近浏览
    private ScanWinportAdapter scanWinportAdapter;

    private void setScanAdapter(List<ScanShopList.DataBeanX.DataBean> list, int totalCount) {
        if (scanWinportAdapter == null) {
            scanWinportAdapter = new ScanWinportAdapter(refreshView, list, totalCount);
            mListView.setAdapter(scanWinportAdapter);
        } else {
            if (pageNumber == 1) {
                scanWinportAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                scanWinportAdapter.updateData(list, totalCount);
            }
        }
    }


    private void showEmptyView(boolean show) {
        if (show) {
            TextView tv = (TextView) empty.findViewById(R.id.tv_empty);
            switch (type) {
                case APPOINT://约看
                    tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_empty_appoint, 0, 0);
                    tv.setText("还木有约看过旺铺哟~");
                    break;
                case COLLECTION://收藏
                    tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_empty_collection, 0, 0);
                    tv.setText("还没有收藏哦~~");
                    break;
                case SCAN://浏览
                    tv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_empty_scan, 0, 0);
                    tv.setText("还木有旺铺浏览记录哟~");
                    break;
            }
        }
        empty.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void asyncData() {
        switch (type) {
            case APPOINT:
                getAppointRanking();
                getAppointList();
                break;
            case COLLECTION:
                queryCollectionCount();
                getCollectionList();
                break;
            case SCAN:
                queryBrowserCount();
                getScanList();
                break;
        }
    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

    @OnClick(R.id.confirm)
    public void onConfirmClicked() {
        startActivity(new Intent(context, MainActivity.class));
    }
}
