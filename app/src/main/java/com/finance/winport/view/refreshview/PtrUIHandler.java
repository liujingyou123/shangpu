package com.finance.winport.view.refreshview;

import com.finance.winport.view.refreshview.indicator.PtrIndicator;

public interface PtrUIHandler {

    /**
     * When the content view has reached top and refresh has been completed, view will be reset.
     *
     * @param frame
     */
    void onUIReset(PtrFrameLayout frame);

    /**
     * prepare for loading
     *
     * @param frame
     */
    void onUIRefreshPrepare(PtrFrameLayout frame);

    /**
     * perform refreshing UI
     */
    void onUIRefreshBegin(PtrFrameLayout frame);

    /**
     * perform UI after refresh
     */
    void onUIRefreshComplete(PtrFrameLayout frame, boolean isHeader);

    void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator);
}
