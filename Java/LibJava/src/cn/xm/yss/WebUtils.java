package cn.xm.yss;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WebUtils {
	private static WebUtils utils;

	private WebUtils() {
	}

	public static WebUtils getInstance() {
		if (utils == null) {
			utils = new WebUtils();
		}
		return utils;
	}
	
	public String encode(String s,String enc){
		String encodeAfter = "";
		try {
			encodeAfter = URLEncoder.encode(s, enc);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encodeAfter;
	}
}
