package com.finance.winport.tab.net;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.net.Ironman;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.AppointStatistics;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.WinportList;
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

    // 获取约看列表
    public Subscription getAppointList(HashMap<String, Object> params, final NetworkCallback<AppointShopList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getAppointList(params)
                .compose(ToolsUtil.<AppointShopList>applayScheduers())
                .subscribe(new NetSubscriber<AppointShopList>() {
                    @Override
                    public void response(AppointShopList response) {
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

    // 我发布的旺铺列表
    public Subscription getWinportList(HashMap<String, Object> params, final NetworkCallback<WinportList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getWinportList(params)
                .compose(ToolsUtil.<WinportList>applayScheduers())
                .subscribe(new NetSubscriber<WinportList>() {
                    @Override
                    public void response(WinportList response) {
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

    // 修改头像
    public Subscription updateHeadInfo(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .updateHeadInfo(params)
                .compose(ToolsUtil.<BaseResponse>applayScheduers())
                .subscribe(new NetSubscriber<BaseResponse>() {
                    @Override
                    public void response(BaseResponse response) {
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

    // 店名测吉凶
    public Subscription predictionShop(HashMap<String, Object> params, final NetworkCallback<Prediction> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .predictionShop(params)
                .compose(ToolsUtil.<Prediction>applayScheduers())
                .subscribe(new NetSubscriber<Prediction>() {
                    @Override
                    public void response(Prediction response) {
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

    // 下架商铺
    public Subscription offShelfSHop(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .offShelfSHop(params)
                .compose(ToolsUtil.<BaseResponse>applayScheduers())
                .subscribe(new NetSubscriber<BaseResponse>() {
                    @Override
                    public void response(BaseResponse response) {
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

    // 直拨房东电话记录接口
    public Subscription callRecord(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .callRecord(params)
                .compose(ToolsUtil.<BaseResponse>applayScheduers())
                .subscribe(new NetSubscriber<BaseResponse>() {
                    @Override
                    public void response(BaseResponse response) {
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

    // 旺铺被看总数和排名统计接口
    public Subscription queryScanCount(HashMap<String, Object> params, final NetworkCallback<ScanCount> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .queryScanCount(params)
                .compose(ToolsUtil.<ScanCount>applayScheduers())
                .subscribe(new NetSubscriber<ScanCount>() {
                    @Override
                    public void response(ScanCount response) {
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

    // 预约统计
    public Subscription getAppointStatistics(HashMap<String, Object> params, final NetworkCallback<AppointStatistics> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getAppointStatistics(params)
                .compose(ToolsUtil.<AppointStatistics>applayScheduers())
                .subscribe(new NetSubscriber<AppointStatistics>() {
                    @Override
                    public void response(AppointStatistics response) {
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

    // 约看次数和排名
    public Subscription getAppointRanking(HashMap<String, Object> params, final NetworkCallback<AppointRanking> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getAppointRanking(params)
                .compose(ToolsUtil.<AppointRanking>applayScheduers())
                .subscribe(new NetSubscriber<AppointRanking>() {
                    @Override
                    public void response(AppointRanking response) {
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

    // 删除约看
    public Subscription deleteAppoint(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .deleteAppoint(params)
                .compose(ToolsUtil.<BaseResponse>applayScheduers())
                .subscribe(new NetSubscriber<BaseResponse>() {
                    @Override
                    public void response(BaseResponse response) {
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
