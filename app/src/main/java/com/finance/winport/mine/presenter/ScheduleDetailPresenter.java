package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;
import com.sina.weibo.sdk.api.share.BaseResponse;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class ScheduleDetailPresenter {
    private IScheduleDetailView mServiceView;

    public ScheduleDetailPresenter(IScheduleDetailView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getScheduleDetail(int id) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("scheduleId",id);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getScheduleDetail(map), new NetSubscriber<ScheduleDetailResponse>() {
            @Override
            public void response(ScheduleDetailResponse response) {
                if (mServiceView != null) {
                    mServiceView.showScheduleDetail(response);
                }
            }
        });

    }


    public void ensureSchedule(int id) {

        HashMap<String,Object> map = new HashMap<>();
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


    public void revokeSchedule(int id) {

        HashMap<String,Object> map = new HashMap<>();
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

}
