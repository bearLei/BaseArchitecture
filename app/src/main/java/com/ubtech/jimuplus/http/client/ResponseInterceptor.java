package com.ubtech.jimuplus.http.client;

import android.text.TextUtils;

import com.ubtech.base_lib.http.annotate.UseCache;
import com.ubtech.base_lib.http.cache.AnnotationCollection;
import com.ubtech.base_lib.http.cache.EngineKey;
import com.ubtech.base_lib.http.cache.MemoryCache;
import com.ubtrobot.log.ALog;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/15.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class ResponseInterceptor implements Interceptor {
    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final String CACHE_DATA = "cacheData";
    private static final int REQUEST_SUCCEED = 200;
    private final ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap;
    private final MemoryCache memoryCache;


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        ResponseBody responseBody = response.body();
        BufferedSource source = responseBody.source();
        source.request(Long.MAX_VALUE);
        Buffer buffer = source.buffer();
        String bodyStr = buffer.clone().readString(UTF8);
        //
        printLog(request, response, bodyStr);
        //
        HttpUrl url = request.url();
//        ALog.E("RequestInterceptor  " + url + "  servceMotchMap:" + serviceMethodMap);
        if (serviceMethodMap != null && serviceMethodMap.size() > 0) {
            String urlStr = url.toString();
            String key = urlStr;
            String query = url.encodedQuery();
            if (!TextUtils.isEmpty(query)) {
                key = urlStr.replace(query, "").replace("?", "");
            }
            AnnotationCollection annotations = serviceMethodMap.get(key);
            if (annotations != null) {

                long cacheLifeTime = annotations.getCacheLifeTime();
                if (cacheLifeTime > 0) {
//                    ALog.E("需要缓存:" + cacheLifeTime);
                    if (response.code() == REQUEST_SUCCEED
                            && annotations.getPolicy() != UseCache.Policy.ONLY_NETWORK
                            && annotations.getCacheLifeTime() > 0) {
                        EngineKey engineKey = EngineKey.obtain(request);
                        if (memoryCache != null) {
                            ALog.E("保存内存缓存Key" + engineKey.hashCode() + "  memoryCache:" + memoryCache.hashCode());
                            memoryCache.putCache(engineKey, bodyStr);
                            engineKey.release();
                        }
                    }
                }
            }
        }
        return response;

    }


    public ResponseInterceptor(ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap, MemoryCache memoryCache) {
        this.serviceMethodMap = serviceMethodMap;
        this.memoryCache = memoryCache;

    }



    private void printLog(Request request, Response response, String bodyStr) {
//        if (BuildConfig.DEBUG) {
            String header = request.headers().toString();
            RequestBody body = request.newBuilder().build().body();
            ALog.E("okhttp:" + "\n"
                    + "method --> " + request.method() + "  code --> " + response.code() + "\n"
                    + "url:" + request.url() + "\n"
                    + "{" + header.toString() + "}"
                    + "\n");
            if (body != null) {
                ALog.E("body:" + bodyToString(body));
            }
            String message = response.message();
            if (CACHE_DATA.equals(message)) {
                ALog.E("返回数据来源( 缓存数据 )-->" + bodyStr);
            } else {
                ALog.E("返回数据来源( 网络数据 )-->" + bodyStr);
            }

//        }
    }


    private String bodyToString(RequestBody body) {
        try {
            final RequestBody copy = body;
            final okio.Buffer buffer = new okio.Buffer();
            if (copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "did not work";
        }
    }

    private boolean bodyEncoded(Headers headers) {
        String contentEncoding = headers.get("Content-Encoding");
        return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
    }
}
