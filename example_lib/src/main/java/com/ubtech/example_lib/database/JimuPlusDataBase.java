package com.ubtech.example_lib.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


/**
 * Created by lei on 2020/5/25
 * desc:
 */

@Database(entities = {UserInfo.class}, version = 1, exportSchema = false)
public abstract class JimuPlusDataBase extends RoomDatabase {
    private static final String DB_NAME = "jimu_plus_db";
    private static volatile JimuPlusDataBase INSTANCE;


    public static JimuPlusDataBase getDataBase(final Context context) {
        if (null == INSTANCE) {
            synchronized (JimuPlusDataBase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), JimuPlusDataBase.class, DB_NAME).build();
                }
            }
        }
        return INSTANCE;
    }



    public abstract UserDao userDao();
}
