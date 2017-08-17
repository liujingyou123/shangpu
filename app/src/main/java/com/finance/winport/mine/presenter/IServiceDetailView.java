package com.finance.winport.mine.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.mine.model.ServiceDetailResponse;

/**
 * Created by jge on 17/5/18.
 */

public interface IServiceDetailView {
    void showScheduleDetail(ServiceDetailResponse response);
    void showensureSchedule(BaseResponse response);
    void showRevokeSchedule(BaseResponse response);

}
