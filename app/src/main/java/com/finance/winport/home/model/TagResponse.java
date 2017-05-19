package com.finance.winport.home.model;

import com.finance.winport.base.BaseResponse;

import java.util.List;

/**
 * Created by liuworkmac on 17/5/19.
 */

public class TagResponse extends BaseResponse{


    /**
     * id : 1
     * name : 上水
     */

    private List<Tag> data;

    public List<Tag> getData() {
        return data;
    }

    public void setData(List<Tag> data) {
        this.data = data;
    }
}
