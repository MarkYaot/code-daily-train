package czm.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        int count = 1000;
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(1000);

        Thread produce = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    try {
                        queue.put(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread consume = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < count; i++) {
                    queue.remove(i);
                }
            }
        });

        produce.start();
        consume.start();
        produce.join();
        consume.join();
    }
}
