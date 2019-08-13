package com.bokecc.cloud.wowza.sdk;

import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import com.bokecc.cloud.wowza.utils.WowzaSdkMultiFactory;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发测试
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class ConcurrencySDKTest {

    private static final Logger logger = LoggerFactory.getLogger(WowzaSDKTest.class);

    private static final ThreadPoolExecutor pool = new ThreadPoolExecutor(20, 20 , 10, TimeUnit.SECONDS, new ArrayBlockingQueue<>(20));

    // private static final String IP = "http://192.168.200.117";
    // private static final String IP = "http://106.14.166.132";
    private static final String IP = "http://192.168.1.86";
    // private static final String IP = "http://192.168.1.189";
    // private static final String IP = "http://192.168.1.127";
    // private static final String IP = "http://47.101.197.110";
    // private static final String IP = "http://218.60.53.11";
    // private static final String IP = "http://39.106.114.63";
    // private static final String IP = "http://119.23.200.43";
    // private static final String IP = "http://122.11.32.90";
    // private static final String IP = "http://101.132.132.196";
    // private static final String IP = "http://139.196.143.127";
    // private static final String IP = "http://47.101.197.110";
    private static final String USERNAME = "zhouzhixiang";
    private static final String PASSWORD = "123456";
    private static IWowzaSDK WowzaSDK;

    // private String streamName = "B1440C8354A952D49C33DC5901307461";
    // private String streamName = "0C7EFC7F727D85D29C33DC5901307461";
    // private String streamName = "B678CA349DA82DBE9C33DC5901307461";
    // private String streamName = "06E187FBBA0CE3849C33DC5901307461";
    // private String streamName = "7091118C5EE3979F9C33DC5901307461";
    // private String streamName = "1F54E375C74E11F49C33DC5901307461";
    // private String streamName = "BA4544AE314C3AF59C33DC5901307461";
    // private String streamName = "1B5A87B912A03F179C33DC5901307461";
    private String streamName = "6FA89732CD75C4DE9C33DC5901307461";
    // private String streamName = "241CC3BD7FBBBCB19C33DC5901307461";
    // private String streamName = "C2F12381D8C24DF79C33DC5901307461";
    // private String streamName = "1CE1399FD41CF9129C33DC5901307461";
    // 110
    // private String streamName = "3C3208ABBAA3ABC69C33DC5901307461";

    private String appName = "host";

    private String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";

    @BeforeAll
    public static void before() {
        logger.info("begin init");
        // WowzaSDK = new WowzaSDK(IP, USERNAME, PASSWORD);
        // WowzaSDKSingleFactory.init(IP, USERNAME, PASSWORD);
        // WowzaSDK = WowzaSDKSingleFactory.getInstance();
        // 多实例
        WowzaSdkMultiFactory.init("192.168.1.86", USERNAME, PASSWORD);
        WowzaSdkMultiFactory.init("192.168.1.127", USERNAME, PASSWORD);
    }

    @AfterAll
    public static void after() {
        logger.info("test end");
    }

    @Test
    public void testRecordStartNoneHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        IWowzaSDK WowzaSDK_86 = WowzaSdkMultiFactory.getInstance("192.168.1.86");
        IWowzaSDK WowzaSDK_189 = WowzaSdkMultiFactory.getInstance("192.168.1.189");
        //单元测试,录制接口
        // ServiceResponse result_189 = WowzaSDK_189.recordStartNonHoster(streamName, recordJson);

        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            ServiceResponse result_86 = WowzaSDK_86.recordStartNonHoster(String.valueOf(count.get()), recordJson);
            logger.info((count.getAndIncrement()) + ":::::" + result_86.toJson());
        }
        // Asserts.notEmpty(result_86.toJson(), null);
        // Asserts.notEmpty(result_189.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }

    public static void main(String[] args) {
        // 多实例
        WowzaSdkMultiFactory.init("192.168.1.86", USERNAME, PASSWORD);
        WowzaSdkMultiFactory.init("192.168.1.127", USERNAME, PASSWORD);
        IWowzaSDK WowzaSDK_86 = WowzaSdkMultiFactory.getInstance("192.168.1.86");
        IWowzaSDK WowzaSDK_189 = WowzaSdkMultiFactory.getInstance("192.168.1.189");
        //单元测试,录制接口
        // ServiceResponse result_189 = WowzaSDK_189.recordStartNonHoster(streamName, recordJson);

        AtomicInteger count = new AtomicInteger();
        for (int i = 0; i < 10; i++) {
            pool.execute(() -> {
                String baseFile = count.get() + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";
                RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
                ServiceResponse result_86 = WowzaSDK_86.recordStartNonHoster(String.valueOf(count.get()), recordJson);
                logger.info((count.getAndIncrement()) + ":::::" + result_86.toJson());
            });
        }
        // Asserts.notEmpty(result_86.toJson(), null);
        // Asserts.notEmpty(result_189.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }
}
