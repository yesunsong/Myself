package cn.xm.libandroid.service;

import java.util.List;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.SupplicantState;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.net.wifi.WifiManager.WifiLock;
import cn.xm.libandroid.service.base.Service;

/**
 * WiFi服务
 * 
 * @PS 一般需要添加权限：android.permission.ACCESS_WIFI_STATE
 * @PS 调用startScan(),需要访问权限：android.permission.CHANGE_WIFI_STATE
 * @author yesunsong
 *
 */
public class WifiService extends Service {
	private static WifiService instance;
	private WifiManager wifiManager;

	/** WifiInfo对象 */
	private WifiInfo mWifiInfo;
	/** 扫描出的网络连接列表 */
	private List<ScanResult> mWifiList;
	/** 网络连接列表 */
	private List<WifiConfiguration> mWifiConfiguration;
	/** 定义一个WifiLock */
	private WifiLock mWifiLock;

	private WifiService(Context context) {
		wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
	}

	public static WifiService getInstance() {
		if (instance == null) {
			instance = new WifiService(context);
		}
		return instance;
	}

	// <<===============================>>
	/** 打开WIFI */
	public void openWifi() {
		if (!isWifiEnabled()) {
			wifiManager.setWifiEnabled(true);
		}
	}

	/** 关闭WIFI */
	public void closeWifi() {
		if (isWifiEnabled()) {
			wifiManager.setWifiEnabled(false);
		}
	}

	// 玩家有可能切换网络，所以每次获取网络信息时，要重新getWifiInfo()
	// <<===============================>>
	public WifiInfo getWifiInfo() {
		mWifiInfo = wifiManager.getConnectionInfo();
		return mWifiInfo;
	}

	/** 返回 MAC地址 */
	public String getMacAddress() {
		String localMac = "";
		getWifiInfo();
		if (isWifiEnabled()) {
			localMac = mWifiInfo.getMacAddress();
			localMac = localMac.replace(":", "-").toLowerCase();
			return localMac;
		}
		return localMac;
	}

	public int getRssi() {
		getWifiInfo();
		return mWifiInfo.getRssi();
	}

	public String getSSID() {
		getWifiInfo();
		return mWifiInfo.getSSID();
	}

	/** 返回 接入点的BSSID */
	public String getBSSID() {
		getWifiInfo();
		return mWifiInfo.getBSSID();
	}

	/** 返回 IP地址 */
	public int getIPAddress() {
		getWifiInfo();
		return mWifiInfo.getIpAddress();
	}

	/** 返回 网络ID */
	public int getNetworkId() {
		getWifiInfo();
		return mWifiInfo.getNetworkId();
	}

	public int getLinkSpeed() {
		getWifiInfo();
		return mWifiInfo.getLinkSpeed();
	}

	public SupplicantState getSupplicantState() {
		getWifiInfo();
		return mWifiInfo.getSupplicantState();
	}

	public boolean getMeteredHint() {
		getWifiInfo();
		return true;
	}

	// <<===============================>>
	/** 锁定WifiLock，当下载大文件时需要锁定 */
	public void acquireWifiLock() {
		mWifiLock.acquire();
	}

	/** 解锁WifiLock */
	public void releaseWifiLock() {
		// 判断时候锁定
		if (mWifiLock.isHeld()) {
			mWifiLock.acquire();
		}
	}

	/** 创建一个WifiLock */
	public void creatWifiLock() {
		mWifiLock = wifiManager.createWifiLock("Test");
	}

	// <<===============================>>
	/** 得到配置好的网络*/
	public List<WifiConfiguration> getConfiguration() {
		return mWifiConfiguration;
	}

	/** 指定配置好的网络进行连接*/
	public void connectConfiguration(int index) {
		// 索引大于配置好的网络索引返回
		if (index > mWifiConfiguration.size()) {
			return;
		}
		// 连接配置好的指定ID的网络
		wifiManager
				.enableNetwork(mWifiConfiguration.get(index).networkId, true);
	}

	/***
	 * 需要访问权限：builder.append("Metered hint：").append(WifiService.getInstance().getMeteredHint()).append("\n");
	 */
	public void startScan() {
		wifiManager.startScan();
		mWifiList = wifiManager.getScanResults();
		// 得到配置好的网络连接
		mWifiConfiguration = wifiManager.getConfiguredNetworks();
	}

	/** 得到网络列表 */
	public List<ScanResult> getWifiList() {
		return mWifiList;
	}

	/** 查看扫描结果 */
	public StringBuilder lookUpScan() {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < mWifiList.size(); i++) {
			stringBuilder.append("Index_" + Integer.valueOf(i + 1).toString()
					+ ":");
			// 将ScanResult信息转换成一个字符串包,其中把包括：BSSID、SSID、capabilities、frequency、level
			stringBuilder.append((mWifiList.get(i)).toString());
			stringBuilder.append("\n");
		}
		return stringBuilder;
	}

	// <<===============================>>
	/** 添加一个网络并连接 */
	public void addNetwork(WifiConfiguration wcg) {
		int wcgID = wifiManager.addNetwork(wcg);
		wifiManager.enableNetwork(wcgID, true);
	}

	/** 断开指定ID的网络 */
	public void disconnectWifi(int netId) {
		wifiManager.disableNetwork(netId);
		wifiManager.disconnect();
	}

	public int getWifiState() {
		return wifiManager.getWifiState();
	}

	/**
	 * Return whether Wi-Fi is enabled or disabled.
	 * 
	 * @return {@code true} if Wi-Fi is enabled
	 */
	public boolean isWifiEnabled() {
		return wifiManager.isWifiEnabled();
	}

}
