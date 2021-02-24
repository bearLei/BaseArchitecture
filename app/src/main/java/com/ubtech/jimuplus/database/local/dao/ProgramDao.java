package com.ubtech.jimuplus.database.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProgramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(ProgramEntity entity);

    @Delete
    void delete(ProgramEntity entity);

    @Delete
    void delete(List<ProgramEntity> entities);

    @Update
    void update(ProgramEntity entity);

    @Query("SELECT * FROM " + ProgramEntity.TABLE_PROGRAM_INFO + " ORDER BY xmlId DESC")
    List<ProgramEntity> queryAll();

    @Query("SELECT * FROM " + ProgramEntity.TABLE_PROGRAM_INFO + " WHERE modelName = :modelName ORDER BY xmlId DESC")
    List<ProgramEntity> queryByModelName(String modelName);

    @Query("SELECT * FROM " + ProgramEntity.TABLE_PROGRAM_INFO + " WHERE xmlId = :xmlId AND modelName = :modelName")
    ProgramEntity queryByXmlId(String xmlId, String modelName);

    @Query("SELECT * FROM " + ProgramEntity.TABLE_PROGRAM_INFO + " WHERE modelName = :modelName AND isOfficial = :isOfficial")
    List<ProgramEntity> queryOfficialByModelName(String modelName, boolean isOfficial);
}
