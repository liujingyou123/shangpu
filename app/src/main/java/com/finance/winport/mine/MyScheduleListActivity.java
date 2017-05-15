package com.finance.winport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.mine.adapter.ScheduleListAdapter;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyScheduleListActivity extends BaseActivity {

    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.empty_tips)
    ImageView emptyTips;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    private ScheduleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        ButterKnife.bind(this);
        setAdapter();
    }


    private void setAdapter() {
        tvFocusHouse.setText("我的日程");
        tvFocusRight.setText("历史");
        if (adapter == null) {
            adapter = new ScheduleListAdapter(MyScheduleListActivity.this);
            mListView.setAdapter(adapter);
//            totalPage = (int) Math.ceil(adapter.getTotalCount() / (float) LIMIT);
        } else {
//            if (pageNumber == 1) {
//                adapter.refreshData(list, totalCount);
//            } else {
//                adapter.updateData(list, totalCount);
//            }
            adapter.notifyDataSetChanged();
        }
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyScheduleListActivity.this,ScheduleDetailActivity.class));
            }
        });
    }

    @OnClick({R.id.imv_focus_house_back, R.id.tv_focus_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;
            case R.id.tv_focus_right:
                startActivity(new Intent(MyScheduleListActivity.this,HistoryScheduleListActivity.class));
                break;
        }
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        finish();
//    }
}
