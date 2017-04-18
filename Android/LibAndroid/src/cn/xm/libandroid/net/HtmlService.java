package cn.xm.libandroid.net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtmlService {

	/**
	 * 获取网页源码
	 * @param path 网页路径
	 * @return
	 * @throws Exception
	 */
	public static String getHtml(String path) throws Exception{
		HttpURLConnection connection=(HttpURLConnection) (new URL(path)).openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if (connection.getResponseCode() == 200) {
			InputStream inputStream=connection.getInputStream();
			byte[] data=StreamTool.readInputStream(inputStream);
			return new String(data);
		}
		return null;
	}
}
