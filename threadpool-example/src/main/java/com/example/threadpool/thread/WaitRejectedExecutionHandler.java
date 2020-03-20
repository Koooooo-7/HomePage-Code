package com.example.threadpool.thread;

import com.example.threadpool.exception.ThreadRejectException;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.LockSupport;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/19
 */
public class WaitRejectedExecutionHandler implements RejectedExecutionHandler {

    private static final ThreadLocal<ThreadInfo> treadInfo = new ThreadLocal<>();

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        ThreadInfo threadInfo = treadInfo.get();

        if (threadInfo == null) {
            threadInfo = new ThreadInfo(r, executor);
            treadInfo.set(threadInfo);
        }

        if (threadInfo.getR() != r || threadInfo.getExecutor() != executor) {
            threadInfo = new ThreadInfo(r, executor);
            treadInfo.set(threadInfo);
        }

        threadInfo.incRejectTimes();

        if (threadInfo.getRejectTimes() > 100) {
            throw new ThreadRejectException(Thread.currentThread().getName() + "was rejected enough !");
        }

        while (!executor.isShutdown()) {
            LockSupport.parkNanos(1000000);
            if (executor.getActiveCount() < executor.getMaximumPoolSize() * 0.9) {
                executor.submit(r);
            }
            LockSupport.parkNanos(1000000000);
        }

    }


}

class ThreadInfo {
    private Runnable r;
    private ThreadPoolExecutor executor;
    private int rejectTimes;

    public ThreadInfo(Runnable r, ThreadPoolExecutor executor) {
        this.r = r;
        this.executor = executor;
    }

    public Runnable getR() {
        return r;
    }

    public void setR(Runnable r) {
        this.r = r;
    }

    public ThreadPoolExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(ThreadPoolExecutor executor) {
        this.executor = executor;
    }

    public int getRejectTimes() {
        return rejectTimes;
    }

    public void incRejectTimes() {
        this.rejectTimes++;
    }
}