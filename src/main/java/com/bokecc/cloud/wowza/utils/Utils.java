package com.bokecc.cloud.wowza.utils;

import com.bokecc.cloud.wowza.enums.WowzaCommonEnum;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Utils {

    private static final Log log = LogFactory.getLog(Utils.class);

    public static <T> String toJSON(T data) {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        if (null != data) {
            try {
                json = mapper.writeValueAsString(data);
            } catch (JsonProcessingException e) {
                log.warn("Operation failure [toJSON]!", e);
            }
        }
        return json;
    }

    public static String toMD5(String src, boolean shortMD5) {
        String md5Str = "";

        if (isNotBlank(src)) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                try {
                    md.update(src.getBytes(WowzaCommonEnum.UTF8.getValue()));
                } catch (UnsupportedEncodingException e) {
                    log.error(e.getMessage());
                }
                byte b[] = md.digest();
                int i;

                StringBuilder buf = new StringBuilder();

                for (int offset = 0; offset < b.length; offset++) {
                    i = b[offset];
                    if (i < 0) {
                        i += 256;
                    }
                    if (i < 16) {
                        buf.append("0");
                    }
                    buf.append(Integer.toHexString(i));
                }

                md5Str = shortMD5 ? buf.toString().substring(8, 24) : buf.toString();
            } catch (NoSuchAlgorithmException e) {
                log.warn("Operation failure [toMD5]!", e);
            }
        }

        return md5Str;
    }

    public static String base64Encode(String src) {
        String b64Str = "";

        if (isNotBlank(src)) {
            b64Str = Base64.getEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
        }

        return b64Str;
    }

    public static String base64UrlEncode(String src) {
        String b64Str = "";

        if (isNotBlank(src)) {
            b64Str = Base64.getUrlEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
        }

        return b64Str;
    }

    public static String base64MimeEncode(String src) {

        String b64Str = "";

        if (isNotBlank(src)) {
            b64Str = Base64.getMimeEncoder().encodeToString(src.getBytes(StandardCharsets.UTF_8));
        }

        return b64Str;
    }

    public static String base64Decode(String src) {
        String b64Str = "";

        if (isNotBlank(src)) {
            byte[] b64Bytes = Base64.getDecoder().decode(src.getBytes(StandardCharsets.UTF_8));
            b64Str = new String(b64Bytes, StandardCharsets.UTF_8);
        }

        return b64Str;
    }

    public static String base64UrlDecode(String src) {
        String b64Str = "";

        try {
            if (isNotBlank(src)) {
                byte[] b64Bytes = Base64.getUrlDecoder().decode(src.getBytes("utf-8"));
                b64Str = new String(b64Bytes, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            log.warn("Operation failure [base64Decode]!", e);
        }

        return b64Str;
    }

    public static String base64MimeDecode(String src) {
        String b64Str = "";

        try {
            if (isNotBlank(src)) {
                byte[] b64Bytes = Base64.getMimeDecoder().decode(src.getBytes("utf-8"));
                b64Str = new String(b64Bytes, "utf-8");
            }
        } catch (UnsupportedEncodingException e) {
            log.warn("Operation failure [base64Decode]!", e);
        }

        return b64Str;
    }

    public static String hidePassword(String src) {
        String hidePwdStr = "******";

        if (isNotBlank(src)) {
            int srcLen = src.length();

            if (srcLen > 6) {
                int hidePwdSum = srcLen - 2;

                String displayStr = src.substring(hidePwdSum, srcLen);

                hidePwdStr = repeatStr("*", hidePwdSum) + displayStr;
            }
        }

        return hidePwdStr;
    }

    public static String repeatStr(String str, int repeat) {
        StringBuilder sb = new StringBuilder();

        if (isNotBlank(str)) {
            for (int i = 0; i < repeat; i++) {
                sb.append(str);
            }
        }

        return sb.toString();
    }

    public static String getEnv(String key) {
        String value = "";
        if (isNotBlank(key)) {
            value = System.getenv(key.trim());
        }
        return value.trim();
    }

    public static boolean isBlank(String src) {
        return null == src || src.trim().isEmpty();
    }

    public static boolean isNotBlank(String src) {
        return null != src && ! src.trim().isEmpty();
    }
}
