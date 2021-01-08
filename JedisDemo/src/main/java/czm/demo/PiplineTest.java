package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 对比pipeline效率提升
 */
public class PiplineTest {
    private static final Logger logger = LoggerFactory.getLogger(PiplineTest.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        Jedis jedis = new Jedis(host, port);
        //清空老数据
        jedis.flushAll();

        //写入1000
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        logger.info(String.valueOf(System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipeline.set(Integer.toString(i), "val");
        }
        pipeline.sync();
        logger.info(String.valueOf(System.currentTimeMillis() - time));
    }
}
