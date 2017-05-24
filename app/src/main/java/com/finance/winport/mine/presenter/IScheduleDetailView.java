package com.finance.winport.mine.presenter;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.mine.model.ScheduleDetailResponse;
import com.finance.winport.mine.model.ScheduleListResponse;

/**
 * Created by jge on 17/5/18.
 */

public interface IScheduleDetailView {
    void showScheduleDetail(ScheduleDetailResponse response);
    void showensureSchedule(BaseResponse response);
    void showRevokeSchedule(BaseResponse response);

}
