package cn.xm.yss;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 * 
 * @author yesunsong
 *
 */
public class StringUtils {
	private static StringUtils utils = null;

	private StringUtils() {
	}

	public static StringUtils getInstance() {
		if (utils == null) {
			utils = new StringUtils();
		}
		return utils;
	}

	/**
	 * 替换 字符串数组中的指定字符
	 * 
	 * @param list
	 * @param oldChar
	 * @param newChar
	 */
	// TODO 需要优化
	@SuppressWarnings({ "unchecked" })
	public void replace(ArrayList<String> list, char oldChar, char newChar) {
		if (list.isEmpty()) {
			return;
		}
		List<String> tmpList = (List<String>) list.clone();
		list.clear();

		for (String string : tmpList) {
			list.add(string.replace(JavaMacro.SLASH, JavaMacro.BACKSLASH));
		}
	}

	public String replace(String string, char oldChar, char newChar) {
		return string.replace(oldChar, newChar);
	}

	/** 实现字节数组向十六进制的转换方法 */
	public String byte2HexStr(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < 9; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	/**
	 * 是否包含中文
	 * @param string
	 * @return
	 */
	public boolean containsChinese(String string) {
		String regEx = "[\u4e00-\u9fa5]";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(string);
		boolean flg = false;
		if (matcher.find()) {
			flg = true;
		}
		return flg;

	}

}
