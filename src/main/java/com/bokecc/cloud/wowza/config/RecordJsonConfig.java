package com.bokecc.cloud.wowza.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * HttpClientPoolConfig属性文件工具类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class RecordJsonConfig {

    private static final Logger logger = LoggerFactory.getLogger(RecordJsonConfig.class);

    private static Properties prop = null;
 
    static {
        try {
            prop = new Properties();
            InputStream resourceAsStream =
                RecordJsonConfig.class.getClassLoader().getResourceAsStream("record-json.properties");
            prop.load(resourceAsStream);
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public static String getValue(String key) {
        return prop.getProperty(key);
    }
}