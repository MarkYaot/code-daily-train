package czm;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class ClusterTest {
    public static void main(String[] args) {
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("192.168.2.124", 8000));
        nodes.add(new HostAndPort("192.168.2.124", 8001));
        nodes.add(new HostAndPort("192.168.2.124", 8002));
        nodes.add(new HostAndPort("192.168.2.124", 8003));
        nodes.add(new HostAndPort("192.168.2.124", 8004));
        nodes.add(new HostAndPort("192.168.2.124", 8005));

        JedisCluster jedisCluster = new JedisCluster(nodes, new GenericObjectPoolConfig());
        jedisCluster.del("name");
        jedisCluster.set("name", "czm");
        System.out.println(jedisCluster.get("name"));
    }
}
