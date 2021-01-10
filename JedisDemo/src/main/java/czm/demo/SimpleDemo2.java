package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;

/**
 * 写入耗时对比，分别为1000，10000，100000
 */
public class SimpleDemo2 {
    private static final Logger logger = LoggerFactory.getLogger(SimpleDemo2.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));

    }
}
