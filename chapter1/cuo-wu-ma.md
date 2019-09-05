### 错误码

```json
{
	"code": 20010016,
	"data": {
		"code": "20010016"
		"message": "The request requires user authentication",
		"wowzaServer": "4.7.0",
		"success": false
	},
	"msg": "The request requires user authentication"
}
```


|api|Code|备注|
|:--|:--|:--|
|allApi|0|OK|
|allApi|20010001|FAIL|
|allApi|20010002|Request param [param] cannot be null|
|allApi|20010003|Username or password wrong|
|allApi|20010004|Request IO exception|
|allApi|20010005|Close resource exception|
|allApi|20010006|Result parse exception|
|allApi|20010007|Request json body cannot be null|
|allApi|20010008|Request is processing, please do not make an duplicate request|
|allApi|20010009|Request time out|
|allApi|20010010|Callback function object is null|
|allApi|20010011|Stream not exists|
|allApi|20010012|Audio stream not exists|
|allApi|20010013|Request param action is invalid|
|allApi|20010014|The request could not be understood by the server due to malformed syntax（400）|
|allApi|20010016|The request requires user authentication（401）|
|allApi|20010017|The server has not found anything matching the request URI（404）|
|allApi|20010018|The request could not be completed due to a conflict with the current state of the resource（409）|
|record|20020000|Record error|
|record|20020001|Record operation error|
|record|20020002|The request param postData、streamName、appName cannot be null|
|record|20020003|Record stream is null|
|record|20020004|Hoster not published|
|host switch|20030000|Same index, host publishing now|
|host switch|20030001|Param stream name is invalid|
|host switch|20030002|Invalid param index, action switch need index|
|host switch|20030003|Hoster is published already, please do not make an duplicate request|
|host switch|20030004|Host stream is not exists|
|ban|20040000|Ban live room stream error|
|ban|20040001|Ban live room audio stream error|
|ban|20040002|Recover live stream error|
|ban|20040003|Recover live audio stream error|
|ban|20040004|Live room is publishing now, please do not make an duplicate recover request|