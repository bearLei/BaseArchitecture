package com.ubtech.jimuplus.database.local.db;

import android.content.Context;

import com.ubtech.jimuplus.database.local.dao.AudioDao;
import com.ubtech.jimuplus.database.local.dao.DeviceDao;
import com.ubtech.jimuplus.database.local.dao.PackageDao;
import com.ubtech.jimuplus.database.local.dao.ProgramDao;
import com.ubtech.jimuplus.database.local.dao.RobotDao;
import com.ubtech.jimuplus.database.local.entity.AudioEntity;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;


/**
 * Created by lei on 2020/5/25
 * desc:
 */

@Database(entities = {PackageEntity.class, RobotEntity.class, DeviceEntity.class, ProgramEntity.class, AudioEntity.class}, version = 3, exportSchema = false)
public abstract class JimuPlusDataBase extends RoomDatabase {
    private static final String DB_NAME = "jimu_plus_db";
    private static volatile JimuPlusDataBase INSTANCE;


    public static JimuPlusDataBase getDataBase(final Context context) {
        if (null == INSTANCE) {
            synchronized (JimuPlusDataBase.class) {
                if (null == INSTANCE) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), JimuPlusDataBase.class, DB_NAME)
                            .enableMultiInstanceInvalidation()
                            .addMigrations(MIGRATION_2_3)
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            //创建表
            database.execSQL(
                    "CREATE TABLE audio_info (id INTEGER NOT NULL,"
                            + "name TEXT,"
                            + "path TEXT,"
                            + "absolutePath TEXT,"
                            + "isSystem INTEGER NOT NULL ,"
                            + "duration INTEGER NOT NULL,"
                            + "PRIMARY KEY(id))");
        }
    };

    public abstract PackageDao packDao();

    public abstract RobotDao robotDao();

    public abstract DeviceDao deviceDao();

    public abstract ProgramDao programDao();

    public abstract AudioDao audioDao();
}
