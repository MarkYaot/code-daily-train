package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.util.PropertiesUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

public class SimpleSyncDemo {
    private static final Logger logger = LoggerFactory.getLogger(SimpleSyncDemo.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");

        RedisClient client = RedisClient.create(String.format("redis://%s", host));
        StatefulRedisConnection<String, String> connection = client.connect();
        RedisCommands<String, String> commands = connection.sync();
        logger.info(commands.get("foo"));
        connection.close();
        client.shutdown();
    }
}
