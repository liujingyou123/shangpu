package com.finance.winport.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.QuyuPopupView;
import com.finance.winport.dialog.SelectionDialog;
import com.finance.winport.dialog.SortPopupView;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.presenter.HomePresenter;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.map.MapActivity;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements IHomeView {

    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    XPtrFrameLayout refreshView;
    Unbinder unbinder;
    @BindView(R.id.select_view)
    SelectView selectionView;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.map_list)
    ImageView mapList;

    private ShopsAdapter adapter;
    private List<ShopListResponse.DataBean.Shop> mData = new ArrayList<>();
    private HomePresenter mPresenter;

    private QuyuPopupView quyuPopupView;
    private SortPopupView sortPopupView;
    private SelectionDialog selectionDialog;
    private ShopRequset mRequest = new ShopRequset();
    private HeaderView headerView;
    private SelectView heardSelectView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initListView();
        getData();

        return root;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new HomePresenter(this);
        }
        mRequest.queryType = 1;
        mPresenter.getShopList(mRequest);
        mPresenter.getShopCount();
        mPresenter.getBanner();
    }

    private void initListView() {
        headerView = new HeaderView(this.getContext());
        heardSelectView = new SelectView(this.getContext());

        headerView.setNewShopsListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListPage(0);
            }
        });

        headerView.setNoMenoyListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListPage(1);

            }
        });

        headerView.setSmallShopListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListPage(2);

            }
        });

        headerView.setNearMetroListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToListPage(3);

            }
        });

        heardSelectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShowQuYuDialog(300);
            }
        });

        heardSelectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showPaiXuDailog(300);
            }
        });

        heardSelectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShaiXuandialog(300);
            }
        });

        selectionView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShowQuYuDialog(0);
            }
        });

        selectionView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaiXuDailog(0);
            }
        });

        selectionView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShaiXuandialog(0);
            }
        });


        lsShops.addHeaderView(headerView);
        lsShops.addHeaderView(heardSelectView);
        if (adapter == null) {
            adapter = new ShopsAdapter(this.getContext(), mData);
            lsShops.setAdapter(adapter);
            lsShops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ShopListResponse.DataBean.Shop shop = (ShopListResponse.DataBean.Shop) parent.getItemAtPosition(position);
                    if (shop != null) {
                        Intent intent = new Intent(HomeFragment.this.getContext(), ShopDetailActivity.class);
                        intent.putExtra("shopId", shop.getId()+"");
                        startActivity(intent);
                    }
                }
            });
        }

        refreshView.setSpaceView(headerView.getBannerView());
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


        lsShops.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView listView, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
