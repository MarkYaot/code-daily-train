package czm.util;

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
        // ʹ��ClassLoader����properties�����ļ����ɶ�Ӧ��������
        InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        // ʹ��properties�������������
        try {
            properties.load(in);
        } catch (IOException e) {
            logger.error("load properties failed! error:{}", e.getMessage());
        }
        return properties;
    }
}
