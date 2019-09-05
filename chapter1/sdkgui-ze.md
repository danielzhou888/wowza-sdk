### SDK规则

> 1. 开发者无需关心调用的是来自wowza自身api还是第三方插件api，在构造鉴权时，仅需传入ip、username、password证明鉴权身份，即可正常使用此sdk；
> 2. 对于鉴权失败统一返回错误码401；
> 3. 开发者在接口调用过程中，对于请求参数必传且不为null时，如传入对象仍为空，返回异常错误码
> eg：
```json
{
	"code": 20010002,
	"msg": "Request param action cannot be null"
}
```
> 4. 开发者需要根据返回错误码自行处理逻辑异常;
> 5. 开发者需要引入sdk jar包有`wowza-sdk-1.0-SNAPSHOT-sources.jar`和`wowza-sdk-1.0-SNAPSHOT-jar-with-dependencies.jar`




