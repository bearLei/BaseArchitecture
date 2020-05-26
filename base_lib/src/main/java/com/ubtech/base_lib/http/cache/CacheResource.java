package com.ubtech.base_lib.http.cache;


import com.ubtech.base_lib.utils.SystemUtils;

/**
 * @Description :
 * @Created Time : yinrongwu on 2019/8/19.
 * @Author: YinRong.Wu@ubtrobot.com
 * @VerSion : Jimu_3.0.4
 */

public class CacheResource {
    private long createTime;
    private long liftCacheTime;
    private long lastTime;
    private String jsonData;
    private boolean isSurvial;

    public boolean isSurvial() {
        return isSurvial;
    }

    public void setSurvial(boolean survial) {
        isSurvial = survial;
    }

    public CacheResource() {
        this.createTime = SystemUtils.getSystemTime();
    }

    public long getCreateTime() {
        return createTime;
    }

    public long getLiftCacheTime() {
        return liftCacheTime;
    }

    public void setLiftCacheTime(long liftCacheTime) {
        this.liftCacheTime = liftCacheTime;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getJsonData() {
        return jsonData;
    }

    public void setJsonData(String jsonData) {
        this.jsonData = jsonData;
    }
}
