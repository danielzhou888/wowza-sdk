#### Retrieves Applications list API
##### GET请求
```java
    /**
     * Retrieves the list of Applications for the specifed vhost<br> Http Method : GET
     *
     * @return Response info
     */
    ServiceResponse retrivesApplications();
```
##### 请求参数
> 请求方式：GET

|传参|是否必传|备注|
|:--|:--|:--|


##### POST请求

```java
    /**
     * Retrieves the list of Applications for the specifed vhost<br> Http Method : POST
     *
     * @param json       Request post data json info
     *
     * @return Response info
     */
    ServiceResponse retrivesApplications(Map<String, Object> json);
```

##### 请求参数
> 请求方式：POST

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

###### 返回成功
```json
{
	"code": 0,
	"data": {
		"serverName": "_defaultServer_",
		"applications": [{
			"id": "class",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/class",
			"appType": "Live",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": false,
			"streamTargetsEnabled": true
		}, {
			"id": "host",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/host",
			"appType": "Live",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": true,
			"streamTargetsEnabled": true
		}, {
			"id": "live",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/live",
			"appType": "Live",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": false,
			"streamTargetsEnabled": false
		}, {
			"id": "origin",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/origin",
			"appType": "Live",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": true,
			"streamTargetsEnabled": false
		}, {
			"id": "src",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/src",
			"appType": "LiveEdge",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": false,
			"streamTargetsEnabled": false
		}, {
			"id": "vod",
			"href": "/v2/servers/_defaultServer_/vhosts/_defaultVHost_/applications/vod",
			"appType": "VOD",
			"dvrEnabled": false,
			"drmEnabled": false,
			"transcoderEnabled": false,
			"streamTargetsEnabled": false
		}]
	},
	"msg": "OK"
}
```