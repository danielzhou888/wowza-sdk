#### 封禁直播间API
##### 接口源码
```java
    /**
     * 封禁直播间
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Response info
     */
    ServiceResponse banLiveRoom(@NotNull String streamName);
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

###### 封禁成功
```json
{
	"code": 0,
	"data": {
		"code": 0,
		"success": true,
		"message": "OK"
	},
	"message": "OK"
}
```

###### 封禁失败
```json
{
	"code": 20040000,
	"data": {
		"message": "Ban live room stream error",
		"success": false,
		"code": 20040000
	},
	"message": "Ban live room stream error"
}
```
```json
{
	"code": 20040000,
	"data": {
		"message": "Ban live room audio stream error",
		"success": false,
		"code": 20040000
	},
	"message": "Ban live room audio stream error"
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

