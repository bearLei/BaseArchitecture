package com.ubtech.base_lib.http.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/15.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UseCache {
    long cacheLife() default 0;//缓存生命周期默认为0，则不使用缓存
    Policy cachePolicy() default Policy.ONLY_NETWORK;//默认先读取缓存，再读取网络数据

    /**
     * 缓存策略
     */
    enum Policy {
        CACHE_NETWORK,//先缓存，后网络
        PRIORITY_CACHE,//优先缓存，如果缓存在有效期内，则只读取缓存数据不读取网络数据。无缓存或者缓存过期则读取网络数据
        ONLY_NETWORK // 只从网络数据
    }
}
