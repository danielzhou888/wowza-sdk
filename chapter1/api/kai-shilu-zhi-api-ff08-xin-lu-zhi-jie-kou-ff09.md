#### 开始录制API（新录制接口）
##### 通用开始录制接口源码
```java
    /**
     * 通用开始录制：Wowza插件开发<br>
     * 新录制接口<br>
     * 用户需要指定appName来区分主持人模式或非主持人模式，可使用recordStartHoster或recordStartNonHoster来替代此方法
     *
     * @param streamName stream name, cannot be null
     * @param appName    application name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    ServiceResponse recordStart(@NotNull String appName, @NotNull String streamName, @NotNull RecordJson json);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|
|streamName|true|Stream name, cannot be null|
|json|false|Request body json data with map， cannot be null|

----------------------------------------
##### 主持人开始录制接口源码
```java
    /**
     * 开始录制：Wowza插件开发<br>
     * 新录制接口<br>
     * 主持人模式开始录制
     * Wowza start to record stream specified
     *
     * @param streamName stream name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    ServiceResponse recordStartHoster(@NotNull String streamName, @NotNull RecordJson json);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|streamName|true|Stream name, cannot be null|
|json|false|Request body， cannot be null|


----------------------------------------

##### 非主持人开始录制接口源码

```java
    /**
     * 非主持人模式开始录制<br>
     * 新录制接口<br>
     * Wowza start to record stream specified
     *
     * @param streamName stream name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    ServiceResponse recordStartNonHoster(@NotNull String streamName, @NotNull RecordJson json);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|streamName|true|Stream name, cannot be null|
|json|false|Request body， cannot be null|




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

###### 录制成功
```json
{
	"code": 0,
	"data": {
		"success": true,
		"message": "Recorder Created"
	},
	"msg": "OK"
}
```

###### 重复录制冲突
```json
{
	"code": 20010018,
	"data": {
		"message": "The request could not be completed due to a conflict with the current state of the resource",
		"code": 20010018,
		"wowzaServer": "4.7.0",
		"success": false
	},
	"msg": "The request could not be completed due to a conflict with the current state of the resource"
}
```


###### 必要参数空异常
```json
{
	"code": 20020002,
	"data": {
		"message": "Request record failed because postData||streamName||appName is null",
		"code": 20020002,
		"success": false
	},
	"msg": "Request record failed because postData||streamName||appName is null"
}
```

###### 录制请求超时
```json
{
	"code": 20010009,
	"data": {
		"message": "Request timeout",
		"code": 20010009,
		"success": false
	},
	"msg": "Request timeout"
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