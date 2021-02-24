package com.ubtech.jimuplus.http.client;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class HttpClient {

    private static final int TIME_OUT = 10;//超时时间

    private static HttpClient mHttpApi;
    private Retrofit retrofit = null;
    private RequestInterceptor requestInterceptor;
    private ResponseInterceptor responseInterceptor;

    private Context context;

    private HttpClient() {

    }

    public static HttpClient getHttpApi() {
        if (mHttpApi == null) {
            mHttpApi = new HttpClient();
        }
        return mHttpApi;
    }

    synchronized public void initHttpApi(Context context) {
        this.context = context;
        requestInterceptor = new RequestInterceptor();
        responseInterceptor = new ResponseInterceptor();
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient()
                    .newBuilder()
                    .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                    .dns(new JDns(TIME_OUT))
                    .retryOnConnectionFailure(true);//错误重连

            builder.addNetworkInterceptor(requestInterceptor);
            builder.addNetworkInterceptor(responseInterceptor);

            Gson gson = new GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ").serializeNulls().create();
            OkHttpClient client = builder.build();
            retrofit = new Retrofit.Builder()
                    .baseUrl("")
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(client)
                    .build();
        }
    }


    /**
     * 创建各种ApiService
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getService(Class<T> clazz) {
        return getHttpApi().retrofit.create(clazz);
    }


    public Context getContext() {
        return context;
    }
}
