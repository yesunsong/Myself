package cn.xm.libandroid.testcase;

import android.os.Bundle;

/**
 * 使活动实现全屏
 * @author yesunsong
 *
 */
public class FullScreenTest extends SingleTouchTest {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		AndroidSetting.getInstance().setFullScreen();
		super.onCreate(savedInstanceState);
	}
}
