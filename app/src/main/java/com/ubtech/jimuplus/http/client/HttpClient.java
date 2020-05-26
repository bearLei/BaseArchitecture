package com.ubtech.jimuplus.http.client;


import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ubtech.base_lib.http.annotate.CleanAllHeaders;
import com.ubtech.base_lib.http.annotate.CleanHeader;
import com.ubtech.base_lib.http.annotate.UseCache;
import com.ubtech.base_lib.http.cache.AnnotationCollection;
import com.ubtech.base_lib.http.cache.MemoryCache;
import com.ubtech.base_lib.http.interceptor.CacheInterceptor;
import com.ubtech.base_lib.utils.AppUtils;
import com.ubtech.jimuplus.http.HttpCommonParams;
import com.ubtrobot.log.ALog;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/15.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class HttpClient {

    private static HttpClient mHttpClient;
    private Retrofit retrofit = null;
    private ConcurrentHashMap<String, AnnotationCollection> serviceMethodMap = new ConcurrentHashMap<>();
    private MemoryCache memoryCache;
    private CacheInterceptor cacheInterceptor;
    private RequestInterceptor requestInterceptor;
    private ResponseInterceptor responseInterceptor;

    private Context context;

    private HttpClient() {
        initHttpClient(AppUtils.getContext());
    }

    public static HttpClient getHttpClient() {
        if (null == mHttpClient ) {
            synchronized (HttpClient.class){
                if (null == mHttpClient){
                    mHttpClient = new HttpClient();
                }
            }
        }
        return mHttpClient;
    }

     private void initHttpClient(Context context) {
        this.context = context;
        memoryCache = new MemoryCache(context);
        cacheInterceptor = new CacheInterceptor(serviceMethodMap, memoryCache);
        requestInterceptor = new RequestInterceptor(context, serviceMethodMap);
        responseInterceptor = new ResponseInterceptor(serviceMethodMap, memoryCache);

        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient()
                    .newBuilder()
                    .readTimeout(HttpCommonParams.TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(HttpCommonParams.TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true);//错误重连

            builder.addInterceptor(cacheInterceptor);
            builder.addNetworkInterceptor(requestInterceptor);
            builder.addNetworkInterceptor(responseInterceptor);
            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
            OkHttpClient client = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl(HttpCommonParams.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
    }


    /**
     * 初始化日志拦截器
     *
     * @return
     */
    private HttpLoggingInterceptor createLogInterceptor() {
        HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(message -> {
//                ALog.e("Test", "日志：" + message);
        });
        logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return logInterceptor;
    }


    /**
     * 创建各种ApiService
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        getHttpClient().collectionAnnotation(clazz);
        return getHttpClient().retrofit.create(clazz);
    }

    /**
     * 收集注解
     */
    private void collectionAnnotation(Class clazz) {
        //只需要收集一次即可
        if (getHttpClient().serviceMethodMap == null) return;
        String name = clazz.getName();
        AnnotationCollection loadTag = getHttpClient().serviceMethodMap.get(name);
        if (loadTag != null) return;

        Method[] methodArray = clazz.getDeclaredMethods();
        if (methodArray != null && methodArray.length > 0) {
            for (Method method : methodArray) {
                AnnotationCollection annotationCollection = new AnnotationCollection();
                UseCache cacheAnnotation = method.getAnnotation(UseCache.class);
                if (cacheAnnotation != null) {
                    long time = cacheAnnotation.cacheLife();
                    UseCache.Policy policy = cacheAnnotation.cachePolicy();
                    annotationCollection.setCacheLifeTime(time);
                    annotationCollection.setPolicy(policy);
                    annotationCollection.setUseCache(true);
                }

                CleanAllHeaders cleanAllHeadersAnnotation = method.getAnnotation(CleanAllHeaders.class);
                if (cleanAllHeadersAnnotation != null) {
                    annotationCollection.setCleanAllHeaders(true);
                }

                CleanHeader cleanHeaderAnnotation = method.getAnnotation(CleanHeader.class);
                if (cleanHeaderAnnotation != null) {
                    String cleanHeaderName = cleanHeaderAnnotation.headerName();
                    annotationCollection.setCleanHeaderName(cleanHeaderName);
                }

                GET getAnnotation = method.getAnnotation(GET.class);
                if (getAnnotation != null) {
                    String value = getAnnotation.value();
                    ALog.E(method.getName() + " " + value);
                    serviceMethodMap.put(value, annotationCollection);
                    continue;
                }
                POST postAnnotation = method.getAnnotation(POST.class);
                if (postAnnotation != null) {
                    String value = postAnnotation.value();
                    ALog.E(method.getName() + " " + postAnnotation.value());
                    serviceMethodMap.put(value, annotationCollection);
                    continue;
                }

                PUT putAnnotation = method.getAnnotation(PUT.class);
                if (putAnnotation != null) {
                    String value = putAnnotation.value();
                    ALog.E(method.getName() + " " + putAnnotation.value());
                    serviceMethodMap.put(value, annotationCollection);
                    continue;
                }
            }
        }
        getHttpClient().serviceMethodMap.put(name, new AnnotationCollection());
    }

    public CacheInterceptor getCacheInterceptor() {
        return cacheInterceptor;
    }


    public Context getContext() {
        return context;
    }
}
