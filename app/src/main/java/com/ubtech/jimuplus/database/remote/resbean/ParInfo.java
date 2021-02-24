package com.ubtech.jimuplus.database.remote.resbean;

import java.io.Serializable;

/**
 * Created by lei on 2020/8/24
 * desc:
 */
public class ParInfo implements Serializable {

    private int id;
    private int version;
    private String visibleMinVersion;//app最小可见版本
    private String androidUrl;
    private String androidMd5;
    private String androidName;
    private int force;//是否强制更新：0-不强制 1-强制
    private String description;
    private String createTime;
    private String iosUrl;

    public ParInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getVisibleMinVersion() {
        return visibleMinVersion;
    }

    public void setVisibleMinVersion(String visibleMinVersion) {
        this.visibleMinVersion = visibleMinVersion;
    }

    public String getAndroidUrl() {
        return androidUrl;
    }

    public void setAndroidUrl(String androidUrl) {
        this.androidUrl = androidUrl;
    }

    public String getAndroidMd5() {
        return androidMd5;
    }

    public void setAndroidMd5(String androidMd5) {
        this.androidMd5 = androidMd5;
    }

    public String getAndroidName() {
        return androidName;
    }

    public void setAndroidName(String androidName) {
        this.androidName = androidName;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getIosUrl() {
        return iosUrl;
    }

    public void setIosUrl(String iosUrl) {
        this.iosUrl = iosUrl;
    }
}
