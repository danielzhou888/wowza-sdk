package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum WowzaCommonEnum {

    /** WowzaCommonEnum */
    MD5("MD5"),
    UTF8("UTF-8"),
    MD5_MESS("MD5-mess"),
    MD5_VALUE("\"MD5\""),
    WOWZA_SELF_API_PORT(":8087"),
    WOWZA_SDK_API_PORT(":8086");

    private String value;

    WowzaCommonEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
