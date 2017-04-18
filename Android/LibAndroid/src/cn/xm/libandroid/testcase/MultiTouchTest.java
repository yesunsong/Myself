package cn.xm.libandroid.testcase;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 测试多点触摸API
 * @author yesunsong
 *
 */
@TargetApi(5)
public class MultiTouchTest extends BaseActivity implements OnTouchListener{
	float[] x=new float[10];
	float[] y=new float[10];
	boolean[] touched=new boolean[10];
	int[] id=new int[10];
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView.setText("Touch and drag (multiple fingers supported)");
		textView.setOnTouchListener(this);
		for (int i = 0; i < 10; i++) {
			id[i]=-1;
		}
		updateTextView();
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		int action=event.getAction()&MotionEvent.ACTION_MASK;
		int pointerIndex=(event.getAction()&MotionEvent.ACTION_POINTER_ID_MASK)>>MotionEvent.ACTION_POINTER_ID_SHIFT;
		int pointerCount=event.getPointerCount();
		for (int i = 0; i < 10; i++) {
			if (i>=pointerCount) {
				touched[i]=false;
				id[i]=-1;
				continue;
			}
			if (event.getAction()!=MotionEvent.ACTION_MOVE&&i!=pointerIndex) {
				continue;
			}
			int pointerId=event.getPointerId(i);
			switch (action) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_POINTER_DOWN:
				touched[i]=true;
				id[i]=pointerId;
				x[i]=(int)event.getX(i);
				y[i]=(int)event.getY(i);
				break;
				
			case MotionEvent.ACTION_OUTSIDE:
			case MotionEvent.ACTION_CANCEL:
				touched[i]=false;
				id[i]=-1;
				x[i]=(int)event.getX(i);
				y[i]=(int)event.getY(i);
				break;
				
			case MotionEvent.ACTION_MOVE:
				touched[i]=true;
				id[i]=pointerId;
				x[i]=(int)event.getX(i);
				y[i]=(int)event.getY(i);
				break;
			}
		}
		updateTextView();
		return true;
	}
	
	public void updateTextView(){
		builder.setLength(0);
		for (int i = 0; i < 10; i++) {
			builder.append(touched[i]).append(", ");
			builder.append(id[i]).append(", ");
			builder.append(x[i]).append(", ");
			builder.append(y[i]).append("\n");
		}
		textView.setText(builder.toString());
	}
}
