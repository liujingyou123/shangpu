package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;

/**
 * 生意圈
 */
public class TradeCircleFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.business_fragment, container, false);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
