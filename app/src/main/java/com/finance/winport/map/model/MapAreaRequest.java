package com.finance.winport.map.model;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapAreaRequest {



    private List<String> rentList;       //租金集合
    private List<String> transferList;   //转让费集合
    private List<String> areaList;       //面积集合
    private List<String> featureTagList; //特色标签集合
    private List<String> supportTagList; //配套标签集合
    private String width;                  //面宽数据
    private String districtId;                  //区域id
    private String minLon;                  //最小经度
    private String maxLon;                  //最大经度



    private String minLat;                  //最小纬度
    private String maxLat;                  //最大纬度
    private String type;                  //查询类型

    public List<String> getRentList() {
        return rentList;
    }

    public void setRentList(List<String> rentList) {
        this.rentList = rentList;
    }

    public List<String> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<String> transferList) {
        this.transferList = transferList;
    }

    public List<String> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<String> areaList) {
        this.areaList = areaList;
    }

    public List<String> getFeatureTagList() {
        return featureTagList;
    }

    public void setFeatureTagList(List<String> featureTagList) {
        this.featureTagList = featureTagList;
    }

    public List<String> getSupportTagList() {
        return supportTagList;
    }

    public void setSupportTagList(List<String> supportTagList) {
        this.supportTagList = supportTagList;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }


    public String getMinLon() {
        return minLon;
    }

    public void setMinLon(String minLon) {
        this.minLon = minLon;
    }

    public String getMaxLon() {
        return maxLon;
    }

    public void setMaxLon(String maxLon) {
        this.maxLon = maxLon;
    }

    public String getMinLat() {
        return minLat;
    }

    public void setMinLat(String minLat) {
        this.minLat = minLat;
    }

    public String getMaxLat() {
        return maxLat;
    }

    public void setMaxLat(String maxLat) {
        this.maxLat = maxLat;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }




}
