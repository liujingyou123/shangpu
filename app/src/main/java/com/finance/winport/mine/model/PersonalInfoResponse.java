package com.finance.winport.mine.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class PersonalInfoResponse extends BaseResponse {


    /**
     * errMsg : null
     * errCode : 0
     * data : {"customerId":4,"phone":"168****883","headPortrait":"https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png","cityId":4,"cityName":"合肥","districtId":4,"districtName":"大牛区","blockId":4,"blockName":"www.sogou.com","industryId":4,"industryName":"休闲","list":[1,2]}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * customerId : 4
         * phone : 168****883
         * headPortrait : https://b-ssl.duitang.com/uploads/item/201607/23/20160723192350_RCwMK.png
         * cityId : 4
         * cityName : 合肥
         * districtId : 4
         * districtName : 大牛区
         * blockId : 4
         * blockName : www.sogou.com
         * industryId : 4
         * industryName : 休闲
         * list : [1,2]
         */

        private int customerId;
        private String phone;
        private String headPortrait;
        private int cityId;
        private String cityName;
        private int districtId;
        private String districtName;
        private int blockId;
        private String blockName;
        private int industryId;
        private String industryName;
        private List<Integer> list;

        public int getCustomerId() {
            return customerId;
        }

        public void setCustomerId(int customerId) {
            this.customerId = customerId;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getHeadPortrait() {
            return headPortrait;
        }

        public void setHeadPortrait(String headPortrait) {
            this.headPortrait = headPortrait;
        }

        public int getCityId() {
            return cityId;
        }

        public void setCityId(int cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public int getDistrictId() {
            return districtId;
        }

        public void setDistrictId(int districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public int getBlockId() {
            return blockId;
        }

        public void setBlockId(int blockId) {
            this.blockId = blockId;
        }

        public String getBlockName() {
            return blockName;
        }

        public void setBlockName(String blockName) {
            this.blockName = blockName;
        }

        public int getIndustryId() {
            return industryId;
        }

        public void setIndustryId(int industryId) {
            this.industryId = industryId;
        }

        public String getIndustryName() {
            return industryName;
        }

        public void setIndustryName(String industryName) {
            this.industryName = industryName;
        }

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }
    }
}
