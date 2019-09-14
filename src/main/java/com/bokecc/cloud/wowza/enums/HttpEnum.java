package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum  HttpEnum {

    /** 常用的请求方式 */
    GET("GET"),
    POST("POST"),
    DELETE("DELETE"),
    PUT("PUT"),
    AUTHORIZATION_HEADER("Authorization"),
    CONTENT_TYPE_HEADER("Content-Type"),
    CONTENT_TYPE_TEXT_HTML("text/xml"),
    CONTENT_TYPE_FORM_URL("application/x-www-form-urlencoded"),
    CONTENT_TYPE_JSON_URL("application/json;charset=utf-8"),
    ACCEPT_HEADER("accept"),
    ACCEPT_TYPE_JSON_URL("application/json;charset=utf-8"),
    ACCEPT_TYPE_ALL_URL("*/*"),
    CONNECTION_HEADER("connection"),
    CONNECTION_TYPE_KEEP_ALIVE("connection"),
    CACHE_CONTROL_HEADER("Cache-Control"),
    CACHE_CONTROL_TYPE_NO_CACHE("no-cache"),
    CHARSET_UTF_8("utf-8"),
    HTTP("http://"),
    HTTPS("https://");

    private String value;

    HttpEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
