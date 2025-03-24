package com.demo.eventlistener.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadPoolUtils {
    private static volatile ThreadPoolExecutor threadPool = null;

    private ThreadPoolUtils() {
    }

    public static void execute(Runnable runnable) {
        getThreadPool().execute(runnable);
    }

    private static synchronized ThreadPoolExecutor getThreadPool() {
        if (threadPool == null) {
            threadPool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2,
                    20,
                    60 * 1000, TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<>(1000),
                    new ThreadFactoryBuilder().setNameFormat("platform-pool-%d").build(),
                    new ThreadPoolExecutor.AbortPolicy() {
                        @Override
                        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
                            log.error("ThreadPoolUtils rejectedExecution");
                        }
                    });
        }
        return threadPool;
    }
}
