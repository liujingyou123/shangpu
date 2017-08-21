package com.finance.winport.trade.view;

import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.ReplyComment;
import com.finance.winport.trade.model.TradeDetailResponse;

/**
 * Created by liuworkmac on 17/5/22.
 */

public interface ITradeDetailView {
    void showTradeDetail(TradeDetailResponse response);

    void showCommentDialog(String parentId, String commentator);

    void zanTopic(boolean isSuccess, String topId);

    void cancelTopic(boolean isSuccess, String topId);

    void commentTopic(boolean isSuccess, ReplyComment reply);

    void deleteTopic(boolean isSuccess, String topId);

    void deleteComment(boolean isSuccess, String topId, String commentId);

    void showComments(CommentResponse response);

    void showCommentsMore(CommentResponse response);

    void showError(TradeDetailResponse response);
}
