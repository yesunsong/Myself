package cn.xm.libandroid.testcase.service;

import android.os.Bundle;
import cn.xm.libandroid.service.WifiService;
import cn.xm.libandroid.testcase.SingleTouchTest;


/***
 * 测试  WifiService类
 * @author yesunsong
 *
 */
public class WifiServiceTest extends SingleTouchTest {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTestSingleTouch(false);
	}
	
	@Override
	protected void handleTouchUpEvent() {
		log("触摸屏幕时可改变开关WIFI");
		
		if (WifiService.getInstance().isWifiEnabled()) {
			WifiService.getInstance().closeWifi();
		}else {
			WifiService.getInstance().openWifi();
		}
		
		log("当前WIFI状态："+WifiService.getInstance().isWifiEnabled());
		log("WIFI信息："+WifiService.getInstance().getWifiInfo());
		log("SSID："+WifiService.getInstance().getSSID());
		log("BSSID："+WifiService.getInstance().getBSSID());
		log("MAC地址："+WifiService.getInstance().getMacAddress());
		log("Supplicant state："+WifiService.getInstance().getMacAddress());
		log("RSSI："+WifiService.getInstance().getRssi());
		log("Link speed："+WifiService.getInstance().getLinkSpeed());
		log("Net ID："+WifiService.getInstance().getNetworkId());
		log("Metered hint："+WifiService.getInstance().getMeteredHint());
		WifiService.getInstance().startScan();
//		log("网络列表：+"\n+WifiService.getInstance().lookUpScan());
	}
}
