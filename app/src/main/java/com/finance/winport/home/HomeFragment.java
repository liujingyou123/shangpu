package com.finance.winport.home;

import android.content.DialogInterface;
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
public class HomeFragment extends BaseFragment implements IHomeView{

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        initListView();
        getData();

        return root;
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new HomePresenter(this);
        }
        mPresenter.getShopList(mRequest);
    }

    private void initListView() {
        final HeaderView headerView = new HeaderView(this.getContext());
        final SelectView selectView = new SelectView(this.getContext());

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

        selectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1,-1, 300);
                showShowQuYuDialog(300);
            }
        });

        selectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1,-1, 300);
                showPaiXuDailog(300);
            }
        });

        selectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1,-1, 300);
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
        lsShops.addHeaderView(selectView);
        if (adapter == null) {
            adapter = new ShopsAdapter(this.getContext(), mData);
            lsShops.setAdapter(adapter);
            lsShops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(HomeFragment.this.getContext(), ShopDetailActivity.class);
                    startActivity(intent);
                }
            });
        }

        refreshView.setSpaceView(headerView.getBannerView());
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mRequest.pageNumber ++;
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
                    sortPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            selectionView.onSortUnClick();
                        }
                    });
                }
                if (!sortPopupView.isShowing()) {
                    if (quyuPopupView != null && quyuPopupView.isShowing()) {
                        quyuPopupView.dismiss();
                    }
                    sortPopupView.showAsDropDown(selectionView);
                    selectionView.onSortClick();
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
                    selectionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            selectionView.onCsUnClick();
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
                mData.clear();
            }
            mData.addAll(response.getData().getData());
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

    @Subscribe
    public void onQuyuEvent(ShopRequset requset) {
        if (requset != null) {
            if (!TextUtils.isEmpty(requset.blockId)) {
                if ("-1".equals(requset.blockId)) {
                    selectionView.setQuYuText(requset.districtName);
                    mRequest.blockId = null;
                } else {
                    selectionView.setQuYuText(requset.blockName);
                    mRequest.blockId = requset.blockId;
                }
            }
            if (!TextUtils.isEmpty(requset.districtId)) {
                if ("-1".equals(requset.districtId)) {
                    selectionView.setQuYuText("区域");
                    mRequest.districtId = null;
                    mRequest.blockId = null;
                } else {
                    mRequest.districtId = requset.districtId;
                }
            }

            mRequest.pageNumber = 1;
            mPresenter.getShopList(mRequest);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
