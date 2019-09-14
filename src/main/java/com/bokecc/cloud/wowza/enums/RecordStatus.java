package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 * @author Daniel Zhou / zzx
 *
 **/
public enum  RecordStatus {
    /** 录制状态 */
    RECORD_IN_PROGRESS(1, "Recording in Progress"),
    WAITING_FOR_STREAM(2, "Waiting for stream");

    private Integer code;

    private String msg;

    RecordStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
