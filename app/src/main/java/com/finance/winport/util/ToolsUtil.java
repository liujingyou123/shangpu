package com.finance.winport.util;

import android.util.Log;

import com.finance.winport.log.XLog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by liuworkmac on 17/1/3.
 */
public class ToolsUtil {

    public static String toJsonString(Object obj) {
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        XLog.json(json);
        return json;
    }

    public static String toJsonStringWithIgnore(Object object) {
        Gson gson = new GsonBuilder().setExclusionStrategies(new FooAnnotationExclusionStrategy()).create();
        String json = gson.toJson(object);
        XLog.json(json);
        return json;
    }


    public static <T> Observable.Transformer<T, T> applayScheduers() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> tObservable) {
                return tObservable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
