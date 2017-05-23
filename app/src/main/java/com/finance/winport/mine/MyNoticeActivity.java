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
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.mine.adapter.NoticeCollectionAdapter;
import com.finance.winport.mine.adapter.ScheduleListAdapter;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.tab.model.NotifyType;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyNoticeActivity extends BaseActivity {


    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_right)
    TextView tvFocusRight;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.rl_title_root)
    RelativeLayout rlTitleRoot;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.empty_img)
    ImageView emptyImg;
    @BindView(R.id.empty)
    RelativeLayout empty;
    private NoticeCollectionAdapter adapter;
    LoadingDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_collection);
        ButterKnife.bind(this);
        initView();
        getNotifyType();
    }

    private void initView() {
        tvFocusHouse.setText("通知");
        loading = new LoadingDialog(context);
    }

    private void getNotifyType() {
        loading.show();
        PersonManager.getInstance().getNotifyType(new HashMap<String, Object>(), new NetworkCallback<NotifyType>() {
            @Override
            public void success(NotifyType response) {
                loading.dismiss();
                setAdapter(response.data.baseNoticeDTOList);
            }

            @Override
            public void failure(Throwable throwable) {
                loading.dismiss();
            }
        });
    }

    private void setAdapter(List<NotifyType.DataBean.BaseNoticeDTOListBean> list) {
        if (adapter == null) {
            adapter = new NoticeCollectionAdapter(context, list);
            lsCircles.setAdapter(adapter);
        }
        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MyNoticeActivity.this, NoticeListActivity.class));
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
                startActivity(new Intent(MyNoticeActivity.this, HistoryScheduleListActivity.class));
                break;
        }
    }
}
