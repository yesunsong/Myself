package cn.xm.yss;

import java.util.ArrayList;
import java.util.List;

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
	public void replace(ArrayList<String> list, char oldChar, char newChar) {
		if (list.isEmpty()) {
			return;
		}
		List<String> tmpList = (List<String>) list.clone();
		list.clear();

		int index = 0;
		for (String string : tmpList) {
			list.add(tmpList.get(index).replace("/", "\\"));
			index++;
		}
	}
	
	public String replace(String string, char oldChar, char newChar) {
		return string.replace(oldChar, newChar);
	}
}
