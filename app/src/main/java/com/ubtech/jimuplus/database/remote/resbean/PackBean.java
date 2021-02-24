package com.ubtech.jimuplus.database.remote.resbean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lei on 2020/9/16
 * desc:
 */
public class PackBean implements Serializable {

    private String packageName;
    private ArrayList<RobotEntity> robotEntities;
    private String createTime;
    private int imgResId;
    public PackBean(String packageName, ArrayList<RobotEntity> robotEntities) {
        this.packageName = packageName;
        this.robotEntities = robotEntities;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public ArrayList<RobotEntity> getRobotEntities() {
        return robotEntities;
    }

    public void setRobotEntities(ArrayList<RobotEntity> robotEntities) {
        this.robotEntities = robotEntities;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
