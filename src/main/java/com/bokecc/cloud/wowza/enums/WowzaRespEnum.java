package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum WowzaRespEnum {
    /** Wowza api 返回信息值 */
    RESP_API_CODE_KEY("code"),
    RESP_API_MESSAGE_KEY("message"),
    RESP_API_SUCCESS_KEY("success"),
    RESP_API_DATA_KEY("data"),
    WOWZA_AUTH_ERROR_CODE("401"),
    WOWZA_AUTH_ERROR_MESSAGE("The request requires user authentication");

    private String value;

    WowzaRespEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
