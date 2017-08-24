package com.finance.winport.mine;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.winport.R;
import com.finance.winport.home.FoundShopListActivity;
import com.finance.winport.home.adapter.FoundShopListAdapter;
import com.finance.winport.home.model.FoundShopListResponse;
import com.finance.winport.home.presenter.ShopListPresenter;
import com.finance.winport.log.XLog;
import com.finance.winport.mine.adapter.MyServiceListAdapter;
import com.finance.winport.mine.model.MyServiceListResponse;
import com.finance.winport.mine.presenter.IMyServiceListView;
import com.finance.winport.mine.presenter.MyServiceListPresenter;
import com.finance.winport.trade.TradeCircleDetailActivity;
import com.finance.winport.trade.adapter.TradeCircleAdapter;
import com.finance.winport.trade.model.CommentNumResponse;
import com.finance.winport.trade.model.EventBusCircleData;
import com.finance.winport.trade.model.EventBusCommentNum;
import com.finance.winport.trade.model.EventBustTag;
import com.finance.winport.trade.model.TradeTopic;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.presenter.TradeCirclePresenter;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.util.ToastUtil;
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
 * Created by jge on 17/8/10.
 * 服务订单
 */

public class MyServiceListFragment extends Fragment implements IMyServiceListView {

    Unbinder unbinder;
    @BindView(R.id.ls_circles)
    ListView lsCircles;
    @BindView(R.id.refresh_view)
    XPtrFrameLayout refreshView;

    private List<TradeTopic> mData = new ArrayList<>();
    private String type;
    private int pageNumber = 1;
    private MyServiceListPresenter mPresenter;
    private List<MyServiceListResponse.DataBeanX.DataBean> list = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_myservicelist, null);
        unbinder = ButterKnife.bind(this, view);
        init();


        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        pageNumber=1;
        getData();
        XLog.e("TradeCircleList onResume");
    }

    private void getData() {
        if (mPresenter == null) {
            mPresenter = new MyServiceListPresenter(this);
        }
        mPresenter.getServiceList(type,pageNumber);
    }

    private void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            type = bundle.getString("type");
        }


        refreshView.setMode(PtrFrameLayout.Mode.LOAD_MORE);
        refreshView.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                pageNumber++;
                getData();
//                mPresenter.getMoreTradeCircles(type, pageNumber);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                pageNumber = 1;
//                mPresenter.getTradeCircles(type, pageNumber);
            }
        });

        lsCircles.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(MyServiceListFragment.this.getContext(),MyServiceDetailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("type",list.get(position).getType());
                startActivity(intent);
            }
        });
    }



    private void setAdapter(List<MyServiceListResponse.DataBeanX.DataBean> response) {
        if(pageNumber==1){
            list.clear();
        }
        list.addAll(response);


        if (lsCircles.getAdapter() == null) {
            lsCircles.setAdapter(new MyServiceListAdapter(this.getContext(), list));
        } else {
            ((BaseAdapter) lsCircles.getAdapter()).notifyDataSetChanged();
        }
    }





    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }

    @Override
    public void showServiceList(MyServiceListResponse response) {

        refreshView.refreshComplete();
        if(list.size()>=response.getData().getTotalSize()){
            if(pageNumber>1){

                ToastUtil.show(getActivity(),"没有更多了");
            }
        }
        setAdapter(response.getData().getData());
    }

    @Override
    public void onError() {

        refreshView.refreshComplete();
    }
}
