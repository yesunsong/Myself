package cn.xm.libandroid.testcase;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 测试按键事件
 * 
 * @author yesunsong
 *
 */
public class KeyTest extends BaseActivity implements OnKeyListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView.setText("Press keys(if you have some)");
		textView.setOnKeyListener(this);
//		textView.setOnKeyListener(new OnKeyListener() {
//
//			@Override
//			public boolean onKey(View v, int keyCode, KeyEvent event) {
//				builder.setLength(0);
//				switch (event.getAction()) {
//				case KeyEvent.ACTION_DOWN:
//					builder.append("down,");
//					break;
//				case KeyEvent.ACTION_UP:
//					builder.append("up,");
//					break;
//				}
//				builder.append(event.getKeyCode());
//				builder.append(",");
//				builder.append(event.getUnicodeChar());
//				Log.d(this.getClass().getCanonicalName(), builder.toString());
//				textView.setText(builder.toString());
//				return (event.getAction() != KeyEvent.KEYCODE_BACK);
//			}
//		});
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		builder.setLength(0);
		switch (event.getAction()) {
		case KeyEvent.ACTION_DOWN:
			builder.append("down,");
			break;
		case KeyEvent.ACTION_UP:
			builder.append("up,");
			break;
		}
		builder.append(event.getKeyCode());
		builder.append(",");
		builder.append(event.getUnicodeChar());
		Log.d(this.getClass().getCanonicalName(), builder.toString());
		textView.setText(builder.toString());
		return (event.getAction() != KeyEvent.KEYCODE_BACK);
	}

}
