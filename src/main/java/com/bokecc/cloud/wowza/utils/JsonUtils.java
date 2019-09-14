package com.bokecc.cloud.wowza.utils;

import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.bokecc.cloud.wowza.enums.ResponseEnum;
import com.bokecc.cloud.wowza.enums.WowzaRespEnum;
import org.apache.commons.lang3.StringUtils;

/**
 * Json数据处理工具
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 * @author Daniel Zhou / zzx
 *
 **/
public class JsonUtils {

    /**
     * Judge the string is json format string or not
     * @param string  Json string
     * @return result
     */
    public static boolean isJSONValid(String string) {
        try {
            if (StringUtils.isEmpty(string)) {
                return false;
            }
            JSONObject.parseObject(string);
        } catch (JSONException ex) {
            return false;
        }
        return true;
    }

    /**
     * 返回响应json体
     * @param code
     * @param message
     * @return
     */
    public static JSONObject getResponseJsonBody(Integer code, String message, boolean success) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(WowzaRespEnum.RESP_API_CODE_KEY.getValue(), code);
        jsonObject.put(WowzaRespEnum.RESP_API_MESSAGE_KEY.getValue(), message);
        jsonObject.put(WowzaRespEnum.RESP_API_SUCCESS_KEY.getValue(), success);
        return jsonObject;
    }
}
