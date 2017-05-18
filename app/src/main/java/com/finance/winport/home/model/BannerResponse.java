package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/18.
 */

public class BannerResponse extends BaseResponse{


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

    public static class DataBean {
        private String adPicUrl;
        private String toUrl;
        private String type;

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
    }
}
