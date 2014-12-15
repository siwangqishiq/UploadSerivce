package network;

import java.util.Locale;

import android.os.Build;

/**
 *网络请求
 * 
 * @author panyi
 * 
 */
public class NetClient {
	private static AsyncHttpClient client = new AsyncHttpClient();
	static {
		client.setUserAgent("Mozilla/5.0(Linux; U; Android "
				+ Build.VERSION.RELEASE
				+ "; "
				+ Locale.getDefault().getLanguage()
				+ "; "
				+ Build.MODEL
				+ ") AppleWebKit/533.0 (KHTML, like Gecko) Version/4.0 Mobile Safari/533.1");
	}

	/**
	 * 发�?�get请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void get(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.get(url, params, responseHandler);
		
	}

	/**
	 * 发�?�post请求
	 * @param url
	 * @param params
	 * @param responseHandler
	 */
	public static void post(String url, RequestParams params,
			AsyncHttpResponseHandler responseHandler) {
		client.post(url, params, responseHandler);
	}
}// end class
