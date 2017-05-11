package com.finance.winport.trade;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.util.SlidingTagPagerItem;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 生意圈
 */
public class TradeCircleFragment extends BaseFragment {


    @BindView(R.id.my_list)
    ImageView myList;
    @BindView(R.id.id_tab)
    SlidingTabLayout idTab;
    @BindView(R.id.id_view_pager)
    ViewPager idViewPager;
    Unbinder unbinder;
    private List<SlidingTagPagerItem> mTab = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.trade_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initView();

        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    private void initView() {
        mTab.add(new TradePageItem("最新", "1"));
        mTab.add(new TradePageItem("最火", "2"));
        idViewPager.setAdapter(new SlidingTabPagerAdapter(this.getChildFragmentManager(), mTab));
        idTab.setViewPager(idViewPager, UnitUtil.dip2px(this.getContext(), 134));
    }

    @OnClick({R.id.my_list, R.id.imv_edit_m})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.my_list:
                break;
            case R.id.imv_edit_m:
                break;
        }
    }
}
