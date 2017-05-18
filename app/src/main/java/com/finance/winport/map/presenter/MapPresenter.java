package com.finance.winport.map.presenter;

import com.finance.winport.map.api.MapServices;
import com.finance.winport.map.model.MapAreaRequest;
import com.finance.winport.map.model.MapAreaResponse;
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

    public void getMapShop(MapShopRequest request) {


        ToolsUtil.subscribe(ToolsUtil.createService(MapServices.class).getMapShop(request), new NetSubscriber<MapShopResponse>() {
            @Override
            public void response(MapShopResponse response) {
                if (mServiceView != null) {
                    mServiceView.showMapShop(response);
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
                        mServiceView.showMapPlate(response);
                    }
                }
            }
        });

    }

}
