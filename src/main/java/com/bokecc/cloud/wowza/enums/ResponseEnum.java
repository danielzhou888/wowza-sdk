package com.bokecc.cloud.wowza.enums;

public enum ResponseEnum {

    /** 响应返回状态码 */
    SUCCESS(0, "OK"),
    ERROR(20010001, "FAIL"),
    INVALID_PARAM(20010002, "Invalid param"),
    INVALID_USERNAME_OR_PASSWORD(20010003, "Username or password wrong"),
    REQUEST_IO_ERROR(20010004, "Request IO exception"),
    RESOURCE_CLOSE_ERROR(20010005, "Close resource exception"),
    PARSE_ERROR(20010006, "Result parse exception"),
    JSON_IS_NULL(20010007, "Request json body cannot be null");

    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
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
