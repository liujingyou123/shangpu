package com.finance.winport.tab.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.base.BaseFragment;
import com.finance.winport.dialog.LoadingDialog;
import com.finance.winport.service.fragment.SendShopRentFragment;
import com.finance.winport.tab.adapter.WinportAdapter;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.WinportList;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.tab.net.PersonManager;
import com.finance.winport.view.refreshview.PtrClassicFrameLayout;
import com.finance.winport.view.refreshview.PtrDefaultHandler2;
import com.finance.winport.view.refreshview.PtrFrameLayout;
import com.umeng.analytics.MobclickAgent;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xzw on 2017/5/12.
 * 我发布的 旺铺
 */

public class MineWinportFragment extends BaseFragment {
    private final int LIMIT = 10;
    private final int START_PAGE = 1;
    private int totalPage;
    private int pageSize = LIMIT;
    private int pageNumber = START_PAGE;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.empty)
    RelativeLayout empty;
    @BindView(R.id.imv_focus_house_back)
    ImageView imvFocusHouseBack;
    @BindView(R.id.tv_focus_house)
    TextView tvFocusHouse;
    @BindView(R.id.tip)
    TextView tip;
    @BindView(R.id.mListView)
    ListView mListView;
    @BindView(R.id.refresh_view)
    PtrClassicFrameLayout refreshView;

    String title;
    LoadingDialog loading;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = LayoutInflater.from(context).inflate(R.layout.fragment_winport, container, false);
        ButterKnife.bind(this, root);
        initView();
        return root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView() {
        tip.setVisibility(View.GONE);
        loading = new LoadingDialog(context);
        setTitle();
        initRefreshView();
    }

    private void setTitle() {
        tvFocusHouse.setText("我发布的旺铺");
    }

    private void setTip(String count, String rank) {
        String s1 = count;
        String s2 = rank;
        if (TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)) return;
        String s = getString(R.string.list_winport_tip, s1, s2);
        SpannableString sp = new SpannableString(s);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s1), s.indexOf(s1) + s1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sp.setSpan(new ForegroundColorSpan(Color.parseColor("#ff0000")), s.indexOf(s2), s.indexOf(s2) + s2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        tip.setText(sp);
        tip.setVisibility(View.VISIBLE);
    }

//    private void initListView() {
//        mListView.setAdapter(new WinportAdapter(refreshView, null, 0));
//    }

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
        queryScanCount();
        getWinportList();

    }

    @Override
    public void onResume() {
        super.onResume();
        loading.show();
        asyncData();
    }

    private void showEmptyView(boolean show) {
        empty.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    private void queryScanCount() {
        PersonManager.getInstance().queryScanCount(new HashMap<String, Object>(), new NetworkCallback<ScanCount>() {
            @Override
            public void success(ScanCount response) {
                if (response != null && response.isSuccess()) {
                    setTip(response.data.weekCount, response.data.rate);
                }
            }

            @Override
            public void failure(Throwable throwable) {

            }
        });
    }

    private void getWinportList() {
        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("pageSize", pageSize);
        params.put("pageNumber", pageNumber);
        PersonManager.getInstance().getWinportList(params, new NetworkCallback<WinportList>() {
            @Override
            public void success(WinportList response) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                loading.dismiss();
                if (response != null && response.isSuccess() && response.data.totalSize > 0) {
                    setWinportAdapter(response.data.data, response.data.totalSize);
                } else {
                    showEmptyView(true);
                }
            }

            @Override
            public void failure(Throwable throwable) {
                if (getView() == null) return;
                refreshView.refreshComplete();
                showEmptyView(true);
                loading.dismiss();
            }
        });
    }

    // 发布的旺铺
    private WinportAdapter winportAdapter;

    private void setWinportAdapter(List<WinportList.DataBeanX.DataBean> list, int totalCount) {
        if (winportAdapter == null) {
            winportAdapter = new WinportAdapter(refreshView, list, totalCount);
            mListView.setAdapter(winportAdapter);
        } else {
            if (pageNumber == 1) {
                winportAdapter.refreshData(list, totalCount);
                mListView.setSelection(0);
            } else {
                winportAdapter.updateData(list, totalCount);
            }
        }

    }


    @OnClick(R.id.imv_focus_house_back)
    public void onViewClicked() {
        handleBack();
    }

    @OnClick(R.id.confirm)
    public void onConfirmClicked() {
        MobclickAgent.onEvent(context, "myshop_let");
        pushFragment(new SendShopRentFragment(), false);
    }

    public void pushFragment(BaseFragment fragment, boolean addToBackStack) {
        String tag = fragment.getClass().getName();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.activity_open_enter, R.anim.activity_open_exit, R.anim.activity_close_enter, R.anim.activity_close_exit);
        ft.replace(R.id.rl_fragment_content, fragment, tag);
        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.commit();
    }
}
