package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class IndustryListResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : [{"industryName":"美食","industryId":1,"depict":null,"list":null},{"industryName":"休闲娱乐","industryId":2,"depict":null,"list":null},{"industryName":"购物","industryId":3,"depict":null,"list":null},{"industryName":"丽人","industryId":4,"depict":null,"list":null},{"industryName":"学习培训","industryId":5,"depict":null,"list":null},{"industryName":"运动健身","industryId":6,"depict":null,"list":null},{"industryName":"酒店","industryId":7,"depict":null,"list":null},{"industryName":"家装","industryId":8,"depict":null,"list":null},{"industryName":"爱车","industryId":9,"depict":null,"list":null},{"industryName":"医疗健康","industryId":10,"depict":null,"list":null},{"industryName":"宠物","industryId":11,"depict":null,"list":null},{"industryName":"生活服务","industryId":12,"depict":null,"list":null},{"industryName":"电影演出","industryId":13,"depict":null,"list":null}]
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
         * industryName : 美食
         * industryId : 1
         * depict : null
         * list : null
         */

        private String industryName;
        private int industryId;
        private Object depict;
        private Object list;

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public int getIndustryId() {
            return industryId;
        }

        public void setIndustryId(int industryId) {
            this.industryId = industryId;
        }

        public Object getDepict() {
            return depict;
        }

        public void setDepict(Object depict) {
            this.depict = depict;
        }

        public Object getList() {
            return list;
        }

        public void setList(Object list) {
            this.list = list;
        }
    }
}
