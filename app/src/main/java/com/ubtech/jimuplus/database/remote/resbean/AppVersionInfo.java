package com.ubtech.jimuplus.database.remote.resbean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lei on 2020/9/10
 * desc:
 */
public class AppVersionInfo implements Serializable {
    /**
     * androidPkgUrl": "string",
     *     "createTime": "2020-09-10T08:50:30.274Z",
     *     "description": "string",
     *     "downloadType": "string",
     *     "downloadTypeList": [
     *       "string"
     *     ],
     *     "id": 0,
     *     "isDeleted": 0,
     *     "isNew": 0,
     *     "noticeType": 0,
     *     "pkgName": "string",
     *     "publishStatus": 0,
     *     "publishTime": "2020-09-10T08:50:30.274Z",
     *     "updateTime": "2020-09-10T08:50:30.274Z",
     *     "updateType": 0,
     *     "updateVersion": "string",
     *     "versionName": "string",
     *     "versionNum": 0,
     *     "versionType": 0
     */
    private String androidPkgUrl;//安卓apk包url
    private String createTime;//创建时间
    private String description;//版本说明
    private String downloadType;//安卓下载地址：1:应用宝 2:小米 3:华为 4:vivo 5:oppo 6:apk包 ,
    private List<String> downloadTypeList;
    private int id;
    private int isNew;//是否最新：0非最新 1最新
    private int noticeType;//提醒强度: 1轻度 2中度 3强制更新
    private String pkgName;
    private String publishTime;
    private int publishStatus;
    private String updateTime;
    private int updateType;//更新类型 1：不强制更新 2：全部强制更新 3：以下版本强制更新
    private String updateVersion;
    private String versionName;//版本名称
    private int versionNum;//版本值
    private int versionType;//1：ios 2:安卓


    public AppVersionInfo() {
    }

    public String getAndroidPkgUrl() {
        return androidPkgUrl;
    }

    public void setAndroidPkgUrl(String androidPkgUrl) {
        this.androidPkgUrl = androidPkgUrl;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public List<String> getDownloadTypeList() {
        return downloadTypeList;
    }

    public void setDownloadTypeList(List<String> downloadTypeList) {
        this.downloadTypeList = downloadTypeList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIsNew() {
        return isNew;
    }

    public void setIsNew(int isNew) {
        this.isNew = isNew;
    }

    public int getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(int noticeType) {
        this.noticeType = noticeType;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public int getUpdateType() {
        return updateType;
    }

    public void setUpdateType(int updateType) {
        this.updateType = updateType;
    }

    public String getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(String updateVersion) {
        this.updateVersion = updateVersion;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionNum() {
        return versionNum;
    }

    public void setVersionNum(int versionNum) {
        this.versionNum = versionNum;
    }

    public int getVersionType() {
        return versionType;
    }

    public void setVersionType(int versionType) {
        this.versionType = versionType;
    }

    public int getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(int publishStatus) {
        this.publishStatus = publishStatus;
    }
}
