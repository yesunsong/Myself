package cn.xm.libandroid.framework;

public interface Music {
	/** 开始播放 */
	public void play();

	/** 停止播放 */
	public void stop();

	/** 暂停播放 */
	public void pause();

	/** 设置循环播放 */
	public void setLooping(boolean isLooping);

	/**
	 * 设置音量值
	 * 
	 * @param volume
	 *            0(静音)~1(最大值)
	 */
	public void setVolume(float volume);

	/***/
	public boolean isPlaying();

	/***/
	public boolean isStopped();

	/***/
	public boolean isLooping();

	/** 释放 */
	public void dispose();

}
