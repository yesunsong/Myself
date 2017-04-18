package cn.xm.libandroid.AndroidUtils;

import android.content.Context;
import android.os.IBinder;
import android.view.inputmethod.InputMethodManager;
import cn.xm.libandroid.service.base.Service;

public class InputmethodService extends Service {
	private static InputmethodService instance; 
	private InputMethodManager inputMethodManager;
	
	private InputmethodService(Context context) {
		inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
	}

	public static InputmethodService getInstance() {
		if (instance == null) {
			instance = new InputmethodService(context);
		}
		return instance;
	}
	// <<===============================>>

	public void hideSoftInputFromWindow(IBinder windowToken, int flags) {
		inputMethodManager.hideSoftInputFromWindow(windowToken, flags);
	}
}
