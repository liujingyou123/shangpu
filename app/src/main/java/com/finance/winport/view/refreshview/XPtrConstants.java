package com.finance.winport.view.refreshview;

import com.finance.winport.view.refreshview.util.PtrLocalDisplay;

/**
 * Created by wangzg on 2017/5/5.
 */

class XPtrConstants {
    private static final int MAX_HEIGHT_DP = 90;
    private static final int MAX_DOT_DP = 30;
    private static final int MIN_DOT_DP = 5;
    private static final int DOT_MARGIN_DP = 20;
    public static final int ANIM_TIME_MS = 450;

    public static int MAX_HEIGHT_PX;
    public static int MAX_DOT_PX;
    public static int MIN_DOT_PX;
    public static int DOT_MARGIN_PX;

    public static void init() {
        MAX_HEIGHT_PX = PtrLocalDisplay.dp2px(MAX_HEIGHT_DP);
        MAX_DOT_PX = PtrLocalDisplay.dp2px(MAX_DOT_DP);
        MIN_DOT_PX = PtrLocalDisplay.dp2px(MIN_DOT_DP);
        DOT_MARGIN_PX = PtrLocalDisplay.dp2px(DOT_MARGIN_DP);
    }
}
