package com.finance.winport.mine.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class CommitFocusRequest implements Serializable {



    private List<Integer> areaList;       //面积集合
    private List<PersonalInfoResponse.DataBean.Attention.Plate> plateList;      //板块
    private List<PersonalInfoResponse.DataBean.Attention.Vocation> vocationList;      //行业

    public List<Integer> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Integer> areaList) {
        this.areaList = areaList;
    }

    public List<PersonalInfoResponse.DataBean.Attention.Plate> getPlateList() {
        return plateList;
    }

    public void setPlateList(List<PersonalInfoResponse.DataBean.Attention.Plate> plateList) {
        this.plateList = plateList;
    }

    public List<PersonalInfoResponse.DataBean.Attention.Vocation> getVocationList() {
        return vocationList;
    }

    public void setVocationList(List<PersonalInfoResponse.DataBean.Attention.Vocation> vocationList) {
        this.vocationList = vocationList;
    }

    public static class PlateBaen implements Serializable{
        private String cityId = "310000";                  //板块id
        private String cityName = "上海市";                  //板块id
        private String districtId;                  //板块id
        private String districtName;                  //板块id
        private String plateId;                  //板块id
        private String plateName;                  //板块id

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getDistrictId() {
            return districtId;
        }

        public void setDistrictId(String districtId) {
            this.districtId = districtId;
        }

        public String getDistrictName() {
            return districtName;
        }

        public void setDistrictName(String districtName) {
            this.districtName = districtName;
        }

        public String getPlateId() {
            return plateId;
        }

        public void setPlateId(String plateId) {
            this.plateId = plateId;
        }

        public String getPlateName() {
            return plateName;
        }

        public void setPlateName(String plateName) {
            this.plateName = plateName;
        }
    }

    public static class VocationBean implements Serializable{
        private String vocationId;                  //板块id
        private String vocationName;                  //板块id

        public String getVocationId() {
            return vocationId;
        }

        public void setVocationId(String vocationId) {
            this.vocationId = vocationId;
        }

        public String getVocationName() {
            return vocationName;
        }

        public void setVocationName(String vocationName) {
            this.vocationName = vocationName;
        }
    }


}
