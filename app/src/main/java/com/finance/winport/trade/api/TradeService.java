package com.finance.winport.trade.api;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.trade.model.TradeCircleResponse;

import java.util.HashMap;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by liuworkmac on 17/5/20.
 */

public interface TradeService {
    //生意圈
    @POST("customerapp/api/topic/topicList/v1.0.0")
    Observable<TradeCircleResponse> getTradeCircle(@Body HashMap<String ,String> params);

    //点赞
    @POST("customerapp/api/topic/likeTopic/v1.0.0")
    Observable<BaseResponse> zanTopic(@Body HashMap<String ,String> params);

    //取消点赞
    @POST("customerapp/api/topic/unlikeTopic/v1.0.0")
    Observable<BaseResponse> cancelzanTopic(@Body HashMap<String ,String> params);

    //我发布的帖子 //TODO 没有数据没有model
    @POST("customerapp/api/topic/topicListByUserId/v1.0.0")
    Observable<BaseResponse> getMyTopics(@Body HashMap<String ,String> params);
}
