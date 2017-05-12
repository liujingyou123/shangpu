package com.finance.winport.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
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
import com.finance.winport.home.model.Shop;
import com.finance.winport.home.model.ShopsListActivity;
import com.finance.winport.map.MapActivity;
import com.finance.winport.util.LogUtil;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * 首页
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;
    Unbinder unbinder;
    @BindView(R.id.select_view)
    SelectView selectionView;
    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;
    @BindView(R.id.map_list)
    ImageView mapList;

    private ShopsAdapter adapter;
    private List<Shop> mData = new ArrayList<>();

    private QuyuPopupView quyuPopupView;
    private SortPopupView sortPopupView;
    private SelectionDialog selectionDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        unbinder = ButterKnife.bind(this, root);
        initListView();
        return root;
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
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShowQuYuDialog();
            }
        });

        selectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showPaiXuDailog();
            }
        });

        selectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.smoothScrollToPositionFromTop(1, -1, 300);
                showShaiXuandialog();
            }
        });

        selectionView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShowQuYuDialog();
            }
        });

        selectionView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaiXuDailog();
            }
        });

        selectionView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShaiXuandialog();
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

        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                LogUtil.e("dddddddddd");
            }
        });


        lsShops.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView listView, int scrollState) {


            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                LogUtil.e("firstVisibleItem = " + firstVisibleItem);
                if (firstVisibleItem >= 1) {
                    LogUtil.e("setVisibility = " + "VISIBLE");
                    setSelectionViewVisible();
                } else {
                    LogUtil.e("setVisibility = " + "Gone");
                    selectionView.setVisibility(View.GONE);

                }
            }
        });
    }

    private void showShowQuYuDialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (quyuPopupView == null) {
                    quyuPopupView = new QuyuPopupView(HomeFragment.this.getContext());
                    quyuPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            selectionView.onNoneClick();
                        }
                    });
                }
                quyuPopupView.showAsDropDown(selectionView);
                selectionView.onLocationClick();
            }
        }, 300);
    }

    private void showPaiXuDailog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sortPopupView == null) {
                    sortPopupView = new SortPopupView(HomeFragment.this.getContext());
                    sortPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                        @Override
                        public void onDismiss() {
                            selectionView.onNoneClick();
                        }
                    });
                }
                sortPopupView.showAsDropDown(selectionView);
                selectionView.onSortClick();
            }
        }, 300);
    }

    private void showShaiXuandialog() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (selectionDialog == null) {
                    selectionDialog = new SelectionDialog(HomeFragment.this.getContext());
                    selectionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialog) {
                            selectionView.onNoneClick();
                        }
                    });
                }
                selectionDialog.show();
                selectionView.onCsClick();
            }
        }, 300);
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.map_list)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), MapActivity.class));
    }
}
