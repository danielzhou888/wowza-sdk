package com.bokecc.cloud.wowza.sdk;


import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.enums.HostIndexEnum;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import com.bokecc.cloud.wowza.utils.WowzaSdkFactory;
import com.bokecc.cloud.wowza.utils.WowzaSdkMultiFactory;
import org.apache.http.util.Asserts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 单元测试
 */
public class WowzaSDKTest {

    private static final Logger logger = LoggerFactory.getLogger(WowzaSDKTest.class);

    // private static final String IP = "http://192.168.200.117";
    // private static final String IP = "http://106.14.166.132";
    // private static final String IP = "http://192.168.1.86";
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
    private static IWowzaSDK WowzaSDK;

    // private String streamName = "B1440C8354A952D49C33DC5901307461";
    // private String streamName = "0C7EFC7F727D85D29C33DC5901307461";
    // private String streamName = "B678CA349DA82DBE9C33DC5901307461";
    // private String streamName = "06E187FBBA0CE3849C33DC5901307461";
    // private String streamName = "7091118C5EE3979F9C33DC5901307461";
    // private String streamName = "1F54E375C74E11F49C33DC5901307461";
    // private String streamName = "BA4544AE314C3AF59C33DC5901307461";
    // private String streamName = "1B5A87B912A03F179C33DC5901307461";
    // private String streamName = "6FA89732CD75C4DE9C33DC5901307461";
    // private String streamName = "326FC0376EFD2AF79C33DC5901307461";
    // private String streamName = "116FC0376EFD232333DC5901307431";
    // private String streamName = "226FC0376EFD232333DC5901307431";
    // private String streamName = "336FC0376EFD232333DC5901307431";
    // private String streamName = "446FC0376EFD232333DC5901307431";
    // private String streamName = "556FC0376EFD232333DC5901307431";
    // private String streamName = "666FC0376EFD232333DC5901307431";
    private String streamName = "777FC0376EFD232333DC5901307431";
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
        WowzaSdkMultiFactory.init(IP, USERNAME, PASSWORD);
        WowzaSDK = WowzaSdkMultiFactory.getInstance(IP);
    }

    @AfterAll
    public static void after() {
        logger.info("test end");
    }


    @Test
    public void testrecordStartByWowzaSelf() {
        String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStartByWowzaSelf(appName, streamName, recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testrecordStartAndStopByWowzaSelf() throws InterruptedException {
        for (int i = 0; i < 20; i++) {
            String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";
            RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
            //单元测试,录制接口
            ServiceResponse result = WowzaSDK.recordStartByWowzaSelf(appName, streamName, recordJson);
            Thread.sleep(10000);
            ServiceResponse stopResult = WowzaSDK.recordStopByWowzaSelf(appName, streamName);
            Thread.sleep(2000);
            logger.info(result.toJson());
            logger.info(stopResult.toJson());
        }
    }

    @Test
    public void testrecordStopByWowzaSelf() {
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStopByWowzaSelf(appName,streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStart() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStart(appName, streamName+"_0", recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testRecordStartNoneHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStartNonHoster(streamName, recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testrecordPauseHoster() {
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordPauseHoster(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStop() {
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStop(appName, streamName+"_host_transcode");
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStopHoster() {
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStopHoster(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStopNonHoster() {
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStopNoneHoster(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStartHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStartHoster(streamName, recordJson);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void testLivingStartHoster() {
        ServiceResponse result = WowzaSDK.livingStartHoster(streamName, HostIndexEnum.ZERO);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
}

    @Test
    public void testLivingStartHosterAndRecordStartHoster() throws InterruptedException {


        System.out.println("----------------------------------");

        for (int i = 0; i < 80; i++) {
            String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";
            RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
            Thread.sleep(2000);
            // if (i == 0) {
                ServiceResponse hostResult = WowzaSDK.livingStartHoster(streamName, HostIndexEnum.ZERO);
                logger.info(hostResult.toJson());
            // }
            ServiceResponse recordResult = WowzaSDK.recordStartHoster(streamName, recordJson);

            logger.info(recordResult.toJson());
            Thread.sleep(10000);
            ServiceResponse recordStopHosterResult = WowzaSDK.recordStopHoster(streamName);

            logger.info(recordStopHosterResult.toJson());
            ServiceResponse livingStopHoster = WowzaSDK.livingStopHoster(streamName);
            logger.info(livingStopHoster.toJson());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        WowzaSdkMultiFactory.init(IP, USERNAME, PASSWORD);
        WowzaSDK = WowzaSdkMultiFactory.getInstance(IP);
        String streamName = "2B5A87B912A03F179C33DC5901307461";
        String baseFile = streamName + "-" + UUID.randomUUID().toString().replace("-", "") + ".flv";

        Thread hostThread = new Thread(() -> {
            ServiceResponse hostResult = WowzaSDK.livingStartHoster(streamName, HostIndexEnum.ZERO);
            logger.info(hostResult.toJson());
        });
        Thread recordThread = new Thread(() -> {
            RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
            ServiceResponse recordResult = WowzaSDK.recordStartHoster(streamName, recordJson);
            logger.info(recordResult.toJson());
        });
        recordThread.start();
        Thread.sleep(1000);
        hostThread.start();
    }

    @Test
    public void testLivingStopHoster() {
        ServiceResponse result = WowzaSDK.livingStopHoster(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testLivingSwitchHoster() {
        ServiceResponse result = WowzaSDK.livingSwitchHoster(streamName, HostIndexEnum.ONE);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testBanLiveRoom() {
        ServiceResponse result = WowzaSDK.banLiveRoom(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testUnBanLiveRoom() {
        ServiceResponse result = WowzaSDK.unbanLiveRoom(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testPublisherWithPost() {
        String json = "{ \"password\": \"1579655633@qq.com\", \"name\": \"test27\", \"serverName\": \"_defaultServer_\", \"description\": \"test\", \"saveFieldList\": [ \"\" ], \"version\": \"v1.0\" }";
        // Map<String, Object> jsonMap = JSONObject.parseObject(json, Map.class);
        ServiceResponse result = WowzaSDK.addPublisher(json);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testgetStreamInfo() {
        ServiceResponse result = WowzaSDK.getStreamInfo(appName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRetrivesApplicationsGet() {
        ServiceResponse result = WowzaSDK.retrivesApplications();
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }


    @Test
    public void testretrievesRecorderStatus() {
        ServiceResponse result = WowzaSDK.retrievesRecorderStatus(appName,streamName + "_record_transcode");
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testretrievesStreamInfo() {
        ServiceResponse result = WowzaSDK.retrievesStreamInfo(appName,streamName+"_0");
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }


}
