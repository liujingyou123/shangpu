package com.finance.winport.home;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.home.model.Shop;
import com.finance.winport.util.LogUtil;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.loadmore.OnScrollLisenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    Unbinder unbinder;
    @BindView(R.id.select_view)
    LinearLayout selectionView;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    private ShopsAdapter adapter;
    private List<Shop> mData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initListView();
        return root;
    }

    private void initListView() {
        final HeaderView headerView = new HeaderView(this.getContext());
        SelectView selectView = new SelectView(this.getContext());
        lsShops.addHeaderView(headerView);
        lsShops.addHeaderView(selectView);
        if (adapter == null) {
            adapter = new ShopsAdapter(this.getContext(), mData);
            lsShops.setAdapter(adapter);
        }

        refreshView.setLoadMoreEnable(true);
        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                LogUtil.e("dddddddddd");
            }
        });

        refreshView.setOnScrollListener(new OnScrollLisenter() {
            @Override
            public void onScrollStateChanged(AbsListView listView, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LogUtil.e("firstVisibleItem = " + firstVisibleItem);
                if (firstVisibleItem >= 1) {
                    LogUtil.e("setVisibility = " + "VISIBLE");
                    setSelectionViewVisible();
                } else {
                    LogUtil.e("setVisibility = " + "Gone");
                    selectionView.setVisibility(View.GONE);

                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                headerView.setTodayShop(5000f);
                lsShops.setSelection(1);

            }
        }, 2000);

    }

    private void setSelectionViewVisible() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectionView.setVisibility(View.VISIBLE);
            }
        }, 100);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
