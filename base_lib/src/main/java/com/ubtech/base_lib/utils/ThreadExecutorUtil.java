package com.ubtech.base_lib.utils;

import com.ubtrobot.log.ALog;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadExecutorUtil {
	
	private String TAG = "ThreadExecutorUtil";
	private ExecutorService executorService = null;

	private static final class Holder {
		private static ThreadExecutorUtil sInstance = new ThreadExecutorUtil();
	}

	public static ThreadExecutorUtil getInstance() {
		return Holder.sInstance;
	}

	private ThreadExecutorUtil() {
		if (this.executorService == null) {
			int max = Math.max(4, (int) (Runtime.getRuntime()
					.availableProcessors() * 1.2 + 1));
			this.executorService = new ThreadPoolExecutor(3, max, 10000,
					TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
		}
	}

	public void doTask(Runnable task){
		if(!executorService.isShutdown()){
			this.executorService.execute(task);
		}else{
			ALog.tag(TAG).e("ExecutorService was already shutdown!");
		}
	}

	public <T> Future<T> doTask(Callable<T> task){
		FutureTask<T> future = new FutureTask<T>(task);
		this.executorService.execute(future);
		return future;
	}

	public void shutdown() {
		this.executorService.shutdown();
	}

}
