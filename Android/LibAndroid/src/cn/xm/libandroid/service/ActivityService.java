package cn.xm.libandroid.service;

import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import cn.xm.libandroid.service.base.Service;

public class ActivityService extends Service {
	public static final String GLES_11="10001";
	public static final String GLES_20="20000";
	public static final String GLES_30="30000";
	
	private static ActivityService instance;
	private ActivityManager activityManager;
	ConfigurationInfo deviConfigurationInfo;
			
	private ActivityService(Context context) {
		activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		deviConfigurationInfo = activityManager.getDeviceConfigurationInfo();
	}

	public static ActivityService getInstance() {
		if (instance == null) {
			instance = new ActivityService(context);
		}
		return instance;
	}
	// <<===============================>>
	
	/**返回 OpenGL ES版本*/
	public String getOpenGLESVer(){
		return	Integer.toHexString(Integer.parseInt(String.valueOf(getPrimitiveOpenGLESVer())));
	}
	
	/**返回 原始的OpenGL ES版本【The upper order 16 bits represent the major version and the lower order 16 bits the minor version.】*/
	public int getPrimitiveOpenGLESVer(){
		return deviConfigurationInfo.reqGlEsVersion;
	}
	
	/**
	 * 判断是否有服务正在运行
	 * @param serviceName  包名+服务的类名（例如：net.loonggg.testbackstage.TestService） 
	 * @return true 在运行 false 不在运行 
	 */
	public boolean hasRunningService(String serviceName) {
		boolean isRunning = false;
		List<RunningServiceInfo> serviceLists = activityManager.getRunningServices(30);
		if (serviceLists.isEmpty()) {
			return isRunning;
		}
		
		int len = serviceLists.size();
		for (int i = 0; i < len; i++) {
//			Log.i("tag","service:"+serviceLists.get(i).service.getClassName()+",package:"+serviceLists.get(i).service.getPackageName());
			if (serviceLists.get(i).service.getClassName().equals(serviceName)) {
				isRunning = true;
				break;
			}
		}
		return isRunning;
	}
	
}
