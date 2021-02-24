package com.ubtech.jimuplus.http;

/**
 * Created by lei on 2020/6/30
 * desc:
 */
public interface IHttpCallBack<T> {
    void success(T t);
    void fail(Throwable e);
}
