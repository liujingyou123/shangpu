package com.finance.winport.home.model;

import com.finance.winport.util.FooAnnotation;

/**
 * Created by liuworkmac on 17/5/16.
 */

public class ShopRequset {
    public int queryType;
    public int pageSize = 10;
    public int pageNumber = 1;


    public String districtId;
    @FooAnnotation
    public String districtName;
    public String blockId;
    @FooAnnotation
    public String blockName;

    public String metroId;
    @FooAnnotation
    public String metroName;
    public String stationId;
    @FooAnnotation
    public String stationName;



}
