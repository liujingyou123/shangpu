package com.finance.winport.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.finance.winport.trade.TradeCircleListFragment;
import com.finance.winport.util.SlidingTagPagerItem;

/**
 * Created by jge on 17/8/7.
 */

public class MyServicePageItem extends SlidingTagPagerItem {

    public MyServicePageItem(String mTitle, String mMsg) {
        super(mTitle, mMsg);
    }
    @Override
    public Fragment createFragment() {
        MyServiceListFragment fragment = null;

        fragment = new MyServiceListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type", getMsg());
        fragment.setArguments(bundle);


        return fragment;
    }

}
