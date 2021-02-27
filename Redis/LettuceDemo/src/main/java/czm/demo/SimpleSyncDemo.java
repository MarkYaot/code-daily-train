package czm.demo;

import io.lettuce.core.RedisURI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import czm.util.PropertiesUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * 以同步方式操作redis
 */
public class SimpleSyncDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSyncDemo.class);

    public static void main(String[] args) throws InterruptedException {
        String host = PropertiesUtil.getRedisProperties("host");
        String port = PropertiesUtil.getRedisProperties("single.port");

        RedisURI redisURI = RedisURI.create(host, Integer.parseInt(port));
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            commands.set(String.valueOf(i), "xxx");
        }
        logger.info("cost {} ms", System.currentTimeMillis() - time);
        connection.close();
        client.shutdown();
    }
}
