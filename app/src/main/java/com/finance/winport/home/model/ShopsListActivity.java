package com.finance.winport.home.model;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.finance.winport.home.HomeFragment;
import com.finance.winport.home.ShopDetailActivity;
import com.finance.winport.home.adapter.ShopsAdapter;
import com.finance.winport.util.LogUtil;
import com.finance.winport.view.home.SelectView;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liuworkmac on 17/5/9.
 */

public class ShopsListActivity extends BaseActivity {

    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.select_view)
    SelectView selectView;
    @BindView(R.id.ls_shops)
    ListView lsShops;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;

    private ShopsAdapter adapter;
    private List<Shop> mData = new ArrayList<>();

    private QuyuPopupView quyuPopupView;
    private SortPopupView sortPopupView;
    private SelectionDialog selectionDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopslist);
        ButterKnife.bind(this);
        init();
        getIntentData();
    }

    private void init() {

        if (adapter == null) {
            adapter = new ShopsAdapter(this, mData);
            lsShops.setAdapter(adapter);
            lsShops.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(ShopsListActivity.this, ShopDetailActivity.class);
                    startActivity(intent);
                }
            });
        }

//        refreshView.setLoadMoreEnable(true);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
            }
        });

        selectView.setOnLocationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShowQuYuDialog();
            }
        });

        selectView.setOnSortClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPaiXuDailog();
            }
        });

        selectView.setOnCsClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShaiXuandialog();
            }
        });
    }

    private void getIntentData() {
        Intent intent = getIntent();
        if (intent != null) {
            int index = intent.getIntExtra("index", -1);
            if (index == 0) {
                tvFocusHouse.setText("今日新铺");
            } else if (index == 1) {
                tvFocusHouse.setText("无转让费");

            } else if (index == 2) {
                tvFocusHouse.setText("百平小铺");

            } else if (index == 3) {
                tvFocusHouse.setText("临近地铁");

            }
        }
    }

    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        finish();
    }

    private void showShowQuYuDialog() {
        if (quyuPopupView == null) {
            quyuPopupView = new QuyuPopupView(this);
            quyuPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    selectView.onNoneClick();
                }
            });
        }
        quyuPopupView.showAsDropDown(selectView);
        selectView.onLocationClick();
    }

    private void showPaiXuDailog() {
        if (sortPopupView == null) {
            sortPopupView = new SortPopupView(this);
            sortPopupView.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    selectView.onNoneClick();
                }
            });
        }
        sortPopupView.showAsDropDown(selectView);
        selectView.onSortClick();
    }

    private void showShaiXuandialog() {
        if (selectionDialog == null) {
            selectionDialog = new SelectionDialog(this);
            selectionDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    selectView.onNoneClick();
                }
            });
        }
        selectionDialog.show();
        selectView.onCsClick();
    }
}
