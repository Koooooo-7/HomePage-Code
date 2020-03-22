package com.example.threadpool.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/19
 */
public class DemoThreadFactory implements ThreadFactory {

    private static final AtomicInteger ai = new AtomicInteger(0);

    @Override
    public Thread newThread(Runnable r) {

        return new Thread(r, "DemoThreadService--" + ai.incrementAndGet());
    }
}
