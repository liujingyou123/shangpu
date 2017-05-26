package com.finance.winport.net;

import android.text.TextUtils;
import android.util.Log;

import com.finance.winport.base.BaseResponse;
import com.finance.winport.base.WinPortApplication;
import com.finance.winport.log.XLog;
import com.finance.winport.util.TextViewUtil;
import com.finance.winport.util.ToastUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by liuworkmac on 17/1/18.
 */
public abstract class NetSubscriber<T> extends Subscriber<T> {

    @Override
    public void onCompleted() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }

        if (isTokenTimeOut(e)) {
//            RxBus.get().post(RxBusTags.TAG_TIME_OUT, "1");
            //登录超时弹框
//            LoadingDialogUtil.getInstance().showTimeOutDialog();
            return;
        }

        ToastUtil.show(WinPortApplication.getInstance().getApplicationContext(), e.getMessage());
        
    }

    @Override
    public void onNext(T response) {
        if (response == null) {
            onError(new Throwable("null response"));
            return;
        }
        if (response instanceof BaseResponse && !((BaseResponse) response).isSuccess()) {
            String errorMsg = ((BaseResponse) response).getErrMsg();
            if (TextUtils.isEmpty(errorMsg)) {
                errorMsg = "success is false";
            }
            onError(new Throwable(errorMsg));
            XLog.e(errorMsg);
            return;
        }

        response(response);
    }

    public abstract void response(T response);

    /**
     * token失效 判断
     *
     * @param e
     */
    private boolean isTokenTimeOut(Throwable e) {
        boolean ret = false;
        if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            if (httpException.response() != null && httpException.response().errorBody() != null) {
                try {
                    JSONObject jsonObject = new JSONObject(httpException.response().errorBody().string());
                    if (jsonObject.optInt("errCode") == -99) {
                        Log.e("NetSubscriber =  ", "- 99");
                        ret = true;
                    }
                } catch (JSONException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return ret;
    }
}
