package com.finance.winport.account.model;

import com.finance.winport.base.BaseResponse;

/**
 * Created by xzw on 2017/5/18.
 */

public class ImageVerifyCode extends BaseResponse {
    public DataBean data;

    public static class DataBean {
        public String base64Str;//base64字符串
        public String picUrl;//图片url　
        public String picVerifyCode;//验证码　
        public String picVerifyId;//验证码id
    }
}
