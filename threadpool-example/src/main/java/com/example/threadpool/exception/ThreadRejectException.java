package com.example.threadpool.exception;

/**
 * @Description
 * @Auther Koy  https://github.com/Koooooo-7
 * @Date 2020/03/19
 */
public class ThreadRejectException extends RuntimeException {

    public ThreadRejectException(String message) {
        super(message);
    }
}
