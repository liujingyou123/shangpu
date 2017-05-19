package com.finance.winport.home.model;

import com.finance.winport.util.FooAnnotation;

/**
 * Created by liuworkmac on 17/5/16.
 */

public class ShopRequset {
    public int queryType; //筛选类型  0-用户关注条件（需登录且不包含经营范围）  1-非用户关注条件
    public int pageSize = 10;
    public int pageNumber = 1;


    public String districtId;  //区域id
    @FooAnnotation
    public String districtName;
    public String blockId; //板块id
    @FooAnnotation
    public String blockName;

    public String metroId; //地铁线路id
    @FooAnnotation
    public String metroName;
    public String stationId; //地铁站id
    @FooAnnotation
    public String stationName;

    public String sortType; //排序方式  1-最近更新  2-距离最近  3-联络最多 4-查看最多

}
