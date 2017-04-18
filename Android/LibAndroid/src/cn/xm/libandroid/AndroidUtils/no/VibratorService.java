package cn.xm.libandroid.AndroidUtils.no;

import android.content.Context;
import android.os.Vibrator;
import cn.xm.libandroid.service.base.Service;

public class VibratorService extends Service {
	private static VibratorService instance;
	private Vibrator vibrator;
	
	private VibratorService(Context context){
		vibrator=(Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
	}
	
	public static VibratorService getInstance(){
		if (instance==null) {
			instance=new VibratorService(context);
		}
		return instance;
	}
	// <<===============================>>
}
