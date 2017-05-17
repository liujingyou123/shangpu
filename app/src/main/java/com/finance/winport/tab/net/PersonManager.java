package com.finance.winport.tab.net;

import com.finance.winport.net.Ironman;
import com.finance.winport.net.LoadingNetSubscriber;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.net.api.PersonService;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by xzw on 2017/5/17.
 */

public class PersonManager {
    private static PersonManager instance = new PersonManager();

    private PersonManager() {
    }

    public static PersonManager getInstance() {
        return instance;
    }

    public Subscription getAppointList(HashMap<String, Object> params, final NetSubscriber<AppointShopList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getAppointList(params)
                .compose(ToolsUtil.<AppointShopList>applayScheduers())
                .subscribe(new LoadingNetSubscriber<AppointShopList>() {
                    @Override
                    public void response(AppointShopList response) {
                        if (callback != null) {
                            callback.response(response);
                        }
                    }
                });
    }


}
