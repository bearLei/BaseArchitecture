package com.ubtech.jimuplus.database.remote.resbean;

import java.io.Serializable;

/**
 * Created by lei on 2020/8/24
 * desc:
 */
public class AppConfigBean implements Serializable {

    private ParInfo part;


    public AppConfigBean() {
    }

    public ParInfo getPartInfo() {
        return part;
    }

    public void setPartInfo(ParInfo partInfo) {
        this.part = partInfo;
    }
}
