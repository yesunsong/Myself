package cn.xm.libandroid.framework;

public interface Graphics {
	public static enum PixmapFormat {
		ARGB8888, ARGB4444, RGB565
	}

	public Pixmap newPixmap(String filename, PixmapFormat format);

	public void clear(int color);

	public void drawPixel(int x, int y, int color);

	public void drawLine(int x, int y, int x1, int x2, int color);

	/**
	 * 
	 * @param x
	 *            左上角位置
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 *            填充矩形的颜色
	 */
	public void drawRect(int x, int y, int width, int height, int color);

	/**
	 * 
	 * @param pixmap
	 * @param x
	 *            左上角位置
	 * @param y
	 * @param srcX
	 *            像素图中相应矩形区域的左上角位置
	 * @param srcY
	 * @param srcWidth
	 * @param srcHeight
	 */
	public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight);

	public void drawPixmap(Pixmap pixmap, int x, int y);

	/**
	 * 以像素为单位的帧缓冲区的宽度
	 */
	public void getWidth();

	/**
	 * 以像素为单位的帧缓冲区的高度
	 */
	public void getHeight();

}
