package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum WowzaRequestParamValueEnum {

    /** Wowza Request Parameter Value */
    SERVER_NAME_VALUE("_defaultServer_"),
    VHOST_NAME_VALUE("_defaultVHost_"),
    INSTANCE_NAME_VALUE("_definst_"),
    RECORD_ACTION_START("start"),
    RECORD_ACTION_PAUSE("pause"),
    RECORD_ACTION_STOP("stop"),
    HOST_PUBLISH_ACTION_START("start"),
    HOST_PUBLISH_ACTION_STOP("stop"),
    HOST_PUBLISH_ACTION_SWITCH("switch"),
    BAN_LIVE_ROOM_ACTION("ban"),
    UNBAN_LIVE_ROOM_ACTION("recover");

    private String value;

    WowzaRequestParamValueEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
