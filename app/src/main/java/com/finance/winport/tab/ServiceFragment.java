package com.finance.winport.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.view.BannerView.Banner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 *
 *
 */

public class ServiceFragment extends BaseFragment {


    @BindView(R.id.banner)
    Banner banner;
    Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.service_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        init();
        return root;
    }


    private void init(){
        banner.start();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
