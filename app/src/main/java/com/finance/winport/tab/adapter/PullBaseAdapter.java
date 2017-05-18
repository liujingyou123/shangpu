package com.finance.winport.tab.adapter;

import android.content.Context;
import android.util.Log;
import android.widget.BaseAdapter;

import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xzw
 */
public abstract class PullBaseAdapter<E> extends BaseAdapter {
    private static final String TAG = "PullBaseAdapter";
    protected PtrClassicFrameLayout baseView;
    protected int maxTotal;
    protected List<E> baseData;
    protected Context context;
    private boolean isLoadMore;

    public PullBaseAdapter(PtrClassicFrameLayout baseView, List<E> baseData, int maxTotal) {
        if (baseView == null) {
            throw new IllegalArgumentException("baseView should not null");
        }
        this.baseView = baseView;
        this.baseData = baseData;
        this.context = baseView.getContext();
        if (baseData == null) {
            this.baseData = new ArrayList<E>();
        }
        this.maxTotal = maxTotal;
        this.initFooterView();
    }

    protected void notifyDataChanged() {
        initHeaderView();
        initFooterView();
        notifyDataSetChanged();
    }

    //初始化header
    private void initHeaderView() {
//        if (baseView == null) return;
//        baseView.refreshComplete();
    }

    /**
     * 初始化footerView，是否要显示
     */
    protected void initFooterView() {
        if (baseView == null) return;
        Log.i(TAG, "initFooterView getCount=" + getCount() + ", maxTotal=" + maxTotal);
        if (getCount() < maxTotal) {
            isLoadMore = true;
            baseView.setMode(PtrFrameLayout.Mode.BOTH);
        } else {
            isLoadMore = false;
            baseView.setMode(PtrFrameLayout.Mode.REFRESH);
            baseView.refreshComplete();
        }
    }

    public void refreshData(List<E> list) {
        if (list == null) return;
        this.baseData = new ArrayList<E>();
        this.baseData.addAll(list);
        notifyDataChanged();
    }

    public void refreshData(List<E> list, int maxTotal) {
        if (list == null) return;
        this.baseData = new ArrayList<E>();
        this.baseData.addAll(list);
        this.maxTotal = maxTotal;
        notifyDataChanged();
    }

    /**
     * 更新数据
     *
     * @param list
     */
    public void updateData(List<E> list) {
        if (list == null) return;
        if (this.baseData == null) {
            this.baseData = new ArrayList<E>();
        }
        this.baseData.addAll(list);
        notifyDataChanged();
    }

    public void updateData(List<E> list, int maxTotal) {
        if (list == null) return;
        if (this.baseData == null) {
            this.baseData = new ArrayList<E>();
        }
        this.baseData.addAll(list);
        this.maxTotal = maxTotal;
        this.notifyDataChanged();
    }


    /**
     * 是否需要加载更多
     *
     * @return
     */
    public boolean isLoadMore() {
        return isLoadMore;
    }

    public List<E> getData() {
        return baseData;
    }

    @Override
    public int getCount() {
        return baseData == null ? 0 : baseData.size();
    }

    @Override
    public Object getItem(int position) {
        return baseData == null ? null : baseData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public int getTotalCount() {
        return maxTotal;
    }
}

