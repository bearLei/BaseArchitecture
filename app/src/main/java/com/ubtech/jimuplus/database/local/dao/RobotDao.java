package com.ubtech.jimuplus.database.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * Created by lei on 2020/7/1
 * desc:
 */
@Dao
public interface RobotDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RobotEntity entity);

    @Delete
    void delete(RobotEntity entity);

    @Query("SELECT * FROM " + RobotEntity.TABLE_ROBOT_INFO)
    LiveData<List<RobotEntity>> queryRobot();

    @Query("SELECT * FROM " + RobotEntity.TABLE_ROBOT_INFO)
    List<RobotEntity> queryRobotList();

    @Query("SELECT * FROM robot_info where robotId=:robotId")
    RobotEntity queryRobotById(long robotId);

    @Update()
    void update(RobotEntity entity);


}
