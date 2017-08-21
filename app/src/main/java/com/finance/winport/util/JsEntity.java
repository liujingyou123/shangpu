package com.finance.winport.util;

import android.webkit.JavascriptInterface;

import java.util.List;


/**
 * Created by css_double on 16/10/25.
 */
public class JsEntity {
    private EndCallBack cb;
    public JsEntity(EndCallBack cb){
        this.cb = cb;
    }

    @JavascriptInterface
    public void print(String msg){
        System.out.println("js call print msg : " + msg);
//        LogPrinter.print("JsEntity", "js call print msg : " + msg);
    }

    @JavascriptInterface
    public void result(String result){
        if (cb != null){
            cb.onResult(result);
        }
    }

    public interface EndCallBack{
        void onResult(String result);
    }
}
