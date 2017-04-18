package cn.xm.libandroid.framework;

/***
 * 音效
 * 
 * @author yesunsong
 *
 */
public interface Sound {
	/**
	 * 
	 * @param voluem
	 *            0(静音)~1(最大值)
	 */
	public void play(float voluem);

	/** 释放 */
	public void dispose();
}
