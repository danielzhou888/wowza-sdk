package com.bokecc.cloud.wowza.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.config.HttpClientPoolConfig;
import com.bokecc.cloud.wowza.constant.HttpClientPoolConst;
import com.bokecc.cloud.wowza.constant.WowzaAuthConst;
import com.bokecc.cloud.wowza.enums.*;
import com.bokecc.cloud.wowza.sdk.WowzaSDK;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.net.URI;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

/**
 * <p>
 * Description: Http Utils
 * </p>
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 * @author Daniel Zhou / zzx
 *
 */
public class HttpUtils implements IHttpUtils {

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * 连接管理器
     */
    private static PoolingHttpClientConnectionManager pool;
    /**
     * 请求配置
     */
    private static RequestConfig requestConfig;
    /**
     * 请求返回json数据头信息map
     */
    private static Map<String, String> jsonheaderMap = new ConcurrentHashMap<>();

    public HttpUtils() {
    }

    static {
        jsonheaderMap.put(HttpEnum.ACCEPT_HEADER.getValue(), HttpEnum.ACCEPT_TYPE_JSON_URL.getValue());
        jsonheaderMap.put(HttpEnum.CONTENT_TYPE_HEADER.getValue(), HttpEnum.CONTENT_TYPE_JSON_URL.getValue());
        jsonheaderMap.put(HttpEnum.CACHE_CONTROL_HEADER.getValue(), HttpEnum.CACHE_CONTROL_TYPE_NO_CACHE.getValue());
        jsonheaderMap.put(HttpEnum.CONNECTION_HEADER.getValue(), HttpEnum.CONNECTION_TYPE_KEEP_ALIVE.getValue());
        try {
            logger.info(" init HttpUtils start ");
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                builder.build());
            // 配置同时支持 HTTP 和 HTTPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry =
                RegistryBuilder.<ConnectionSocketFactory> create().register(
                    "http", PlainConnectionSocketFactory.getSocketFactory()).register(
                    "https", sslsf).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(
                socketFactoryRegistry);
            // 将最大连接数增加到20000，从配置文件中读取这个值
            pool.setMaxTotal(HttpClientPoolConfig.getValue(HttpClientPoolConst.MAX_TOTAL));
            // 设置最大路由
            pool.setDefaultMaxPerRoute(HttpClientPoolConfig.getValue(HttpClientPoolConst.DEFAULT_MAX_PER_ROUTE));
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.SOCKET_TIMEOUT);
            int connectTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.CONNECT_TIMEOUT);
            int connectionRequestTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.CONNECTION_REQUEST_TIMEOUT);
            requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
                connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
                connectTimeout).build();
            logger.info(" init HttpUtils stop ");
        } catch (NoSuchAlgorithmException e) {
            logger.error("static code block, error message is {}", e.getMessage());
        } catch (KeyStoreException e) {
            logger.error("static code block, error message is {}", e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("static code block, error message is {}", e.getMessage());
        }

        /** 设置请求超时时间 */
        requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
            .setConnectionRequestTimeout(50000).build();
    }

    @Override
    public JSONObject doGet(WowzaSDK WowzaSDK, String path) {
        return doGetRequest(WowzaSDK, path, jsonheaderMap);
    }

    @Override
    public JSONObject doGet(WowzaSDK WowzaSDK, String path, Map<String, String> headers) {
        return doGetRequest(WowzaSDK, path, headers);
    }

    @Override
    public JSONObject doPost(WowzaSDK WowzaSDK, String path) {
        return doPostRequest(WowzaSDK, path, jsonheaderMap, null);
    }

    @Override
    public JSONObject doPost(WowzaSDK WowzaSDK, String path, String json) {
        return doPostRequest(WowzaSDK, path, jsonheaderMap, json);
    }

    @Override
    public JSONObject doPost(WowzaSDK WowzaSDK, String path, String json, Map<String, String> headers) {
        return doPostRequest(WowzaSDK, path, headers, json);
    }

    @Override
    public JSONObject doPut(WowzaSDK WowzaSDK, String path) {
        return doPutRequest(WowzaSDK, path, jsonheaderMap, null);
    }

    @Override
    public JSONObject doPut(WowzaSDK WowzaSDK, String path, String json) {
        return doPutRequest(WowzaSDK, path, jsonheaderMap, json);
    }

    @Override
    public JSONObject doPut(WowzaSDK WowzaSDK, String path, String json, Map<String, String> headers) {
        return doPutRequest(WowzaSDK, path, headers, json);
    }

    @Override
    public JSONObject doDelete(WowzaSDK WowzaSDK, String path) {
        return doDeleteRequest(WowzaSDK, path, jsonheaderMap);
    }

    @Override
    public JSONObject doDelete(WowzaSDK WowzaSDK, String path, Map<String, String> headers) {
        return doDeleteRequest(WowzaSDK, path, headers);
    }

    /**
     * Send an get Request
     *
     * @param httpUrl Entire http url
     * @param headers Request headers with map
     *
     * @return Reponse info
     */
    private static JSONObject doGetRequest(WowzaSDK WowzaSDK, String httpUrl, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(httpUrl);
        return doGet(WowzaSDK, httpGet, headers);
    }

    /**
     * Send an get Request
     *
     * @param httpGet HttpGet object
     * @param headers Request headers with map
     *
     * @return Reponse info
     */
    private static JSONObject doGet(WowzaSDK WowzaSDK, HttpGet httpGet, Map<String, String> headers) {
        return doHttpRequest(WowzaSDK, httpGet, headers);
    }

    /**
     * Send an get Request
     *
     * @param httpRequest httpRequest object
     * @param headers     Request headers with map
     *
     * @return Reponse info
     */
    private static JSONObject doGetOrDelete(WowzaSDK WowzaSDK, HttpRequestBase httpRequest, Map<String, String> headers) {
        CloseableHttpAsyncClient httpClient;
        // 响应内容
        JSONObject responseContent;
        // 创建默认的httpClient实例.
        httpClient = getHttpClient();
        // 配置请求信息
        httpRequest.setConfig(requestConfig);
        // 设置请求头
        if (headers != null && !headers.isEmpty()) {
            headers.forEach((k, v) -> {
                httpRequest.setHeader(k, v);
            });
        }
        // 得到响应实例
        responseContent = excuteRequest(httpClient, WowzaSDK, httpRequest);
        return responseContent;
    }

    /**
     * 执行请求<br>
     * 1. 请求鉴权分为wowza自身rest-api接口的鉴权和拓展项目的鉴权（如wowza-cc项目）<br>
     * 2. 此方法支持二次请求保持同一个HttpClient连接<br>
     * 3. 对于无鉴权请求，一次请求返回结果，对于需要鉴权的接口，第一次请求返回鉴权信息头，处理，同一连接再次请求获取数据
     * @param httpClient
     * @param WowzaSDK
     * @param httpRequest
     * @return
     * @throws IOException
     */
    private static JSONObject excuteRequest(CloseableHttpAsyncClient httpClient, WowzaSDK WowzaSDK,
        HttpRequestBase httpRequest) {
        WowzaSDK.nc.get().incrementAndGet();
        // TODO
        HttpResponse response = null;
        // CloseableHttpResponse response = null;

        JSONObject responseContent;
        try {
            httpClient.start();
            Future<HttpResponse> responseFuture = httpClient.execute(httpRequest, null);
            response = responseFuture.get();
            HttpEntity entity = response.getEntity();
            String responseContentStr = EntityUtils.toString(entity, HttpEnum.CHARSET_UTF_8.getValue());
            responseContent = formatResponseContent(responseContentStr);
            EntityUtils.consume(entity);
            URI uri = httpRequest.getURI();
            if (uri != null) {
                int port = uri.getPort();
                if (port == WowzaAuthConst.WOWZA_AUTH_PORT) {
                    responseContent = getResponseContentFromWowza(responseContent, response, httpClient, WowzaSDK, httpRequest);
                } else {
                    responseContent = getResponseContentFromWowza3rd(responseContent.toJSONString(), response, httpClient, WowzaSDK, httpRequest);
                }
            }
        } catch (IOException e) {
            logger.error("doHttpRequest run, error message is {}", e.getMessage());
            responseContent = JsonUtils.getResponseJsonBody(ResponseEnum.REQUEST_IO_ERROR.getCode(), ResponseEnum.REQUEST_IO_ERROR.getMsg(), false);
        } catch (InterruptedException e) {
            logger.error("doHttpRequest run, error message is {}", e.getMessage());
            responseContent = JsonUtils.getResponseJsonBody(ResponseEnum.INTERRUPTED_EXCEPTION.getCode(), ResponseEnum.REQUEST_IO_ERROR.getMsg(), false);
        } catch (ExecutionException e) {
            logger.error("doHttpRequest run, error message is {}", e.getMessage());
            responseContent = JsonUtils.getResponseJsonBody(ResponseEnum.EXECUTION_EXCEPTION.getCode(), ResponseEnum.REQUEST_IO_ERROR.getMsg(), false);
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                logger.error("doHttpRequest run, error message is {}", e.getMessage());
                responseContent = JsonUtils.getResponseJsonBody(ResponseEnum.RESOURCE_CLOSE_ERROR.getCode(), ResponseEnum.RESOURCE_CLOSE_ERROR.getMsg(), false);
            }
        }
        return responseContent;
    }

    /**
     * 此方法用于从wowza自身rest-api接口获取到的响应信息，对应的端口8087
     * @param responseContent
     * @param response
     * @param httpClient
     * @param WowzaSDK
     * @param httpRequest
     * @return
     * @throws IOException
     */
    private static JSONObject getResponseContentFromWowza3rd(String responseContent, HttpResponse response,
        CloseableHttpAsyncClient httpClient, WowzaSDK WowzaSDK, HttpRequestBase httpRequest)
        throws IOException, ExecutionException, InterruptedException {
        List<Header> authHeaderList = Arrays.asList(response.getHeaders(WowzaAuthEnum.WWW_AUTHENTICATE.getValue())).stream()
            .filter(h -> h.getName().trim().equals(WowzaAuthEnum.WWW_AUTHENTICATE.getValue())).collect(Collectors.toList());
        if (authHeaderList != null && authHeaderList.size() > 0) {
            responseContent = getWWWAuthenticateHeader(response);
        }
        return doRequestWithAuthHeader(responseContent, httpClient, httpRequest, WowzaSDK);
    }

    /**
     * 此方法用于从wowza-cc项目（第三方拓展）接口获取到的响应信息，对应的端口8086
     * @param responseContent
     * @param response
     * @param httpClient
     * @param WowzaSDK
     * @param httpRequest
     * @return
     * @throws IOException
     */
    private static JSONObject getResponseContentFromWowza(JSONObject responseContent,
        HttpResponse response,
        CloseableHttpAsyncClient httpClient, WowzaSDK WowzaSDK, HttpRequestBase httpRequest)
        throws IOException, ExecutionException, InterruptedException {
            if (responseContent != null && StringUtils.isNotEmpty(responseContent.getString(WowzaRespEnum.RESP_API_CODE_KEY.getValue())) && responseContent.getString(WowzaRespEnum.RESP_API_CODE_KEY.getValue())
                .equals(WowzaRespEnum.WOWZA_AUTH_ERROR_CODE.getValue())) {
                String wwwAuthenticateHeader = getWWWAuthenticateHeader(response);
                responseContent = doRequestWithAuthHeader(wwwAuthenticateHeader, httpClient, httpRequest, WowzaSDK);
                logger.info(" Wowza auth success, response code is 401, Wowza SDK init success ");
            } else {
                logger.info(" Wowza digest auth is not open ");
            }
        return responseContent;
    }

    /**
     *
     * @param responseContent
     * @param httpClient
     * @param httpRequest
     * @param WowzaSDK
     * @return
     * @throws IOException
     */
    private static JSONObject doRequestWithAuthHeader(String responseContent,
        CloseableHttpAsyncClient httpClient, HttpRequestBase httpRequest, WowzaSDK WowzaSDK)
        throws IOException, ExecutionException, InterruptedException {
        String method = httpRequest.getMethod();
        URI uri = httpRequest.getURI();
        String path = "";
        if (uri != null) {
            path = uri.getPath();
        }
        JSONObject resultJson = new JSONObject();
        if (JsonUtils.isJSONValid(responseContent)) {
            resultJson = JSONObject.parseObject(responseContent);
        }
        if (StringUtils.isNotEmpty(responseContent) && responseContent.startsWith(WowzaAuthEnum.WWW_AUTHENTICATE.getValue())) {
            ThreadLocal<String> wwwAuthenticate = WowzaSDK.getWwwAuthenticate();
            if (wwwAuthenticate != null) {
                wwwAuthenticate.set(responseContent);
            }
            String authorizationValue = WowzaSDK.getAuthorization(path, method);
            httpRequest.setHeader(HttpEnum.AUTHORIZATION_HEADER.getValue(), authorizationValue);
            Future<HttpResponse> responseFuture = httpClient.execute(httpRequest, null);
            HttpResponse response = responseFuture.get();
            // 得到响应实例
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity, HttpEnum.CHARSET_UTF_8.getValue());
            resultJson = formatResponseContent(result);
            EntityUtils.consume(entity);
        }
        return resultJson;
    }

    /**
     * Withdraw WWW-Authenticate headers value from response
     *
     * @param response CloseableHttpResponse object
     *
     * @return WWW-Authenticate headers value
     */
    private static String getWWWAuthenticateHeader(HttpResponse response) {
        Header[] headers = response.getHeaders(WowzaAuthEnum.WWW_AUTHENTICATE.getValue());
        String wwwAuthHeaderValue = "";
        if (headers != null && headers.length > 0) {
            for (int i = 0; i < headers.length; i++) {
                Header header = headers[i];
                if (header != null && header.getName().equals(WowzaAuthEnum.WWW_AUTHENTICATE.getValue())) {
                    wwwAuthHeaderValue = header.getValue();
                }
            }
        }
        logger.info("WWW-Authenticate header value : {}", wwwAuthHeaderValue);
        return WowzaAuthEnum.WWW_AUTHENTICATE.getValue() + " " + wwwAuthHeaderValue;
    }

    /**
     * Get CloseableHttpClient instance
     *
     * @return
     */
    private static CloseableHttpAsyncClient getHttpClient() {

        // CloseableHttpClient httpClient = HttpClients.custom()
        //     // 设置连接池管理
        //     .setConnectionManager(pool)
        //     // 设置请求配置
        //     .setDefaultRequestConfig(requestConfig)
        //     // 设置重试次数
        //     .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
        //     .build();

        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
            // // 设置连接池管理
            // .setConnectionManager(pool)
            // // 设置请求配置
            // .setDefaultRequestConfig(requestConfig)
            // // 设置重试次数
            // .setRetryHandler(new DefaultHttpRequestRetryHandler(0, false))
            // .build();


        return httpClient;
    }

    /**
     * Send an post request
     *
     * @param httpUrl Entire http url
     * @param json    Request body with json store in map
     * @param headers Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doPostRequest(WowzaSDK WowzaSDK, String httpUrl, Map<String, String> headers, String json) {
        HttpPost httpPost = new HttpPost(httpUrl);
        return doPost(WowzaSDK, httpPost, headers, json);
    }

    /**
     * Send an post request
     *
     * @param httpPost HttpPost object
     * @param json     Request body with json store in map
     * @param headers  Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doPost(WowzaSDK WowzaSDK, HttpPost httpPost, Map<String, String> headers, String json) {
        setHttpBody(httpPost, json);
        return doHttpRequest(WowzaSDK, httpPost, headers);
    }

    /**
     * Send an post request
     *
     * @param httpRequest HttpEntityEnclosingRequestBase object
     * @param headers     Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doHttpRequest(WowzaSDK WowzaSDK, HttpRequestBase httpRequest, Map<String, String> headers) {
        CloseableHttpAsyncClient httpClient;
        // 响应内容
        JSONObject responseContent;
        // 创建默认的httpClient实例.
        httpClient = getHttpClient();
        // 配置请求信息
        httpRequest.setConfig(requestConfig);
        // 设置请求头
        if (headers != null) {
            headers.forEach((k, v) -> {
                httpRequest.setHeader(k, v);
            });
        }
        responseContent = excuteRequest(httpClient, WowzaSDK, httpRequest);
        return responseContent;
    }

    /**
     * Format response content, set code from string to integer
     *
     * @param responseContent response result
     * @return format response result
     */
    private static JSONObject formatResponseContent(String responseContent) {
        JSONObject jsonObject;
        if (JsonUtils.isJSONValid(responseContent)) {
            jsonObject = JSONObject.parseObject(responseContent);
            if (jsonObject != null) {
                Object o = jsonObject.get(WowzaRespEnum.RESP_API_CODE_KEY.getValue());
                if (o != null && o instanceof String) {
                    try {
                        Integer i = Integer.parseInt((String)o);
                        jsonObject.put(WowzaRespEnum.RESP_API_CODE_KEY.getValue(), i);
                        return jsonObject;
                    } catch (ParseException e) {
                        logger.error("Wowza response code invalid, it's not a integer value, error message is %s", e.getMessage());
                        jsonObject = JsonUtils.getResponseJsonBody(ResponseEnum.PARSE_ERROR.getCode(), ResponseEnum.PARSE_ERROR.getMsg(), false);
                    }
                }
            }
        } else {
            jsonObject = JsonUtils.getResponseJsonBody(ResponseEnum.SUCCESS.getCode(), responseContent, true);

        }
        return jsonObject;
    }

    /**
     * Send a get request
     *
     * @param httpUrl Entire http url
     * @param json    Request body with json store in map
     * @param headers Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doPutRequest(WowzaSDK WowzaSDK, String httpUrl, Map<String, String> headers, String json) {
        HttpPut httpPut = new HttpPut(httpUrl);
        return doPut(WowzaSDK, httpPut, headers, json);
    }

    /**
     * Send an post request
     *
     * @param httpPut HttpPut Object
     * @param json    Request body with json store in map
     * @param headers Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doPut(WowzaSDK WowzaSDK, HttpPut httpPut, Map<String, String> headers, String json) {
        setHttpBody(httpPut, json);
        return doHttpRequest(WowzaSDK, httpPut, headers);
    }

    /**
     * Send an delete request
     *
     * @param httpUrl Entire http url
     * @param headers Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doDeleteRequest(WowzaSDK WowzaSDK, String httpUrl, Map<String, String> headers) {
        HttpDelete httpDelete = new HttpDelete(httpUrl);
        return doDelete(WowzaSDK, httpDelete, headers);
    }

    /**
     * Send a delete request
     *
     * @param httpDelete HttpDelete Object
     * @param headers    Request header store in Map
     *
     * @return Response info
     */
    private static JSONObject doDelete(WowzaSDK WowzaSDK, HttpDelete httpDelete, Map<String, String> headers) {
        return doGetOrDelete(WowzaSDK, httpDelete, headers);
    }

    /**
     * HttpPost、HttpPut set Http Body
     *
     * @param httpMethod HttpPut or HttpPost父类
     * @param json       键值对的Map，存储json，此处方法转成json字符串
     */
    private static void setHttpBody(HttpEntityEnclosingRequestBase httpMethod, String json) {
        if (StringUtils.isNotEmpty(json)) {
            // String paramsJson = JSONObject.toJSONString(json);
            StringEntity stringEntity = new StringEntity(json, HttpEnum.CHARSET_UTF_8.getValue());
            stringEntity.setContentType(HttpEnum.CONTENT_TYPE_JSON_URL.getValue());
            httpMethod.setEntity(stringEntity);
        }
    }

    /**
     * Return headers map used in getting response info with json result
     *
     * @return Headers Map
     */
    @Deprecated
    private static Map<String, String> headerMapGetJsonResponse() {
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put(HttpEnum.ACCEPT_HEADER.getValue(), HttpEnum.ACCEPT_TYPE_JSON_URL.getValue());
        headerMap.put(HttpEnum.CONTENT_TYPE_HEADER.getValue(), HttpEnum.CONTENT_TYPE_JSON_URL.getValue());
        headerMap.put(HttpEnum.CACHE_CONTROL_HEADER.getValue(), HttpEnum.CACHE_CONTROL_TYPE_NO_CACHE.getValue());
        headerMap.put(HttpEnum.CONNECTION_HEADER.getValue(), HttpEnum.CONNECTION_TYPE_KEEP_ALIVE.getValue());
        return headerMap;
    }

}
