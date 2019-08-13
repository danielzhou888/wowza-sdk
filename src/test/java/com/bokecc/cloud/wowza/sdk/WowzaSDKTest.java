package com.bokecc.cloud.wowza.sdk;


import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.enums.HostIndexEnum;
import com.bokecc.cloud.wowza.result.ServiceResponse;
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

public class WowzaSDKTest {

    private static final Logger logger = LoggerFactory.getLogger(WowzaSDKTest.class);

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
        WowzaSdkMultiFactory.init(IP, USERNAME, PASSWORD);
        WowzaSDK = WowzaSdkMultiFactory.getInstance(IP);
    }

    @AfterAll
    public static void after() {
        logger.info("test end");
    }


    @Test
    public void testrecordStartByWowzaSelf() {
        // Map<String, Object> jsonMap = JSONObject.parseObject(recordJson, Map.class);
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        //单元测试,录制接口
        // String result = WowzaSDK.recordStartByWowzaSelf(null, null,appName, null, streamName+"_host_transcode", jsonMap);
        ServiceResponse result = WowzaSDK.recordStartByWowzaSelf(appName, streamName+"_0", recordJson);
        // String result = WowzaSDK.recordStartByWowzaSelf(null, null,appName, null, streamName+"_record_transcode", jsonMap);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testrecordStopByWowzaSelf() {
        // Map<String, Object> jsonMap = JSONObject.parseObject(recordJson, Map.class);
        // Map<String, Object> jsonMap = new HashMap<>(JSONObject.fromObject(postData));

        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordStopByWowzaSelf(appName,streamName+"_0");
        // String result = WowzaSDK.recordStopByWowzaSelf(null, null,appName, null, streamName+"_host_transcode", jsonMap);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStart() {
        // Map<String, Object> jsonMap = JSONObject.parseObject(recordJson, Map.class);
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
        // Map<String, Object> jsonMap = JSONObject.parseObject(recordJson, Map.class);
        //单元测试,录制接口
        ServiceResponse result = WowzaSDK.recordPauseHoster(streamName);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testRecordStop() {
        // Map<String, Object> jsonMap = JSONObject.parseObject(recordJson, Map.class);
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
        // String result = WowzaSDK.hostStartPublish(null, null, streamName, "1");
        ServiceResponse result = WowzaSDK.livingStartHoster(streamName, HostIndexEnum.ZERO);
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
    }

    @Test
    public void testLivingStartHosterAndRecordStartHoster() throws InterruptedException {

        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        ServiceResponse recordResult = WowzaSDK.recordStartHoster(streamName, recordJson);
        logger.info(recordResult.toJson());

        System.out.println("----------------------------------");

        ServiceResponse hostResult = WowzaSDK.livingStartHoster(streamName, HostIndexEnum.ZERO);
        logger.info(hostResult.toJson());
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
        // String result = WowzaSDK.retrievesRecorderStatus(null, null, appName, null, streamName);
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
