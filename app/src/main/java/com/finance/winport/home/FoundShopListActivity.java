package com.finance.winport.home;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.service.adapter.LoanListAdapter;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.service.presenter.ILoanListView;
import com.finance.winport.service.presenter.LoanListPresenter;
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

public class FoundShopListActivity extends BaseActivity implements ILoanListView{

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
    private LoanListAdapter adapter;

    private LoanListPresenter mPresenter;

    private int pageNum = 1;

    private List<LoanListResponse.DataBeanX.DataBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan_apply);
        ButterKnife.bind(this);

        init();
        getData();
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new LoanListPresenter(this);
        }
        mPresenter.getLoanList(pageNum);
    }


    public void init(){
        tvFocusHouse.setText("发现旺铺");
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

    private void setAdapter(LoanListResponse response) {
        list.addAll(response.getData().getData());
        if (adapter == null) {
            adapter = new LoanListAdapter(FoundShopListActivity.this,list);
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
    public void shopLoanList(LoanListResponse response) {

        refreshView.refreshComplete();
        setAdapter(response);
    }
}
