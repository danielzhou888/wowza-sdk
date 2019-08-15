package com.bokecc.cloud.wowza.httpclient;

import com.bokecc.cloud.wowza.handler.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public abstract class HttpClientBase {

	/**
	 * 连接管理器
	 */
	private static PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;
	/**
	 * 请求配置
	 */
	private static RequestConfig requestConfig;
	protected static CloseableHttpClient httpClient;
	
	static{
		poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
		poolingHttpClientConnectionManager.setMaxTotal(1024);
		poolingHttpClientConnectionManager.setDefaultMaxPerRoute(32);
		// 检测有效连接的间隔
		poolingHttpClientConnectionManager.setValidateAfterInactivity(2000);
		SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(1000*5).build();
		poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
		httpClient = HttpClientBuilder.create()
								.setConnectionManager(poolingHttpClientConnectionManager)
								.disableCookieManagement()
								.setRetryHandler(new HttpRequestRetryHandler())
								.build();

		/** 设置请求超时时间 */
		requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000)
		    .setConnectionRequestTimeout(10000).build();
	}
}