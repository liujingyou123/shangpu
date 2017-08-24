package com.finance.winport.trade.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.trade.model.TradeSubList;
import com.finance.winport.trade.model.TradeTag;
import com.finance.winport.trade.view.ITradeSubDetailsView;
import com.finance.winport.trade.view.ITradeSubDetailsView;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 */

public class TradeSubDetailsPresenter {
    private ITradeSubDetailsView iView;


    public TradeSubDetailsPresenter() {
    }

    public TradeSubDetailsPresenter(ITradeSubDetailsView iView) {
        this.iView = iView;
    }

    public void setTradeSubView(ITradeSubDetailsView iView) {
        this.iView = iView;
    }

    public void getSubDetails(String contentId, final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("contentId", contentId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getSubDetails(hashMap), new LoadingNetSubscriber<TradeDetails>() {
            @Override
            public void response(TradeDetails response) {
                if (iView != null) {
                    iView.setDetails(response);
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

    public void praise(String contentId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("contentId", contentId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).praise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (iView != null) {
                    iView.praise(response.isSuccess());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.showError(e.getMessage());
                }
            }
        });
    }


    public void downPraise(String contentId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("contentId", contentId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).downPraise(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (iView != null) {
                    iView.downPraise(response.isSuccess());
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.showError(e.getMessage());
                }
            }
        });
    }


}
