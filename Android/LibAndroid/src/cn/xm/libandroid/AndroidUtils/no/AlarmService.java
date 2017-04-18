package cn.xm.libandroid.AndroidUtils.no;

import android.app.AlarmManager;
import android.content.Context;
import cn.xm.libandroid.service.base.Service;

public class AlarmService extends Service {
	private static AlarmService instance;
	private AlarmManager alarmManager;
	
	private AlarmService(Context context) {
		alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
	}

	public static AlarmService getInstance() {
		if (instance == null) {
			instance = new AlarmService(context);
		}
		return instance;
	}
}
