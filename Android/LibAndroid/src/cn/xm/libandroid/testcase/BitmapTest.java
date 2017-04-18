package cn.xm.libandroid.testcase;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class BitmapTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(new RenderView(this));
	}
	
	class RenderView extends View {
		Bitmap bob565;
		Bitmap bob4444;
		Rect dst = new Rect();

		public RenderView(Context context) {
			super(context);
			try {
				AssetManager assetManager=context.getAssets();
				InputStream inputStream=assetManager.open("bobrgb888.png");
				bob565=BitmapFactory.decodeStream(inputStream);
				inputStream.close();				
				Log.d("BitmapTest", "bobrgb888.png format:"+bob565.getConfig());
				
				inputStream=assetManager.open("bobargb8888.png");
				BitmapFactory.Options options=new BitmapFactory.Options();
				options.inPreferredConfig=Bitmap.Config.ARGB_4444;
				bob4444=BitmapFactory.decodeStream(inputStream,null,options);
				inputStream.close();
				Log.d("BitmapTest", "bobargb8888.png format:"+bob4444.getConfig());
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
			}
		}
		
		@Override
		protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			canvas.drawRGB(0, 0, 0);
			dst.set(50, 50, 350, 350);
			canvas.drawBitmap(bob565, null, dst,null);
			canvas.drawBitmap(bob4444, 100, 100,null);
//			Bitmap缩放
			int sourceWidth=bob565.getWidth();
			int sourceHeight=bob565.getHeight();
			Matrix matrix=new Matrix();
			matrix.postScale(0.5f, 0.5f);
			Bitmap bitmap=Bitmap.createBitmap(bob565, 0, 0, sourceWidth, sourceHeight, matrix, true);
			canvas.drawBitmap(bitmap, 250,250, null);
			invalidate();
		}
	}
}
