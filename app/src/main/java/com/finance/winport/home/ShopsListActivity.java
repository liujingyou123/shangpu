package com.finance.winport.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseActivity;
import com.finance.winport.dialog.QuyuPopupView;
import com.finance.winport.dialog.SelectionDialog;
import com.finance.winport.dialog.SortPopupView;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.presenter.ShopListPresenter;
import com.finance.winport.home.view.IShopListView;
import com.finance.winport.view.home.SelectView;
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
 * Created by liuworkmac on 17/5/9.
 */

public class ShopsListActivity extends BaseActivity implements IShopListView {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.select_view)
    SelectView selectView;
    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;

    private ShopsAdapter adapter;
    private List<ShopListResponse.DataBean.Shop> mData = new ArrayList<>();

    private QuyuPopupView quyuPopupView;
    private SortPopupView sortPopupView;
    private SelectionDialog selectionDialog;

    private ShopRequset mRequest = new ShopRequset();
    private ShopListPresenter mPresenter;
    private int index;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopslist);
        ButterKnife.bind(this);
        init();
        getIntentData();
        getData();

    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new ShopListPresenter(this);
        }
        mRequest.queryType = 1;
        mPresenter.getShopList(mRequest);
    }

    private void init() {

        if (adapter == null) {
            adapter = new ShopsAdapter(this, mData);
            lsShops.setAdapter(adapter);
            lsShops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    MobclickAgent.onEvent(context, "shoplist_shop");
                    ShopListResponse.DataBean.Shop shop = (ShopListResponse.DataBean.Shop) parent.getItemAtPosition(position);
                    if (shop != null) {
                        Intent intent = new Intent(ShopsListActivity.this, ShopDetailActivity.class);
                        intent.putExtra("shopId", shop.getId() + "");
                        startActivity(intent);
                    }

                }
            });
        }

        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mRequest.pageNumber++;
                mPresenter.getMoreShopList(mRequest);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mRequest.pageNumber = 1;
                mPresenter.getShopList(mRequest);
            }
        });

        selectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_place");
                showShowQuYuDialog();
            }
        });

        selectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_sort");
                showPaiXuDailog();
            }
        });

        selectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_select");
                showShaiXuandialog();
            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            index = intent.getIntExtra("index", -1);
            if (index == 0) {
                tvFocusHouse.setText("今日新铺");
            } else if (index == 1) {
                tvFocusHouse.setText("无转让费");

            } else if (index == 2) {
                tvFocusHouse.setText("百平小铺");

            } else if (index == 3) {
                tvFocusHouse.setText("临近地铁");

            }
            mRequest.shopType = index + "";
            mRequest.longitude = intent.getStringExtra("lon");
            mRequest.latitude = intent.getStringExtra("lat");
        }

    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (sortPopupView != null && sortPopupView.isShowing()) {
                sortPopupView.dismiss();
                return true;
            }

            if (quyuPopupView != null && quyuPopupView.isShowing()) {
                quyuPopupView.dismiss();
                return true;

            }

            return super.onKeyDown(keyCode, event);
        }
        return super.onKeyDown(keyCode, event);

    }

    private void showShowQuYuDialog() {
        if (quyuPopupView == null) {
            quyuPopupView = new QuyuPopupView(this);
            quyuPopupView.setOnSelectionListener(new QuyuPopupView.OnSelectListener() {
                @Override
                public void onSelect(ShopRequset requst) {
                    onQuyuHandle(requst);
                }
            });
            quyuPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    selectView.onLocationArrowDown();
                    ShopRequset requset = quyuPopupView.getShopRequest();
                    if (requset == null || (TextUtils.isEmpty(requset.districtId) && TextUtils.isEmpty(requset.blockId)
                            && TextUtils.isEmpty(requset.metroId) && TextUtils.isEmpty(requset.stationId))) {
                        selectView.onLocationUnClick();
                    }
                }
            });


        }
        if (!quyuPopupView.isShowing()) {
            if (sortPopupView != null && sortPopupView.isShowing()) {
                sortPopupView.dismiss();
            }
            quyuPopupView.showAsDropDown(selectView);
            selectView.onLocationClick();
            selectView.onLocationArrowUp();
        } else {
            quyuPopupView.dismiss();
        }
    }

    private void showPaiXuDailog() {
        if (sortPopupView == null) {
            sortPopupView = new SortPopupView(this);
            sortPopupView.setOnSortSelectListener(new SortPopupView.OnSortSelectListener() {
                @Override
                public void onSortSelect(String sortType, String sortTypeName) {
                    selectView.onSortArrowDown();
                    if ("0".equals(sortType)) {
                        mRequest.sortType = null;
                        selectView.setSortText("排序");
                        selectView.onSortUnClick();
                    } else {
                        mRequest.sortType = sortType;
                        selectView.setSortText(sortTypeName);
                    }
                    mRequest.pageNumber = 1;
                    mPresenter.getShopList(mRequest);
                }
            });
            sortPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    selectView.onSortArrowDown();
                    if (TextUtils.isEmpty(mRequest.sortType)) {
                        selectView.onSortUnClick();
                    }
                }
            });
        }
        if (!sortPopupView.isShowing()) {
            if (quyuPopupView != null && quyuPopupView.isShowing()) {
                quyuPopupView.dismiss();
            }
            sortPopupView.showAsDropDown(selectView);
            selectView.onSortClick();
            selectView.onSortArrowUp();
        }
    }

    private void showShaiXuandialog() {
        if (selectionDialog == null) {
            selectionDialog = new SelectionDialog(this);
            selectionDialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
                @Override
                public void onSelect(ShopRequset request) {
                    selectView.onCsUnClick();
                    if (request.rentList != null && request.rentList.size() > 0) {
                        selectView.onCsClick();
                        mRequest.rentList = request.rentList;
                    } else {
                        mRequest.rentList = null;
                    }
                    if (request.transferList != null && request.transferList.size() > 0) {
                        selectView.onCsClick();
                        mRequest.transferList = request.transferList;
                    } else {
                        mRequest.transferList = null;
                    }
                    if (request.areaList != null && request.areaList.size() > 0) {
                        selectView.onCsClick();
                        mRequest.areaList = request.areaList;
                    } else {
                        mRequest.areaList = null;
                    }
                    mRequest.width = request.width;
                    if (request.featureTagList != null && request.featureTagList.size() > 0) {
                        selectView.onCsClick();
                        mRequest.featureTagList = request.featureTagList;
                    } else {
                        mRequest.featureTagList = null;
                    }
                    if (request.supportTagList != null && request.supportTagList.size() > 0) {
                        selectView.onCsClick();
                        mRequest.supportTagList = request.supportTagList;
                    } else {
                        mRequest.supportTagList = null;
                    }

                    mRequest.pageNumber = 1;
                    mPresenter.getShopList(mRequest);
                }
            });
        }
        if (!selectionDialog.isShowing()) {
            if (sortPopupView != null && sortPopupView.isShowing()) {
                sortPopupView.dismiss();
            }
            if (quyuPopupView != null && quyuPopupView.isShowing()) {
                quyuPopupView.dismiss();
            }
            if (index == 1) {
                selectionDialog.setNoPrice();
            } else if (index == 2) {
                selectionDialog.setNoArea();
            }
            selectionDialog.show();
            selectView.onCsClick();
        }
    }

    public void onQuyuHandle(ShopRequset requset) {
        if (requset != null) {
            selectView.onLocationArrowDown();
            if (!TextUtils.isEmpty(requset.blockId)) {
                selectView.setQuYuText(requset.blockName);
                mRequest.blockId = requset.blockId;
                mRequest.districtId = requset.districtId;
                mRequest.metroId = null;
                mRequest.stationId = null;
            } else if (!TextUtils.isEmpty(requset.districtId)) {
                mRequest.districtId = requset.districtId;
                mRequest.blockId = null;
                mRequest.metroId = null;
                mRequest.stationId = null;
                selectView.setQuYuText(requset.districtName);
            } else if (!TextUtils.isEmpty(requset.stationId)) {
                selectView.setQuYuText(requset.stationName);
                mRequest.metroId = requset.metroId;
                mRequest.stationId = requset.stationId;
                mRequest.blockId = null;
                mRequest.districtId = null;
            } else if (!TextUtils.isEmpty(requset.metroId)) {
                selectView.setQuYuText(requset.metroName);
                mRequest.metroId = requset.metroId;
                mRequest.stationId = null;
                mRequest.blockId = null;
                mRequest.districtId = null;
            } else {
                selectView.onLocationUnClick();
                selectView.setQuYuText("位置");
                mRequest.districtId = null;
                mRequest.blockId = null;
                mRequest.metroId = null;
                mRequest.stationId = null;
            }

            mRequest.pageNumber = 1;
            mPresenter.getShopList(mRequest);
        }
    }

    @Override
    public void showShopList(ShopListResponse response) {
        if (response != null) {
            if (refreshView.isRefreshing()) {
                refreshView.refreshComplete();
            }
            mData.clear();
            if (response.getData().getData() == null || response.getData().getData().size() == 0) {
                mData.add(null);
                refreshView.setMode(PtrFrameLayout.Mode.REFRESH);
            } else {
                mData.addAll(response.getData().getData());
                refreshView.setMode(PtrFrameLayout.Mode.BOTH);
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void showMoreList(ShopListResponse response) {
        if (response != null) {
            refreshView.refreshComplete();
            mData.addAll(response.getData().getData());
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onError() {
        refreshView.refreshComplete();
    }
}
