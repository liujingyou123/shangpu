package com.finance.winport.view.refreshview.loadmore;

import android.widget.AbsListView;

/**
 * Created by liuworkmac on 16/11/3.
 */
public interface OnScrollLisenter {
    void onScrollStateChanged(AbsListView listView, int scrollState);
    void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
}
