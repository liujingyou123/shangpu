package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.ServiceDetailResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class ServiceDetailPresenter {
    private IServiceDetailView mServiceView;

    public ServiceDetailPresenter(IServiceDetailView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getScheduleDetail(String id) {

        HashMap<String,String> map = new HashMap<>();
        map.put("scheduleId",id);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getServiceDetail(map), new NetSubscriber<ServiceDetailResponse>() {
            @Override
            public void response(ServiceDetailResponse response) {
                if (mServiceView != null) {
                    mServiceView.showScheduleDetail(response);
                }
            }
        });

    }


    public void ensureSchedule(String id) {

        HashMap<String,String> map = new HashMap<>();
        map.put("scheduleId",id);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).ensureSchedule(map), new NetSubscriber<com.finance.winport.base.BaseResponse>() {
            @Override
            public void response(com.finance.winport.base.BaseResponse response) {
                if (mServiceView != null) {
                    mServiceView.showensureSchedule(response);
                }
            }
        });

    }


    public void revokeSchedule(String id) {

        HashMap<String,String> map = new HashMap<>();
        map.put("scheduleId",id);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).revokeSchedule(map), new NetSubscriber<com.finance.winport.base.BaseResponse>() {
            @Override
            public void response(com.finance.winport.base.BaseResponse response) {
                if (mServiceView != null) {
                    mServiceView.showRevokeSchedule(response);
                }
            }
        });

    }

    public void clear(){
        mServiceView = null;
    }

}
