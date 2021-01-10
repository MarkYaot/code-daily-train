package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * 连接池的使用，执行1000个set请求，对比连接池与单连接的性能
 */
public class ConnectionPoolDemo {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolDemo.class);

    public static void main(String[] args) throws InterruptedException {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);

        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        final JedisPool pool = new JedisPool(config, host, port);
        List<Thread> threads = new ArrayList<>();

        long time = System.currentTimeMillis();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    Jedis jedis = pool.getResource();
                    for (int i = 0; i < 100; i++) {
                        jedis.set(Integer.toString(i), "val");
                    }
                    jedis.close();
                }
            });
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.join();
        }
        pool.close();
        logger.info("pool 1000 requests cost {}ms", System.currentTimeMillis() - time);

        Jedis jedis = new Jedis(host, port);
        jedis.flushAll();

        //写入1000
        time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info("single 1000 requests costs {}ms", System.currentTimeMillis() - time);
    }
}
