package com.bokecc.cloud.wowza.utils;

import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.enums.HttpEnum;
import com.bokecc.cloud.wowza.enums.JointerEnum;
import com.bokecc.cloud.wowza.enums.WowzaAuthEnum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 字符串操作工具类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 * @author Daniel Zhou / zzx
 *
 **/
public class StringUtil {

    private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

    /**
     * Concat ip + path + param to an entire http url
     *
     * @param ip     Wowza ip
     * @param path   Wowza api path
     * @param params Request params like a1 equals a1 and b1 equals b1 store with key value ini map
     *
     * @return Path info
     */
    public static String convertStringPath(String ip, String path, Map<String, Object> params) {
        StringBuilder var1 = new StringBuilder();
        if (StringUtils.isEmpty(ip)) {
            logger.error(" Wowza ip cannot be null ");
            return null;
        }
        if (StringUtils.isEmpty(path)) {
            logger.error(" Wowza path cannot be null ");
            return null;
        }
        var1.append(ip).append(path);
        return convertStringPath(var1.toString(), params);
    }

    /**
     * Concat ip + path + param to an entire http url
     *
     * @param path   Wowza api path
     * @param params Request params like a1 equals a1 and b1 equals b1 store with key value init map
     *
     * @return Path info
     */
    public static String convertStringPath(String path, Map<String, Object> params) {
        StringBuilder var1 = new StringBuilder();

        if (StringUtils.isEmpty(path)) {
            logger.error(" Wowza path cannot be null ");
            return null;
        }
        var1.append(path);
        // if (params == null) {
        //     params = new HashMap<>();
        // }
        if (params != null) {
            var1.append(JointerEnum.QUESTION.getValue()).append(StringUtil.convertStringParamter(params));
        }
        return var1.toString();
    }

    /**
     * Convert request params with map
     *
     * @param parameterMap Request params with map
     *
     * @return Request params with string
     */
    public static String convertStringParamter(Map<String, Object> parameterMap) {
        StringBuffer parameterBuffer = new StringBuffer();
        String key;
        Object value;
        if (parameterMap != null) {
            Iterator iterator = parameterMap.entrySet().iterator();
            while (iterator.hasNext()) {

                Map.Entry entry = (Map.Entry)iterator.next();
                value = entry.getValue();
                key = String.valueOf(entry.getKey());
                if (value == null) {
                    value = JointerEnum.EMPTY.getValue();
                }
                parameterBuffer.append(key).append(JointerEnum.EQUAL.getValue()).append(value);
                if (iterator.hasNext()) {
                    parameterBuffer.append(JointerEnum.AND.getValue());
                }
            }
        }
        return parameterBuffer.toString();
    }

    /**
     * 将返回的Authrization信息转成json
     *
     * @param authorization authorization info
     *
     * @return 返回authrization json格式数据
     */
    public static String withdrawJson(String authorization) {
        String temp = authorization.replaceFirst(WowzaAuthEnum.WWW_AUTHENTICATE.getValue(), JointerEnum.EMPTY.getValue()).trim().replaceFirst(WowzaAuthEnum.DIGEST.getValue(), JointerEnum.EMPTY.getValue()).trim()
            .replaceAll(JointerEnum.DOUBLE_QUOTE.getValue(), JointerEnum.EMPTY.getValue());
        String[] split = temp.split(JointerEnum.COMMA.getValue());
        Map<String, String> map = new HashMap<>();
        Arrays.asList(split).forEach(c -> {
            String c1 = c.replaceFirst(JointerEnum.EQUAL.getValue(), JointerEnum.COLON.getValue());
            String[] split1 = c1.split(JointerEnum.COLON.getValue());
            map.put(split1[0].trim(), split1[1].trim());
        });
        return JSONObject.toJSONString(map);
    }

    /**
     * Check ip contains http or https or not, if not exist http or https, add http to id as default value
     * @param ip  Request ip
     * @return Request ip contains http or https
     */
    public static String checkHttpIp(String ip) {
        StringBuilder ipTemp = new StringBuilder();
        if (ip.contains(HttpEnum.HTTP.getValue()) || ip.contains(HttpEnum.HTTPS.getValue())) {
            return ip;
        } else {
            ipTemp.append(HttpEnum.HTTP.getValue()).append(ip);
        }
        return ipTemp.toString();
    }
}
