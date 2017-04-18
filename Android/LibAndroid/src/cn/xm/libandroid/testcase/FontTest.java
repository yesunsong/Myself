package cn.xm.libandroid.testcase;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class FontTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
	}
}

class RenderView extends View {
	Paint paint;
	Typeface font;
	Rect bounds = new Rect();

	public RenderView(Context context) {
		super(context);
		paint = new Paint();
		font = Typeface.createFromAsset(context.getAssets(), "arial.ttf");
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawRGB(0, 0, 3);
		paint.setColor(Color.YELLOW);
		paint.setTextSize(28);
		paint.setTextAlign(Paint.Align.CENTER);
		canvas.drawText("This is a test!", canvas.getWidth() / 2, 100, paint);

		String text = "This is another test o_0";
		paint.setColor(Color.WHITE);
//		因为可以使用类似18*2形式，所以可以实现 两点缩放字体大小的功能 或者 自适应不同屏幕分辨率的字体大小
		paint.setTextSize(18*2);
		paint.setTextAlign(Paint.Align.LEFT);
		paint.getTextBounds(text, 0, text.length(), bounds);
		canvas.drawText(text, canvas.getWidth() - bounds.width(), 140, paint);
		invalidate();
	}

}
