#### 主持人切换主讲API
##### 接口源码
```java
    /**
     * 主持人切换主讲 <br>
     * Host app switch current published stream
     *
     * @param streamName Stream name, cannot be null
     * @param index      Host publish stream index, such as {streamName}_0, {streamName}_1, cannot be null
     *
     * @return Response info
     */
    ServiceResponse livingSwitchHoster(@NotNull String streamName, @NotNull HostIndexEnum index);
```
##### 请求参数
> 请求方式：GET

|传参|是否必传|备注|
|:--|:--|:--|
|streamName|true|Stream name, cannot be null|
|index|true|Hoster stream index（主持人流序号）, cannot be null|

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

###### 切换主持人成功
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

###### 非法参数
```json
{
	"code": 20010013,
	"data": {
		"message": "Request param action is invalid",
		"code": 20010013,
		"success": false
	},
	"message": "Request param action is invalid"
}
```
```json
{
	"code": 20030001,
	"data": {
		"message": "Param stream name is invalid",
		"code": 20030001,
		"success": false
	},
	"message": "Param stream name is invalid"
}
```
```json
{
	"code": 20030002,
	"data": {
		"message": "invalid param index, action switch need index",
		"code": 20030002,
		"success": false
	},
	"message": "invalid param index, action switch need index"
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

