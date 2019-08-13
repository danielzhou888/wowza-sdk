package com.bokecc.cloud.wowza.utils;

import com.bokecc.cloud.wowza.sdk.IWowzaSDK;
import com.bokecc.cloud.wowza.sdk.WowzaSDK;

/**
 * WowzaSDK初始化，单实例调用
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaSdkSingleFactory {

    private volatile static IWowzaSDK WowzaSDK;
    private static String ip;
    private static String username;
    private static String password;

    public static void init(String ip, String username, String password) {
        WowzaSdkSingleFactory.ip = ip;
        WowzaSdkSingleFactory.username = username;
        WowzaSdkSingleFactory.password = password;
        WowzaSDK = new WowzaSDK(ip, username, password);
    }

    public static IWowzaSDK getInstance() {
        return WowzaSDK;
    }
}
