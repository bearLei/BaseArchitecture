package com.ubtech.jimuplus.database.remote.resbean;

import com.ubtech.jimuplus.base.data.Robot;

import java.io.Serializable;
import java.util.List;

public class RobotListBean implements Serializable {

    private List<Robot> models;

    public List<Robot> getModels() {
        return models;
    }

    public void setModels(List<Robot> models) {
        this.models = models;
    }


}
