package com.finance.winport.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.finance.winport.MainActivity;
import com.finance.winport.R;
import com.finance.winport.account.event.TokenTimeOutEvent;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.base.BaseResponse;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.dialog.QuyuPopupView;
import com.finance.winport.dialog.SelectionDialog;
import com.finance.winport.dialog.SortPopupView;
import com.finance.winport.dialog.UpdateTipDialog;
import com.finance.winport.dialog.WelcomeDialog;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.home.model.BannerResponse;
import com.finance.winport.home.model.CheckVersionResponse;
import com.finance.winport.home.model.EventLoginSuccess;
import com.finance.winport.home.model.ShopCount;
import com.finance.winport.home.model.ShopListResponse;
import com.finance.winport.home.model.ShopRequset;
import com.finance.winport.home.presenter.HomePresenter;
import com.finance.winport.home.view.IHomeView;
import com.finance.winport.log.XLog;
import com.finance.winport.map.MapActivity;
import com.finance.winport.map.MyLocation;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.util.Constant;
import com.finance.winport.util.SelectDialogUtil;
import com.finance.winport.util.SharedPrefsUtil;
import com.finance.winport.util.SpUtil;
import com.finance.winport.util.ToastUtil;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.XPtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment implements IHomeView, MyLocation.XLocationListener {

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
    private LoadingDialog loadingDialog;
    private boolean isFirstLoad;

    private boolean isTimeOut = false;
    private boolean isLoginIn = false;
    private UpdateTipDialog updateTipDialog;
    private WelcomeDialog welcomeDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        isFirstLoad = true;
        initListView();
        getData();

        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }

        XLog.e("onCreateView");

        return root;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new HomePresenter(this);
        }
        if (SharedPrefsUtil.getUserInfo() != null) {
            mRequest.queryType = 0;
            mPresenter.getIsUnReader();
        } else {
            mRequest.queryType = 1;
        }
        mPresenter.getShopCount();
        mPresenter.getBanner();

        try {
            String versionName = getActivity().getPackageManager().getPackageInfo(
                    getActivity().getPackageName(), 0).versionName;
            mPresenter.checkVersion(versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        getCurrentLocation();

        if (SharedPrefsUtil.getUserInfo() != null) {
            mPresenter.getPersonalInfo();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        XLog.e("onResume");

        if (SharedPrefsUtil.getUserInfo() != null) {
            mPresenter.getIsUnReader();
        }

        if (mRequest != null && mRequest.isMap) {
            mRequest.pageNumber = 1;
            mPresenter.getShopList(mRequest);
            mRequest.isMap = false;
        }

        if (!this.isHidden()) {
            getChangeTabData();
        }
    }

    @Subscribe
    public void onLoginTimeOut(TokenTimeOutEvent param) {
        isTimeOut = true;
//        mRequest.queryType = 1;
    }

    @Subscribe
    public void onLoginIn(EventLoginSuccess param) {
        if (param.isLoginIn) {
            isLoginIn = true;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        XLog.e("hidden = " + hidden);
        if (!hidden) {
            changeTab();
        }
    }

    private void getChangeTabData() {
        if (isLoginIn) {
            isLoginIn = false;

            if (SharedPrefsUtil.getUserInfo() != null && TextUtils.isEmpty(SpUtil.getInstance().getStringData(SharedPrefsUtil.getUserInfo().data.userPhone, null))) {
                mPresenter.getPersonalInfo();
            }

            if (SharedPrefsUtil.getUserInfo() != null) {
                mPresenter.getIsUnReader();
            }

            if (SharedPrefsUtil.getUserInfo() == null && mRequest.queryType == 0) {
                mRequest.queryType = 1;
                mPresenter.getShopList(mRequest);
            }

        } else if (isTimeOut) {
            isTimeOut = false;
            if (SharedPrefsUtil.getUserInfo() == null && mRequest.queryType == 0) {
                mRequest.queryType = 1;
                mPresenter.getShopList(mRequest);
            }

        }
    }

    private void changeTab() {

        getChangeTabData();
//        XLog.e(" mRequest.queryType = " + mRequest.queryType);
//        if (SharedPrefsUtil.getUserInfo() != null && mRequest.queryType == 1 && "1".equals(SpUtil.getInstance().getStringData("login", "0"))) {
////            mRequest.queryType = 0;
////            mPresenter.getShopList(mRequest);
//        } else if (SharedPrefsUtil.getUserInfo() == null && mRequest.queryType == 0) {
//            mRequest.queryType = 1;
//            mPresenter.getShopList(mRequest);
//        }
//
//        XLog.e(" mRequest.queryType2 = " + mRequest.queryType);
//
//        if (SharedPrefsUtil.getUserInfo() != null && TextUtils.isEmpty(SpUtil.getInstance().getStringData(SharedPrefsUtil.getUserInfo().data.userPhone, null))) {
//            mPresenter.getPersonalInfo();
//        }
//
//        if (SharedPrefsUtil.getUserInfo() != null) {
//            mPresenter.getIsUnReader();
//        }
    }

    private void getCurrentLocation() {
        MyLocation myLocation = new MyLocation(this.getContext());
        myLocation.start(this);

        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog(this.getContext());
            loadingDialog.setTip("加载中...");
            loadingDialog.show();
        }

    }

    private void initListView() {
        headerView = new HeaderView(this.getContext());
        heardSelectView = new SelectView(this.getContext());

        headerView.setNewShopsListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_new");
                goToListPage(0);
            }
        });

        headerView.setNoMenoyListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_nofee");
                goToListPage(1);

            }
        });

        headerView.setSmallShopListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_100m2");
                goToListPage(2);

            }
        });

        headerView.setNearMetroListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_aroundtraffic");
                goToListPage(3);

            }
        });

        heardSelectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_place");
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShowQuYuDialog(300);
            }
        });

        heardSelectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_sort");
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showPaiXuDailog(300);
            }
        });

        heardSelectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_select");
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShaiXuandialog(300);
            }
        });

        selectionView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_place");
                showShowQuYuDialog(0);
            }
        });

        selectionView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_sort");
                showPaiXuDailog(0);
            }
        });

        selectionView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MobclickAgent.onEvent(context, "shoplist_select");
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
                    MobclickAgent.onEvent(context, "shoplist_shop");
                    ShopListResponse.DataBean.Shop shop = (ShopListResponse.DataBean.Shop) parent.getItemAtPosition(position);
                    if (shop != null) {
                        Intent intent = new Intent(HomeFragment.this.getContext(), ShopDetailActivity.class);
                        intent.putExtra("shopId", shop.getId() + "");
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
//                if (SharedPrefsUtil.getUserInfo() != null ) {
////                    mRequest.queryType = 0;
//                } else  {
//                    mRequest.queryType = 1;
//                }
                XLog.e(" mRequest.queryType3 = " + mRequest.queryType);

                mRequest.pageNumber = 1;
                mPresenter.getShopList(mRequest);

                mPresenter.getShopCount();
                mPresenter.getBanner();
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
                            selectionView.onLocationArrowDown();
                            heardSelectView.onLocationArrowDown();
                            ShopRequset requset = quyuPopupView.getShopRequest();
                            if (requset == null || (TextUtils.isEmpty(requset.districtId) && TextUtils.isEmpty(requset.blockId)
                                    && TextUtils.isEmpty(requset.metroId) && TextUtils.isEmpty(requset.stationId))) {
                                selectionView.onLocationUnClick();
                                heardSelectView.onLocationUnClick();
                            }
//
                        }
                    });
                    quyuPopupView.setRequest(mRequest);
                }
                if (!quyuPopupView.isShowing()) {
                    if (sortPopupView != null && sortPopupView.isShowing()) {
                        sortPopupView.dismiss();
                    }
                    quyuPopupView.showAsDropDown(selectionView);
                    selectionView.onLocationClick();
                    heardSelectView.onLocationClick();
                    selectionView.onLocationArrowUp();
                    heardSelectView.onLocationArrowUp();
                } else {
                    quyuPopupView.dismiss();
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
                            selectionView.onSortArrowDown();
                            heardSelectView.onSortArrowDown();
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
                            selectionView.onSortArrowDown();
                            heardSelectView.onSortArrowDown();
                            if (TextUtils.isEmpty(mRequest.sortType)) {
                                selectionView.onSortUnClick();
                                heardSelectView.onSortUnClick();
                            }
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
                    selectionView.onSortArrowUp();
                    heardSelectView.onSortArrowUp();
                } else {
                    sortPopupView.dismiss();
                }

            }
        }, time);
    }

