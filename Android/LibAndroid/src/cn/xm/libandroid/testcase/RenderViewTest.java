package cn.xm.libandroid.testcase;

import java.util.Random;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.view.View;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class RenderViewTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
	}

	class RenderView extends View {
		Random rand=new Random();
		
		public RenderView(Context context) {
			super(context);
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawRGB(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
			invalidate();
		}
	}
}

