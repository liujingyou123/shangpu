package com.finance.winport.map.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapShopRequest  {



    private List<Integer> rentList;       //租金集合
    private List<Integer> transferList;   //转让费集合
    private List<Integer> areaList;       //面积集合
    private List<Integer> featureTagList; //特色标签集合
    private List<Integer> supportTagList; //配套标签集合
    private float width;                  //面宽数据
    private String blockId;                  //板块id
    private String minLon;                  //最小经度
    private String maxLon;                  //最大经度
    private String minLat;                  //最小纬度
    private String maxLat;                  //最大纬度

    public List<Integer> getRentList() {
        return rentList;
    }

    public void setRentList(List<Integer> rentList) {
        this.rentList = rentList;
    }

    public List<Integer> getTransferList() {
        return transferList;
    }

    public void setTransferList(List<Integer> transferList) {
        this.transferList = transferList;
    }

    public List<Integer> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Integer> areaList) {
        this.areaList = areaList;
    }

    public List<Integer> getFeatureTagList() {
        return featureTagList;
    }

    public void setFeatureTagList(List<Integer> featureTagList) {
        this.featureTagList = featureTagList;
    }

    public List<Integer> getSupportTagList() {
        return supportTagList;
    }

    public void setSupportTagList(List<Integer> supportTagList) {
        this.supportTagList = supportTagList;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
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




}
