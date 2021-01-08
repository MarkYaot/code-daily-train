package czm.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

public class ClusterDemo {
    private static final Logger logger = LoggerFactory.getLogger(ClusterDemo.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster1.port"))));
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster2.port"))));
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster3.port"))));
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster4.port"))));
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster5.port"))));
        nodes.add(new HostAndPort(host, Integer.parseInt(PropertiesUtil.getRedisProperties("cluster6.port"))));

        JedisCluster jedisCluster = new JedisCluster(nodes, new GenericObjectPoolConfig());
        jedisCluster.del("name");
        jedisCluster.set("name", "czm");
        logger.info(jedisCluster.get("name"));
        jedisCluster.close();
    }
}
