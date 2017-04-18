package cn.xm.libandroid.testcase;

import android.os.Bundle;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 演示活动的生命周期
 * 
 * @author yesunsong
 *
 */
public class LifeCycleTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		log("create");
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		log("paused");
		if (isFinishing()) {
			log("finishing");
		}
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		log("resumed");
	}
}
