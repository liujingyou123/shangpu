package com.finance.winport.home;

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
import com.finance.winport.home.adapter.FoundShopListAdapter;
import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.home.presenter.FoundShopListPresenter;
import com.finance.winport.home.view.IFoundShopListView;
import com.finance.winport.mine.MyScheduleListActivity;
import com.finance.winport.mine.presenter.ScheduleListPresenter;
import com.finance.winport.service.adapter.LoanListAdapter;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.presenter.ILoanListView;
import com.finance.winport.service.presenter.LoanListPresenter;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * gejin
 */

public class FoundShopListActivity extends BaseActivity implements IFoundShopListView {

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
    private FoundShopListAdapter adapter;
    private FoundShopListPresenter mPresenter;


    private int pageNum = 1;

    private List<FoundShopListResponse.DataBeanX.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_shop);
        ButterKnife.bind(this);

        init();
        getData();
    }

    private void getData() {

        if (mPresenter == null) {
            mPresenter = new FoundShopListPresenter(this);
        }
        mPresenter.getFoundShopList(pageNum);
    }


    public void init(){
        tvFocusHouse.setText("发现旺铺");
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNum = 1;
                getData();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNum++;
                getData();
            }
        });
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FoundShopListActivity.this,FoundShopDetailActivity.class);
                startActivity(intent);

            }
        });
    }

    private void setAdapter(List<FoundShopListResponse.DataBeanX.DataBean> response) {
        if(pageNum==1){
            list.clear();
        }
        list.addAll(response);
        if (adapter == null) {
            adapter = new FoundShopListAdapter(FoundShopListActivity.this,list);
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
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void showFoundShopList(FoundShopListResponse response) {

        refreshView.refreshComplete();
//        if(list.size()==0){
//
//            if(response.getData().getScheduleList()==null||response.getData().getScheduleList().size()==0){
//                empty.setVisibility(View.VISIBLE);
//                refreshView.setVisibility(View.GONE);
//
//            }
//        }
        if(list.size()>=response.getData().getTotalSize()){
            if(pageNum>1){

                ToastUtil.show(FoundShopListActivity.this,"没有更多了");
            }
        }
        setAdapter(response.getData().getData());
    }

    @Override
    public void onError() {

        refreshView.refreshComplete();
    }
}
