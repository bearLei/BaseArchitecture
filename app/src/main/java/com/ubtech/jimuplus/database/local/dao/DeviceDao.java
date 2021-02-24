package com.ubtech.jimuplus.database.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import io.reactivex.Maybe;

/**
 * Created by lei on 2020/7/1
 * desc:
 */
@Dao
public interface DeviceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Maybe<Long> insert(DeviceEntity entity);

    @Delete
    void delete(DeviceEntity entity);

    @Query("SELECT * FROM " + DeviceEntity.TABLE_DEVICE_INFO)
    LiveData<DeviceEntity> queryRobot();

    @Query("SELECT * FROM " + DeviceEntity.TABLE_DEVICE_INFO)
    DeviceEntity queryRobotValue();

    @Update()
    void update(DeviceEntity entity);
}
