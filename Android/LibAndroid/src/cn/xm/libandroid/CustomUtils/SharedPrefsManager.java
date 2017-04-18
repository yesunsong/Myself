package cn.xm.libandroid.CustomUtils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * 共享首选项
 * @author yesunsong
 *
 */
public class SharedPrefsManager  {
	private static final int DEFAULT_INT = -1;
	private static final String DEFAULT_STRING = "";
	private static final boolean DEFAULT_BOOLEAN = false;

	private static SharedPreferences sSharedPrefs;

	/**
	 * 存档初始化
	 * 
	 * @param context
	 */
	public static void init(Context context) {
		sSharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
	}

	public static int getInt(String key) {
		return getInt(key, DEFAULT_INT);
	}

	public static int getInt(String key, int defaultValue) {
		return sSharedPrefs.getInt(key, defaultValue);
	}

	public static void putInt(String key, int value) {
		sSharedPrefs.edit().putInt(key, value).commit();
	}

	public static String getString(String key) {
		return getString(key, DEFAULT_STRING);
	}

	public static String getString(String key, String defaultValue) {
		return sSharedPrefs.getString(key, defaultValue);
	}

	public static void putString(String key, String value) {
		sSharedPrefs.edit().putString(key, value).commit();
	}

	public static boolean getBoolean(String key) {
		return getBoolean(key, DEFAULT_BOOLEAN);
	}

	public static boolean getBoolean(String key, boolean defaultBoolean) {
		return sSharedPrefs.getBoolean(key, defaultBoolean);
	}

	public static void putBoolean(String key, boolean value) {
		sSharedPrefs.edit().putBoolean(key, value).commit();
	}

	public static void remove(String key) {
		sSharedPrefs.edit().remove(key);
	}
}
