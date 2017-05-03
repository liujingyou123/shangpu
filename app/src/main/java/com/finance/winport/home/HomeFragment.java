package com.finance.winport.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.home.model.Shop;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    Unbinder unbinder;

    private ShopsAdapter adapter;
    private List<Shop> mData = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initListView();
        return root;
    }

    private void initListView() {
        HeaderView headerView = new HeaderView(this.getContext());
        SelectView selectView = new SelectView(this.getContext());
        lsShops.addHeaderView(headerView);
        lsShops.addHeaderView(selectView);

        if (adapter == null) {
            adapter = new ShopsAdapter(this.getContext(), mData);
            lsShops.setAdapter(adapter);
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
