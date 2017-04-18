package cn.xm.libandroid.implement;

import android.app.Activity;
import cn.xm.libandroid.AndroidUtils.AndroidSetting;
import cn.xm.libandroid.service.ActivityService;
import cn.xm.libandroid.service.ConnectivityService;
import cn.xm.libandroid.service.DirectoryService;
import cn.xm.libandroid.service.NotificationService;
import cn.xm.libandroid.service.PackageService;
import cn.xm.libandroid.service.PowerService;
import cn.xm.libandroid.service.WifiService;

public class Application {
	public static Activity activity;
	
	/**初始化*/
	public static void initService(Activity activity) {
		WifiService.init(activity);
		PowerService.init(activity);
		AndroidSetting.init(activity);
		PackageService.init(activity);
		ActivityService.init(activity);
		DirectoryService.init(activity);
		ConnectivityService.init(activity);
		NotificationService.init(activity);
	}
}
