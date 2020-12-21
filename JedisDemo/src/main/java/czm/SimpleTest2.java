package czm;

import redis.clients.jedis.Jedis;

/**
 * 写入耗时对比，分别为1000，10000，100000
 */
public class SimpleTest2 {
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

        //写入10000
        time = System.currentTimeMillis();
        for (int i = 1000; i < 11000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        System.out.println(System.currentTimeMillis() - time);

        //写入100000
        time = System.currentTimeMillis();
        for (int i = 20000; i < 120000; i++) {
            jedis.set(Integer.toString(i), "val");
        }
        System.out.println(System.currentTimeMillis() - time);
    }
}
