##### 接口源码
```java
    /**
     * Retrieves the specifed Stream Recorder
     *
     * @param appName      Application name
     * @param recorderName Stream name during record
     *
     * @return Response info
     */
    ServiceResponse retrievesRecorderStatus(@NotNull String appName, @NotNull String recorderName);
```
##### 请求参数
> 请求方式：POST

|传参|是否必传|备注|
|:--|:--|:--|
|appName|true|Application name, cannot be null|
|recorderName|true|Recorder stream name, cannot be null|



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

###### 查询录制状态成功
```json
{
	"code": 0,
	"data": {
		"serverName": "_defaultServer_",
		"recorderName": "B1440C8354A952D49C33DC5901307461_record_transcode",
		"instanceName": "_definst_",
		"recorderState": "Recording in Progress",
		"defaultRecorder": false,
		"segmentationType": "None",
		"outputPath": "/var/www/dream/webroot/flv",
		"baseFile": "B1440C8354A952D49C33DC5901307461-5.flv",
		"fileFormat": "FLV",
		"fileVersionDelegateName": "com.wowza.wms.livestreamrecord.manager.StreamRecorderFileVersionDelegate",
		"fileTemplate": "${BaseFileName}_${RecordingStartTime}_${SegmentNumber}",
		"segmentDuration": 165784,
		"segmentSize": 10485760,
		"segmentSchedule": "0 * * * * *",
		"recordData": true,
		"startOnKeyFrame": false,
		"splitOnTcDiscontinuity": false,
		"backBufferTime": 8000,
		"option": "Append to existing file",
		"moveFirstVideoFrameToZero": true,
		"currentSize": 230757814,
		"currentDuration": 165784,
		"currentFile": "/var/www/dream/webroot/flv/B1440C8354A952D49C33DC5901307461-5.flv",
		"recordingStartTime": "2019-06-07-18.15.28.934-+08:00",
		"timeScale": 90000
	},
	"msg": "OK"
}
```

###### 查询录制状态失败
```json
{
	"code": 20010017,
	"data": {
		"wowzaServer": "4.7.0",
		"code": 20010017,
		"success": false,
		"message": "The server has not found anything matching the request URI"
	},
	"msg": "The server has not found anything matching the request URI"
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