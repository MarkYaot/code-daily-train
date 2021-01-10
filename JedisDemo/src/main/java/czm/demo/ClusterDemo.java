package czm.demo;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.HashSet;
import java.util.Set;

/**
 * JedisCluster的使用，操作redis集群
 */
public class ClusterDemo {
    private static final Logger logger = LoggerFactory.getLogger(ClusterDemo.class);

    public static void main(String[] args) {
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.valueOf(PropertiesUtil.getRedisProperties("cluster1.port"));
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort(host, port));

        JedisCluster jedisCluster = new JedisCluster(nodes);
        jedisCluster.set("name", "czm");
        logger.info(jedisCluster.get("name"));
        jedisCluster.close();
    }
}
