package cn.xm.libandroid.implement;

import java.util.List;

import android.view.View.OnTouchListener;
import cn.xm.libandroid.framework.Input.TouchEvent;

/**
 * 为兼容Android1.5和1.6而实现
 * @author yesunsong
 *
 */
public interface TouchHandler extends OnTouchListener {

	public boolean isTouchDown(int pointer);
	
	public int getTouchX(int pointer);
	
	public int getTouchY(int pointer);
	
	public List<TouchEvent> getTouchEvents();
	
}
