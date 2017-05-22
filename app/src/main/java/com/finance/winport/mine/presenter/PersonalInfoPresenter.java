package com.finance.winport.mine.presenter;

import com.finance.winport.mine.api.MineServices;
import com.finance.winport.mine.model.IndustryListResponse;
import com.finance.winport.mine.model.PersonalInfoResponse;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.util.ToolsUtil;

/**
 * Created by jge on 17/5/17.
 */

public class PersonalInfoPresenter {
    private IPersonalInfoView mServiceView;

    public PersonalInfoPresenter(IPersonalInfoView mServiceView) {
        this.mServiceView = mServiceView;
    }

    public void getPersonalInfo() {

        ToolsUtil.subscribe(ToolsUtil.createService(MineServices.class).getPersonalInfo(), new NetSubscriber<PersonalInfoResponse>() {
            @Override
            public void response(PersonalInfoResponse response) {
                if (mServiceView != null) {
                    mServiceView.showPersonalInfo(response);
                }
            }
        });

    }

}
