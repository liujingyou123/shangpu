package com.finance.winport.trade.view;

import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.TradeDetailResponse;

/**
 * Created by liuworkmac on 17/5/22.
 */

public interface ITradeDetailView {
    void showTradeDetail(TradeDetailResponse response);

    void zanTopic(boolean isSuccess, String topId);

    void cancelTopic(boolean isSuccess, String topId);

    void commentTopic(boolean isSuccess);

    void deleteTopic(boolean isSuccess, String topId);

    void deleteComment(boolean isSuccess, String topId, String commentId);

    void showComments(CommentResponse response);
    void showCommentsMore(CommentResponse response);

    void showError(TradeDetailResponse response);
}
