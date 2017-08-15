package com.finance.winport.trade.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.trade.model.CommentNumResponse;
import com.finance.winport.trade.model.CommentResponse;
import com.finance.winport.trade.model.MyTopicResponse;
import com.finance.winport.trade.model.PublicTopic;
import com.finance.winport.trade.model.TradeCircleResponse;
import com.finance.winport.trade.model.TradeDetailResponse;
import com.finance.winport.trade.model.TradeDetails;
import com.finance.winport.trade.model.TradeHome;
import com.finance.winport.trade.model.TradeSub;
import com.finance.winport.trade.model.TradeSubList;
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
    @POST("customerapp/api/topic/getHomePageData")
    Observable<TradeHome> getHomeTrade(@Body HashMap<String, String> params);

    //生意圈 tag
    @POST("customerapp/api/topic/getTagsList")
    Observable<TradeTag> getTagList(@Body HashMap<String, String> params);

    //行业头条、生意宝典 列表
    @POST("customerapp/api/topic/getContentList")
    Observable<TradeSubList> getSubList(@Body HashMap<String, String> params);

    //行业头条、生意宝典 详情
    @POST("customerapp/api/topic/getContentDetail")
    Observable<TradeDetails> getSubDetails(@Body HashMap<String, String> params);

    //行业头条、生意宝典详情 点赞
    @POST("customerapp/api/topic/contentGood")
    Observable<BaseResponse> praise(@Body HashMap<String, String> params);

    //行业头条、生意宝典详情 踩
    @POST("customerapp/api/topic/contentBad")
    Observable<BaseResponse> downPraise(@Body HashMap<String, String> params);

    //生意圈
    @POST("customerapp/api/topic/topicList")
    Observable<TradeCircleResponse> getTradeCircle(@Body HashMap<String, String> params);

    //点赞
    @POST("customerapp/api/topic/likeTopic")
    Observable<BaseResponse> zanTopic(@Body HashMap<String, String> params);

    //取消点赞
    @POST("customerapp/api/topic/unLikeTopic")
    Observable<BaseResponse> cancelzanTopic(@Body HashMap<String, String> params);

    //我发布的帖子
    @POST("customerapp/api/topic/topicListByUserId")
    Observable<MyTopicResponse> getMyTopics(@Body HashMap<String, String> params);

    //发布帖子
    @POST("customerapp/api/topic/publishTopic")
    Observable<BaseResponse> publishTopic(@Body PublicTopic params);

    //帖子详情
    @POST("customerapp/api/topic/topicDetail")
    Observable<TradeDetailResponse> getTopicDetail(@Body HashMap params);

    //发布评论
    @POST("customerapp/api/topic/publishComment")
    Observable<BaseResponse> commentTopic(@Body HashMap params);

    //删除评论
    @POST("customerapp/api/topic/deleteComment")
    Observable<BaseResponse> deleteComment(@Body HashMap params);

    //删除帖子
    @POST("customerapp/api/topic/deleteTopic")
    Observable<BaseResponse> deleteTopic(@Body HashMap params);

    //评论列表
    @POST("customerapp/api/topic/topicCommentList")
    Observable<CommentResponse> getComments(@Body HashMap params);

    //帖子评论人数
    @POST("customerapp/api/notice/selectBusNoticeCount")
    Observable<CommentNumResponse> getCommentsNum();
}
