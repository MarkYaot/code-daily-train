package czm.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
    private static final Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);

    public static String getRedisProperties(String name) {
        return getProperties("redis.properties").getProperty(name);
    }

    private static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        // 使用ClassLoader加载properties配置文件生成对应的输入流
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        // 使用properties对象加载输入流
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error("load properties failed! error:{}", e.getMessage());
        }
        return properties;
    }
}
