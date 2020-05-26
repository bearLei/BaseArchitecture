package com.ubtech.base_lib.http.cache;


import com.ubtech.base_lib.http.annotate.UseCache;

/**
 * @Description :注解收集器
 * @Created Time : yinrongwu on 2019/9/4.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class AnnotationCollection {

    private long cacheLifeTime;//缓存生命周期
    private UseCache.Policy mPolicy; //缓存策略
    private boolean cleanAllHeaders;//清理所有预置的头部请求参数
    private String cleanHeaderName;//清除指定头部参数
    private boolean isUseCache;

    public boolean isUseCache() {
        return isUseCache;
    }

    public void setUseCache(boolean useCache) {
        isUseCache = useCache;
    }

    public String getCleanHeaderName() {
        return cleanHeaderName;
    }

    public void setCleanHeaderName(String cleanHeaderName) {
        this.cleanHeaderName = cleanHeaderName;
    }

    public boolean isCleanAllHeaders() {
        return cleanAllHeaders;
    }

    public void setCleanAllHeaders(boolean cleanAllHeaders) {
        this.cleanAllHeaders = cleanAllHeaders;
    }

    public UseCache.Policy getPolicy() {
        return mPolicy;
    }

    public void setPolicy(UseCache.Policy policy) {
        mPolicy = policy;
    }

    public long getCacheLifeTime() {
        return cacheLifeTime;
    }

    public void setCacheLifeTime(long cacheLifeTime) {
        this.cacheLifeTime = cacheLifeTime;
    }
}
