package cn.xm.libandroid.framework;

import cn.xm.libandroid.framework.Graphics.PixmapFormat;

public interface Pixmap {
	/**
	 * 宽度，单位为像素
	 */
	public void getWidth();

	/**
	 * 高度，单位为像素
	 */
	public void getHeight();

	/**
	 * Pixmap在RAM中的存储格式
	 * 
	 * @return
	 */
	public PixmapFormat getFormat();

	/** 释放 */
	public void dispose();
}
