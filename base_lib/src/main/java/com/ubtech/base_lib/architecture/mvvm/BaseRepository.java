package com.ubtech.base_lib.architecture.mvvm;


import androidx.core.util.Consumer;

import com.ubtech.base_lib.utils.ThreadExecutorUtil;
import com.ubtrobot.log.ALog;

/**
 * Created by lei on 2020/5/25
 * desc:
 */
public class BaseRepository {

    /**
     * 数据库操作
     *
     * @param consumer
     */
    protected void launchDB(Consumer consumer) {
        ThreadExecutorUtil.getInstance().doTask(() -> {
            ALog.tag("lei").d("launchDB");
            try {
                consumer.accept(true);
            } catch (Exception e) {

            } finally {

            }
        });
    }

    /**
     * 网络操作
     *
     * @param consumer
     */
    protected void launchHttp(Consumer consumer) {
        ThreadExecutorUtil.getInstance().doTask(() -> {
            try {
                consumer.accept(null);
            } catch (Exception e) {

            } finally {

            }
        });
    }
}
