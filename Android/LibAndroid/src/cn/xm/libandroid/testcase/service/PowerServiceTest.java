package cn.xm.libandroid.testcase.service;

import android.os.Bundle;
import android.os.PowerManager.WakeLock;
import cn.xm.libandroid.service.PowerService;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * @唤醒锁需要权限：android.permission.WAKE_LOCK
 * @author yesunsong
 *
 */
public class PowerServiceTest extends BaseActivity {
	/** 唤醒锁 */
	private WakeLock wakeLock;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		wakeLock = PowerService.getInstance().newWakeLock("My Lock");
	}

	@Override
	protected void onResume() {
		super.onResume();
		wakeLock.acquire();
	}

	@Override
	protected void onPause() {
		super.onPause();
		wakeLock.release();
	}

}
