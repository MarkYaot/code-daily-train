package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;

/**
 * 写入耗时对比，分别为1000，10000，100000
 */
public class SimpleTest2 {
    private static final Logger logger = LoggerFactory.getLogger(SimpleTest2.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        Jedis jedis = new Jedis(host, port);
        //清空老数据
        jedis.flushAll();

        //写入100
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info(String.valueOf(System.currentTimeMillis() - time));

        //写入1000
        time = System.currentTimeMillis();
        for (int i = 0; i < 1100; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info(String.valueOf(System.currentTimeMillis() - time));

        //写入10000
        time = System.currentTimeMillis();
        for (int i = 1000; i < 11000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info(String.valueOf(System.currentTimeMillis() - time));

        //写入100000
        time = System.currentTimeMillis();
        for (int i = 20000; i < 120000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info(String.valueOf(System.currentTimeMillis() - time));
    }
}
