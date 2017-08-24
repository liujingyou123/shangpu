package com.finance.winport.mine.presenter;

import com.finance.winport.mine.model.MyServiceListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;

/**
 * Created by liuworkmac on 17/5/16.
 */

public interface IMyServiceListView {
    void showServiceList(MyServiceListResponse response);
    void onError();

}
