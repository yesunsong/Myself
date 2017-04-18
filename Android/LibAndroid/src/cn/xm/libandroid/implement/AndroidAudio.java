package cn.xm.libandroid.implement;

import java.io.IOException;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import cn.xm.libandroid.framework.Audio;
import cn.xm.libandroid.framework.Music;
import cn.xm.libandroid.framework.Sound;

public class AndroidAudio implements Audio {
	private AssetManager assets;
	private SoundPool soundPool;

	public AndroidAudio(Activity activity) {
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		this.soundPool = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
	}

	@Override
	public Music newMusic(String filename) throws IOException {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
			return new AndroidMusic(assetFileDescriptor);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music '" + filename + "'");
		}
	}

	@Override
	public Sound newSound(String filename) throws IOException {
		try {
			AssetFileDescriptor assetFileDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetFileDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}

}
