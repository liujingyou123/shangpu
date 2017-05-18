package com.finance.winport.tab.net;

/**
 * Created by jge on 16/9/5.
 */
public interface NetworkCallback<T> {

    void success(T response);

    void failure(Throwable throwable);
}
