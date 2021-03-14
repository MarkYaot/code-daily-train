package czm.demo;

import czm.util.PropertiesUtil;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import io.lettuce.core.cluster.api.StatefulRedisClusterConnection;
import io.lettuce.core.cluster.api.sync.RedisClusterCommands;

public class ConnectToCluster {
    public static void main(String[] args) {
        //读取配置
        String host = PropertiesUtil.getRedisProperties("host");
        String port = PropertiesUtil.getRedisProperties("cluster1.port");

        RedisURI redisURI = RedisURI.create(host, Integer.parseInt(port));
        RedisClusterClient client = RedisClusterClient.create(redisURI);
        StatefulRedisClusterConnection<String, String> connection = client.connect();

        RedisClusterCommands<String, String> commands = connection.sync();
        commands.flushall();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            commands.set(String.valueOf(i), "xxx");
        }
        System.out.println("cost " + (System.currentTimeMillis() - time) + " ms");

        connection.close();
        client.shutdown();
    }
}
