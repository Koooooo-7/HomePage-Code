package com.example.threadpool.service;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/19
 */
public class ThreadService implements Runnable {
    private String number;

    public ThreadService(String number) {
        this.number = number;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        System.out.println(String.format("Thread: %s is sending massage to %s ", name, number));
        try {
            Thread.sleep(2_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
