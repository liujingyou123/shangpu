package com.finance.winport.tab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.mine.MyNoticeActivity;
import com.finance.winport.mine.MyScheduleListActivity;
import com.finance.winport.mine.SettingsActivity;
import com.finance.winport.mine.ShopFocusActivity;
import com.finance.winport.view.StopWatchTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 *
 *
 */

public class MineFragment extends BaseFragment {


    @BindView(R.id.tv_focus_right)
    ImageView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.shop_img)
    ImageView shopImg;
    @BindView(R.id.address)
    TextView address;
    @BindView(R.id.modify)
    TextView modify;
    @BindView(R.id.tv_today_shop)
    StopWatchTextView tvTodayShop;
    Unbinder unbinder;
    @BindView(R.id.schedule_list)
    RelativeLayout scheduleList;
    @BindView(R.id.setting)
    RelativeLayout setting;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.mine_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.tv_focus_right, R.id.modify, R.id.schedule_list, R.id.setting})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_focus_right:
                startActivity(new Intent(getActivity(), MyNoticeActivity.class));
                break;
            case R.id.modify:
                startActivity(new Intent(getActivity(), ShopFocusActivity.class));
                break;
            case R.id.schedule_list:
                startActivity(new Intent(getActivity(), MyScheduleListActivity.class));
                break;
            case R.id.setting:
                startActivity(new Intent(getActivity(), SettingsActivity.class));
                break;
        }
    }

}
