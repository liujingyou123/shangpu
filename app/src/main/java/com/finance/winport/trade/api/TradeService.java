package com.finance.winport.trade.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.trade.model.CommentNumResponse;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.MyTopicResponse;
import com.finance.winport.trade.model.PublicTopic;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeTag;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/5/20.
 */

public interface TradeService {
    //生意圈首页
    @POST("")
    Observable<TradeHome> getHomeTrade(@Body HashMap<String, String> params);

    //生意圈 tag
    @POST("")
    Observable<TradeTag> getTagList(@Body HashMap<String, String> params);

    //行业头条、生意宝典
    @POST("")
    Observable<TradeSub> getSubList(@Body HashMap<String, String> params);

    //生意圈
    @POST("customerapp/api/topic/topicList/v1.0.0")
    Observable<TradeCircleResponse> getTradeCircle(@Body HashMap<String, String> params);

    //点赞
    @POST("customerapp/api/topic/likeTopic/v1.0.0")
    Observable<BaseResponse> zanTopic(@Body HashMap<String, String> params);

    //取消点赞
    @POST("customerapp/api/topic/unLikeTopic/v1.0.0")
    Observable<BaseResponse> cancelzanTopic(@Body HashMap<String, String> params);

    //我发布的帖子
    @POST("customerapp/api/topic/topicListByUserId/v1.0.0")
    Observable<MyTopicResponse> getMyTopics(@Body HashMap<String, String> params);

    //发布帖子
    @POST("customerapp/api/topic/publishTopic/v1.0.0")
    Observable<BaseResponse> publishTopic(@Body PublicTopic params);

    //帖子详情
    @POST("customerapp/api/topic/topicDetail/v1.0.0")
    Observable<TradeDetailResponse> getTopicDetail(@Body HashMap params);

    //发布评论
    @POST("customerapp/api/topic/publishComment/v1.0.0")
    Observable<BaseResponse> commentTopic(@Body HashMap params);

    //删除评论
    @POST("customerapp/api/topic/deleteComment/v1.0.0")
    Observable<BaseResponse> deleteComment(@Body HashMap params);

    //删除帖子
    @POST("customerapp/api/topic/deleteTopic/v1.0.0")
    Observable<BaseResponse> deleteTopic(@Body HashMap params);

    //评论列表
    @POST("customerapp/api/topic/topicCommentList/v1.0.0")
    Observable<CommentResponse> getComments(@Body HashMap params);

    //帖子评论人数
    @POST("customerapp/api/notice/selectBusNoticeCount/v1.0.0")
    Observable<CommentNumResponse> getCommentsNum();
}
