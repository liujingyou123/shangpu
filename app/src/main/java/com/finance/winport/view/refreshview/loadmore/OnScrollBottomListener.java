package com.finance.winport.view.refreshview.loadmore;

import android.widget.AbsListView;

public interface OnScrollBottomListener {
	void onScrollBottom();
	void onScrollStateChanged(AbsListView listView, int scrollState);
	void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount);
}
