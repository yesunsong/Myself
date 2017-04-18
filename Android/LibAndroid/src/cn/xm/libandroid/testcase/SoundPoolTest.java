package cn.xm.libandroid.testcase;

import java.io.IOException;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import cn.xm.libandroid.AndroidUtils.AndroidSetting;

/**
 * 播放音效
 * @PS 只要是媒体文件，不因为格式不同而无法读取，甚至去除后缀也无妨
 * 
 * @author yesunsong
 *
 */
public class SoundPoolTest extends SingleTouchTest {
	SoundPool soundPool;
	int explosionId = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTestSingleTouch(false);
		AndroidSetting.getInstance().setVolumeControlStream(AudioManager.STREAM_MUSIC);
		soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
		try {
			AssetManager assetManager = getAssets();
			AssetFileDescriptor descriptor = assetManager.openFd("explosion.ogg");
			explosionId = soundPool.load(descriptor, 1);
		} catch (IOException e) {
			textView.setText("Couldn't load sound effect from asset, "
					+ e.getMessage());
		}
	}

	@Override
	protected void handleTouchUpEvent() {
		if (explosionId != -1) {
			soundPool.play(explosionId, 1, 1, 0, 0, 1);
		}
	}
	
}