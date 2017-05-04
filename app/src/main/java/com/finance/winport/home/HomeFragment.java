package com.finance.winport.home;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
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
import com.finance.winport.util.LogUtil;
import com.finance.winport.view.home.HeaderView;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.finance.winport.view.refreshview.loadmore.OnScrollLisenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        selectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.setSelection(1);
                showShowQuYuDialog();
            }
        });

        selectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.setSelection(1);
                showPaiXuDailog();
            }
        });

        selectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lsShops.setSelection(1);
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
        }

        refreshView.setLoadMoreEnable(true);
        refreshView.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                LogUtil.e("dddddddddd");
            }
        });

        refreshView.setOnScrollListener(new OnScrollLisenter() {
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
        }, 200);
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
        }, 200);
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
        }, 200);
    }

    private void setSelectionViewVisible() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                selectionView.setVisibility(View.VISIBLE);
            }
        }, 100);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
