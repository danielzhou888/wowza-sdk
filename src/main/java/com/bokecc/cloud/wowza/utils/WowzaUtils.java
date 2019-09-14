package com.bokecc.cloud.wowza.utils;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.enums.WowzaRequestParamEnum;
import com.bokecc.cloud.wowza.enums.WowzaRequestParamValueEnum;
import com.bokecc.cloud.wowza.result.ServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 此工具用于处理Wowza常见的Map、String等
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 * @author Daniel Zhou / zzx
 **/
public class WowzaUtils {

    private static final Logger logger = LoggerFactory.getLogger(WowzaUtils.class);

    /**
     * Assemble serverName and vHostName to wowzaApi url
     *
     * @param wowzaApi    Wowza api request address to be stitched
     * @param wowzaApiMap wowzaApiMap
     *
     * @return Real Wowza api request address
     */
    public static String initWowzaApi(String wowzaApi, Map<String, Object> wowzaApiMap) {
        logger.info(" Wowza start init the wowza api path ");
        if (wowzaApiMap != null && wowzaApiMap.entrySet() != null) {
            Iterator<Map.Entry<String, Object>> iterator = wowzaApiMap.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> next = iterator.next();
                if (wowzaApi.contains(next.getKey())) {
                    wowzaApi = wowzaApi.replace(next.getKey(), String.valueOf(next.getValue()));
                }
            }
            logger.info(" Wowza end init the wowza api path ");
        }
        return wowzaApi.trim();
    }

    /**
     * 初始化Wowza serverName、vHostName，如果未传入数据，则默认_defaultServer_和_defaultVHost_，并返回初始化后的Map
     *
     * @param serverName   Server name
     * @param vHostName    VHost name
     * @param instanceName Instance name
     * @return Wowza api Map
     */
    public static Map<String, Object> getWowzaApiMap(String serverName, String vHostName, String instanceName) {
        Map<String, Object> wowzaApiMap = new HashMap<>();
        if (StringUtils.isEmpty(serverName)) {
            wowzaApiMap.put(WowzaRequestParamEnum.L_SERVER_NAME_R.getValue(), WowzaRequestParamValueEnum.SERVER_NAME_VALUE.getValue());
        } else {
            wowzaApiMap.put(WowzaRequestParamEnum.L_SERVER_NAME_R.getValue(), serverName);
        }
        if (StringUtils.isEmpty(vHostName)) {
            wowzaApiMap.put(WowzaRequestParamEnum.L_VHOST_NAME_R.getValue(), WowzaRequestParamValueEnum.VHOST_NAME_VALUE.getValue());
        } else {
            wowzaApiMap.put(WowzaRequestParamEnum.L_VHOST_NAME_R.getValue(), vHostName);
        }
        if (StringUtils.isEmpty(instanceName)) {
            wowzaApiMap.put(WowzaRequestParamEnum.L_INSTANCE_NAME_R.getValue(), WowzaRequestParamValueEnum.INSTANCE_NAME_VALUE.getValue());
        } else {
            wowzaApiMap.put(WowzaRequestParamEnum.L_INSTANCE_NAME_R.getValue(), instanceName);
        }
        return wowzaApiMap;
    }

    /**
     * 初始化Wowza serverName、vHostName，如果未传入数据，则默认_defaultServer_和_defaultVHost_，并返回初始化后的Map
     *
     * @return Wowza api Map
     */
    public static Map<String, Object> getWowzaApiMap() {
        return getWowzaApiMap(null, null, null);
    }

}
