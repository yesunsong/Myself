package cn.xm.libandroid.testcase;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import cn.xm.libandroid.AndroidUtils.AndroidSetting;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 播放音频流
 * 
 * @author yesunsong
 *
 */
public class MediaPlayerTest extends BaseActivity {
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidSetting.getInstance().setVolumeControlStream(AudioManager.STREAM_MUSIC);
		mediaPlayer = new MediaPlayer();
		try {
			AssetManager assetManager = getAssets();
			AssetFileDescriptor descriptor = assetManager.openFd("login_music.mp3");
			mediaPlayer.setDataSource(descriptor.getFileDescriptor(),descriptor.getStartOffset(), descriptor.getLength());
//			 使用这种方式会报错： java.io.IOException: setDataSourceFD failed.: status=0x80000000
//			mediaPlayer.setDataSource(descriptor.getFileDescriptor());
			mediaPlayer.prepare();
			mediaPlayer.setLooping(false);
			mediaPlayer.isPlaying();
			mediaPlayer.setVolume(1, 1);
//			如果没有把mediaPlayer设置为循环播放，可以通过isPlaying()或回调事件来监听
			mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
				
				@Override
				public void onCompletion(MediaPlayer mp) {
					textView.setText("播放完毕");
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			textView.setText("Couldn't load music file:" + e.getMessage());
			mediaPlayer = null;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mediaPlayer != null) {
			mediaPlayer.start();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mediaPlayer != null) {
			mediaPlayer.pause();
			if (isFinishing()) {
				mediaPlayer.stop();
				mediaPlayer.release();
			}
		}
	}
}
