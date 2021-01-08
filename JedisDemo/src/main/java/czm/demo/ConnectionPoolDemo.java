package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 连接池的使用
 */
public class ConnectionPoolDemo {
    private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolDemo.class);

    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);

        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        final JedisPool pool = new JedisPool(config, host, port);

        for (int i = 0; i < 50; i++) {
            new Thread(new Runnable() {
                public void run() {
                    Jedis jedis = pool.getResource();
                    long time = System.currentTimeMillis();
                    for (int i = 0; i < 1000; i++) {
                        jedis.set(Integer.toString(i), "val");
                    }
                    logger.info(Thread.currentThread().toString() + (System.currentTimeMillis() - time));
                    //回收连接
                    jedis.close();
                }
            }).start();
        }
    }
}
