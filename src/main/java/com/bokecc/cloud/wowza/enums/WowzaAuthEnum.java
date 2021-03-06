package com.bokecc.cloud.wowza.enums;

/**
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 *
 * @author Daniel Zhou / zzx
 **/
public enum WowzaAuthEnum {

    /** Wowza认证请求body */
    WWW_AUTHENTICATE("WWW-Authenticate"),
    DIGEST("Digest"),
    NC_00000000("00000000"),
    ALGORITHM("algorithm"),
    QOP("qop"),
    NONCE("nonce"),
    REALM("realm"),
    DIGEST_USERNAME("Digest username"),
    URI("uri"),
    NC("nc"),
    CNONCE("cnonce"),
    RESPONSE("response");


    private String value;

    WowzaAuthEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
