package com.finance.winport.base;

/**
 * Created by liuworkmac on 16/9/5.
 * 接口返回积类
 */
public class BaseResponse {

    public String errMsg;  //结果状描述

    public boolean success;

    public String errCode;

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
