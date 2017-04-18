package cn.xm.libandroid.framework;

import java.util.List;

public interface Input {
	public class KeyEvent {
		public static final int KEY_DOWN = 0;
		public static final int KEY_UP = 1;

		/** 类型 */
		public int type;
		/** 按键代码 */
		public int keyCode;
		/** Unicode字符 */
		public char keyChar;
	}

	public class TouchEvent {
		public static final int TOUCH_DOWN = 0;
		public static final int TOUCH_UP = 1;
		public static final int TOUCH_DRAGGED = 1;

		/** 事件类型 */
		public int type;
		/** 触点相对于UI组件原点的位置 */
		public int x, y;
		/** 触摸屏的驱动程序赋予手指的指针ID */
		public int pointer;
	}

	/** 对应按键是否被按下 */
	public boolean isKeyPressed(int keyCode);

	/** 是否按下一个给定的指针 */
	public boolean isTouchDown(int pointer);

	/** 给定指针当前的x坐标 */
	public int getTouchX(int pointer);

	/** 给定指针当前的y坐标 */
	public int getTouchY(int pointer);

	/** 加速计x轴上的坐标 */
	public float getAccelX();

	/** 加速计y轴上的坐标 */
	public float getAccelY();

	/** 加速计z轴上的坐标 */
	public float getAccelZ();

	/** 返回 调用以上方法所记录的KeyEvent实例 */
	public List<KeyEvent> getKeyEvents();

	/** 返回 调用以上方法所记录的TouchEvent实例 */
	public List<TouchEvent> getTouchEvents();
}
