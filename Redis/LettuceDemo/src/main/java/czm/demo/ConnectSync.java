package czm.demo;

import io.lettuce.core.RedisURI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.util.PropertiesUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * 以同步方式操作单机redis
 */
public class ConnectSync {
    private static final Logger logger = LoggerFactory.getLogger(ConnectSync.class);

    public static void main(String[] args) {
        //读取配置
        String host = PropertiesUtil.getRedisProperties("host");
        String port = PropertiesUtil.getRedisProperties("single.port");

        //建立连接
        RedisURI redisURI = RedisURI.create(host, Integer.parseInt(port));
        RedisClient client = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();

        //执行set命令，计算耗时
        commands.flushall();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            commands.set(String.valueOf(i), "xxx");
        }
        logger.info("cost {} ms", System.currentTimeMillis() - time);

        //断开连接
        connection.close();
        client.shutdown();
    }
}
