package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum WowzaRequestParamEnum {

    /** Wowza Request Parameter */
    L_SERVER_NAME_R("{serverName}"),
    L_VHOST_NAME_R("{vhostName}"),
    L_STREAM_NAME_R("{streamName}"),
    L_ACTION_R("{action}"),
    L_APP_NAME_R("{appName}"),
    L_INSTANCE_NAME_R("{instanceName}"),
    L_RECORDER_NAME_R("{recorderName}"),
    SERVER_NAME("serverName"),
    VHOST_NAME("vhostName"),
    STREAM_NAME("streamName"),
    NAME("name"),
    STREAM_NAME_LOW_CASE("streamname"),
    ACTION("action"),
    APP_NAME("appName"),
    INSTANCE_NAME("instanceName"),
    HOST_INDEX("index"),
    STREAM_NAME_TRANSCODE_SUFFIX("_host_transcode");


    private String value;

    WowzaRequestParamEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
