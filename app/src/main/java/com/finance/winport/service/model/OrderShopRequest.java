package com.finance.winport.service.model;

import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class OrderShopRequest {



    private String shopId;                  //商铺Id
    private String contactName;                  //联系人
    private String contactMobile;                  //联系电话
    private String smsVerifyCode;                  //短信验证码
    private String messageId;                  //短信验证码消息id
    private String picVerifyCode;                  //短图片验证码
    private String picVerifyId;                  //图片验证码id
    private String subscribeTime;                  //预约时间

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
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
