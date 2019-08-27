package com.bokecc.cloud.wowza.utils;

import com.bokecc.cloud.wowza.sdk.IWowzaSDK;
import com.bokecc.cloud.wowza.sdk.WowzaSDK;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * WowzaSDK初始化
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public class WowzaSdkFactory {

    private static final Map<String, IWowzaSDK> wowzaSDKMap = new ConcurrentHashMap<>();

    public static IWowzaSDK getInstance(String ip, String username, String password) {
        if (!wowzaSDKMap.containsKey(ip)) {
            synchronized (WowzaSdkFactory.class) {
                if (!wowzaSDKMap.containsKey(ip)) {
                    IWowzaSDK wowzaSDK = new WowzaSDK(ip, username, password);
                    wowzaSDKMap.put(ip, wowzaSDK);
                    return wowzaSDK;
                }
            }
        }
        return wowzaSDKMap.get(ip);
    }
}
