package com.ubtech.jimuplus.database.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by lei on 2020/6/30
 * desc:
 */
@Dao
public interface PackageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(PackageEntity entity);

    @Delete
    void delete(PackageEntity entity);

    @Query("SELECT * FROM " + PackageEntity.TABLE_PACKAGE_INFO)
    LiveData<List<PackageEntity>> queryAll();

    @Update()
    void update(PackageEntity entity);

    @Update
    Completable update01(PackageEntity entity);
}
