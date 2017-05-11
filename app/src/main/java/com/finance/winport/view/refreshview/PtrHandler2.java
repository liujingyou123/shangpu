package com.finance.winport.view.refreshview;

import android.view.View;

public interface PtrHandler2 extends PtrHandler {

    /**
     * Check can do load more or not. For example the content is empty or the first child is in view.
     */
    boolean checkCanDoLoadMore(final PtrFrameLayout frame, final View content, final View footer);

    /**
     * When load more begin
     */
    void onLoadMoreBegin(final PtrFrameLayout frame);
}