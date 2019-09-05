#### 停止录制API（老接口—Wowza自身录制接口）
##### 接口源码
```java
    /**
     * 停止录制: Wowza自身api</br>
     * Wowza start to stop stream specified
     *
     * @param serverName Server name, default _defaultServer_
     * @param vHostName  VHost name, default _defaultVHost_
     * @param streamName Stream name
     * @param appName    Application name
     * @param json       Request body json data with map
     *
     * @return Wowza response info
     */
    String stopRecordByWowzaSelfWithPost(String serverName, String vHostName, @NotNull String appName, String instanceName, @NotNull String streamName,
        Map<String, Object> json);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|serverName|false|Server name, default _defaultServer_|
|vHostName|false|VHost name, default _defaultVHost_|
|appName|true|Application name, cannot be null|
|instanceName|false|Application instance name, default value is _definst_|
|streamName|true|Stream name, cannot be null|
|json|false|Request body json data with map|



##### 返回结果

###### 鉴权失败
```json
{
	"code": 401,
	"success": false,
	"message": "The request requires user authentication"
}
```

###### 停止录制成功
```json
{
	"success": true,
	"message": "Recording (B1440C8354A952D49C33DC5901307461_record_transcode) stopped",
	"data": null
}
```

###### 停止录制冲突
```json
{
	"message": "The server has not found anything matching the request URI",
	"code": "404",
	"wowzaServer": "4.7.0",
	"success": false
}
```


###### 必要参数空异常
```json
{
	"message": "Request record failed because postData||streamName||appName is null.",
	"code": "602",
	"success": false
}
```

###### Wowza unsupported operaiton IO error
```json
{
	"message": "Can't read wowza's reply: UnsupportedOperationException",
	"code": "601",
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