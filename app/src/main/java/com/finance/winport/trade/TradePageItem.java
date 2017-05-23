package com.finance.winport.trade;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.finance.winport.util.SlidingTagPagerItem;

/**
 * Created by liuworkmac on 17/5/10.
 */

public class TradePageItem extends SlidingTagPagerItem {

    public TradePageItem(String mTitle, String mMsg) {
        super(mTitle, mMsg);
    }
    @Override
    public Fragment createFragment() {
        TradeCircleListFragment fragment = null;
        if ("0".equals(getMsg())) {
            fragment = new TradeCircleListFragment();
        } else if ("1".equals(getMsg())) {
            fragment = new TradeCircleListFragment();
        }

        Bundle bundle = new Bundle();
        bundle.putString("type", getMsg());
        fragment.setArguments(bundle);


        return fragment;
    }

}
