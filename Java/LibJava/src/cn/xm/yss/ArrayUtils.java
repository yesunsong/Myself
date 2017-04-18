package cn.xm.yss;

import java.util.List;

/**
 * 数组操作工具类
 * @author yesunsong
 *
 */
public class ArrayUtils {
	private static ArrayUtils utils;

	private ArrayUtils() {
	}

	public static ArrayUtils getInstance() {
		if (utils == null) {
			utils = new ArrayUtils();
		}
		return utils;
	}

	/**
	 * 在数组中存在移除项时,移除并返回布尔值
	 * @param list
	 * @param target
	 * @return boolean 是否存在移除项
	 */
	public <T> boolean removeWhenOccur(List<T> list, T target) {
		boolean isIn = false;
		if (list.isEmpty()) {
			return isIn;
		}
		for (T string : list) {
			if (string.equals(target)) {
				isIn = true;
				list.remove(string);
				break;
			}
		}
		return isIn;
	}
}
