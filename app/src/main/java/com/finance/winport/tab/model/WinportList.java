package com.finance.winport.tab.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by xzw on 2017/5/12.
 * 我发布的旺铺
 */

public class WinportList extends BaseResponse {

    public WinportList.DataBeanX data;

    public static class DataBeanX {

        public int totalSize;
        public int pageSize;
        public int pageNumber;
        public int totalPage;
        public List<WinportList.DataBeanX.DataBean> data;

        public static class DataBean {
            public String id;
            public String coverImg;
            public String address;
            public float area;
            public String scanCount;
            public String visitCount;
            public String rentTypeName;
            public String publishTime;//发布时间
            public String undoTime;//撤下时间
            public int rentStatus;//出租状态 0-待出租 1-出租中 2-已出租  3-已下架（撤下）
            public String clerkPhone;//业务员电话
        }
    }
}
