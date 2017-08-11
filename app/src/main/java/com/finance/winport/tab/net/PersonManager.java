package com.finance.winport.tab.net;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.home.api.HomeServices;
import com.finance.winport.home.model.TagResponse;
import com.finance.winport.net.Ironman;
import com.finance.winport.net.NetSubscriber;
import com.finance.winport.tab.model.AppointRanking;
import com.finance.winport.tab.model.AppointShopList;
import com.finance.winport.tab.model.CollectionShopList;
import com.finance.winport.tab.model.Lunar;
import com.finance.winport.tab.model.NotifyList;
import com.finance.winport.tab.model.NotifyType;
import com.finance.winport.tab.model.Prediction;
import com.finance.winport.tab.model.ScanCount;
import com.finance.winport.tab.model.ScanShopList;
import com.finance.winport.tab.model.UnReadMsg;
import com.finance.winport.tab.model.WinportCounts;
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

    // 获取收藏列表
    public Subscription getCollectionList(HashMap<String, Object> params, final NetworkCallback<CollectionShopList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getCollectionList(params)
                .compose(ToolsUtil.<CollectionShopList>applayScheduers())
                .subscribe(new NetSubscriber<CollectionShopList>() {
                    @Override
                    public void response(CollectionShopList response) {
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

    // 获取最近浏览列表
    public Subscription getScanList(HashMap<String, Object> params, final NetworkCallback<ScanShopList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getScanList(params)
                .compose(ToolsUtil.<ScanShopList>applayScheduers())
                .subscribe(new NetSubscriber<ScanShopList>() {
                    @Override
                    public void response(ScanShopList response) {
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

    // 修改手机号
    public Subscription modifyUserPhone(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .modifyUserPhone(params)
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

    // 修改昵称
    public Subscription modifyNickName(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .modifyNickName(params)
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

    // 修改签名
    public Subscription modifySign(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .modifySign(params)
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

    // 最近浏览总数和排名统计接口
    public Subscription queryBrowserCount(HashMap<String, Object> params, final NetworkCallback<AppointRanking> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .queryBrowserCount(params)
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

    // 收藏总数和排名统计接口
    public Subscription queryCollectionCount(HashMap<String, Object> params, final NetworkCallback<AppointRanking> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .queryCollectionCount(params)
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

    // 取消收藏
    public Subscription cancelCollection(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .cancelCollection(params)
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

    // 删除浏览
    public Subscription deleteScan(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .deleteScan(params)
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

    // 我的未读消息
    public Subscription getUnReadMsg(HashMap<String, Object> params, final NetworkCallback<UnReadMsg> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getUnReadMsg(params)
                .compose(ToolsUtil.<UnReadMsg>applayScheduers())
                .subscribe(new NetSubscriber<UnReadMsg>() {
                    @Override
                    public void response(UnReadMsg response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }

    // 我的发布、约看、收藏、浏览、未来日程 总数统计接口
    public Subscription getWinportCounts(HashMap<String, Object> params, final NetworkCallback<WinportCounts> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getWinportCounts(params)
                .compose(ToolsUtil.<WinportCounts>applayScheduers())
                .subscribe(new NetSubscriber<WinportCounts>() {
                    @Override
                    public void response(WinportCounts response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }

    // 农历接口
    public Subscription getLunar(HashMap<String, Object> params, final NetworkCallback<Lunar> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getLunar(params)
                .compose(ToolsUtil.<Lunar>applayScheduers())
                .subscribe(new NetSubscriber<Lunar>() {
                    @Override
                    public void response(Lunar response) {
                        if (callback != null) {
                            callback.success(response);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }

    // 标签接口
    public Subscription getTagList(HashMap<String, Object> params, final NetworkCallback<TagResponse> callback) {
        return Ironman.getInstance()
                .createService(HomeServices.class)
                .getTagList(params)
                .compose(ToolsUtil.<TagResponse>applayScheduers())
                .subscribe(new NetSubscriber<TagResponse>() {
                    @Override
                    public void response(TagResponse response) {
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

    // 通知类型列表
    public Subscription getNotifyType(HashMap<String, Object> params, final NetworkCallback<NotifyType> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getNotifyType(params)
                .compose(ToolsUtil.<NotifyType>applayScheduers())
                .subscribe(new NetSubscriber<NotifyType>() {
                    @Override
                    public void response(NotifyType response) {
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

    // 通知列表
    public Subscription getNotifyList(HashMap<String, Object> params, final NetworkCallback<NotifyList> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .getNotifyList(params)
                .compose(ToolsUtil.<NotifyList>applayScheduers())
                .subscribe(new NetSubscriber<NotifyList>() {
                    @Override
                    public void response(NotifyList response) {
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

    // 更新 registrationId
    public Subscription updateRegistrationId(HashMap<String, Object> params, final NetworkCallback<BaseResponse> callback) {
        return Ironman.getInstance()
                .createService(PersonService.class)
                .updateRegistrationId(params)
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
                        if (callback != null) {
                            callback.failure(e);
                        }
                    }
                });
    }


}
