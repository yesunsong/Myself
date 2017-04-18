package cn.xm.libandroid.AndroidUtils;

import android.content.Context;
import android.media.AudioManager;
import cn.xm.libandroid.service.base.Service;

public class AudioService extends Service {
	private static AudioService instance; 
	private AudioManager audioManager;
	
	public int currentVol;
	public int maxVol;
	
	private AudioService(Context context) {
		audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		getCurrentVol(AudioManager.STREAM_MUSIC);
		getMaxVol(AudioManager.STREAM_MUSIC);
	}

	public static AudioService getInstance() {
		if (instance == null) {
			instance = new AudioService(context);
		}
		return instance;
	}
	// <<===============================>>
	public int getMaxVol() {
		return maxVol;
	}
	
	public int getMaxVol(int streamType) {
		maxVol = audioManager.getStreamMaxVolume(streamType);
		return maxVol;
	}

	public int getCurrentVol(){
		return currentVol;
	}
	
	public int getCurrentVol(int streamType) {
		currentVol = audioManager.getStreamVolume(streamType);
		return currentVol;
	}

	/**提高音量*/
	public void increaseVolume() {
		currentVol++;
		if (currentVol > maxVol) {
			currentVol = maxVol;
		}
		setVolume();
	}
	
	/**降低音量*/
	public void decreaseVolume() {
		currentVol--;
		if (currentVol <= 0) {
			currentVol = 0;
		}
		setVolume();
	}

	public void setMinVolume() {
		currentVol = 0;
		setVolume();
	}

	public void setVolume() {
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVol,
				AudioManager.FLAG_PLAY_SOUND);
	}
}
