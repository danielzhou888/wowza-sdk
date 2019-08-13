package com.bokecc.cloud.wowza.enums;

/**
 * 主持人执行开始、切换、停止直播枚举
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum HostActionEnum {

    /** 主持人槽值 */
    START("start"),
    SWITCH("switch"),
    STOP("stop");

    private String value;

    HostActionEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
