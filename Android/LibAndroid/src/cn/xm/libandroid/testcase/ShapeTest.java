package cn.xm.libandroid.testcase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Bundle;
import android.view.View;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 绘制形状
 * @author yesunsong
 *
 */
public class ShapeTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
	}
	
	class RenderView extends View {
		Paint paint;
		
		public RenderView(Context context) {
			super(context);
			paint = new Paint();
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawRGB(255,255,255);
			paint.setColor(Color.RED);
			canvas.drawLine(0, 0, canvas.getWidth()-1, canvas.getHeight()-1, paint);
			
			paint.setStyle(Style.STROKE);
			paint.setColor(0xff00ff00);
			canvas.drawCircle(canvas.getWidth()/2, canvas.getHeight()/2, 40, paint);
			
			paint.setStyle(Style.FILL);
			paint.setColor(0x770000ff);
			canvas.drawRect(100, 100, 200, 200, paint);
			invalidate();
		}
	}
}