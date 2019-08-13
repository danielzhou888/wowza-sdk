package com.bokecc.cloud.wowza.utils;

import com.bokecc.cloud.wowza.sdk.IWowzaSDK;
import com.bokecc.cloud.wowza.sdk.WowzaSDK;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WowzaSDK初始化，多实例调用
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaSdkMultiFactory {

    private static final Map<String, IWowzaSDK> WowzaSDKMap = new ConcurrentHashMap<>();

    public static void init(String ip, String username, String password) {
        IWowzaSDK WowzaSDK = new WowzaSDK(ip, username, password);
        WowzaSDKMap.put(ip, WowzaSDK);
    }

    public static IWowzaSDK getInstance(String ip) {
        return WowzaSDKMap.get(ip);
    }
}
