#### 流信息API
##### 接口源码
```java
    /**
     * 获取当前application下所有流信息 <br> 
     * Retrieves the specified Application Instance information <br>
     *
     * @param appName  Application name, cannot be null
     *
     * @return Current application stream info
     */
    ServiceResponse getStreamInfo(@NotNull String appName);
```
##### 请求参数
> 请求方式：GET

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|

##### 返回结果

###### 鉴权失败
```json
{
	"code": 20010016,
	"data": {
		"message": "The request requires user authentication",
		"code": 20010016,
		"wowzaServer": "4.7.0",
		"success": false
	},
	"message": "The request requires user authentication"
}
```

###### 存在流信息
```json
{
	"code": 0,
	"data": {
		"serverName": "_defaultServer_",
		"incomingStreams": [{
			"sourceIp": "rtmp://192.168.202.180:57032",
			"isPTZEnabled": false,
			"applicationInstance": "_definst_",
			"name": "65EC1D187271AB679C33DC5901307461",
			"isRecordingSet": false,
			"isStreamManagerStream": false,
			"isPublishedToVOD": false,
			"isConnected": true,
			"ptzPollingInterval": 2000,
			"ptzPollingIntervalMinimum": 1000
		}, {
			"sourceIp": "local (Transcoder)",
			"isPTZEnabled": false,
			"applicationInstance": "_definst_",
			"name": "65EC1D187271AB679C33DC5901307461_audio",
			"isRecordingSet": false,
			"isStreamManagerStream": false,
			"isPublishedToVOD": false,
			"isConnected": true,
			"ptzPollingInterval": 2000,
			"ptzPollingIntervalMinimum": 1000
		}],
		"outgoingStreams": [],
		"recorders": [],
		"streamGroups": [],
		"name": "_definst_"
	},
	"msg": "OK"
}
```

###### 无流信息
```json
{
	"code": 0,
	"data": {
		"serverName": "_defaultServer_",
		"incomingStreams": [],
		"outgoingStreams": [],
		"recorders": [],
		"streamGroups": [],
		"name": "_definst_2"
	},
	"msg": "OK"
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