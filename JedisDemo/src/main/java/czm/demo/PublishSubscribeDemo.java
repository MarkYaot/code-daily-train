package czm.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import czm.utils.PropertiesUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisPubSub;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PublishSubscribeTest {
    private static final Logger logger = LoggerFactory.getLogger(PublishSubscribeTest.class);

    public static void main(String[] args) {
        // 替换成你的reids地址和端口
        String host = PropertiesUtil.getRedisProperties("host");
        Integer port = Integer.parseInt(PropertiesUtil.getRedisProperties("single.port"));
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), host, port);
        logger.info("redis pool is starting, redis ip %s, redis port %d", host, port);

        SubThread subThread = new SubThread(jedisPool);
        subThread.start();

        Publisher publisher = new Publisher(jedisPool);
        publisher.start();
    }
}

class Subscriber extends JedisPubSub {
    private static final Logger logger = LoggerFactory.getLogger(Subscriber.class);

    public Subscriber() {
    }

    public void onMessage(String channel, String message) {
        logger.info(String.format("receive redis published message, channel %s, message %s", channel, message));
    }

    public void onSubscribe(String channel, int subscribedChannels) {
        logger.info(String.format("subscribe redis channel success, channel %s, subscribedChannels %d",
            channel, subscribedChannels));
    }

    public void onUnsubscribe(String channel, int subscribedChannels) {
        logger.info(String.format("unsubscribe redis channel, channel %s, subscribedChannels %d",
            channel, subscribedChannels));

    }
}

class SubThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(SubThread.class);

    private final JedisPool jedisPool;

    private final Subscriber subscriber = new Subscriber();

    private final String channel = "mychannel";

    public SubThread(JedisPool jedisPool) {
        super("SubThread");
        this.jedisPool = jedisPool;
    }

    @Override
    public void run() {
        logger.info(String.format("subscribe redis, channel %s, thread will be blocked", channel));
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.subscribe(subscriber, channel);
        } catch (Exception e) {
            logger.info(String.format("subsrcibe channel error, %s", e));
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}

class Publisher {
    private static final Logger logger = LoggerFactory.getLogger(Publisher.class);

    private final JedisPool jedisPool;

    public Publisher(JedisPool jedisPool) {
        this.jedisPool = jedisPool;
    }

    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Jedis jedis = jedisPool.getResource();
        while (true) {
            String line = null;
            try {
                line = reader.readLine();
                if (!"quit".equals(line)) {
                    jedis.publish("mychannel", line);
                } else {
                    break;
                }
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}