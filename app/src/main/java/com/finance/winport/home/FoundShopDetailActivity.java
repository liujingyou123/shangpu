package com.finance.winport.home;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.home.adapter.FoundShopsCommendAdapter;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.util.ListViewUtils;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * gejin
 */

public class FoundShopDetailActivity extends BaseActivity {

    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.top)
    RelativeLayout lltop;
    private FoundShopsCommendAdapter adapter;


    private int pageNum = 1;

    private List<ShopListResponse.DataBean.Shop> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_shop_detail);
        ButterKnife.bind(this);

        init();
        getData();
    }

    private void getData() {
        setAdapter(null);
    }


    public void init() {
        lltop.setAlpha(0);
        refreshView.setMode(PtrFrameLayout.Mode.NONE);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
//                asyncData();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNum++;
                getData();
            }
        });
        View header = LayoutInflater.from(context).inflate(R.layout.activity_found_shop_detail_header, null);
        mListView.addHeaderView(header);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private SparseArray<ListViewUtils.ItemRecord> recordSp = new SparseArray(0);
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                View firstView = view.getChildAt(0);
                if (firstView != null) {
                    ListViewUtils.ItemRecord itemRecord = recordSp.get(firstVisibleItem);
                    if (itemRecord == null) {
                        itemRecord = new ListViewUtils.ItemRecord();
                    }
                    itemRecord.height = firstView.getHeight();
                    itemRecord.top = firstView.getTop();
                    recordSp.append(firstVisibleItem, itemRecord);
                }
                int scrollY = ListViewUtils.getScrollY(firstVisibleItem, recordSp);
//                updatePinnedView(scrollY);
                updateView(scrollY);
            }
        });

//        refreshView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                Log.i("滑动距离",scrollY+"");
//                Log.i("滑动距离",oldScrollY+"");
//
//                updateView(scrollY-oldScrollY);
//            }
//        });

    }

    private void updateView(float scrollY) {

        int top = UnitUtil.dip2px(context, 165);

        int titleView = UnitUtil.dip2px(context, 56);

        if (scrollY >= 0) {
            if (scrollY == 0) {
                back.setImageResource(R.mipmap.icon_white_back);
                share.setImageResource(R.mipmap.icon_share_white);
            } else {
                back.setImageResource(R.mipmap.icon_back);
                share.setImageResource(R.mipmap.icon_share_black);
            }
            float deltaY = top - titleView - scrollY;
            if (deltaY >= 0) {
                float fraction = deltaY / (top - titleView);
                lltop.setAlpha(1 - fraction);
            } else {
                lltop.setAlpha(1);
            }
        } else {
//            llTop.setAlpha(1);
        }
    }

    private void setAdapter(LoanListResponse response) {
//        list.addAll(response.getData().getData());
        if (adapter == null) {
            adapter = new FoundShopsCommendAdapter(FoundShopDetailActivity.this, list);
            mListView.setAdapter(adapter);
//            totalPage = (int) Math.ceil(adapter.getTotalCount() / (float) LIMIT);
        } else {
//            if (pageNumber == 1) {
//                adapter.refreshData(list, totalCount);
//            } else {
//                adapter.updateData(list, totalCount);
//            }
            adapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

}
