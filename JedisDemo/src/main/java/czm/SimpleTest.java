package czm;

import redis.clients.jedis.Jedis;

/**
 * 最简单的测试：连接redis写入一个key,断开连接
 */
public class SimpleTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.124", 6379);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
        jedis.close();
    }
}
