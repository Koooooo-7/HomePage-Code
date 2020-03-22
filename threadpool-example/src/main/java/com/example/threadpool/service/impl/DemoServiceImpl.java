package com.example.threadpool.service.impl;

import com.example.threadpool.service.DemoService;
import com.example.threadpool.service.ThreadService;
import com.example.threadpool.thread.DemoThreadFactory;
import com.example.threadpool.thread.WaitRejectedExecutionHandler;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/19
 */
@Service
public class DemoServiceImpl implements DemoService {


    private static final ThreadPoolExecutor threadPool;

    private static final int CORE_THREAD = 5;
    private static final int MAX_THREAD = 5;
    private static final Long KEEP_ALIVE = 0L;
    private static final int QUEUE_SIZE = 10;

    static {
        threadPool = new ThreadPoolExecutor(CORE_THREAD
                , MAX_THREAD
                , KEEP_ALIVE
                , TimeUnit.NANOSECONDS
                , new LinkedBlockingQueue<>(QUEUE_SIZE)
                , new DemoThreadFactory()
                , new WaitRejectedExecutionHandler());

    }


    @Override
    public void sendMessage(List<String> numbers) {

        for (String number : numbers) {
            threadPool.execute(new ThreadService(number));
        }

        System.out.println("already response now");
    }
}
