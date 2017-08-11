package com.finance.winport.trade.presenter;

import android.text.TextUtils;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.view.ITradeHomeView;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 */

public class TradeHomePresenter {
    private ITradeHomeView iView;


    public TradeHomePresenter() {
    }

    public TradeHomePresenter(ITradeHomeView iView) {
        this.iView = iView;
    }

    public void setTradeHomeView(ITradeHomeView iView) {
        this.iView = iView;
    }

    public void getTradeHome(final boolean withLoading) {
        HashMap<String, String> hashMap = new HashMap<>();
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getHomeTrade(hashMap), new LoadingNetSubscriber<TradeHome>() {
            @Override
            public void response(TradeHome response) {
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
    
    /**
     * 删除帖子
     *
     * @param topicId
     */
    public void deleteTopic(final String topicId, final int groupPosition, final int childPosition) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).deleteTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (iView != null) {
                    iView.deleteTopic(true, topicId, groupPosition, childPosition);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.deleteTopic(false, topicId, groupPosition, childPosition);
                }
            }
        });
    }

    public void zanTopic(final String topicId, final int groupPosition, final int childPosition) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).zanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (iView != null) {
                    iView.zanTopic(true, topicId, groupPosition, childPosition);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.zanTopic(false, topicId, groupPosition, childPosition);
                }
            }
        });
    }

    /**
     * 取消点赞
     *
     * @param topicId
     */
    public void cancelZanTopic(final String topicId, final int groupPosition, final int childPosition) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).cancelzanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (iView != null) {
                    iView.cancelZanTopic(true, topicId, groupPosition, childPosition);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (iView != null) {
                    iView.cancelZanTopic(false, topicId, groupPosition, childPosition);
                }
            }
        });
    }

}
