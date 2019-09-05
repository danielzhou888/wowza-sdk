#### 通用停止录制API（新录制接口）
##### 接口源码
```java
    /**
     * 通用停止录制: Wowza插件开发 <br>
     * 可通过传递appName来对主持人和非主持人模式进行暂停录制，可使用 recordStopHoster或 recordStopNoneHoster替代
     * Wowza start to stop stream specified
     *
     * @param appName    Application name, cannot be null
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStop(@NotNull String appName, @NotNull String streamName);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|
|streamName|true|Stream name, cannot be null|


----------------------------------------
#### 主持人模式停止录制API（新录制接口）
##### 接口源码
```java
    /**
     * 主持人模式停止录制<br>
     * 新录制接口<br>
     * Wowza start to stop stream specified
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStopHoster(@NotNull String streamName);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|streamName|true|Stream name, cannot be null|

----------------------------------------
#### 非主持人模式停止录制API（新录制接口）
##### 接口源码
```java
    /**
     * 非主持人模式停止录制<br>
     * 新录制接口<br>
     * Wowza start to stop stream specified
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStopNoneHoster(@NotNull String streamName);
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

###### 停止录制成功
```json
{
	"code": 0,
	"data": {
		"success": true,
		"message": "Recording (B1440C8354A952D49C33DC5901307461_record_transcode) stopped",
		"data": null
	},
	"message": "OK"
}
```

###### 停止录制冲突
```json
{
	"code": 20010017,
	"data": {
		"message": "The server has not found anything matching the request URI",
		"code": 20010017,
		"wowzaServer": "4.7.0",
		"success": false
	},
	"message": "The server has not found anything matching the request URI"
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
	"message": "The request param postData||streamName||appName cannot be null"
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
	"message": "The request could not be understood by the server due to malformed syntax"
}
```

