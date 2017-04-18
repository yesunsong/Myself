package cn.xm.libandroid.testcase;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.graphics.Point;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import cn.xm.libandroid.AndroidBasicStarter;
import cn.xm.libandroid.testcase.base.BaseActivity;

/***
 * 屏幕分辨率
 * 
 * @author yesunsong
 *
 */
public class ScreenResolution extends BaseActivity {
	private String TAG = AndroidBasicStarter.class.getCanonicalName();
	private int screen_w;
	private int screen_h;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getDisplayScreenResolution();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus) {
			Log.d(this.getClass().getSimpleName(), "onWindowFocusChanged w/h:"
					+ getWindow().getDecorView().getWidth() + "/"
					+ getWindow().getDecorView().getHeight());
		}
	}

	public int getDisplayScreenResolution() {
		int ver = Build.VERSION.SDK_INT;

		DisplayMetrics metrics = new DisplayMetrics();
		android.view.Display display = getWindowManager().getDefaultDisplay();
		display.getMetrics(metrics);

		builder.append("宽度：").append(metrics.widthPixels);
		builder.append("高度：").append(metrics.heightPixels);
		builder.append("密度：").append(metrics.density);
		builder.append("x：").append(metrics.xdpi);
		builder.append("y：").append(metrics.ydpi);
		textView.setText(builder.toString());
		
		screen_w = metrics.widthPixels;

		Log.d(TAG, "Run1 first get resolution:" + metrics.widthPixels + " * "
				+ metrics.heightPixels + ",Android Ver:" + ver);
		
		if (ver < 13) {
			screen_h = metrics.heightPixels;
		} else if (ver == 13) {
			try {
				Method mt = display.getClass().getMethod("getRealHeight");
				screen_h = (Integer) mt.invoke(display);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ver < 17) {
			try {
				Method mt = display.getClass().getMethod("getRawHeight");
				screen_h = (Integer) mt.invoke(display);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (ver > 17) {
			// used when SDK_INT >= 17; includes window decorations (statusbar
			// bar/menu bar)
			Point realSize = new Point();
			try {
				Display.class.getMethod("getRealSize", Point.class).invoke(
						display, realSize);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			}
			screen_w = realSize.x;
			screen_h = realSize.y;
		}
		Log.d(TAG, "Run2 Calibration resolution:" + screen_w + " * " + screen_h);
		return 0;
	}
}
