package com.bokecc.cloud.wowza.sdk;

import com.bokecc.cloud.wowza.entity.RecordJson;
import com.bokecc.cloud.wowza.enums.HostIndexEnum;
import com.bokecc.cloud.wowza.result.ServiceResponse;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Wowza SDK interface
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public interface IWowzaSDK {

    /**
     * 获取当前application下所有流信息 <br>
     * Retrieves the specified Application Instance information <br>
     *
     * @param appName  Application name, cannot be null
     *
     * @return Current application stream info
     */
    ServiceResponse getStreamInfo(@NotNull String appName);

    /**
     * 通用开始录制：Wowza插件开发<br>
     * 新录制接口<br>
     * 用户需要指定appName来区分主持人模式或非主持人模式，可使用recordStartHoster或recordStartNonHoster来替代此方法
     *
     * @param streamName stream name, cannot be null
     * @param appName    application name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    @Deprecated
    ServiceResponse recordStart(@NotNull String appName, @NotNull String streamName, @NotNull RecordJson json);

    /**
     * 开始录制：Wowza插件开发<br>
     * 新录制接口<br>
     * 主持人模式开始录制
     * Wowza start to record stream specified
     *
     * @param streamName stream name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    ServiceResponse recordStartHoster(@NotNull String streamName, @NotNull RecordJson json);

    /**
     * 非主持人模式开始录制<br>
     * 新录制接口<br>
     * Wowza start to record stream specified
     *
     * @param streamName stream name, cannot be null
     * @param json       Request body json data with map
     *
     * @return Response info
     */
    ServiceResponse recordStartNonHoster(@NotNull String streamName, @NotNull RecordJson json);

    /**
     * 主持人模式暂停录制<br>
     * 新录制接口<br>
     * Wowza start to pause stream specified
     *
     * @param streamName stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordPauseHoster(@NotNull String streamName);

    /**
     * 通用停止录制: Wowza插件开发 <br>
     * 可通过传递appName来对主持人和非主持人模式进行暂停录制，可使用 recordStopHoster或 recordStopNoneHoster替代
     * Wowza start to stop stream specified
     *
     * @param appName    Application name, cannot be null
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStop(@NotNull String appName, @NotNull String streamName);

    /**
     * 主持人模式停止录制<br>
     * 新录制接口<br>
     * Wowza start to stop stream specified
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStopHoster(@NotNull String streamName);

    /**
     * 非主持人模式停止录制<br>
     * 新录制接口<br>
     * Wowza start to stop stream specified
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse recordStopNoneHoster(@NotNull String streamName);

    /**
     * 开始录制, Wowza 自身api <br> Wowza start to record stream specified <br>
     *
     * @param streamName   Stream name, cannot be null
     * @param appName      Application name
     * @param json         Request body json data with map
     *
     * @return Response info
     */
    @Deprecated
    ServiceResponse recordStartByWowzaSelf(@NotNull String appName, @NotNull String streamName, @NotNull RecordJson json);

    /**
     * 停止录制: Wowza自身api<br> Wowza start to stop stream specified
     *
     * @param streamName   Stream name
     * @param appName      Application name
     *
     * @return Wowza response info
     */
    @Deprecated
    ServiceResponse recordStopByWowzaSelf(@NotNull String appName, @NotNull String streamName);

    /**
     * 主持人开始直播 <br>
     * Wowza start to publish the stream of host <br>
     *
     * @param streamName Stream name, cannot be null
     * @param index      Hoster stream index（主持人流序号）, cannot be null
     *
     * @return Wowza response info
     */
    ServiceResponse livingStartHoster(@NotNull String streamName, @NotNull HostIndexEnum index);

    /**
     * 主持人停止直播
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Response info
     */
    ServiceResponse livingStopHoster(@NotNull String streamName);

    /**
     * 主持人切换主讲 <br>
     * Host app switch current published stream
     *
     * @param streamName Stream name, cannot be null
     * @param index      Host publish stream index, such as {streamName}_0, {streamName}_1, cannot be null
     *
     * @return Response info
     */
    ServiceResponse livingSwitchHoster(@NotNull String streamName, @NotNull HostIndexEnum index);

    /**
     * 封禁直播间
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Response info
     */
    ServiceResponse banLiveRoom(@NotNull String streamName);

    /**
     * 解除封禁直播间
     *
     * @param streamName Stream name, cannot be null
     *
     * @return Response info
     */
    ServiceResponse unbanLiveRoom(@NotNull String streamName);

    /**
     * Retrieves the list of Applications for the specifed vhost<br> Http Method : GET
     *
     * @return Response info
     */
    ServiceResponse retrivesApplications();

    /**
     * Retrieves the list of Applications for the specifed vhost<br>
     * Http Method : POST
     *
     * @param json       Request post data json info
     *
     * @return Response info
     */
    ServiceResponse retrivesApplications(String json);

    /**
     * Adds a new Publisher to the list
     *
     * @param json       Request post data json info
     *
     * @return Response info
     */
    ServiceResponse addPublisher(String json);

    /**
     * Retrieves the specifed Stream Recorder
     *
     * @param appName      Application name
     * @param recorderName Stream name during record
     *
     * @return Response info
     */
    ServiceResponse retrievesRecorderStatus(@NotNull String appName, @NotNull String recorderName);

    /**
     * Retrieves the specifed Stream Recorder
     *
     * @param appName      Application name
     * @param streamName   Stream name
     *
     * @return Response info
     */
    ServiceResponse retrievesStreamInfo(@NotNull String appName, @NotNull String streamName);

}
