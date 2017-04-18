package cn.xm.libandroid.implement;

import android.media.SoundPool;
import cn.xm.libandroid.framework.Sound;

public class AndroidSound implements Sound {

	private int soundId;
	private SoundPool soundPool;

	AndroidSound(SoundPool soundPool, int soundId) {
		this.soundId = soundId;
		this.soundPool = soundPool;
	}

	@Override
	public void play(float voluem) {
		soundPool.play(soundId, voluem, voluem, 0, 0, 1);
	}

	@Override
	public void dispose() {
		soundPool.unload(soundId);
	}

}
