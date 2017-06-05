package com.finance.winport.map.presenter;

import android.text.TextUtils;

import com.finance.winport.map.api.MapServices;
import com.finance.winport.map.model.MapAreaRequest;
import com.finance.winport.map.model.MapAreaResponse;
import com.finance.winport.map.model.MapShopDetailResponse;
import com.finance.winport.map.model.MapShopRequest;
import com.finance.winport.map.model.MapShopResponse;
import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.presenter.IShopFocusView;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jge on 17/5/17.
 */

public class MapPresenter {
    private IMapView mServiceView;

    public MapPresenter(IMapView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getMapShop(final MapShopRequest request) {


        ToolsUtil.subscribe(ToolsUtil.createService(MapServices.class).getMapShop(request), new NetSubscriber<MapShopResponse>() {
            @Override
            public void response(MapShopResponse response) {
                if (mServiceView != null) {

                    if(TextUtils.isEmpty(request.getBlockId())){
                        mServiceView.showRemoveMapShop(response);
                    }
                    else{

                        mServiceView.showMapShop(response);
                    }
                }
            }
        });

    }

    public void getMapArea(final MapAreaRequest request) {


        ToolsUtil.subscribe(ToolsUtil.createService(MapServices.class).getMapArea(request), new NetSubscriber<MapAreaResponse>() {
            @Override
            public void response(MapAreaResponse response) {
                if (mServiceView != null) {
                    if(request.getType().equals("0")){

                        mServiceView.showMapArea(response);
                    }
                    else{
                        if (TextUtils.isEmpty(request.getDistrictId())) {
                            mServiceView.showRemoveMapPlate(response);

                        }
                        else{
                            mServiceView.showMapPlate(response);
                        }
                    }
                }
            }
        });

    }


    public void getMapShopDetail(String id,String latitude,String longitude) {



        HashMap<String,String> map = new HashMap<>();
        map.put("shopId",id);
        if(!TextUtils.isEmpty(latitude)){
            map.put("latitude",latitude);
        }
        if(!TextUtils.isEmpty(longitude)){
            map.put("longitude",longitude);
        }
        ToolsUtil.subscribe(ToolsUtil.createService(MapServices.class).getMapShopDetail(map), new NetSubscriber<MapShopDetailResponse>() {
            @Override
            public void response(MapShopDetailResponse response) {
                if (mServiceView != null) {


                        mServiceView.showMapShopDetail(response);
                }
            }
        });

    }

}
