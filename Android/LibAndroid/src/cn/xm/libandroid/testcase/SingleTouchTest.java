package cn.xm.libandroid.testcase;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 测试单点触摸处理
 * 
 * @author yesunsong
 *
 */
public class SingleTouchTest extends BaseActivity {
	boolean isTestSingleTouch = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		textView.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return handleTouch(v, event);
			}
		});
		textView.setText("Touch and drag(one finger only)!");
	}

	public boolean handleTouch(View v, MotionEvent event) {
		builder.setLength(0);

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (isTestSingleTouch) {
				builder.append("down,");
			}
			break;

		case MotionEvent.ACTION_UP:
			handleTouchUpEvent();
			if (isTestSingleTouch) {
				builder.append("up,");
			}
			break;

		case MotionEvent.ACTION_MOVE:
			if (isTestSingleTouch) {
				builder.append("move,");
			}
			break;

		case MotionEvent.ACTION_CANCEL:
			if (isTestSingleTouch) {
				builder.append("cancle,");
			}
			break;
		}
		if (isTestSingleTouch) {
			builder.append(event.getX()).append(",");
			builder.append(event.getY());
			log2(builder.toString());
		}
		// return应返回true，除非事件还想继续往下传递
		return true;
	}

	/**
	 * 
	 * @return true:表示 测试 单点触摸；false：表示 不是测试 单点触摸
	 */
	protected void handleTouchUpEvent() {
	}

	public void setTestSingleTouch(boolean isTest) {
		this.isTestSingleTouch = isTest;
	}

}
