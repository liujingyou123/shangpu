package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.mine.model.ScheduleListResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class ScheduleListPresenter {
    private IScheduleListView mServiceView;

    public ScheduleListPresenter(IScheduleListView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getScheduleList(int pageNum,int type) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("pageSize",20);
        map.put("pageNumber",pageNum);
        map.put("type",type);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getScheduleList(map), new NetSubscriber<ScheduleListResponse>() {
            @Override
            public void response(ScheduleListResponse response) {
                if (mServiceView != null) {
                    mServiceView.showScheduleList(response);
                }
            }
        });

    }

    public void clear(){
        mServiceView = null;
    }

}
