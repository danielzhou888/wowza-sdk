### 账号密码
所有节点的账号密码统一
> username：admin
> password：advUser

### 初始化调用案例
**代码**
**实例调用：**
```java
    private static final String IP = "http://192.168.1.189";
    private static final String USERNAME = "zhouzhixiang";
    private static final String PASSWORD = "123456";
    private static final String IP2 = "192.168.1.196";
    private static final String USERNAME2 = "zhouzhixiang2";
    private static final String PASSWORD2 = "123456";

    @Test
    public void testRecordStartHoster() {
        RecordJson recordJson = new RecordJson.Builder().baseFile(baseFile).build();
        ServiceResponse result = WowzaSdkFactory.getInstance(IP, USERNAME, PASSWORD).recordStartHoster(streamName, recordJson);
        if (result.isOk()) {
            logger.info("success");
        }
        logger.info(result.toJson());
        Asserts.notEmpty(result.toJson(), null);
        System.out.println(System.currentTimeMillis());
    }
```


传参数据从配置文件中读取，本例仅参考。
**注意：**
> 对于未调用`WowzaSDKSingleFactory.init(IP, USERNAME, PASSWORD);`进行初始化，
> 直接调用`wowzaSDK = WowzaSDKSingleFactory.getInstance();`
> 将返回不带鉴权功能的sdk实例