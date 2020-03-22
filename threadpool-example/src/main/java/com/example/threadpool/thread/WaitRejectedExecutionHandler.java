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

        if (threadInfo.getTask() != r || threadInfo.getExecutor() != executor) {
            threadInfo = new ThreadInfo(r, executor);
            treadInfo.set(threadInfo);
        }

        threadInfo.incRejectTimes();

        if (threadInfo.getRejectTimes() > 3) {
            throw new ThreadRejectException(Thread.currentThread().getName() + "was rejected enough !");
        }

        while (!executor.isShutdown()) {
            // holding current thread
            LockSupport.parkNanos(100_000 * threadInfo.getRejectTimes());
            //  try again, set condition
            if (executor.getActiveCount() < executor.getMaximumPoolSize() * 0.9) {
                executor.execute(r);
                break;
            }
            //  holding again
            LockSupport.parkNanos(500_000);
        }

    }


}

class ThreadInfo {
    private Runnable task;
    private ThreadPoolExecutor executor;
    private int rejectTimes;

    ThreadInfo(Runnable task, ThreadPoolExecutor executor) {
        this.task = task;
        this.executor = executor;
    }

    public Runnable getTask() {
        return task;
    }

    ThreadPoolExecutor getExecutor() {
        return executor;
    }

    int getRejectTimes() {
        return rejectTimes;
    }

    void incRejectTimes() {
        this.rejectTimes++;
    }
}