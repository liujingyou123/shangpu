package com.finance.winport.trade.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.trade.api.TradeService;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.view.ITradeDetailView;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by liuworkmac on 17/5/22.
 */

public class TradeCircleDetailPresener {
    ITradeDetailView mITradeDetailView;

    public TradeCircleDetailPresener(ITradeDetailView mITradeDetailView) {
        this.mITradeDetailView = mITradeDetailView;
    }

    public void clearData() {
        mITradeDetailView = null;
    }

    public void getTradeCircleDetail(String topicId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getTopicDetail(hashMap), new LoadingNetSubscriber<TradeDetailResponse>() {
            @Override
            public void response(TradeDetailResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.showTradeDetail(response);
                }
            }

            @Override
            public void error(TradeDetailResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.showError(response);
                }
            }
        });
    }

    public void zanTopic(final String topicId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).zanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.zanTopic(true, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeDetailView != null) {
                    mITradeDetailView.zanTopic(false, topicId);
                }
            }
        });
    }

    public void cancelzanTopic(final String topicId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).cancelzanTopic(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.cancelTopic(true, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeDetailView != null) {
                    mITradeDetailView.cancelTopic(false, topicId);
                }
            }
        });
    }

    /**
     * 评论
     *
     * @param topicId
     * @param content
     */
    public void commentTopic(String topicId, String content) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        hashMap.put("content", content);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).commentTopic(hashMap), new LoadingNetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.commentTopic(true);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeDetailView != null) {
                    mITradeDetailView.commentTopic(false);
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
                if (mITradeDetailView != null) {
                    mITradeDetailView.deleteTopic(true, topicId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeDetailView != null) {
                    mITradeDetailView.deleteTopic(false, topicId);
                }
            }
        });
    }

    /**
     * 删除评论
     *
     * @param commentId
     */
    public void deleteComment(final String commentId, final String topicId) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("commentId", commentId);
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).deleteComment(hashMap), new NetSubscriber<BaseResponse>() {
            @Override
            public void response(BaseResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.deleteComment(true, topicId, commentId);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mITradeDetailView != null) {
                    mITradeDetailView.deleteComment(false, topicId, commentId);
                }
            }
        });
    }

    /**
     * 获取评论
     *
     * @param topicId
     */
    public void getComment(final String topicId, String pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        hashMap.put("pageNumber", pageNumber);
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getComments(hashMap), new NetSubscriber<CommentResponse>() {
            @Override
            public void response(CommentResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.showComments(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }

    /**
     * 获取评论更多
     *
     * @param topicId
     */
    public void getCommentMore(final String topicId, String pageNumber) {
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("topicId", topicId);
        hashMap.put("pageNumber", pageNumber);
        hashMap.put("pageSize", "10");
        ToolsUtil.subscribe(ToolsUtil.createService(TradeService.class).getComments(hashMap), new NetSubscriber<CommentResponse>() {
            @Override
            public void response(CommentResponse response) {
                if (mITradeDetailView != null) {
                    mITradeDetailView.showCommentsMore(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
            }
        });
    }
}
