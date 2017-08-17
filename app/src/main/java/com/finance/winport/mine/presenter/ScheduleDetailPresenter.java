package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ServiceDetailResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class ScheduleDetailPresenter {
    private IScheduleDetailView mServiceView;

    public ScheduleDetailPresenter(IScheduleDetailView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getScheduleDetail(String id) {

        HashMap<String,String> map = new HashMap<>();
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




    public void clear(){
        mServiceView = null;
    }

}
