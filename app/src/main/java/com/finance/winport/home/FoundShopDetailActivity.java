package com.finance.winport.home;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.ShareDialog;
import com.finance.winport.home.adapter.FoundShopsCommendAdapter;
import com.finance.winport.home.model.FoundShopDetailResponse;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.presenter.FoundShopDetailPresenter;
import com.finance.winport.home.view.IFoundShopDetailView;
import com.finance.winport.image.Batman;
import com.finance.winport.service.model.LoanListResponse;
import com.finance.winport.util.H5Util;
import com.finance.winport.util.ListViewUtils;
import com.finance.winport.util.UnitUtil;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * gejin
 */

public class FoundShopDetailActivity extends BaseActivity implements IFoundShopDetailView {

    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.share)
    ImageView share;
    @BindView(R.id.top)
    RelativeLayout lltop;
    private FoundShopsCommendAdapter adapter;

    private ImageView img;
    private TextView title,time;
    private WebView contentView;
    private RelativeLayout commend;


    private List<FoundShopDetailResponse.DataBean.ShopListBean> list = new ArrayList<>();

    private FoundShopDetailPresenter mPresenter;
    private ShareDialog shareDialog;
    private FoundShopDetailResponse mShopDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_shop_detail);
        ButterKnife.bind(this);

        init();
        getData();
    }

    private void getData() {
        int contentId = getIntent().getIntExtra("contentId",-1);
        if (mPresenter == null) {
            mPresenter = new FoundShopDetailPresenter(this);
        }
        mPresenter.getFoundShopList(contentId);
    }


    public void init() {
        lltop.setAlpha(0);
        refreshView.setMode(PtrFrameLayout.Mode.NONE);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
            }
        });
        View header = LayoutInflater.from(context).inflate(R.layout.activity_found_shop_detail_header, null);
        img = (ImageView) header.findViewById(R.id.img_shop);
        title = (TextView) header.findViewById(R.id.title);
        time = (TextView) header.findViewById(R.id.time);
        contentView = (WebView) header.findViewById(R.id.content);
        commend = (RelativeLayout) header.findViewById(R.id.commend);
        WebSettings settings = contentView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        settings.setBlockNetworkImage(false);
        settings.setDomStorageEnabled(true);
        settings.setDisplayZoomControls(false);
        settings.setLoadWithOverviewMode(true);
        settings.setUseWideViewPort(true);
        contentView.setWebViewClient(new WebViewClient());
        mListView.addHeaderView(header);

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            private SparseArray<ListViewUtils.ItemRecord> recordSp = new SparseArray(0);

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

                View firstView = view.getChildAt(0);
                if (firstView != null) {
                    ListViewUtils.ItemRecord itemRecord = recordSp.get(firstVisibleItem);
                    if (itemRecord == null) {
                        itemRecord = new ListViewUtils.ItemRecord();
                    }
                    itemRecord.height = firstView.getHeight();
                    itemRecord.top = firstView.getTop();
                    recordSp.append(firstVisibleItem, itemRecord);
                }
                int scrollY = ListViewUtils.getScrollY(firstVisibleItem, recordSp);
//                updatePinnedView(scrollY);
                updateView(scrollY);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(FoundShopDetailActivity.this, ShopDetailActivity.class);
                intent.putExtra("shopId", list.get(position).getShopId() + "");
                startActivity(intent);
            }
        });

//        refreshView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
//            @Override
//            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
//
//                Log.i("滑动距离",scrollY+"");
//                Log.i("滑动距离",oldScrollY+"");
//
//                updateView(scrollY-oldScrollY);
//            }
//        });

    }

    private void updateView(float scrollY) {

        int top = UnitUtil.dip2px(context, 165);

        int titleView = UnitUtil.dip2px(context, 56);

        if (scrollY >= 0) {
            if (scrollY == 0) {
                back.setImageResource(R.mipmap.icon_white_back);
                share.setImageResource(R.mipmap.icon_share_white);
            } else {
                back.setImageResource(R.mipmap.icon_back);
                share.setImageResource(R.mipmap.icon_share_black);
            }
            float deltaY = top - titleView - scrollY;
            if (deltaY >= 0) {
                float fraction = deltaY / (top - titleView);
                lltop.setAlpha(1 - fraction);
            } else {
                lltop.setAlpha(1);
            }
        } else {
//            llTop.setAlpha(1);
        }
    }

    private void setAdapter(List<FoundShopDetailResponse.DataBean.ShopListBean> response) {
        list.addAll(response);
        if (adapter == null) {
            adapter = new FoundShopsCommendAdapter(FoundShopDetailActivity.this, list);
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


    @OnClick({R.id.back, R.id.share})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.share:
                if (mShopDetail == null) {
                    return;
                }
                if (shareDialog == null) {
                    shareDialog = new ShareDialog(this);
                }
                shareDialog.setDes(mShopDetail.getData().getDesc());
//                shareDialog.setDes(mShopDetail.getData().getAddress() + "(" + UnitUtil.formatSNum(mShopDetail.getData().getArea()) + "㎡)旺铺急租，租金仅" + rentPrice);
                shareDialog.setTitle(mShopDetail.getData().getTitle());
                shareDialog.setImage(mShopDetail.getData().getImage());
                shareDialog.setUrl(H5Util.getFoundShopDetail(mShopDetail.getData().getContentId()+""));
                shareDialog.show();
                break;
        }
    }

    @Override
    public void showFoundShopDetail(FoundShopDetailResponse response) {

        mShopDetail =response;
        title.setText(response.getData().getTitle());
        time.setText(response.getData().getDateTime());
        if(!TextUtils.isEmpty(response.getData().getContent())){

//            contentView.setText(Html.fromHtml(response.getData().getContent()));
            contentView.loadDataWithBaseURL(null,response.getData().getContent(),"text/html","utf-8",null);
        }
        Batman.getInstance().fromNet(response.getData().getImage(),img);
        if(response.getData().getShopList()==null||response.getData().getShopList().size()==0){
            commend.setVisibility(View.GONE);
        }else{
            commend.setVisibility(View.VISIBLE);
            setAdapter(response.getData().getShopList());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        contentView.destroy();
    }
}
