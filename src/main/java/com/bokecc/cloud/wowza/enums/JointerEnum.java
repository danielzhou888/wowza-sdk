package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum JointerEnum {

    /** 常用的字符串连接符 */
    EQUAL("="),
    AND("&"),
    QUESTION("?"),
    COLON(":"),
    COMMA(","),
    DOUBLE_QUOTE("\""),
    BACK_SLASH("\\"),
    FORWARD_SLASH("/"),
    EMPTY(""),
    LEFT_BIG_BRACKETS("{"),
    RIGHT_BIG_BRACKETS("}");

    private String value;

    JointerEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
