package com.bokecc.cloud.wowza.common;

import com.bokecc.cloud.wowza.enums.WowzaCommonEnum;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Http Digest MD5
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> Java猿社区</p>
 * @author Daniel Zhou / zzx
 *
 **/
public class Digests {

    private static final Logger logger = LoggerFactory.getLogger(Digests.class);

    private static SecureRandom random = new SecureRandom();

    /**
     * 加密遵循RFC2671规范 将相关参数加密生成一个MD5字符串,并返回
     * @param username  Wowza server username
     * @param realm     Wowza response realm params, usually a fixed value like server domain name
     * @param password  Wowza server password
     * @param nonce     Wowza response a fixed string
     * @param nc        Request wowza api count string, it's length 8
     * @param cnonce    A dynamic value generated by Wowza SDK
     * @param qop       Wowza response a fixed string
     * @param method    Request method to wowza server
     * @param uri       Request uri
     * @param algorithm Wowza response a fixed string
     * @return MD5 string
     */
    public static String httpDigestMD5(String username, String realm, String password,
        String nonce, String nc, String cnonce, String qop,
        String method, String uri, String algorithm) {
        logger.info(" Http digest md5 start to run");
        String HA1, HA2;
        if ("MD5-sess".equals(algorithm)) {
            HA1 = ha1Md5Sess(username, realm, password, nonce, cnonce);
        } else {
            HA1 = ha1Md5(username, realm, password);
        }
        byte[] md5Byte = new byte[0];
        try {
            md5Byte = md5(HA1.getBytes(WowzaCommonEnum.UTF8.getValue()));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        HA1 = new String(Hex.encodeHex(md5Byte));

        try {
            md5Byte = md5(HA2(method, uri).getBytes(WowzaCommonEnum.UTF8.getValue()));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        HA2 = new String(Hex.encodeHex(md5Byte));

        String original = HA1 + ":" + (nonce + ":" + nc + ":" + cnonce + ":" + qop) + ":" + HA2;

        try {
            md5Byte = md5(original.getBytes(WowzaCommonEnum.UTF8.getValue()));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        String result = new String(Hex.encodeHex(md5Byte));
        logger.info(" Http digest md5 result {} ", result);
        return result;
    }

    /**
     * algorithm值为MD5时规则
     * @param username  Wowza server username
     * @param realm     Wowza response realm params, usually a fixed value like server domain name
     * @param password  Wowza server password
     * @return
     */
    private static String ha1Md5(String username, String realm, String password) {
        return username + ":" + realm + ":" + password;
    }

    /**
     * algorithm值为MD5-sess时规则
     * @param username  Wowza server username
     * @param realm     Wowza response realm params, usually a fixed value like server domain name
     * @param password  Wowza server password
     * @param nonce     Wowza response a fixed string
     * @param cnonce    A dynamic value generated by Wowza SDK
     * @return
     */
    private static String ha1Md5Sess(String username, String realm, String password, String nonce, String cnonce) {
        //      MD5(username:realm:password):nonce:cnonce
        String s = ha1Md5(username, realm, password);
        byte[] md5Byte = new byte[0];
        try {
            md5Byte = md5(s.getBytes(WowzaCommonEnum.UTF8.getValue()));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        String smd5 = new String(Hex.encodeHex(md5Byte));
        return smd5 + ":" + nonce + ":" + cnonce;
    }

    /**
     * 生成method:uri规则
     * @param method Http method
     * @param uri    uri
     * @return
     */
    private static String HA2(String method, String uri) {
        return method + ":" + uri;
    }

    /**
     * 对输入字符串进行md5散列
     * @param input Input bytes
     * @return Md5 bytes
     */
    public static byte[] md5(byte[] input) {
        return digest(input, "MD5", null, 1);
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法
     * @param input      Input bytes
     * @param algorithm  Wowza response a fixed string
     * @param salt       Salt bytes
     * @param iterations iterations
     * @return Digest bytes
     */
    private static byte[] digest(byte[] input, String algorithm, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(algorithm);
            if (salt != null) {
                digest.update(salt);
            }
            byte[] result = digest.digest(input);

            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 随机生成numBytes长度数组
     * @param numBytes num length
     * @return Bytes array
     */
    @Deprecated
    public static byte[] generateSaltV1(int numBytes) {
        Validate.isTrue(numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", (long)numBytes);
        byte[] bytes = new byte[numBytes];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 随机生成length长度字符串数组
     * @param length 生成字符串长度
     * @return String
     */
    public static String generateSaltV2(int length) {
        StringBuffer val = new StringBuffer();
        Random random = new Random();
        //参数length，表示生成几位随机数
        for(int i = 0; i < length; i++) {
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";
            //输出字母还是数字
            if( "char".equalsIgnoreCase(charOrNum) ) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2)%2 == 0 ? 65 : 97;
                val.append((char)(random.nextInt(26) + temp));

            } else if( "num".equalsIgnoreCase(charOrNum) ) {
                val.append(String.valueOf(random.nextInt(10)));
            }
        }
        return val.toString().toLowerCase();
    }

}