package com.next.framework.tool;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ClassName:线程工具类
 *
 * @author Afton
 * @time 2023/8/30
 * @auditor
 */
public class ThreadTool {

    //CPU计数
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();

    //线程池数
    private static final int CORE_POOL_SIZE = Math.max(CPU_COUNT, 5);

    //线程保活时间
    private static final int THREAD_KEEP_LIVE_TIME = 30;

    //任务队列最大计数
    private static final int TASK_QUEUE_MAX_COUNT = 128;

    //线程池执行器对象
    private static ThreadPoolExecutor mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE,
            CORE_POOL_SIZE,
            THREAD_KEEP_LIVE_TIME,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(TASK_QUEUE_MAX_COUNT));

    //主线程处理对象
    public static Handler mMainHandler = new Handler(Looper.getMainLooper());

    /**
     * 在子线程中运行任务
     *
     * @param runnable 可执行接口对象
     */
    public static void runOnThreadPool(Runnable runnable) {
        mThreadPoolExecutor.execute(runnable);
    }

    /**
     * 在主线程中运行任务
     *
     * @param runnable 可执行接口对象
     */
    public static void runOnUIThread(Runnable runnable) {
        if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
            runnable.run();
        } else {
            mMainHandler.post(runnable);
        }
    }

    /**
     * 在子线程中切主线程运行任务
     *
     * @param runnable 可执行接口对象
     */
    public static void runOnUIThreadByThreadPool(Runnable runnable) {
        mThreadPoolExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mMainHandler.post(runnable);
            }
        });
    }

    /**
     * 在子线程中运行任务
     *
     * @param callable
     * @param <T>
     * @return
     */
    public static <T> Future<T> runOnThreadPool(Callable<T> callable) {
        return mThreadPoolExecutor.submit(callable);
    }
}