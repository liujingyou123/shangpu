package com.finance.winport.trade.presenter;

import android.text.TextUtils;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeSubList;
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

    public void getSubList(int pageNumber, int pageSize, String contentType, String tagId, final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", pageSize + "");
        hashMap.put("contentType", contentType);
        if (!TextUtils.isEmpty(tagId)) {
            hashMap.put("tagId", tagId);
        }
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getSubList(hashMap), new LoadingNetSubscriber<TradeSubList>() {
            @Override
            public void response(TradeSubList response) {
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

    public void getTagList(String contentType, final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("contentType", contentType);
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