//    private void showSelectDialog() {
//        if (!SelectDialogUtil.getInstance().isShowing()) {
//
//            if (sortPopupView != null && sortPopupView.isShowing()) {
//                sortPopupView.dismiss();
//            }
//            if (quyuPopupView != null && quyuPopupView.isShowing()) {
//                quyuPopupView.dismiss();
//            }
//            SelectDialogUtil.getInstance().showDialogWithData(mRequest.areaList);
//            selectionView.onCsClick();
//            heardSelectView.onCsUnClick();
//        }
//    }

    private void showSelectDialog() {
        if (selectionDialog == null) {
            selectionDialog = new SelectionDialog(this.getContext());
            selectionDialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
                @Override
                public void onSelect(ShopRequset request) {

                    EventBus.getDefault().post(request);
//                    getSelectDilogData(request);
//                    selectionView.onCsUnClick();
//                    heardSelectView.onCsUnClick();
//                    if (request.rentList != null && request.rentList.size() > 0) {
//                        mRequest.rentList = request.rentList;
//                    } else {
//                        mRequest.rentList = null;
//                    }
//                    if (request.transferList != null && request.transferList.size() > 0) {
//                        mRequest.transferList = request.transferList;
//                    } else {
//                        mRequest.transferList = null;
//                    }
//                    if (request.areaList != null && request.areaList.size() > 0) {
//                        mRequest.areaList = request.areaList;
//                    } else {
//                        mRequest.areaList = null;
//                    }
//                    mRequest.width = request.width;
//                    if (request.featureTagList != null && request.featureTagList.size() > 0) {
//                        mRequest.featureTagList = request.featureTagList;
//                    } else {
//                        mRequest.featureTagList = null;
//                    }
//                    if (request.supportTagList != null && request.supportTagList.size() > 0) {
//                        mRequest.supportTagList = request.supportTagList;
//                    } else {
//                        mRequest.supportTagList = null;
//                    }
//
//                    mRequest.pageNumber = 1;
//                    mPresenter.getShopList(mRequest);
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
            selectionDialog.initAreaData(mRequest.areaList);
            selectionDialog.initRentData(mRequest.rentList);
            selectionDialog.initPriceData(mRequest.transferList);
            selectionDialog.initWidth(mRequest.width);
            selectionDialog.initFeatureData(mRequest.featureTagList);
            selectionDialog.initSupportData(mRequest.supportTagList);
            selectionDialog.show();
            selectionView.onCsClick();
            heardSelectView.onCsUnClick();
        }
    }

    @Subscribe
    public void getSelectDilogData(ShopRequset request) {
        XLog.e("getSelectDilogData");
        mRequest.queryType = 1;
        selectionView.onCsUnClick();
        heardSelectView.onCsUnClick();
        if (request.rentList != null && request.rentList.size() > 0) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
            mRequest.rentList = request.rentList;
        } else {
            mRequest.rentList = null;
        }
        if (request.transferList != null && request.transferList.size() > 0) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
            mRequest.transferList = request.transferList;
        } else {
            mRequest.transferList = null;
        }
        if (request.areaList != null && request.areaList.size() > 0) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
            mRequest.areaList = request.areaList;
        } else {
            mRequest.areaList = null;
        }
        if (!TextUtils.isEmpty(request.width)) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
        }
        mRequest.width = request.width;
        if (request.featureTagList != null && request.featureTagList.size() > 0) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
            mRequest.featureTagList = request.featureTagList;
        } else {
            mRequest.featureTagList = null;
        }
        if (request.supportTagList != null && request.supportTagList.size() > 0) {
            selectionView.onCsClick();
            heardSelectView.onCsClick();
            mRequest.supportTagList = request.supportTagList;
        } else {
            mRequest.supportTagList = null;
        }

        mRequest.isMap = request.isMap;

        if (!mRequest.isMap) {
            mRequest.pageNumber = 1;
            mPresenter.getShopList(mRequest);
        }
    }

    private void showShaiXuandialog(int time) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showSelectDialog();
