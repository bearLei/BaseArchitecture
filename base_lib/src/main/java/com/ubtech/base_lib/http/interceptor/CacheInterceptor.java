package com.ubtech.base_lib.http.interceptor;

import android.text.TextUtils;

import com.ubtech.base_lib.http.annotate.UseCache;
import com.ubtech.base_lib.http.cache.AnnotationCollection;
import com.ubtech.base_lib.http.cache.CacheCallback;
import com.ubtech.base_lib.http.cache.CacheResource;
import com.ubtech.base_lib.http.cache.EngineKey;
import com.ubtech.base_lib.http.cache.MemoryCache;
import com.ubtrobot.log.ALog;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.ConcurrentHashMap;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/19.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class CacheInterceptor implements Interceptor {

    private static final Charset UTF8 = Charset.forName("UTF-8");
    private static final int LOCAL_CACHE_CODE = 203;
    //    private static CacheInterceptor cacheInterceptor;
    private ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap;
    private MemoryCache memoryCache;
    private CacheCallback cacheCallback;

    public CacheInterceptor(ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap, MemoryCache memoryCache) {
        this.serviceMethodMap = serviceMethodMap;
        this.memoryCache = memoryCache;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (serviceMethodMap == null || serviceMethodMap.size() <= 0) {
            return chain.proceed(request);
        }
        HttpUrl url = request.url();
        String urlStr = url.toString();
        String key = urlStr;
        String query = url.encodedQuery();
        if (!TextUtils.isEmpty(query)) {
            key = urlStr.replace(query, "").replace("?", "");
        }
        AnnotationCollection annotations = serviceMethodMap.get(key);
        if (annotations != null) {
            long cacheLifeTime = annotations.getCacheLifeTime();
            UseCache.Policy cachePolicy = annotations.getPolicy();
            if (cachePolicy == UseCache.Policy.ONLY_NETWORK) {
                return chain.proceed(request);
            }

            ALog.E("cacheLifeTime:" + cacheLifeTime);
            if (annotations.isUseCache()) {
                if (memoryCache == null) return chain.proceed(request);
                EngineKey engineKey = EngineKey.obtain(request);
                ALog.E("获取缓存Key:" + engineKey.hashCode() + " memoryCache:" + memoryCache.hashCode());
                CacheResource cacheResource = memoryCache.getCache(engineKey, cacheLifeTime);
                if (cacheResource != null) {
                    ALog.E("返回缓存数据--> " + url);

                    if (cacheCallback != null) {
                        cacheCallback.readCacheListener(cacheResource.getJsonData());
                    }
                    if (cachePolicy == UseCache.Policy.CACHE_NETWORK) {
                        engineKey.release();
                        return chain.proceed(request);
                    } else if (cachePolicy == UseCache.Policy.PRIORITY_CACHE) {
                        engineKey.release();
                        if (cacheResource.isSurvial()) {
                            return createCacheResponse(request, cacheResource.getJsonData());
                        } else {
                            return chain.proceed(request);
                        }
                    }
                }
                engineKey.release();
                return chain.proceed(request);
            }
            return chain.proceed(request);

        }
        ALog.E("不使用缓存 --> " + url);
        return chain.proceed(request);
    }


    private Response createCacheResponse(Request request, String cacheData) {
        return new Response.Builder()
                .code(LOCAL_CACHE_CODE)
                .body(ResponseBody.create(null, cacheData))
                .request(request)
                .message("cacheData")
                .protocol(Protocol.HTTP_1_0)
                .build();
    }


    public void setCacheCallback(CacheCallback cacheCallback) {
        this.cacheCallback = cacheCallback;
    }
}
