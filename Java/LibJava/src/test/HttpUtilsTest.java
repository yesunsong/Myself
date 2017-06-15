package test;

import cn.xm.yss.HttpUtils;
import net.sf.json.JSONObject;
import test.base.BaseTest;

public class HttpUtilsTest extends BaseTest {
	@Override
	public void test() {
//		String url = "http://www.xunyingwang.com/search?q=test";
		String url = "http://tag.xdowns.com/tag.asp?keyword=test";		
		String html = HttpUtils.getInstance().httpGet1(url);
		System.err.println(html);
//		http://www.dy2018.com/e/search/index.php
//		show=title%2Csmalltext & tempid=1 & keyboard= test & Submit= %C1%A2%BC%B4%CB%D1%CB%F7
		String postUrl = "http://www.dy2018.com/e/search/index.php?";
		JSONObject jsonParam = new JSONObject();
		jsonParam.put("show", "title,smalltext");
		jsonParam.put("tempid", "1");
		jsonParam.put("keyboard", "test");
		jsonParam.put("Submit", "%C1%A2%BC%B4%CB%D1%CB%F7");
//		HttpUtils.getInstance().httpPost(postUrl, jsonParam);	
//		show:title,smalltext
//		tempid:1
//		keyboard:test
//		Submit:(unable to decode value)
//		http://www.dy2018.com/e/search/index.php?show=title%2Csmalltext&tempid=1&keyboard=test&Submit=%C1%A2%BC%B4%CB%D1%CB%F7
	}
}
