package com.ubtech.example_lib.viewmodel;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ubtech.base_lib.architecture.mvvm.BaseViewModel;
import com.ubtech.base_lib.utils.AppUtils;
import com.ubtech.base_lib.utils.ThreadExecutorUtil;
import com.ubtech.example_lib.database.UserInfo;
import com.ubtech.example_lib.database.UserRepository;

import java.util.List;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class UserViewModel extends BaseViewModel {
    private UserRepository mRepository;
    private LiveData<List<UserInfo>> mUserInfoList;

    public UserViewModel() {
        mRepository = new UserRepository(AppUtils.getContext());
        ThreadExecutorUtil.getInstance().doTask(() -> {
            mUserInfoList = mRepository.getAllUserInfo();
        });
    }

    public LiveData<List<UserInfo>> getAllUserInfo() {
        return mUserInfoList;
    }

    public void insert(UserInfo info) {
        mRepository.insert(info);
    }
}
