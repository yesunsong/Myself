package cn.xm.libandroid.AndroidUtils.no;

import android.app.KeyguardManager;
import android.content.Context;
import cn.xm.libandroid.service.base.Service;

public class KeyguardService extends Service {
	private static KeyguardService instance;
	private KeyguardManager keyguardManager;
	
	private KeyguardService(Context context){
		keyguardManager=(KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
	}
	
	public static KeyguardService getInstance(){
		if (instance==null) {
			instance=new KeyguardService(context);
		}
		return instance;
	}
	// <<===============================>>
}
