package cn.xm.yss;

import java.io.IOException;
import java.net.URLDecoder;
//import java.util.logging.Logger;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class HttpUtils {
	private static HttpUtils utils;

	private HttpUtils() {
	}

	public static HttpUtils getInstance() {
		if (utils == null) {
			utils = new HttpUtils();
		}
		return utils;
	}

	// private static Logger logger =
	// LoggerFactory.getlog(HttpRequestUtils.class); //日志记录

	/**
	 * httpPost
	 * 
	 * @param url
	 *            路径
	 * @param jsonParam
	 *            参数
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam) {
		return httpPost(url, jsonParam, false);
	}

	/**
	 * post请求
	 * 
	 * @param url
	 *            url地址
	 * @param jsonParam
	 *            参数
	 * @param noNeedResponse
	 *            不需要返回结果
	 * @return
	 */
	public static JSONObject httpPost(String url, JSONObject jsonParam, boolean noNeedResponse) {
		// post请求返回结果
		DefaultHttpClient httpClient = new DefaultHttpClient();
		JSONObject jsonResult = null;
		HttpPost method = new HttpPost(url);
		try {
			if (null != jsonParam) {
				// 解决中文乱码问题
				StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");
				entity.setContentEncoding("UTF-8");
				entity.setContentType("application/json");
				method.setEntity(entity);
			}
			HttpResponse result = httpClient.execute(method);
			url = URLDecoder.decode(url, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			System.err.println(result.getStatusLine().getStatusCode());
			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					System.out.println(str);
					
					if (noNeedResponse) {
						return null;
					}
					/** 把json字符串转换成json对象 **/
					jsonResult = JSONObject.fromObject(str);
				} catch (Exception e) {
					// logger.error("post请求提交失败:" + url, e);
				}
			}
		} catch (IOException e) {
			// logger.error("post请求提交失败:" + url, e);
		}
		return jsonResult;
	}
	
	/**
	 * 发送get请求
	 * @param url 路径
	 * @return
	 */
	public static JSONObject httpGet(String url) {
		// get请求返回结果
		JSONObject jsonResult = null;
		jsonResult = JSONObject.fromObject(httpGet(url));
//		url = URLDecoder.decode(url, "UTF-8");
		return jsonResult;
	}
	
	public static String httpGet1(String url){
		String result = "";
		CloseableHttpClient client = HttpClients.createDefault();
		HttpGet request = new HttpGet(url);
		CloseableHttpResponse response;
		try {
			response = client.execute(request);
			int status = response.getStatusLine().getStatusCode();
			if (status==HttpStatus.SC_OK) {
				result = EntityUtils.toString(response.getEntity());
			}
			response.close();
			client.close();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
		}	
		return result;
	}
}
