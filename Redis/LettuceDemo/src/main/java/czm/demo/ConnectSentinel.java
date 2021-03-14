package czm.demo;

import czm.util.PropertiesUtil;
import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;

public class ConnectSentinel {
    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        String port1 = PropertiesUtil.getRedisProperties("sentinel1.port");
        String port2 = PropertiesUtil.getRedisProperties("sentinel2.port");
        String port3 = PropertiesUtil.getRedisProperties("sentinel3.port");
        RedisClient client = RedisClient.create(String.format("redis-sentinel://%s:%s,%s:%s,%s:%s",
                host, port1, host, port2, host, port3));

        StatefulRedisConnection<String, String> connection = client.connect();

        connection.close();
        client.shutdown();
    }
}
