package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by xzw on 2017/5/19.
 */

public class NotifyType extends BaseResponse {

    public DataBean data;

    public static class DataBean {
        public List<BaseNoticeDTOListBean> baseNoticeDTOList;

        public static class BaseNoticeDTOListBean {

            public String id;
            public String creatTimeFormat;
            public int status;
            public int notifyType;//通知类型：0-服务 1-系统 2-生意圈 3-商铺 4-工作
            public String digest;
        }
    }
}
