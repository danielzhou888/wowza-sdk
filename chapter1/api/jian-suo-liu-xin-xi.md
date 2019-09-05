##### 接口源码
```java
    /**
     * Retrieves the specifed Stream Recorder
     *
     * @param appName      Application name
     * @param streamName   Stream name
     *
     * @return Response info
     */
    ServiceResponse retrievesStreamInfo(@NotNull String appName, @NotNull String streamName);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|
|streamName|true|stream name, cannot be null|


##### 返回结果

###### 鉴权失败
```json
{
	"code": 20010016,
	"data": {
		"code": 20010016,
		"success": false,
		"message": "The request requires user authentication"
	},
	"message": "The request requires user authentication"
}
```

###### 查询录制状态成功
```json
{
	"code": 0,
	"data": {
		"ptzPollingInterval": 2000,
		"isRecordingSet": false,
		"ptzPollingIntervalMinimum": 1000,
		"sourceIp": "wowz://192.168.1.189:37031",
		"applicationInstance": "_definst_",
		"isPTZEnabled": false,
		"name": "BA4544AE314C3AF59C33DC5901307461_0",
		"isConnected": true,
		"serverName": "_defaultServer_",
		"isPublishedToVOD": false,
		"isStreamManagerStream": false
	},
	"message": "OK"
}
```

###### 查询录制状态失败
```json
{
	"code": 20010017,
	"data": {
		"wowzaServer": "4.7.0",
		"code": 20010017,
		"success": false,
		"message": "The server has not found anything matching the request URI"
	},
	"msg": "The server has not found anything matching the request URI"
}
```

###### 参数值异常
```json
{
	"code": 20010014,
	"data": {
		"wowzaServer": "4.7.0",
		"code": 20010014,
		"success": false,
		"message": "The request could not be understood by the server due to malformed syntax"
	},
	"msg": "The request could not be understood by the server due to malformed syntax"
}
```