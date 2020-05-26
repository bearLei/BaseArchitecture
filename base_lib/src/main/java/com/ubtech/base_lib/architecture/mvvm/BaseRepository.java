package com.ubtech.base_lib.architecture.mvvm;


import androidx.core.util.Consumer;

import com.ubtech.base_lib.utils.ThreadExecutorUtil;
import com.ubtrobot.log.ALog;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class BaseRepository {

    protected void launchDB(Consumer consumer){
        ThreadExecutorUtil.getInstance().doTask(() -> {
            ALog.tag("lei").d("launchDB");
            consumer.accept(true);
        });
    }
}
