package com.ubtech.example_lib.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;

/**
 * Created by lei on 2020/5/25
 * desc: Room+RxJava 的组合 以 LiveData+Room的组合
 */
@Dao
public interface UserDao {
    /**
     * Insert插入操作，Room的Insert操作能返回三种类型的数据
     * 1.completable
     * 2.Single/Maybe
     * 3.Single<List>/Maybe<List>
     */
    @Insert
    void insert01(UserInfo userInfo);
//    @Insert
//    Maybe<Long> insert02(UserInfo userInfo);
//    @Insert
//    Maybe<List<Long>> insert03(List<UserInfo> userInfoList);
//    @Insert
//    Single<List<Long>> insert04(List<UserInfo> userInfoList);
//
//
//    @Insert
//    LiveData<List<UserInfo>> insert05(UserInfo userInfo);


    /**
     * update/delete操作
     * 1.completable--->update/delete完成时  马上调用onComplete
     * 2.Single/Maybe ---> onSuccess发射的值
     */

    @Update
    Completable update01(UserInfo info);

//    @Update
//    Single<Integer> update02(UserInfo info);
//
//    @Update
//    Single<Integer> updateAll(List<UserInfo> userInfoList);


//    @Delete
//    Single<Integer> deleteAll(List<UserInfo> userInfoList);
//
//    @Delete
//    Single<Integer> delete01(UserInfo userInfo);

    @Delete
    Completable delete02(UserInfo userInfo);

    /**
     * LiveData用法
     * @return
     */
    @Query("SELECT * FROM "+UserInfo.TABLE_USER_INFO)
    LiveData<List<UserInfo>> queryAll();

}