//                XLog.e("firstVisibleItem = " + firstVisibleItem);
                if (firstVisibleItem >= 1) {
                    setSelectionViewVisible();
                } else {
                    selectionView.setVisibility(View.GONE);

                }
            }
        });


    }

    private void showShowQuYuDialog(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (quyuPopupView == null) {
                    quyuPopupView = new QuyuPopupView(HomeFragment.this.getContext());
                    quyuPopupView.setOnSelectionListener(new QuyuPopupView.OnSelectListener() {
                        @Override
                        public void onSelect(ShopRequset requst) {
                            onQuyuHandle(requst);
                        }
                    });
                    quyuPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
//                            selectionView.onLocationUnClick();
                        }
                    });
                }
                if (!quyuPopupView.isShowing()) {
                    if (sortPopupView != null && sortPopupView.isShowing()) {
                        sortPopupView.dismiss();
                    }
                    quyuPopupView.showAsDropDown(selectionView);
                    selectionView.onLocationClick();
                    heardSelectView.onLocationClick();
                }

            }
        }, time);
    }

    private void showPaiXuDailog(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sortPopupView == null) {
                    sortPopupView = new SortPopupView(HomeFragment.this.getContext());
                    sortPopupView.setOnSortSelectListener(new SortPopupView.OnSortSelectListener() {
                        @Override
                        public void onSortSelect(String sortType, String sortTypeName) {
                            if ("0".equals(sortType)) {
                                mRequest.sortType = null;

                                selectionView.setSortText("排序");
                                selectionView.onSortUnClick();

                                heardSelectView.setSortText("排序");
                                heardSelectView.onSortUnClick();
                            } else {
                                mRequest.sortType = sortType;
                                selectionView.setSortText(sortTypeName);
                                heardSelectView.setSortText(sortTypeName);
                            }
                            mRequest.pageNumber = 1;
                            mPresenter.getShopList(mRequest);
                        }
                    });
                    sortPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
//                            selectionView.onSortUnClick();
                        }
                    });
                }
                if (!sortPopupView.isShowing()) {
                    if (quyuPopupView != null && quyuPopupView.isShowing()) {
                        quyuPopupView.dismiss();
                    }
                    sortPopupView.showAsDropDown(selectionView);
                    selectionView.onSortClick();
                    heardSelectView.onSortClick();
                }

            }
        }, time);
    }

    private void showShaiXuandialog(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (selectionDialog == null) {
                    selectionDialog = new SelectionDialog(HomeFragment.this.getContext());
                    selectionDialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
                        @Override
                        public void onSelect(ShopRequset request) {
                            selectionView.onCsUnClick();
                            heardSelectView.onCsUnClick();
                            if (request.rentList != null && request.rentList.size() > 0) {
                                mRequest.rentList = request.rentList;
                            } else {
                                mRequest.rentList = null;
                            }
                            if (request.transferList != null && request.transferList.size() > 0) {
                                mRequest.transferList = request.transferList;
                            } else {
                                mRequest.transferList = null;
                            }
                            if (request.areaList != null && request.areaList.size() > 0) {
                                mRequest.areaList = request.areaList;
                            } else {
                                mRequest.areaList = null;
                            }
                            mRequest.width = request.width;
                            if (request.featureTagList != null && request.featureTagList.size() > 0) {
                                mRequest.featureTagList = request.featureTagList;
                            } else {
                                mRequest.featureTagList = null;
                            }
                            if (request.supportTagList != null && request.supportTagList.size() > 0) {
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
                    selectionDialog.show();
                    selectionView.onCsClick();
                    heardSelectView.onCsUnClick();
                }

            }
        }, time);
    }

    private void setSelectionViewVisible() {
        selectionView.setVisibility(View.VISIBLE);
    }

    private void goToListPage(int index) {
        Intent intent = new Intent(this.getContext(), ShopsListActivity.class);
        intent.putExtra("index", index);
        startActivity(intent);
    }

    @Override
    public boolean handleBack() {
        if (sortPopupView != null && sortPopupView.isShowing()) {
            sortPopupView.dismiss();
            return true;
        }

        if (quyuPopupView != null && quyuPopupView.isShowing()) {
            quyuPopupView.dismiss();
            return true;

        }

        return super.handleBack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.map_list)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MapActivity.class));
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
    public void showShopCount(ShopCount response) {
        if (headerView != null) {
            ShopCount.DataBean dataBean = response.getData();
            if (dataBean == null) {
                return;
            }
            headerView.setTodayShop(dataBean.getCountNewShop());
            headerView.setNoChange(dataBean.getCountNoTransferFee());
            headerView.setTvLimtArea(dataBean.getCountHundredArea());
            headerView.setTvNearStation(dataBean.getCountNearStation());
        }
    }

    @Override
    public void showBanners(BannerResponse response) {
        headerView.setUrls(response.getData());
    }


    @Override
    public void onError() {
        refreshView.refreshComplete();
    }

    public void onQuyuHandle(ShopRequset requset) {
        if (requset != null) {
            if (!TextUtils.isEmpty(requset.blockId)) {
                selectionView.setQuYuText(requset.blockName);
                heardSelectView.setQuYuText(requset.blockName);
                mRequest.blockId = requset.blockId;
                mRequest.districtId = requset.districtId;
                mRequest.metroId = null;
                mRequest.stationId = null;
            } else if (!TextUtils.isEmpty(requset.districtId)) {
                mRequest.districtId = requset.districtId;
                mRequest.blockId = null;
                mRequest.metroId = null;
                mRequest.stationId = null;
                selectionView.setQuYuText(requset.districtName);
                heardSelectView.setQuYuText(requset.districtName);
            } else if (!TextUtils.isEmpty(requset.stationId)) {
                selectionView.setQuYuText(requset.stationName);
                heardSelectView.setQuYuText(requset.stationName);
                mRequest.metroId = requset.metroId;
                mRequest.stationId = requset.stationId;
                mRequest.blockId = null;
                mRequest.districtId = null;
            } else if (!TextUtils.isEmpty(requset.metroId)) {
                selectionView.setQuYuText(requset.metroName);
                heardSelectView.setQuYuText(requset.metroName);
                mRequest.metroId = requset.metroId;
                mRequest.stationId = null;
                mRequest.blockId = null;
                mRequest.districtId = null;
            } else {
                selectionView.onLocationUnClick();
                selectionView.setQuYuText("区域");
                heardSelectView.onLocationUnClick();
                heardSelectView.setQuYuText("区域");
                mRequest.districtId = null;
                mRequest.blockId = null;
            }

            mRequest.pageNumber = 1;
            mPresenter.getShopList(mRequest);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
