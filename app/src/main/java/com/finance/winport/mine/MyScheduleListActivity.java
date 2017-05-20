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
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.mine.presenter.IScheduleListView;
import com.finance.winport.mine.presenter.ScheduleListPresenter;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.presenter.LoanListPresenter;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyScheduleListActivity extends BaseActivity implements IScheduleListView {

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
    private ScheduleListPresenter mPresenter;

    private int pageNum = 1;
    private List<ScheduleListResponse.DataBean.ScheduleListBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_list);
        ButterKnife.bind(this);
        init();
        getData();
    }


    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ScheduleListPresenter(this);
        }
        mPresenter.getScheduleList(pageNum,0);
    }

    public void init(){
        tvFocusHouse.setText("我的日程");
        tvFocusRight.setText("历史");
        refreshView.setMode(PtrFrameLayout.Mode.LOAD_MORE);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
//                pageNumber = 1;
//                asyncData();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNum++;
                getData();
            }
        });
    }


    private void setAdapter(ScheduleListResponse response) {
        list.addAll(response.getData().getScheduleList());
        if (adapter == null) {
            adapter = new ScheduleListAdapter(MyScheduleListActivity.this,list);
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
                Intent intent = new Intent(MyScheduleListActivity.this,ScheduleDetailActivity.class);
                intent.putExtra("scheduleId",list.get(position).getScheduleId());
                startActivity(intent);
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

    @Override
    public void showScheduleList(ScheduleListResponse response) {
        refreshView.refreshComplete();
        setAdapter(response);
    }

//    @OnClick(R.id.imv_focus_house_back)
//    public void onViewClicked() {
//        finish();
//    }
}
