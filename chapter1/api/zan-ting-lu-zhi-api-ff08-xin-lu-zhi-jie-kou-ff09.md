#### 主持人模式暂停录制API（新录制接口）
##### 接口源码
```java
    /**
     * 主持人模式暂停录制<br>
     * 新录制接口<br>
     * Wowza start to pause stream specified
     *
     * @param streamName stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordPauseHoster(@NotNull String streamName);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|streamName|true|Stream name, cannot be null|


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

###### 暂停录制成功
```json
{
	"code": 0,
	"data": {
		"success": true,
		"message": "Recording (B1440C8354A952D49C33DC5901307461_record_transcode) stopped",
		"data": null
	},
	"msg": "OK"
}
```

###### 暂停录制冲突
```json
{
	"code": 20010017,
	"data": {
		"message": "The server has not found anything matching the request URI",
		"code": "20010017",
		"wowzaServer": "4.7.0",
		"success": false
	},
	"msg": "The server has not found anything matching the request URI"
}
```


###### 必要参数空异常
```json
{
	"code": 20020002,
	"data": {
		"message": "The request param postData||streamName||appName cannot be null",
		"code": 20020002,
		"success": false
	},
	"msg": "The request param postData||streamName||appName cannot be null"
}
```

###### 录制操作异常（Unsupported Exception）
```json
{
	"code": 20020001,
	"data": {
		"message": "Record operation error",
		"code": 20020001,
		"success": false
	},
	"msg": "Record operation error"
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