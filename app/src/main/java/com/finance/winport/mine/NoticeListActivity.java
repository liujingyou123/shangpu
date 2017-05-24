package com.finance.winport.mine;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.mine.adapter.BusinessNoticeAdapter;
import com.finance.winport.mine.adapter.ServiceNoticeAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class NoticeListActivity extends BaseActivity {
    private final int LIMIT = 10;
    private final int START_PAGE = 1;
    private int pageSize = LIMIT;
    private int pageNumber = START_PAGE;
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
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    private int type;//通知类型：0-服务 1-系统 2-生意圈 3-商铺 4-工作
    private String title;
    LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    private void initData() {
        loading.show();
        asyncData();
    }

    private void initView() {
        type = getIntent().getIntExtra("type", -1);
        title = getIntent().getStringExtra("title");
        tvFocusHouse.setText(title);
        loading = new LoadingDialog(context);
        initRefreshView();
    }

    private void initRefreshView() {
        refreshView.autoLoadMore();
        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
                asyncData();
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                asyncData();
            }
        });

    }

    private void asyncData() {
        getNotifyList();
    }


    private void getNotifyList() {
        HashMap<String, Object> params = new HashMap<>();
        params.put("pageNumber", pageNumber);
        params.put("pageSize", pageSize);
        params.put("notifyType", type);
        PersonManager.getInstance().getNotifyList(params, new NetworkCallback<NotifyList>() {
            @Override
            public void success(NotifyList response) {
                refreshView.refreshComplete();
                loading.dismiss();
                if (response != null && response.isSuccess()) {
                    setAdapter(response);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                refreshView.refreshComplete();
                loading.dismiss();
            }
        });
    }

    private void setAdapter(NotifyList notifyList) {
        if (notifyList == null) return;
        if (type == 0) {
            int count = notifyList.data.totalSize;
            setServiceNoticeAdapter(notifyList.data.serviceNoticeRespDTOList, count);
        } else if (type == 2) {
            int count = notifyList.data.totalSize;
            setBusinessNoticeAdapter(notifyList.data.bussinessNoticeRespDTOList, count);
        }
    }

    BusinessNoticeAdapter businessNoticeAdapter;

    private void setBusinessNoticeAdapter(List<NotifyList.DataBean.BussinessNoticeBean> list, int totalCount) {
        if (businessNoticeAdapter == null) {
            businessNoticeAdapter = new BusinessNoticeAdapter(refreshView, list, totalCount);
            mListView.setAdapter(businessNoticeAdapter);
        } else {
            if (pageNumber == 1) {
                businessNoticeAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                businessNoticeAdapter.updateData(list, totalCount);
            }
        }
    }

    ServiceNoticeAdapter serviceNoticeAdapter;

    private void setServiceNoticeAdapter(List<NotifyList.DataBean.ServiceNoticeBean> list, int totalCount) {
        if (serviceNoticeAdapter == null) {
            serviceNoticeAdapter = new ServiceNoticeAdapter(refreshView, list, totalCount);
            mListView.setAdapter(serviceNoticeAdapter);
        } else {
            if (pageNumber == 1) {
                serviceNoticeAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                serviceNoticeAdapter.updateData(list, totalCount);
            }
        }
    }


    @OnClick({R.id.imv_focus_house_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_focus_house_back:
                finish();
                break;

        }
    }

}
