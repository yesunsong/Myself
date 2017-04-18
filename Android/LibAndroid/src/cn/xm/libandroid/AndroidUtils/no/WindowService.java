package cn.xm.libandroid.AndroidUtils.no;

import android.content.Context;
import android.view.WindowManager;
import cn.xm.libandroid.service.base.Service;

public class WindowService extends Service {
	private static WindowService instance;
	private WindowManager windowManager;
	
	private WindowService(Context context) {
		windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
	}

	public static WindowService getInstance() {
		if (instance == null) {
			instance = new WindowService(context);
		}
		return instance;
	}
}
