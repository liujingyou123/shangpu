package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class BannerResponse extends BaseResponse implements Serializable{


    /**
     * adPicUrl : http://shfc-img.img-cn-shanghai.aliyuncs.com/ext/20170426/2017/04/26/IMG_1493195766747_7904.png
     * toUrl : www.baidu.com
     * type : 0
     */

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        private String adPicUrl;
        private String toUrl;
        private String type;
        private String title;
        private String innerType;
        private String businessId;

        public String getAdPicUrl() {
            return adPicUrl;
        }

        public void setAdPicUrl(String adPicUrl) {
            this.adPicUrl = adPicUrl;
        }

        public String getToUrl() {
            return toUrl;
        }

        public void setToUrl(String toUrl) {
            this.toUrl = toUrl;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getInnerType() {
            return innerType;
        }

        public void setInnerType(String innerType) {
            this.innerType = innerType;
        }

        public String getBusinessId() {
            return businessId;
        }

        public void setBusinessId(String businessId) {
            this.businessId = businessId;
        }
    }
}
