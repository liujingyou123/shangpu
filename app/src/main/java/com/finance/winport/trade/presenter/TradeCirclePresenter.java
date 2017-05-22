package com.finance.winport.trade.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.view.ITradeCircleView;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/5/20.
 */

public class TradeCirclePresenter {
    private ITradeCircleView mITradeCircleView;

    public TradeCirclePresenter() {
    }

    public void setmITradeCircleView(ITradeCircleView iTradeCircleView) {
        this.mITradeCircleView = iTradeCircleView;
    }

    public void getTradeCircles(String type, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("pageSize", "10");
        hashMap.put("pageNumber", pageNumber + "");

        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getTradeCircle(hashMap), new NetSubscriber<TradeCircleResponse>() {
            @Override
            public void response(TradeCircleResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.showTradeCircle(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.onError();
                }
            }
        });
    }

    public void getMoreTradeCircles(String type, int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("type", type);
        hashMap.put("pageSize", "10");
        hashMap.put("pageNumber", pageNumber + "");

        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getTradeCircle(hashMap), new NetSubscriber<TradeCircleResponse>() {
            @Override
            public void response(TradeCircleResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.showMoreTradeCircle(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.onError();
                }
            }
        });
    }

    public void zanTopic(final String topicId, final int position) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        hashMap.put("likeType", "0");
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).zanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.zanTopic(true, position, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.zanTopic(false, position, topicId);
                }
            }
        });
    }

    /**
     * 取消点赞
     *
     * @param topicId
     * @param position
     */
    public void cancelzanTopic(final String topicId, final int position) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("likeId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).cancelzanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.cancelTopic(true, position, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.cancelTopic(false, position, topicId);
                }
            }
        });
    }

    /**
     * 我的帖子
     *
     * @param pageNumber
     */
    public void getMyTopics(int pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("pageNumber", pageNumber + "");
        hashMap.put("pageSize", 10 + "");
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getMyTopics(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.showTradeCircle(null);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.onError();
                }
            }
        });
    }

    /**
     * 删除帖子
     *
     * @param topicId
     */
    public void deleteTopic(final String topicId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).deleteTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeCircleView != null) {
                    mITradeCircleView.deleteTopic(true, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeCircleView != null) {
                    mITradeCircleView.deleteTopic(false, topicId);
                }
            }
        });
    }
}
