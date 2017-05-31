package com.finance.winport.service.presenter;

import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.service.api.FindServices;
import com.finance.winport.service.model.CalendarListResponse;
import com.finance.winport.service.model.FindLoanCountResponse;
import com.finance.winport.service.model.FindServiceResponse;
import com.finance.winport.service.model.ShopOrderCountResponse;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

/**
 * Created by jge on 17/5/17.
 */

public class FindServiceHomePresenter {
    private IFindServiceHomeView mServiceView;

    public FindServiceHomePresenter(IFindServiceHomeView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getFindServiceHome() {

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getFindService(), new LoadingNetSubscriber<FindServiceResponse>() {
            @Override
            public void response(FindServiceResponse response) {
                if (mServiceView != null) {
                    mServiceView.showServiceHome(response);
                }
            }
        });

    }

    public void getCalendarHome() {

        HashMap<String,String> map = new HashMap<>();
        map.put("preDayCount","12");

        ToolsUtil.subscribe(ToolsUtil.createService(FindServices.class).getCalendar(map), new NetSubscriber<CalendarListResponse>() {
            @Override
            public void response(CalendarListResponse response) {
                if (mServiceView != null) {
                    mServiceView.showCalendar(response);
                }
            }
        });

    }


}
