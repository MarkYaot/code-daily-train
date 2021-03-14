package czm.demo;

import czm.util.PropertiesUtil;
import io.lettuce.core.ReadFrom;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.codec.StringCodec;
import io.lettuce.core.masterreplica.MasterReplica;
import io.lettuce.core.masterreplica.StatefulRedisMasterReplicaConnection;

import java.util.Arrays;
import java.util.List;

public class ConnectToMasterSlave {
    public static void main(String[] args) {
        //读取连接
        String host = PropertiesUtil.getRedisProperties("host");
        String masterPort = PropertiesUtil.getRedisProperties("ha1.port");
        String slavePort = PropertiesUtil.getRedisProperties("ha2.port");

        //建立连接，设置读写分离
        RedisClient client = RedisClient.create();
        List<RedisURI> nodes = Arrays.asList(RedisURI.create(host, Integer.parseInt(masterPort)),
                RedisURI.create(host, Integer.parseInt(slavePort)));
        StatefulRedisMasterReplicaConnection<String, String> connection =
                MasterReplica.connect(client, StringCodec.UTF8, nodes);
        connection.setReadFrom(ReadFrom.REPLICA_PREFERRED);
        RedisCommands<String, String> commands = connection.sync();

        //执行set和get命令，计算耗时
        commands.flushall();
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100; i++) {
            String key = String.valueOf(i);
            commands.set(key, "xxx");
            commands.get(key);
        }
        System.out.println("cost " + (System.currentTimeMillis() - time) + " ms");

        //断开连接
        connection.close();
        client.shutdown();
    }
}
