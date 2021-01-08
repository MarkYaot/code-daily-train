package czm.executor;

import czm.SimpleTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ExecutorDemo {
    public static void main(String[] args) {
        /**
         * 使用Executors提供的工厂方法创建线程池
         */
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new SimpleTask());

        ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(1);
        scheduledThreadPoolExecutor.scheduleAtFixedRate(new SimpleTask(), 1, 2, TimeUnit.SECONDS);
    }

}

