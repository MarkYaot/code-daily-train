package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;

/**
 * 最简单的测试：连接redis写入一个key,断开连接
 */
public class SimpleDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleDemo.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        Jedis jedis = new Jedis(host, port);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        logger.info(value);
        jedis.close();
    }
}
