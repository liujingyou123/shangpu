package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jge on 17/8/15.
 */

public class HomeFoundShopResponse extends BaseResponse{


    /**
     * errMsg : null
     * errCode : 0
     * data : [{"contentId":30,"title":"dfsafa","image":"rrrr"},{"contentId":31,"title":"dfsafa","image":"rrrr"},{"contentId":32,"title":"kjkjk3","image":"http://wp-oss-file.oss-cn-shanghai.aliyuncs.com/image/wangpu_contentImg_img_154fa6ce-b43f-ec31-e92c-e9fbd1c7bb85.png"}]
     * exception : null
     */

    private List<DataBean> data;


    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * contentId : 30
         * title : dfsafa
         * image : rrrr
         */

        private int contentId;
        private String title;
        private String image;

        public int getContentId() {
            return contentId;
        }

        public void setContentId(int contentId) {
            this.contentId = contentId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
