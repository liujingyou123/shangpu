package com.finance.winport.view.refreshview;

import android.content.Context;
import android.util.AttributeSet;

import com.finance.winport.view.refreshview.util.PtrLocalDisplay;

public class XPtrFrameLayout extends PtrFrameLayout {
    private XPtrHeaderFooter xPtrHeader;
    private XPtrHeaderFooter xPtrFooter;

    public XPtrFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public XPtrFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public XPtrFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        PtrLocalDisplay.init(getContext());

        xPtrHeader = new XPtrHeaderFooter(getContext());
        setHeaderView(xPtrHeader);
        addPtrUIHandler(xPtrHeader);
        xPtrFooter = new XPtrHeaderFooter(getContext());
        setFooterView(xPtrFooter);
        addPtrUIHandler(xPtrFooter);
    }

    public XPtrHeaderFooter getHeader() {
        return xPtrHeader;
    }

    public XPtrHeaderFooter getFooter() {
        return xPtrFooter;
    }
}