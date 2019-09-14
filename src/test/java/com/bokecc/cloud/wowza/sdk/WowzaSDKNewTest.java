package com.bokecc.cloud.wowza.sdk;

import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import com.bokecc.cloud.wowza.utils.WowzaSdkFactory;
import com.bokecc.cloud.wowza.utils.WowzaSdkMultiFactory;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaSDKNewTest {

    private static final Logger logger = LoggerFactory.getLogger(WowzaSDKNewTest.class);

    private static final String IP = "http://192.168.1.189";
    // private static final String IP = "http://192.168.1.127";
    // private static final String IP = "http://47.101.197.110";
    // private static final String IP = "http://47.93.17.14";
    // private static final String IP = "http://218.60.53.11";
    // private static final String IP = "http://39.106.114.63";
    // private static final String IP = "http://119.23.200.43";
    // private static final String IP = "http://122.11.32.90";
    // private static final String IP = "http://101.132.132.196";
    // private static final String IP = "http://139.196.143.127";
    // private static final String IP = "http://47.101.197.110";
    private static final String USERNAME = "zhouzhixiang";
    private static final String PASSWORD = "123456";

    private String streamName = "777FC0376EFD232333DC5901307431";

    private String appName = "host";

    private String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";

    @BeforeAll
    public static void before() {
        logger.info("begin init");
    }

    @AfterAll
    public static void after() {
        logger.info("test end");
    }

    @Test
    public void testRecordStartHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        ServiceResponse result = WowzaSdkFactory.getInstance(IP, USERNAME, PASSWORD).recordStartHoster(streamName, recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testRecordStartNoneHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        ServiceResponse result = WowzaSdkFactory.getInstance(IP, USERNAME, PASSWORD).recordStartNonHoster(streamName, recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }
}
