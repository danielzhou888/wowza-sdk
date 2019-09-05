#### Adds a new Publisher to the list API
##### 接口源码
```java
    /**
     * Adds a new Publisher to the list
     *
     * @param json       Request post data json info
     *
     * @return Response info
     */
    ServiceResponse addPublisher(Map<String, Object> json);
```

##### 请求参数

> 请求方式：GET

|传参|是否必传|备注|
|:--|:--|:--|
|json|false|Request body json data with map|


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


###### publisher conflict
```json
{
	"code": 20010018,
	"data": {
		"wowzaServer": "4.7.0",
		"code": 20010018,
		"success": false,
		"message": "The request could not be completed due to a conflict with the current state of the resource"
	},
	"msg": "The request could not be completed due to a conflict with the current state of the resource"
}
```

###### publisher success
```json
{
	"code": 0,
	"data": {
		"success": true,
		"message": "",
		"data": null
	},
	"msg": "OK"
}
```
