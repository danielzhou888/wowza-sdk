package com.bokecc.cloud.wowza.enums;

/**
 * 主持人槽下标枚举
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum HostIndexEnum {

    /** 主持人槽值 */
    ZERO("0"),
    ONE("1"),
    TWO("2");

    private String value;

    HostIndexEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
