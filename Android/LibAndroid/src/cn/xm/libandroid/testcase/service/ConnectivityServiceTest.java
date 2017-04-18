package cn.xm.libandroid.testcase.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import cn.xm.libandroid.service.ConnectivityService;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 测试 网络信号
 * @PS需要权限：android.permission.ACCESS_NETWORK_STATE
 * @author yesunsong
 *
 */
public class ConnectivityServiceTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		log("是否 有网络可连接:"+ConnectivityService.getInstance().isNetworkAvaiable()+"\n");
		log("是否 WIFI可连接:"+ConnectivityService.getInstance().isWifiAvaiable()+"\n");
		log("是否 Mobile可连接:"+ConnectivityService.getInstance().isMobileAvaiable()+"\n");
		
		log("是否 有网络已连接:"+ConnectivityService.getInstance().isNetworkConnected()+"\n");
		log("是否 有WIFI已连接:"+ConnectivityService.getInstance().isWifiConnected()+"\n");
		log("是否 有Mobile已连接:"+ConnectivityService.getInstance().isMobileConnected()+"\n");
		
		log("网络类型:"+ConnectivityService.getInstance().getCurrentNetworkType()+"\n");
		log("精确的网络类型:"+ConnectivityService.getInstance().getNetworkType()+"\n");
		log("模糊的网络类型:"+ConnectivityService.getInstance().getFuzzyNetworkType()+"\n");
		// log(""+""+"\n");
		// log(""+""+""\n);
		
		ConnectivityService.getInstance().turnToWifiSettings();
		ConnectivityService.getInstance().turnToWirelessSettings();
		
		this.registerReceiver(mBroadcastReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
	}
	
	private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle bundle = intent.getExtras();
			NetworkInfo aNetworkInfo = (NetworkInfo) bundle
					.get(ConnectivityManager.EXTRA_NETWORK_INFO);

			if (aNetworkInfo.isAvailable()) {
				log("available"+" ");
			} else {
				log("unavailable"+" ");
			}
			
			if (aNetworkInfo.isConnected()) {
				log("connected"+"\n");
			} else {
				log("unconnected"+"\n");
			}
//			textView.setText(builder.toString());
		}
	};
	
}
