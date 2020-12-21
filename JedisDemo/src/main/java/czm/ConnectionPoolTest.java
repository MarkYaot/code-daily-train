package czm;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池的使用
 */
public class ConnectionPoolTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        final JedisPool pool = new JedisPool(config, "192.168.2.124", 6379);
        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Jedis jedis = pool.getResource();
                    long time = System.currentTimeMillis();
                    for (int i = 0; i < 1000; i++) {
                        jedis.set(Integer.toString(i), "val");
                    }
                    System.out.println(Thread.currentThread().toString() + (System.currentTimeMillis() - time));
                    //回收连接
                    jedis.close();
                }
            }).start();
        }
    }
}
