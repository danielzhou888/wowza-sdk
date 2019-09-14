package com.bokecc.cloud.wowza.utils;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.sdk.WowzaSDK;

import java.util.Map;

/**
 * <p>
 * Description: Http Utils
 * </p>
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 */
public interface IHttpUtils {

    /**
     * Send an get request
     *
     * @param path   Wowza api path
     *
     * @return Response result
     */
    JSONObject doGet(WowzaSDK WowzaSDK, String path);

    /**
     * Send an get request and customize heaers
     *
     * @param path    Wowza api path
     * @param headers Request headers
     *
     * @return Response result
     */
    JSONObject doGet(WowzaSDK WowzaSDK, String path, Map<String, String> headers);

    /**
     * Send an post request
     *
     * @param path   Wowza api path
     *
     * @return Response result
     */
    JSONObject doPost(WowzaSDK WowzaSDK, String path);

    /**
     * Send an post request
     *
     * @param path   Wowza api path
     * @param json   Request body with map
     *
     * @return Response result
     */
    JSONObject doPost(WowzaSDK WowzaSDK, String path, String json);

    /**
     * Send an post request and customize headers
     *
     * @param path    Wowza api path
     * @param json    Request body with map
     * @param headers Headers map
     *
     * @return Response result
     */
    JSONObject doPost(WowzaSDK WowzaSDK, String path, String json, Map<String, String> headers);

    /**
     * Send an put request
     *
     * @param path   Wowza api path
     *
     * @return Response result
     */
    JSONObject doPut(WowzaSDK WowzaSDK, String path);

    /**
     * Send an put request
     *
     * @param path   Wowza api path
     * @param json   Request body
     *
     * @return Response result
     */
    JSONObject doPut(WowzaSDK WowzaSDK, String path, String json);

    /**
     * Send an put request
     *
     * @param path    Wowza api path
     * @param json    Request body
     * @param headers Headers map
     *
     * @return Response result
     */
    JSONObject doPut(WowzaSDK WowzaSDK, String path, String json, Map<String, String> headers);

    /**
     * Send an delete request
     *
     * @param path   Wowza api path
     *
     * @return Response result
     */
    JSONObject doDelete(WowzaSDK WowzaSDK, String path);

    /**
     * Send an delete request
     *
     * @param path    Wowza api path
     * @param headers Headers map
     *
     * @return Response result
     */
    JSONObject doDelete(WowzaSDK WowzaSDK, String path, Map<String, String> headers);

}
