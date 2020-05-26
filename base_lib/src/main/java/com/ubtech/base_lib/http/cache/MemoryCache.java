package com.ubtech.base_lib.http.cache;

import android.content.Context;
import android.text.TextUtils;
import android.util.LruCache;

import com.google.gson.Gson;
import com.ubtech.base_lib.utils.SystemUtils;
import com.ubtrobot.log.ALog;


/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/19.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */


public class MemoryCache {
    LruCache<Integer, String> mLruCache;
    Gson gson;
    ProDiskLruCache proDiskLruCache;
    private Context mContext;

    public MemoryCache(Context context) {
        this.mContext = context;
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 8;
        mLruCache = new LruCache<>(cacheSize);
        gson = new Gson();
        proDiskLruCache = new ProDiskLruCache(mContext);

    }

    public CacheResource getCache(EngineKey key, long liftCycle) {
        ALog.E("getCache：" + mLruCache.size());
        //从内存中获取
        String jsonData = mLruCache.get(key.hashCode());
        //如果内存中没有,则从
        if (TextUtils.isEmpty(jsonData)) {
            CacheResource diskCacheResource = proDiskLruCache.getDiskCache(key.hashCode() + "", liftCycle);
            ALog.E("内存中没有缓存-->从本地SD卡中获取：" + diskCacheResource);
            if (diskCacheResource == null) return null;
            //内存中保存一份
            updateMemoryCache(key, diskCacheResource);
            long createTime = diskCacheResource.getCreateTime();
            long currentTime = SystemUtils.getSystemTime();
            if ((currentTime - createTime) < liftCycle) {
                ALog.E("从内存中获取缓存->");
                diskCacheResource.setSurvial(true);
                return diskCacheResource;
            } else {
                //将缓存数据设置为失效状态，并且返回
                diskCacheResource.setSurvial(false);
                ALog.E("缓存时间过期 --> " + (currentTime - createTime));
                return diskCacheResource;

            }

        } else {
            CacheResource cacheResource = gson.fromJson(jsonData, CacheResource.class);
            if (cacheResource != null) {
                long createTime = cacheResource.getCreateTime();
                long currentTime = SystemUtils.getSystemTime();
                if ((currentTime - createTime) < liftCycle) {
                    ALog.E("从内存中获取缓存->");
                    cacheResource.setSurvial(true);
                    return cacheResource;
                } else {
//                    mLruCache.remove(key.hashCode());
//                    proDiskLruCache.removeCache(key.hashCode() + "");
                    ALog.E("缓存时间过期 --> " + (currentTime - createTime));
                    cacheResource.setSurvial(false);
                    return cacheResource;
                }
            }
        }
        return null;
    }


    public void putCache(EngineKey engineKey, String json) {
        if (engineKey == null || TextUtils.isEmpty(json)) return;
        CacheResource cacheResource = new CacheResource();
        cacheResource.setJsonData(json);
        String cacheResourceStr = updateMemoryCache(engineKey, cacheResource);
        ALog.E("数据已经缓存:" + mLruCache.size());
        if (!TextUtils.isEmpty(cacheResourceStr)) {
            proDiskLruCache.updateDiskCache(engineKey, cacheResourceStr);
        }

    }


    public String updateMemoryCache(EngineKey engineKey, CacheResource cacheResource) {
        String cacheResourceStr = gson.toJson(cacheResource);
        mLruCache.put(engineKey.hashCode(), cacheResourceStr);
        return cacheResourceStr;
    }

    public void updataCacheResource(EngineKey key, CacheResource cacheResource) {
        if (cacheResource == null) return;
        String cacheResourceStr = gson.toJson(cacheResource);
        mLruCache.put(key.hashCode(), cacheResourceStr);
    }
}