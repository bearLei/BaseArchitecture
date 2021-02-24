package com.ubtech.jimuplus.database.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.ubtech.jimuplus.database.local.entity.AudioEntity;

import java.util.List;

@Dao
public interface AudioDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AudioEntity entity);

    @Delete
    void delete(AudioEntity entity);

    @Delete
    void delete(List<AudioEntity> entities);

    @Update
    void update(AudioEntity entity);

    @Query("SELECT * FROM " + AudioEntity.TABLE_AUDIO_INFO + " WHERE name != '' ORDER BY isSystem DESC, id DESC")
    List<AudioEntity> queryAll();

    @Query("SELECT * FROM " + AudioEntity.TABLE_AUDIO_INFO + " WHERE isSystem = :isSystem ORDER BY id DESC")
    List<AudioEntity> queryAudioListByType(boolean isSystem);

    @Query("SELECT * FROM " + AudioEntity.TABLE_AUDIO_INFO + " WHERE id = :id")
    AudioEntity queryById(long id);

    @Query("SELECT * FROM " + AudioEntity.TABLE_AUDIO_INFO + " WHERE name = :name")
    List<AudioEntity> queryByName(String name);

}
