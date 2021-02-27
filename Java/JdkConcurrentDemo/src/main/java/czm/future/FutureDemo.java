package czm.future;

import czm.ComputeTask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 使用Future，Callable，FutureTask完成异步计算任务
 */
public class FutureDemo {
    public static void main(String[] args) {
        //提交任务
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future future = executorService.submit(new ComputeTask());
        executorService.shutdown();

        //主线程去做别的耗时任务
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //获取任务的计算结果
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
