package cn.xm.libandroid.AndroidUtils;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

/**
 * Android常用设置
 * 
 * @author yesunsong
 *
 */
public class AndroidSetting {
	private static AndroidSetting androidSetting;
	private static Activity activity;

	private AndroidSetting() {
	}

	public static AndroidSetting getInstance() {
		if (androidSetting == null) {
			androidSetting = new AndroidSetting();
		}
		return androidSetting;
	}

	public static void init(Activity activity) {
		AndroidSetting.activity = activity;
	}
	// <<===============================>>
	/** 设置屏幕全屏【要在setContentView(textView)之前】 */
	public void setFullScreen() {
		activity.requestWindowFeature(Window.FEATURE_NO_TITLE);
		activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

	/** 窗口可见时，屏幕常亮[在初始化完onCreate()之后使用] */
	public void setScreenAlawaysBright() {
		activity.getWindow().addFlags(
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}

	/*
	 * Android OS中，如果你去按手机上的调节音量的按钮，会分两种情况，
	 * 一种是调整手机本身的铃声音量，一种是调整游戏，软件，音乐播放的音量 当我们在游戏中的时候 ，总是想调整游戏的音量而不是手机的铃声音量，
	 * 可是烦人的问题又来了，我在开发中发现，只有游戏中有声音在播放的时候 ，你才能去调整游戏的音量，否则就是手机的音量.
	 * 设定调整音量为媒体音量,当暂停播放的时候调整音量就不会再默认调整铃声音量了
	 */
	/**控制 媒体流的音量*/
	public void setVolumeControlStream(int streamType){
		activity.setVolumeControlStream(streamType);
	}

	/**设置 屏幕方向*/
	public void setRequestedOrientation(int screenOrientation) {
		activity.setRequestedOrientation(screenOrientation);
	}
}
