package com.bokecc.cloud.wowza.sdk;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.common.Digests;
import com.bokecc.cloud.wowza.constant.AppNamesConsts;
import com.bokecc.cloud.wowza.constant.HttpConsts;
import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.enums.*;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import com.bokecc.cloud.wowza.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static com.bokecc.cloud.wowza.api.WowzaApi.*;
/**
 * Wowza SDK interface implements class, all of request processed in this
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaSDK implements IWowzaSDK {

    private final Logger logger = LoggerFactory.getLogger(WowzaSDK.class);

    private IHttpUtils httpUtils = new HttpUtils();

    private final String ip;
    private final String username;
    private final String password;
    /**
     * request count
     */
    public ThreadLocal<AtomicInteger> nc = new ThreadLocal<AtomicInteger>() {
        @Override
        protected AtomicInteger initialValue() {
            AtomicInteger atomicInteger = new AtomicInteger();
            atomicInteger.set(0);
            return atomicInteger;
        }
    };

    /**
     * Wowza response auth header(WWW Authenticate) value info
     */
    private ThreadLocal<String> wwwAuthenticate = new ThreadLocal<>();

    /**
     * Auth info we do an request need to carry
     */
    private ThreadLocal<String> authorization = new ThreadLocal<>();

    /**
     * Contructor
     *
     * @param ip       Wowza server ip
     * @param username Wowza auth username
     * @param password Wowza auth password
     */
    public WowzaSDK(String ip, String username, String password) {

        this.ip = StringUtil.checkHttpIp(ip);
        this.username = username;
        this.password = password;

    }


    /**
     * Request wowza expand api by developer, with port 8087 <br>
     * This belong to common process ways
     *
     * @param httpMethod
     * @param wowzaReqApi
     * @param params
     * @param json
     *
     * @return
     */
    private JSONObject wowzaApi(String httpMethod, String wowzaReqApi, Map<String, Object> wowzaApiMap,
        Map<String, Object> params,
        String json) {
        String wowzaApiPath = WowzaUtils.initWowzaApi(wowzaReqApi, wowzaApiMap);
        String path = StringUtil.convertStringPath(ip + WowzaCommonEnum.WOWZA_SELF_API_PORT.getValue(), wowzaApiPath, params);
        return this.doProcess(path, httpMethod, json);
    }

    /**
     * Request wowza expand api by develop, with port 8086
     *
     * @param httpMethod  Http request Method
     * @param wowzaReqApi Request wowza server api path
     * @param params      Request params
     * @param json        Request body
     *
     * @return Wowza response info
     */
    private JSONObject wowzaApi3rd(String httpMethod, String wowzaReqApi, Map<String, Object> wowzaApiMap,
        Map<String, Object> params,
        String json) {
        String wowzaApiPath = WowzaUtils.initWowzaApi(wowzaReqApi, wowzaApiMap);
        String path = StringUtil.convertStringPath(ip + WowzaCommonEnum.WOWZA_SDK_API_PORT.getValue(), wowzaApiPath, params);
        return this.doProcess(path, httpMethod, json);
    }

    /**
     * Request process, it can access to post, get, put, delete; You need to put some params, json etc... on it, it can
     * execute request and response result
     *
     * @param httpMethod   Http request Method
     * @param path         Request path
     * @param json         Request body
     *
     * @return Wowza response info
     */
    private JSONObject doProcess(String path, String httpMethod, String json) {
        logger.info("Wowza start to do request process");
        JSONObject result = doRequestProcess(path, httpMethod, json);
        nc.get().set(0);
        logger.info("Wowza end to do request process, doProcess method request get result {}", result);
        return result;
    }

    /**
     * Do an http request with customize http method
     *
     * @param httpMethod   Http request Method
     * @param path         Real request path
     * @param json         Request body
     *
     * @return Wowza response info
     */
    private JSONObject doRequestProcess(String path, String httpMethod, String json) {
        JSONObject result;
        switch (httpMethod) {
            case HttpConsts.POST:
                result = httpUtils.doPost(this, path, json, this.requestAuthHeader());
                break;
            case HttpConsts.PUT:
                result = httpUtils.doPut(this, path, json, this.requestAuthHeader());
                break;
            case HttpConsts.DELETE:
                result = httpUtils.doDelete(this, path, this.requestAuthHeader());
                break;
            default:
                result = httpUtils.doGet(this, path, this.requestAuthHeader());
                break;
        }
        return result;
    }

    @Override
    public ServiceResponse addPublisher(String json) {
        logger.info(" Wowza start to publish ");
        ServiceResponse response = new ServiceResponse();
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        JSONObject result = this.wowzaApi(HttpConsts.POST, PUBLISHER, wowzaApiMap, null, json);
        logger.info(" Wowza publish end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse retrievesRecorderStatus(String appName, String recorderName) {
        logger.info(" Wowza start to publish ");
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        wowzaApiMap.put(WowzaRequestParamEnum.L_APP_NAME_R.getValue(), appName);
        wowzaApiMap.put(WowzaRequestParamEnum.L_RECORDER_NAME_R.getValue(), recorderName);
        ServiceResponse response = ParamsCheckUtils.isParamsEmpty(wowzaApiMap);
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result =
            this.wowzaApi(HttpConsts.GET, RETRIEVES_RECORD_STATUS_BY_WOWZA_SELF, wowzaApiMap, null,
                                   null);
        logger.info(" Wowza publish end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse retrievesStreamInfo(String appName, String streamName) {
        logger.info(" Wowza start to find stream info ");
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        wowzaApiMap.put(WowzaRequestParamEnum.L_APP_NAME_R.getValue(), appName);
        wowzaApiMap.put(WowzaRequestParamEnum.L_STREAM_NAME_R.getValue(), streamName);
        ServiceResponse response = ParamsCheckUtils.isParamsEmpty(wowzaApiMap);
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result = this.wowzaApi(HttpConsts.GET, RETRIEVES_STREAM_INFO, wowzaApiMap, null, null);
        logger.info(" Wowza stream info end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse retrivesApplications() {
        logger.info(" Wowza start to get applications info");
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        ServiceResponse response = ParamsCheckUtils.isParamsEmpty(wowzaApiMap);
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result = this.wowzaApi(HttpConsts.GET, APPLICATIONS, wowzaApiMap, null, null);
        logger.info(" Wowza stream info end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse retrivesApplications(String json) {
        logger.info(" Wowza start to get applications info ");
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        ServiceResponse response = ParamsCheckUtils.isParamsEmpty(wowzaApiMap);
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result = this.wowzaApi(HttpConsts.POST, APPLICATIONS, wowzaApiMap, null, json);
        logger.info(" Wowza stream info end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse getStreamInfo(String appName) {
        logger.info(" Wowza start to get applications info which contains stream info list ");
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        wowzaApiMap.put(WowzaRequestParamEnum.L_APP_NAME_R.getValue(), appName);
        ServiceResponse response = ParamsCheckUtils.isParamsEmpty(wowzaApiMap);
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result =
            this.wowzaApi(HttpConsts.GET, APPLICATIONS_BY_INSTANCE_NAME, wowzaApiMap, null, null);
        logger.info(" Wowza stream info end, result is {} ", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse recordStart(String appName, String streamName,
        RecordJson json) {
        logger.info("Wowza start to record, streaName is {}", streamName);
        ServiceResponse response = record(streamName, appName, WowzaRequestParamValueEnum.RECORD_ACTION_START.getValue(), json);
        logger.info("Wowza start record streamName is {}, result is {}", response.toString());
        return response;
    }

    @Override
    public ServiceResponse recordStartHoster(String streamName, RecordJson json) {
        logger.info("Wowza start to record, streaName is {}", streamName);
        ServiceResponse response = record(streamName, AppNamesConsts.HOST, WowzaRequestParamValueEnum.RECORD_ACTION_START.getValue(), json);
        logger.info("Wowza start record streamName is {}, result is {}", response.toString());
        return response;
    }

    @Override
    public ServiceResponse recordStartNonHoster(String streamName, RecordJson json) {
        logger.info("Wowza start to record, streaName is {}", streamName);
        ServiceResponse response = recordWowzaSelf(streamName, AppNamesConsts.ORIGIN, json, RECORD_BY_WOWZA_SELF, HttpConsts.POST);
        logger.info("Wowza start record streamName is {}, result is {}", response.toString());
        return response;
    }

    @Override
    @Deprecated
    public ServiceResponse recordStartByWowzaSelf(String appName, String streamName, RecordJson json) {
        logger.info("Wowza start to record, streaName is {}", streamName);
        ServiceResponse result =
            recordWowzaSelf(streamName, appName, json, RECORD_BY_WOWZA_SELF, HttpConsts.POST);
        logger.info("Wowza start record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    @Deprecated
    public ServiceResponse recordStopByWowzaSelf(String appName,
        String streamName) {
        logger.info("Wowza start to record, streaName is %s", streamName);
        ServiceResponse result = recordWowzaSelf(streamName, appName, null, STOP_RECORD_BY_WOWZA_SELF,
                                                 HttpConsts.PUT);
        logger.info("Wowza start record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    /**
     * Record common request
     *
     * @param streamName Stream name
     * @param appName    Application name
     * @param json       Request body
     *
     * @return Wowza response info
     */
    private ServiceResponse recordWowzaSelf(String streamName, String appName,
        RecordJson json, String wowzaReqApi, String httpMethod) {
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        wowzaApiMap.put(WowzaRequestParamEnum.L_APP_NAME_R.getValue(), appName);
        wowzaApiMap.put(WowzaRequestParamEnum.L_STREAM_NAME_R.getValue(), streamName);
        ServiceResponse response = ParamsCheckUtils
            .isParamsEmpty(wowzaApiMap, WowzaRequestParamEnum.L_SERVER_NAME_R.getValue(),
                                 WowzaRequestParamEnum.L_VHOST_NAME_R.getValue(),
                                 WowzaRequestParamEnum.L_INSTANCE_NAME_R.getValue());
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result = this.wowzaApi(httpMethod, wowzaReqApi, wowzaApiMap, null, json == null ? null : json.toJson());
        logger.info("Wowza record precess end streamName is {}, result is {}", streamName, result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    /**
     * Record common request
     *
     * @param streamName Stream name
     * @param appName    Application name
     * @param action     Action like start or stop
     * @param json       Request body
     *
     * @return Wowza response info
     */
    private ServiceResponse record(String streamName, String appName, String action,
        RecordJson json) {
        Map<String, Object> paramsMap = new HashMap<>(3);
        paramsMap.put(WowzaRequestParamEnum.ACTION.getValue(), action);
        paramsMap.put(WowzaRequestParamEnum.STREAM_NAME.getValue(), streamName);
        paramsMap.put(WowzaRequestParamEnum.APP_NAME.getValue(), appName);
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        ServiceResponse response =
            ParamsCheckUtils.isParamsEmpty(paramsMap, WowzaRequestParamEnum.ACTION.getValue());
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result = this.wowzaApi3rd(HttpConsts.POST, RECORD, wowzaApiMap, paramsMap, json == null ? null : json.toJson());
        logger.info("Wowza record precess end streamName is {}, result is {}", streamName, result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse recordPauseHoster(String streamName) {
        logger.info("Wowza stop to record, streaName is {}", streamName);
        ServiceResponse result =
            record(streamName, AppNamesConsts.HOST, WowzaRequestParamValueEnum.RECORD_ACTION_PAUSE.getValue(), null);
        logger.info("Wowza start record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    public ServiceResponse recordStop(String appName, String streamName) {
        logger.info("Wowza stop to record, streaName is {}", streamName);
        ServiceResponse result =
            record(streamName, appName, WowzaRequestParamValueEnum.RECORD_ACTION_STOP.getValue(), null);
        logger.info("Wowza start record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    public ServiceResponse recordStopHoster(String streamName) {
        logger.info("Wowza stop to record, streaName is {}", streamName);
        ServiceResponse result =
            record(streamName, AppNamesConsts.HOST, WowzaRequestParamValueEnum.RECORD_ACTION_STOP.getValue(), null);
        logger.info("Wowza stop record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    public ServiceResponse recordStopNoneHoster(String streamName) {
        logger.info("Wowza stop to record, streaName is {}", streamName);
        ServiceResponse result = recordWowzaSelf(streamName, AppNamesConsts.ORIGIN, null, STOP_RECORD_BY_WOWZA_SELF,
                                                 HttpConsts.PUT);
        logger.info("Wowza stop record streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    public ServiceResponse banLiveRoom(String streamName) {
        logger.info("Wowza ban live room start, streaName is {}", streamName);
        ServiceResponse result =
            banOrUnbanLiveRoom(streamName, WowzaRequestParamValueEnum.BAN_LIVE_ROOM_ACTION.getValue());
        logger.info("Wowza ban live room end, streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    @Override
    public ServiceResponse unbanLiveRoom(String streamName) {
        logger.info("Wowza unban live room start, streaName is {}", streamName);
        ServiceResponse result =
            banOrUnbanLiveRoom(streamName, WowzaRequestParamValueEnum.UNBAN_LIVE_ROOM_ACTION.getValue());
        logger.info("Wowza unban live room end, streamName is {}, result is {}", streamName, result.toString());
        return result;
    }

    /**
     * Ban or unban common request
     *
     * @param streamName Stream name
     * @param action     Action like start or stop
     *
     * @return Wowza response info
     */
    private ServiceResponse banOrUnbanLiveRoom(String streamName,
        String action) {
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put(WowzaRequestParamEnum.ACTION.getValue(), action);
        paramsMap.put(WowzaRequestParamEnum.STREAM_NAME_LOW_CASE.getValue(), streamName);
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        ServiceResponse response = ParamsCheckUtils
            .isParamsEmpty(paramsMap, WowzaRequestParamEnum.STREAM_NAME_LOW_CASE.getValue(),
                                 WowzaRequestParamEnum.L_VHOST_NAME_R.getValue());
        if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
            return response;
        }
        JSONObject result =
            this.wowzaApi3rd(HttpConsts.GET, BAN_LIVE_ROOM, wowzaApiMap, paramsMap, null);
        logger.info("Wowza record precess end, streaName is {}, result is {}", streamName, result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    @Override
    public ServiceResponse livingSwitchHoster(String streamName, HostIndexEnum index) {
        logger.info("Wowza host node start to publish");
        ServiceResponse result =
            hostPublish(streamName, index, HostActionEnum.SWITCH);
        logger.info("Wowza host node result {}", result);
        return result;
    }

    @Override
    public ServiceResponse livingStartHoster(String streamName, HostIndexEnum index) {
        logger.info(" Wowza host node start to publish ");
        ServiceResponse result = hostPublish(streamName, index, HostActionEnum.START);
        logger.info(" Wowza host node result {}", result.toJson());
        return result;
    }

    @Override
    public ServiceResponse livingStopHoster(String streamName) {
        logger.info(" Wowza host node stop to publish ");
        ServiceResponse result =
            hostPublish(streamName, null, HostActionEnum.STOP);
        logger.info(" Wowza host node result {}", result);
        return result;
    }

    /**
     * Host publish common request, now contains start, stop, switch operations
     *
     * @param streamName Stream name
     * @param index      Hoster stream index（主持人流序号）
     * @param action     Action like start or stop
     *
     * @return Wowza response info
     */
    private ServiceResponse hostPublish(String streamName, HostIndexEnum index, HostActionEnum action) {
        ServiceResponse response = new ServiceResponse();
        Map<String, Object> paramsMap = new HashMap<>(3);
        if (action != null) {
            paramsMap.put(WowzaRequestParamEnum.ACTION.getValue(), action.getValue());
        }
        paramsMap.put(WowzaRequestParamEnum.NAME.getValue(), streamName);
        if (index != null) {
            paramsMap.put(WowzaRequestParamEnum.HOST_INDEX.getValue(), index.getValue());
        } else if (action != null){
            if (action == HostActionEnum.START || action == HostActionEnum.SWITCH) {
                String info = "Wowza SDK, host Publish params index cannot be null";
                logger.info(info);
                response.setCode(ResponseEnum.INVALID_PARAM.getCode());
                response.setMessage(info);
                return response;
            } else if (action == HostActionEnum.STOP) {
                response = ParamsCheckUtils.isParamsEmpty(paramsMap, WowzaRequestParamEnum.HOST_INDEX.getValue());
                if (response != null && ResponseEnum.INVALID_PARAM.getCode().equals(response.getCode())) {
                    return response;
                }
            }
        }
        Map<String, Object> wowzaApiMap = WowzaUtils.getWowzaApiMap();
        JSONObject result = this.wowzaApi3rd(HttpConsts.GET, HOST_PUBLISH, wowzaApiMap, paramsMap, null);
        logger.info("Wowza record precess end, result is {}", result == null ? "null" : result.toJSONString());
        response.setData(result);
        return response;
    }

    /**
     * Set request header : set Wowza Authticate header、 request return json info、no cache
     *
     * @return Request auth header with map
     */
    public Map<String, String> requestAuthHeader() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpEnum.ACCEPT_HEADER.getValue(), HttpEnum.ACCEPT_TYPE_JSON_URL.getValue());
        headerMap.put(HttpEnum.CONTENT_TYPE_HEADER.getValue(), HttpEnum.CONTENT_TYPE_JSON_URL.getValue());
        headerMap.put(HttpEnum.CACHE_CONTROL_HEADER.getValue(), HttpEnum.CACHE_CONTROL_TYPE_NO_CACHE.getValue());
        headerMap.put(HttpEnum.CONNECTION_HEADER.getValue(), HttpEnum.CONNECTION_TYPE_KEEP_ALIVE.getValue());
        headerMap.put(HttpEnum.AUTHORIZATION_HEADER.getValue(), authorization.get());
        return headerMap;
    }

    /**
     * Generate Wowza authentication
     *
     * @param uri        Request entire uri
     * @param httpMethod Request method
     *
     * @return Authorization value which request need to carry
     */
    public String getAuthorization(String uri, String httpMethod) {
        String temp =
            wwwAuthenticate.get().replaceFirst(WowzaAuthEnum.WWW_AUTHENTICATE.getValue(), JointerEnum.EMPTY.getValue())
                .trim().replaceFirst(
                WowzaAuthEnum.DIGEST.getValue(), JointerEnum.EMPTY.getValue()).trim()
                .replaceAll(WowzaCommonEnum.MD5.getValue(), WowzaCommonEnum.MD5_VALUE.getValue());
        String json = StringUtil.withdrawJson(wwwAuthenticate.get());

        JSONObject jsonObject = JSONObject.parseObject(json);
        String cnonce = Digests.generateSaltV2(8);
        //认证的次数,第一次是1，第二次是2...
        String ncstr = (WowzaAuthEnum.NC_00000000.getValue() + nc.get().get())
            .substring(Integer.toString(nc.get().get()).length());
        String algorithm = jsonObject.getString(WowzaAuthEnum.ALGORITHM.getValue());
        String qop = jsonObject.getString(WowzaAuthEnum.QOP.getValue());
        String nonce = jsonObject.getString(WowzaAuthEnum.NONCE.getValue());
        String realm = jsonObject.getString(WowzaAuthEnum.REALM.getValue());

        String response = Digests.httpDigestMD5(username, realm, password,
                                                nonce, ncstr, cnonce, qop,
                                                httpMethod, uri, algorithm);
        return this.buildAuthinfo(temp, uri, ncstr, cnonce, response);
    }

    /**
     * Assemble response authorization
     *
     * @param temp     wwwAuthenticate value temporary
     * @param uri      Entire http url like ip + port + path + param
     * @param ncstr    Wowza server return fixed string we need to carray when request
     * @param cnonce   Wowza server return fixed string we need to carray when request
     * @param response The value we generate by wowza server response string
     *
     * @return Authorization value we assembled
     */
    private String buildAuthinfo(String temp, String uri, String ncstr, String cnonce, String response) {
        StringBuilder stringBuilder = new StringBuilder();
        //组成响应authorization
        stringBuilder.append(WowzaAuthEnum.DIGEST_USERNAME.getValue()).append(JointerEnum.EQUAL.getValue())
            .append(JointerEnum.DOUBLE_QUOTE.getValue())
            .append(username).append(JointerEnum.DOUBLE_QUOTE.getValue()).append(JointerEnum.COMMA.getValue())
            .append(temp).append(JointerEnum.COMMA.getValue())
            .append(WowzaAuthEnum.URI.getValue()).append(JointerEnum.EQUAL.getValue())
            .append(JointerEnum.DOUBLE_QUOTE.getValue())
            .append(uri).append(JointerEnum.DOUBLE_QUOTE.getValue()).append(JointerEnum.COMMA.getValue())
            .append(WowzaAuthEnum.NC.getValue()).append(JointerEnum.EQUAL.getValue())
            .append(JointerEnum.DOUBLE_QUOTE.getValue())
            .append(ncstr).append(JointerEnum.DOUBLE_QUOTE.getValue()).append(JointerEnum.COMMA.getValue())
            .append(WowzaAuthEnum.CNONCE.getValue()).append(JointerEnum.EQUAL.getValue())
            .append(JointerEnum.DOUBLE_QUOTE.getValue())
            .append(cnonce).append(JointerEnum.DOUBLE_QUOTE.getValue()).append(JointerEnum.COMMA.getValue())
            .append(WowzaAuthEnum.RESPONSE.getValue()).append(JointerEnum.EQUAL.getValue())
            .append(JointerEnum.DOUBLE_QUOTE.getValue())
            .append(response).append(JointerEnum.DOUBLE_QUOTE.getValue());
        String authorizationStr = stringBuilder.toString();
        authorization.set(authorizationStr);
        return authorizationStr;
    }

    public ThreadLocal<String> getWwwAuthenticate() {
        return wwwAuthenticate;
    }

    public void setWwwAuthenticate(ThreadLocal<String> wwwAuthenticate) {
        this.wwwAuthenticate = wwwAuthenticate;
    }

    public ThreadLocal<String> getAuthorization() {
        return authorization;
    }

    public void setAuthorization(ThreadLocal<String> authorization) {
        this.authorization = authorization;
    }
}
