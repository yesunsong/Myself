package cn.xm.libandroid.service;

import java.text.DecimalFormat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import cn.xm.libandroid.service.base.Service;

/**
 * 网络连接服务
 * 
 * @author yesunsong
 *
 */
public class ConnectivityService extends Service {
	/** 取值取负值，是为了不和 TelephonyManager.NETWORK_TYPE_XXXX起冲突 */
	private static final int NETWORK_TYPE_WIFI = -101;
	/** 未知网络 */
	private static final int NETWORK_UNKNOWN = 0;
	/** WiFi网络 */
	private static final int NETWORK_WIFI = 1;
	/** 2G网络 */
	private static final int NETWORK_2G = 2;
	/** 3G网络 */
	private static final int NETWORK_3G = 3;
	/** 4G网络 */
	private static final int NETWORK_4G = 4;

	private static ConnectivityService instance;
	private ConnectivityManager connectivityManager;
	NetworkInfo activeNetworkInfo = null;
	private static DecimalFormat df = new DecimalFormat("#.##");
	
	private ConnectivityService(Context context) {
		connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	}

	public static ConnectivityService getInstance() {
		if (instance == null) {
			instance = new ConnectivityService(context);
		}
		return instance;
	}

	// <<===============================>>
	/**返回 可用网络*/
	public void getActiveNetworkInfo() {
		activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
		// 获取所有可用网络
		connectivityManager.getAllNetworkInfo();
		// isNetworkTypeValid();
	}

	/** 是否 有网络连接 */
	public boolean isNetworkAvaiable() {
		getActiveNetworkInfo();
		if (activeNetworkInfo == null) {
			return false;
		}
		return activeNetworkInfo.isAvailable();
	}
	
	/**是否 WIFI连接*/
	public boolean isWifiAvaiable(){
		// 获取固定网络信息
		NetworkInfo wifiInfo= connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiInfo!=null) {
			return wifiInfo.isAvailable();
		}
		return false;
	}

	/**是否 手机数据连接*/
	public boolean isMobileAvaiable(){
		// 获取固定网络信息
		NetworkInfo wifiInfo= connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (wifiInfo!=null) {
			return wifiInfo.isAvailable();
		}
		return false;
	}
	
	/** 是否 有网络已经连接 */
	public boolean isNetworkConnected() {
		getActiveNetworkInfo();
		if (activeNetworkInfo == null) {
			return false;
		}
		return activeNetworkInfo.isConnected();
	}

	/** 是否 有Mobile网络已经连接 */
	public boolean isMobileConnected() {
		NetworkInfo wifiInfo=connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_MOBILE);
		return wifiInfo.isConnected();
	}

	/** 是否 有WIFI网络已经连接 */
	public boolean isWifiConnected() {
		NetworkInfo wifiInfo= connectivityManager.getNetworkInfo(
				ConnectivityManager.TYPE_WIFI);
		// wifiInfo.getState()
		return wifiInfo.isConnected();
	}
//	==================
	/** 返回 网络类型：WiFi、2G、3G、4G、未知 */
	public String getCurrentNetworkType() {
		String type = "";
		switch (getFuzzyNetworkType()) {
		case NETWORK_WIFI:
			type = "WiFi";
			break;
		case NETWORK_2G:
			type = "2G";
			break;
		case NETWORK_3G:
			type = "3G";
			break;
		case NETWORK_4G:
			type = "4G";
			break;
		case NETWORK_UNKNOWN:
			type = "未知";
			break;
		}
		return type;
	}

	/** 返回 精确的网络类型 */
	public int getNetworkType() {
		int networkType = TelephonyManager.NETWORK_TYPE_UNKNOWN;
		if (isNetworkAvaiable() && isNetworkConnected()) {
			// 获取当前网络的类型:mobile or Wi-Fi
			int type = activeNetworkInfo.getType();
			if (type == ConnectivityManager.TYPE_WIFI) {
				networkType = NETWORK_TYPE_WIFI;
			} else if (type == ConnectivityManager.TYPE_MOBILE) {
				networkType = TelephonyService.getInstance().getNetworkType();
			}
		}
		return networkType;
	}
	
	/** 获取当前网络的类型名:WIFI or MOBILE */
	public void getTypeName() {
	}
	
	/** 返回 笼统的网络类型 */
	public  int getFuzzyNetworkType() {
		int networkType=getNetworkType();
		switch (networkType) {
		case NETWORK_TYPE_WIFI:
			return NETWORK_WIFI;
		case TelephonyManager.NETWORK_TYPE_GPRS:
		case TelephonyManager.NETWORK_TYPE_EDGE:
		case TelephonyManager.NETWORK_TYPE_CDMA:
		case TelephonyManager.NETWORK_TYPE_1xRTT:
		case TelephonyManager.NETWORK_TYPE_IDEN:
			return NETWORK_2G;
		case TelephonyManager.NETWORK_TYPE_UMTS:
		case TelephonyManager.NETWORK_TYPE_EVDO_0:
		case TelephonyManager.NETWORK_TYPE_EVDO_A:
		case TelephonyManager.NETWORK_TYPE_HSDPA:
		case TelephonyManager.NETWORK_TYPE_HSUPA:
		case TelephonyManager.NETWORK_TYPE_HSPA:
		case TelephonyManager.NETWORK_TYPE_EVDO_B:
		case TelephonyManager.NETWORK_TYPE_EHRPD:
		case TelephonyManager.NETWORK_TYPE_HSPAP:
			return NETWORK_3G;
		case TelephonyManager.NETWORK_TYPE_LTE:
			return NETWORK_4G;
		default:
			return NETWORK_UNKNOWN;
		}
	}

	public void turnToWirelessSettings() {
		// 跳转到无线网络设置界面
//		context.startActivity(new Intent(
//				android.provider.Settings.ACTION_WIRELESS_SETTINGS));
//		// 跳转到无限wifi网络设置界面
		context.startActivity(new Intent(
				android.provider.Settings.ACTION_WIFI_SETTINGS));

		// activeNetworkInfo.getTypeName()
	}
	
	public void turnToWifiSettings() {
		// 跳转到无线网络设置界面
//		context.startActivity(new Intent(
//				android.provider.Settings.ACTION_WIRELESS_SETTINGS));
//		// 跳转到无限wifi网络设置界面
		context.startActivity(new Intent(
				android.provider.Settings.ACTION_WIFI_SETTINGS));

		// activeNetworkInfo.getTypeName()
	}
	
//	在WLAN设置界面
//	1，显示连接已保存，但标题栏没有，即没有实质连接上，输出为：not connect， available
//	2，显示连接已保存，标题栏也有已连接上的图标，            输出为：connect， available
//	3，选择不保存后                      			          输出为：not connect， available
//	4，选择连接，在正在获取IP地址时                                     输出为：not connect， not available
//	5，连接上后                                                                                输出为：connect， available
}
