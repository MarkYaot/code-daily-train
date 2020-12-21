package czm;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

/**
 * 对比pipeline效率提升
 */
public class PiplineTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.2.124", 6379);
        //清空老数据
        jedis.flushAll();

        //写入1000
        long time = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        System.out.println(System.currentTimeMillis() - time);

        time = System.currentTimeMillis();
        Pipeline pipeline = jedis.pipelined();
        for (int i = 0; i < 1000; i++) {
            pipeline.set(Integer.toString(i), "val");
        }
        pipeline.sync();
        System.out.println(System.currentTimeMillis() - time);
    }
}
