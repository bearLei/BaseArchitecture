package com.ubtech.example_lib.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.ubtech.base_lib.architecture.mvvm.BaseRepository;
import com.ubtrobot.log.ALog;

import java.util.List;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class UserRepository extends BaseRepository {
    private UserDao mDao;
    private LiveData<List<UserInfo>> mAllUserInfo;


    public UserRepository(Application application) {
        JimuPlusDataBase db = JimuPlusDataBase.getDataBase(application);
        mDao = db.userDao();
        mAllUserInfo = mDao.queryAll();
    }

    /**
     * db handle
     */
    public LiveData<List<UserInfo>> getAllUserInfo() {
        return mAllUserInfo;
    }

    public void insert(UserInfo info) {
        launchDB(o -> {
            ALog.tag("lei").d("插入数据");
            mDao.insert01(info);
        });
    }


}
