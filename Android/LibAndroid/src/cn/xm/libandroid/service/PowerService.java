package cn.xm.libandroid.service;

import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import cn.xm.libandroid.service.base.Service;

/**
 * 电源管理
 * @author yesunsong
 *
 */
public class PowerService extends Service {
	private static PowerService instance;
	private PowerManager powerManager;
	
	private PowerService(Context context){
		powerManager=(PowerManager) context.getSystemService(Context.POWER_SERVICE);
	}
	
	public static PowerService getInstance(){
		if (instance==null) {
			instance=new PowerService(context);
		}
		return instance;
	}
	
	// <<===============================>>
	/***
	 * 
	 * @param tag 锁标记
	 * @return 使用唤醒锁需要获取权限：android.permission.WAKE_LOCK
	 */
	public WakeLock newWakeLock(String tag){
		return powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, tag);
	}
	
}