//                if (selectionDialog == null) {
//                    selectionDialog = new SelectionDialog(HomeFragment.this.getContext());
//                    selectionDialog.setOnSelectListener(new SelectionDialog.OnSelectListener() {
//                        @Override
//                        public void onSelect(ShopRequset request) {
//                            selectionView.onCsUnClick();
//                            heardSelectView.onCsUnClick();
//                            if (request.rentList != null && request.rentList.size() > 0) {
//                                mRequest.rentList = request.rentList;
//                            } else {
//                                mRequest.rentList = null;
//                            }
//                            if (request.transferList != null && request.transferList.size() > 0) {
//                                mRequest.transferList = request.transferList;
//                            } else {
//                                mRequest.transferList = null;
//                            }
//                            if (request.areaList != null && request.areaList.size() > 0) {
//                                mRequest.areaList = request.areaList;
//                            } else {
//                                mRequest.areaList = null;
//                            }
//                            mRequest.width = request.width;
//                            if (request.featureTagList != null && request.featureTagList.size() > 0) {
//                                mRequest.featureTagList = request.featureTagList;
//                            } else {
//                                mRequest.featureTagList = null;
//                            }
//                            if (request.supportTagList != null && request.supportTagList.size() > 0) {
//                                mRequest.supportTagList = request.supportTagList;
//                            } else {
//                                mRequest.supportTagList = null;
//                            }
//
//                            mRequest.pageNumber = 1;
//                            mPresenter.getShopList(mRequest);
//                        }
//                    });
//                }
//                if (!selectionDialog.isShowing()) {
//                    if (sortPopupView != null && sortPopupView.isShowing()) {
//                        sortPopupView.dismiss();
//                    }
//                    if (quyuPopupView != null && quyuPopupView.isShowing()) {
//                        quyuPopupView.dismiss();
//                    }
//                    selectionDialog.show();
//                    selectionView.onCsClick();
//                    heardSelectView.onCsUnClick();
//                }

            }
        }, time);
    }

    private void setSelectionViewVisible() {
        selectionView.setVisibility(View.VISIBLE);
    }

    private void goToListPage(int index) {
        Intent intent = new Intent(this.getContext(), ShopsListActivity.class);
        if (mRequest != null && mRequest.latitude != null) {
            intent.putExtra("lat", mRequest.latitude);
        }

        if (mRequest != null && mRequest.longitude != null) {
            intent.putExtra("lon", mRequest.longitude);
        }
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

        return false;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.map_list)
    public void onViewClicked() {
        MobclickAgent.onEvent(context, "shoplist_more");
        Intent intent = new Intent(getActivity(), MapActivity.class);
        intent.putExtra("shopRequest", mRequest);
        startActivity(intent);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("shopRequest", mRequest);
//        ((MainActivity)getActivity()).handleMap(bundle);
//        getActivity().overridePendingTransition(R.anim.card_flip_right_in,R.anim.card_flip_right_out);
    }

    @Override
    public void showShopList(ShopListResponse response) {
        if (SharedPrefsUtil.getUserInfo() != null) {
            SpUtil.getInstance().setStringData("login", "0");
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
        if (response != null) {

            if (response.getData() != null && response.getData().getTotalSize() > 0) {
                ToastUtil.show(this.getContext(), "共找到" + response.getData().getTotalSize() + "间旺铺");
            }
            mData.clear();

            if (response.getData().getData() == null || response.getData().getData().size() == 0) {
                mData.add(null);
                if (refreshView != null) {
                    refreshView.setMode(PtrFrameLayout.Mode.REFRESH);
                }
            } else {
                mData.addAll(response.getData().getData());
                if (mData.size() < 10) {
                    if (refreshView != null) {
                        refreshView.setMode(PtrFrameLayout.Mode.REFRESH);
                    }
                } else {
                    if (refreshView != null) {
                        refreshView.setMode(PtrFrameLayout.Mode.BOTH);
                    }
                }
                if (mData.size() < 4) {
                    mData.add(null);
                    mData.add(null);
                    mData.add(null);
                    mData.add(null);
                }
            }
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }

            if (refreshView.isRefreshing()) {
                refreshView.refreshComplete();
            } else {
                if (!isFirstLoad) {
                    lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                }
            }

            isFirstLoad = false;

        }
    }

    @Override
    public void showMoreList(ShopListResponse response) {
        if (response != null) {
            if (refreshView != null) {
                refreshView.refreshComplete();
            }
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
    public void isUnReadMsg(UnReadMsg readMsg) {
        headerView.setNotice(readMsg.data);
    }


    @Override
    public void onError() {
        if (refreshView != null) {
            refreshView.refreshComplete();
        }
        if (loadingDialog != null) {
            loadingDialog.dismiss();
        }
    }

    @Override
    public void showPersonalInfo(PersonalInfoResponse response) {
        if ("1".equals(response.getData().getIsNew()) && (response.getData() == null || (TextUtils.isEmpty(response.getData().getIndustryName())
                && TextUtils.isEmpty(response.getData().getIndustryName())
                && (response.getData().getList() == null || response.getData().getList().size() == 0)
                && TextUtils.isEmpty(SpUtil.getInstance().getStringData(SharedPrefsUtil.getUserInfo().data.userPhone, null)))
        )) {


            welcomeDialog = new WelcomeDialog(this.getContext());
            if (updateTipDialog.isShowing()) {

            } else {

                welcomeDialog.show();

                SpUtil.getInstance().setStringData(SharedPrefsUtil.getUserInfo().data.userPhone, "1");
            }
        } else if (response.getData() != null && (TextUtils.isEmpty(response.getData().getIndustryName())
                || !TextUtils.isEmpty(response.getData().getIndustryName())
                || ((response.getData().getList() == null && response.getData().getList().size() == 0)))) {

            SpUtil.getInstance().setStringData(SharedPrefsUtil.getUserInfo().data.userPhone, "1");

            if (!TextUtils.isEmpty(response.getData().getDistrictId())) {
                mRequest.districtId = response.getData().getDistrictId() + "";
            }

            if (!TextUtils.isEmpty(response.getData().getDistrictName())) {
                mRequest.districtName = response.getData().getDistrictName();
            }

            if (!TextUtils.isEmpty(response.getData().getBlockId())) {
                mRequest.blockId = response.getData().getBlockId() + "";
            }

            if (!TextUtils.isEmpty(response.getData().getBlockName())) {
                mRequest.blockName = response.getData().getBlockName();
            }

            if (!TextUtils.isEmpty(mRequest.blockId) && !"null".equals(mRequest.blockId)) {
                selectionView.setQuYuText(mRequest.blockName);
                heardSelectView.setQuYuText(mRequest.blockName);
                selectionView.onLocationClick();
                heardSelectView.onLocationClick();
                selectionView.onLocationArrowDown();
                heardSelectView.onLocationArrowDown();
            } else if (!TextUtils.isEmpty(mRequest.districtId) && !"null".equals(mRequest.districtId)) {
                selectionView.setQuYuText(mRequest.districtName);
                heardSelectView.setQuYuText(mRequest.districtName);
                selectionView.onLocationClick();
                heardSelectView.onLocationClick();
                selectionView.onLocationArrowDown();
                heardSelectView.onLocationArrowDown();
            }
            if (response.getData().getList() != null && response.getData().getList().size() > 0) {
                List<String> arrayList = null;
                if (mRequest.areaList != null) {
                    arrayList = mRequest.areaList;
                } else {
                    arrayList = new ArrayList<>();
                }
                for (int i = 0; i < response.getData().getList().size(); i++) {
                    arrayList.add(response.getData().getList().get(i) + "");
                }

                mRequest.areaList = arrayList;

                selectionView.onCsClick();
                heardSelectView.onCsClick();
            }


        }
    }

    @Override
    public void checkVersion(final CheckVersionResponse response) {

        Constant.SERVICE_PHONE = response.getData().getServicePhone();
        if (response.getData().isNeedUpdate()) {

            Constant.NEEd_UPDATE = true;
            Constant.DOWNLOAD_URL = response.getData().getDownloadUrl();
            if (response.getData().getUpdateLevel() != 0) {

                String html = "<html><head><title>TextView使用HTML</title></head><body>"
                        + "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#00bbaa\">下面是网络图片"
                        + "</p></body></html>";
                updateTipDialog = new UpdateTipDialog(this.getContext());
                updateTipDialog.setMessage(response.getData().getDesc());
                updateTipDialog.setTitle(response.getData().getTitle());
                if (response.getData().getUpdateLevel() == 1) {
                    updateTipDialog.setClose(true);
                } else if (response.getData().getUpdateLevel() == 2) {
                    updateTipDialog.setClose(false);
                }
                updateTipDialog.setOkClickListener(new UpdateTipDialog.OnPreClickListner() {
                    @Override
                    public void onClick() {
                        Intent intent = new Intent();
                        intent.setAction("android.intent.action.VIEW");
                        Uri content_url = Uri.parse(response.getData().getDownloadUrl());
                        intent.setData(content_url);
                        startActivity(intent);

                    }

                    @Override
                    public void onClose() {
                        if (welcomeDialog != null) {

                            if (!welcomeDialog.isShowing()) {
                                welcomeDialog.show();

                                SpUtil.getInstance().setStringData(SharedPrefsUtil.getUserInfo().data.userPhone, "1");
                            }
                        }
                    }
                });
                updateTipDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    @Override
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            return true;
                        }
                        return false;
                    }
                });
                updateTipDialog.show();
            }
        }
    }

    public void onQuyuHandle(ShopRequset requset) {
        if (requset != null) {
            selectionView.onLocationArrowDown();
            heardSelectView.onLocationArrowDown();
            mRequest.queryType = 1;
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
                selectionView.setQuYuText("位置");
                heardSelectView.onLocationUnClick();
                heardSelectView.setQuYuText("位置");
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
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (mPresenter != null) {
            mPresenter.clear();
        }
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void result(boolean result, BDLocation location) {
        if (loadingDialog != null) {
            loadingDialog.setTip("加载中...");
        }
        if (result) {
            mRequest.latitude = location.getLatitude() + "";
            mRequest.longitude = location.getLongitude() + "";
        }

        mPresenter.getShopList(mRequest);

    }

//    @Override
//    public boolean handleDispatchKeyEvent(KeyEvent event) {
//        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
//
//            if(updateTipDialog.isShowing()){
//
//                return true;
//            }
//        }
//        return super.handleDispatchKeyEvent(event);
//    }
}
