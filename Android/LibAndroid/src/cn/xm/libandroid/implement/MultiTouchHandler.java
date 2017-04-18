package cn.xm.libandroid.implement;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.view.View;
import cn.xm.libandroid.framework.Input.TouchEvent;

@TargetApi(5)
public class MultiTouchHandler implements TouchHandler {
	private static final int MAX_TOUCHPOINTS=10;
	boolean[] isTouched=new boolean[MAX_TOUCHPOINTS];
	int[] touchX=new int[MAX_TOUCHPOINTS];
	int[] touchY=new int[MAX_TOUCHPOINTS];
	int[] id=new int[MAX_TOUCHPOINTS];
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEvents=new ArrayList<TouchEvent>();
	List<TouchEvent> touchEventsBuffer=new ArrayList<TouchEvent>();
	float scaleX;
	float scaleY;
	
	public MultiTouchHandler(View view,float scaleX,float scaleY){
	}
	
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return false;
	}

	@Override
	public boolean isTouchDown(int pointer) {
		return false;
	}

	@Override
	public int getTouchX(int pointer) {
		return 0;
	}

	@Override
	public int getTouchY(int pointer) {
		return 0;
	}

	@Override
	public List<TouchEvent> getTouchEvents() {
		return null;
	}

}
