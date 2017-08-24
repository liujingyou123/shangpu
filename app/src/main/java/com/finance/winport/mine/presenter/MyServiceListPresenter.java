package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.MyServiceListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class MyServiceListPresenter {
    private IMyServiceListView mIMyServiceListView;

    public MyServiceListPresenter(IMyServiceListView mIMyServiceListView) {
        this.mIMyServiceListView = mIMyServiceListView;
    }

    public void getServiceList(String type,int pageNum) {

        HashMap<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("pageSize",10);
        map.put("pageNumber",pageNum);
        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getServiceList(map), new NetSubscriber<MyServiceListResponse>() {
            @Override
            public void response(MyServiceListResponse response) {
                if (mIMyServiceListView != null) {
                    mIMyServiceListView.showServiceList(response);
                }
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                if (mIMyServiceListView != null) {
                    mIMyServiceListView.onError();
                }
            }
        });

    }

}
