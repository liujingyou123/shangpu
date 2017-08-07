package com.finance.winport.trade.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/8/4.
 * 生意社区
 */

public class TradeCommunity extends BaseResponse {
    public String publisherId;
    public String topicId;
    public String title;
    public String dateTime;
    public String publishType;
    public String content;
    public String praiseNumber;
    public String commentNumber;
    public String kind;
    public String likeStatus;
    public String canBeDelete;
    public List<ImageList> imgList;

    public static class ImageList {
        public String topicId;
        public String imgUrl;
        public String imgIndex;
    }
}
