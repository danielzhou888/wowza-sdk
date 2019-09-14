package com.bokecc.cloud.wowza.api;

/**
 * <p>
 * Wowza api path consts
 * </p>
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaApi {

    /**
     * 用户鉴权
     */
    public static final String AUTHENTICATION = "/";

    /**
     * 新录制
     */
    public static final String RECORD = "/record";
    /**
     * 录制（Wowza自身pai）
     */
    public static final String RECORD_BY_WOWZA_SELF =
        "/v2/servers/{serverName}/vhosts/{vhostName}/applications/{appName}/instances/{instanceName}/streamrecorders/{streamName}";
    /**
     * 停止录制（Wowza自身pai）
     */
    public static final String STOP_RECORD_BY_WOWZA_SELF =
        "/v2/servers/{serverName}/vhosts/{vhostName}/applications/{appName}/instances/{instanceName}/streamrecorders/{streamName}/actions/stopRecording";
    /**
     * 查询指定流名称录制状态（Wowza自身pai）
     */
    public static final String RETRIEVES_RECORD_STATUS_BY_WOWZA_SELF =
        "/v2/servers/{serverName}/vhosts/{vhostName}/applications/{appName}/instances/{instanceName}/streamrecorders/{recorderName}";

    /**
     * 主持人直播—直播
     */
    public static final String HOST_PUBLISH = "/host";

    /**
     * 解封禁直播间
     */
    public static final String BAN_LIVE_ROOM = "/ban";

    /**
     * publisher
     */
    public static final String PUBLISHER = "/v2/servers/{serverName}/publishers";
    // public static final String PUBLISHER = "/v2/servers/_defaultServer_/publishers";

    /**
     * applications
     */
    public static final String APPLICATIONS = "/v2/servers/{serverName}/vhosts/{vhostName}/applications";
    public static final String APPLICATIONS_BY_INSTANCE_NAME =
        "/v2/servers/{serverName}/vhosts/{vhostName}/applications/{appName}/instances/{instanceName}";

    public static final String RETRIEVES_STREAM_INFO = "/v2/servers/{serverName}/vhosts/{vhostName}/applications/{appName}/instances/{instanceName}/incomingstreams/{streamName}";
}
