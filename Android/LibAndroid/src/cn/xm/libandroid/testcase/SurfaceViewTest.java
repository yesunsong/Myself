package cn.xm.libandroid.testcase;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class SurfaceViewTest extends BaseActivity {
	private FastRenderView renderView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		renderView = new FastRenderView(this);
		setContentView(renderView);
	}

	@Override
	protected void onResume() {
		super.onResume();
		renderView.resume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		renderView.pause();
	}
}

class FastRenderView extends SurfaceView implements Runnable {
	Thread renderThread = null;
	SurfaceHolder holder;
	volatile boolean running = false;

	public FastRenderView(Context context) {
		super(context);
		holder = getHolder();
	}

	public void resume() {
		running = true;
		renderThread = new Thread(this);
		renderThread.start();
	}

	public void pause() {
		running = false;
		while (true) {
			try {
				renderThread.join();
				return;
			} catch (InterruptedException e) {
				// retry
			}
		}
	}

	@Override
	public void run() {
		while (running) {
			if (!holder.getSurface().isValid()) {
				continue;
			}
			// 绘制中出现异常，捕捉后退出循环
			try {
				Canvas canvas = holder.lockCanvas();
				canvas.drawRGB(250, 0, 0);
				holder.unlockCanvasAndPost(canvas);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
	}

}
