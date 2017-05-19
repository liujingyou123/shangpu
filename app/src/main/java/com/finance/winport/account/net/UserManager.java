package com.finance.winport.account.net;

import com.finance.winport.account.model.ImageVerifyCode;
import com.finance.winport.account.model.Message;
import com.finance.winport.account.model.UserInfo;
import com.finance.winport.net.Ironman;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.net.NetworkCallback;
import com.finance.winport.util.ToolsUtil;

import java.util.HashMap;

import rx.Subscription;

/**
 * Created by xzw on 2017/5/17.
 */

public class UserManager {
    private static UserManager instance = new UserManager();

    private UserManager() {
    }

    public static UserManager getInstance() {
        return instance;
    }

    // 登录
    public Subscription login(HashMap<String, Object> params, final NetworkCallback<UserInfo> callback) {
        return Ironman.getInstance()
                .createService(UserService.class)
                .login(params)
                .compose(ToolsUtil.<UserInfo>applayScheduers())
                .subscribe(new NetSubscriber<UserInfo>() {
                    @Override
                    public void response(UserInfo response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }

    // 获取验证码
    public Subscription getVerifyCode(HashMap<String, Object> params, final NetworkCallback<Message> callback) {
        return Ironman.getInstance()
                .createService(UserService.class)
                .getVerifyCode(params)
                .compose(ToolsUtil.<Message>applayScheduers())
                .subscribe(new NetSubscriber<Message>() {
                    @Override
                    public void response(Message response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }

    // 获取图片验证码
    public Subscription getPicCode(HashMap<String, Object> params, final NetworkCallback<ImageVerifyCode> callback) {
        return Ironman.getInstance()
                .createService(UserService.class)
                .getPicCode(params)
                .compose(ToolsUtil.<ImageVerifyCode>applayScheduers())
                .subscribe(new NetSubscriber<ImageVerifyCode>() {
                    @Override
                    public void response(ImageVerifyCode response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }


}
