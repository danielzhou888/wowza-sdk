#### 开始录制API（老接口—Wowza自身录制接口）
##### 接口源码
```java
    /**
     * 开始录制, Wowza 自身api</br>
     * Wowza start to record stream specified
     *
     * @param serverName Server name, default _defaultServer_
     * @param vHostName  VHost name, default _defaultVHost_
     * @param streamName Stream name, cannot be null
     * @param appName    Application name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    String startRecordByWowzaSelftWithPost(String serverName, String vHostName, @NotNull String appName, String instanceName, @NotNull String streamName,
        Map<String, Object> json);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|
|streamName|true|Stream name, cannot be null;<br>streamName must be passed [stream]_record_transcode when new business online; <br> streamName must be passed [stream]_host_transcode when old business online|
|json|false|Request body json data with map|


##### 返回结果

###### 鉴权失败
```json
{
	"code": 0,
	"data": {
		"code": 401,
		"success": false,
		"message": "The request requires user authentication"
	},
	"msg": "OK"
}

```

###### 录制成功
```json
{
	"success": true,
	"message": "Recorder Created",
	"data": null
}
```

###### 重复录制冲突
```json
{
	"message": "The request could not be completed due to a conflict with the current state of the resource",
	"code": "409",
	"wowzaServer": "4.7.0",
	"success": false
}
```

###### 参数值异常
```json
{
	"wowzaServer": "4.7.0",
	"code": 400,
	"success": false,
	"message": "The request could not be understood by the server due to malformed syntax"
}
```