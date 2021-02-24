package com.ubtech.jimuplus.database.repository;


import androidx.lifecycle.LiveData;

import com.ubtech.base_lib.architecture.mvvm.BaseRepository;

import com.ubtech.jimuplus.database.local.dao.RobotDao;

import java.util.List;



/**
 * Created by lei on 2020/7/1
 * desc:
 */
public class RobotRepository extends BaseRepository {

    private static RobotRepository mRepository;
    private RobotDao mDao;

    private RobotRepository(RobotDao mDao) {
        this.mDao = mDao;
    }

    public static RobotRepository getInstance(RobotDao dao) {
        if (null == mRepository) {
            synchronized (RobotRepository.class) {
                if (null == mRepository) {
                    mRepository = new RobotRepository(dao);
                }
            }
        }
        return mRepository;
    }




}
