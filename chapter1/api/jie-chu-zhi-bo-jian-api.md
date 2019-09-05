#### 解除直播间API
##### 接口源码
```java
    /**
     * 解除封禁直播间
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Response info
     */
    ServiceResponse unbanLiveRoom(@NotNull String streamName);
```

##### 请求参数

> 请求方式：GET

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

###### 解封成功
```json
{
	"code": 0,
	"data": {
		"code": 0,
		"success": true,
		"message": "OK"
	},
	"msg": "OK"
}
```

###### 解封失败
```json
{
	"code": 20040002,
	"data": {
		"message": "Recover live stream error",
		"success": false,
		"code": 20040002
	},
	"message": "Recover live stream error"
}
```
```json
{
	"code": 20040003,
	"data": {
		"message": "Recover live audio stream error",
		"success": false,
		"code": 20040003
	},
	"message": "Recover live audio stream error"
}
```
```json
{
	"code": 20040004,
	"data": {
		"message": "Live room is publishing now, please do not make an duplicate recover request",
		"success": false,
		"code": 20040004
	},
	"message": "Live room is publishing now, please do not make an duplicate recover request"
}
```

###### 非法参数
```json
{
	"code": 20010013,
	"data": {
		"code": 20010013,
		"success": false,
		"message": "Request param action is invalid"
	},
	"message": "Request param action is invalid"
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

