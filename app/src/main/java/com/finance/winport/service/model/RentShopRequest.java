package com.finance.winport.service.model;

/**
 * Created by jge on 17/5/17.
 */

public class RentShopRequest {



    private String latitude;                  //商铺Id
    private String longitude;                  //商铺Id
    private String districtName;                  //商铺Id
    private String blockName;                  //商铺Id
    private String districtId;                  //商铺Id
    private String blockId;                  //商铺Id
    private String shopAddress;                  //商铺Id
    private String linkmanName;                  //联系人
    private String linkmanPhone;                  //联系电话
    private String smsVerifyCode;                  //短信验证码
    private String messageId;                  //短信验证码消息id
    private String picVerifyCode;                  //短图片验证码
    private String picVerifyId;                  //图片验证码id
    private String subscribeTime;                  //预约时间

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getBlockName() {
        return blockName;
    }

    public void setBlockName(String blockName) {
        this.blockName = blockName;
    }

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getBlockId() {
        return blockId;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getLinkmanName() {
        return linkmanName;
    }

    public void setLinkmanName(String linkmanName) {
        this.linkmanName = linkmanName;
    }

    public String getLinkmanPhone() {
        return linkmanPhone;
    }

    public void setLinkmanPhone(String linkmanPhone) {
        this.linkmanPhone = linkmanPhone;
    }

    public String getSmsVerifyCode() {
        return smsVerifyCode;
    }

    public void setSmsVerifyCode(String smsVerifyCode) {
        this.smsVerifyCode = smsVerifyCode;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getPicVerifyCode() {
        return picVerifyCode;
    }

    public void setPicVerifyCode(String picVerifyCode) {
        this.picVerifyCode = picVerifyCode;
    }

    public String getPicVerifyId() {
        return picVerifyId;
    }

    public void setPicVerifyId(String picVerifyId) {
        this.picVerifyId = picVerifyId;
    }

    public String getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(String subscribeTime) {
        this.subscribeTime = subscribeTime;
    }
}
