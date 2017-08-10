package com.finance.winport.trade.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeTag;
import com.finance.winport.trade.view.ITradeHomeView;
import com.finance.winport.trade.view.ITradeSubView;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 */

public class TradeSubPresenter {
    private ITradeSubView iView;


    public TradeSubPresenter() {
    }

    public TradeSubPresenter(ITradeSubView iView) {
        this.iView = iView;
    }

    public void setTradeSubView(ITradeSubView iView) {
        this.iView = iView;
    }

    public void getSubList(final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getSubList(hashMap), new LoadingNetSubscriber<TradeSub>() {
            @Override
            public void response(TradeSub response) {
                if (iView != null) {
                    iView.setAdapter(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.showError(e.getMessage());
                }
            }

            @Override
            public void onStart() {
                if (withLoading) {
                    super.onStart();
                }
            }
        });
    }

    public void getTagList(final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getTagList(hashMap), new LoadingNetSubscriber<TradeTag>() {
            @Override
            public void response(TradeTag response) {
                if (iView != null) {
                    iView.setHeadInfo(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.showError(e.getMessage());
                }
            }

            @Override
            public void onStart() {
                if (withLoading) {
                    super.onStart();
                }
            }
        });
    }

}
