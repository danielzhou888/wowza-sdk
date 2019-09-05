
Release v2.0.1
> 1. 支持对所有的异常，都统一返回response（状态码，message），如资源关闭失败，参数不能为空等返回值
2. 支持多实例、单实例进行初始化sdk
3. 支持将请求传递的json body转成对象传递，如RecordJson对象（维护了所有json中各个字段，但只需要初始化个别动态字段，如baseFile）
4. 支持将方法中必要参数抽离，独立出新方法（如之前统一使用recordStart(String appName, String serverName, Stream streamName)，通过appName区分主持人还是非主持人模式，现在使用新方法，recordStartNonHoster(String streamName), recordStartHoster(String streamName)）
5. 支持鉴权二次请求使用同一HttpClient连接

Release v2.0.2
> 1. 对HttpClient连接池进行优化，解决单个路由挂载线程数量过少导致的请求阻塞问题
> 2. 取消单实例调用初始化sdk实例默认用户密码值