package com.bokecc.cloud.wowza.httpclient;

import com.bokecc.cloud.wowza.config.HttpClientPoolConfig;
import com.bokecc.cloud.wowza.constant.HttpClientPoolConst;
import com.bokecc.cloud.wowza.handler.HttpRequestRetryHandler;
import com.bokecc.cloud.wowza.utils.HttpUtils;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

/**
 * Http重试机制处理类
 * <p><em>Copyright:</em> All Rights Reserved</p>
 * <p><em>Company:</em> 创盛视联数码科技（北京）有限公司   https://www.bokecc.com/</p>
 *
 * @author Daniel Zhou / zzx
 **/
public abstract class BaseHttpClient {

	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

	/**
	 * 连接管理器
	 */
	private static PoolingHttpClientConnectionManager pool;
	/**
	 * 请求配置
	 */
	protected static RequestConfig requestConfig;
	protected static CloseableHttpClient httpClient;

	static {
		try {
		    SSLContextBuilder builder = new SSLContextBuilder();
		    builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
		    SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
		        builder.build());
		    // 配置同时支持 HTTP 和 HTTPS
		    Registry<ConnectionSocketFactory> socketFactoryRegistry =
		        RegistryBuilder.<ConnectionSocketFactory> create().register(
					"http", PlainConnectionSocketFactory.getSocketFactory()).register(
		            "https", sslsf).build();
		    // 初始化连接管理器
		    pool = new PoolingHttpClientConnectionManager(
		        socketFactoryRegistry);
		    // 将最大连接数增加到20000，从配置文件中读取这个值
		    pool.setMaxTotal(HttpClientPoolConfig.getValue(HttpClientPoolConst.MAX_TOTAL));
		    // 设置最大路由
		    pool.setDefaultMaxPerRoute(HttpClientPoolConfig.getValue(HttpClientPoolConst.DEFAULT_MAX_PER_ROUTE));
		    // 根据默认超时限制初始化requestConfig
		    int socketTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.SOCKET_TIMEOUT);
		    int connectTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.CONNECT_TIMEOUT);
		    int connectionRequestTimeout = HttpClientPoolConfig.getValue(HttpClientPoolConst.CONNECTION_REQUEST_TIMEOUT);
		    requestConfig = RequestConfig.custom().setConnectionRequestTimeout(
		        connectionRequestTimeout).setSocketTimeout(socketTimeout).setConnectTimeout(
		        connectTimeout).build();
			httpClient = HttpClientBuilder.create()
				.setConnectionManager(pool)
				.setRetryHandler(new HttpRequestRetryHandler())
				.disableCookieManagement()
				.build();
		} catch (NoSuchAlgorithmException e) {
		    logger.error("static code block, error message is {}", e.getMessage());
		} catch (KeyStoreException e) {
		    logger.error("static code block, error message is {}", e.getMessage());
		} catch (KeyManagementException e) {
		    logger.error("static code block, error message is {}", e.getMessage());
		}

		/** 设置请求超时时间 */
		requestConfig = RequestConfig.custom().setSocketTimeout(50000).setConnectTimeout(50000)
		    .setConnectionRequestTimeout(50000).build();
	}
}