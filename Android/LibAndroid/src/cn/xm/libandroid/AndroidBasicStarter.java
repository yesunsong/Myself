package cn.xm.libandroid;

import java.util.List;

import android.app.ListActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.OrientationEventListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import cn.xm.libandroid.AndroidUtils.AndroidSetting;
import cn.xm.libandroid.implement.Application;
import cn.xm.libandroid.service.ActivityService;
import cn.xm.libandroid.service.PackageService;

/**
 *  Android相关组件、服务、功能的测试用例
 * 
 * @author yesunsong
 *
 */
public class AndroidBasicStarter extends ListActivity {
	private String TAG = AndroidBasicStarter.class.getCanonicalName();

	private String tests[] = { "AccelerometerTest","ActivityServiceTest", "AssetTest", "BitmapTest", "ConnectivityServiceTest", "DirectoryServiceTest", "ExternalStorageTest", "FontTest", "FullScreenTest", "KeyTest", "LifeCycleTest", "MediaPlayerTest", "MultiTouchTest", "NotificationServiceTest", "PackageServiceTest","PowerServiceTest","RenderViewTest", "ScreenResolution", "ShapeTest", "SharedPreferencesTest", "SingleTouchTest", "SoundPoolTest", "SurfaceViewTest", "WifiServiceTest" ,"TelephonyServiceTest"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Application.activity = this;
		// 初始化
		Application.initService(this);
		AndroidSetting.getInstance().setFullScreen();
		AndroidSetting.getInstance().setScreenAlawaysBright();
		AndroidSetting.getInstance().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//
		setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tests));

		if (!ActivityService.getInstance().hasRunningService(PackageService.getInstance().getPackageName() + ".NotificationService")) {
			startService2(this);
		}

		// this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
//		 this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_FULL_SENSOR);

		String[] sports = getResources().getStringArray(R.array.sports);
		int color = getResources().getColor(R.color.contents_text);
		Drawable drawable = getResources().getDrawable(R.color.contents_text);

		startOrientationChangeListener();
	}

	private OrientationEventListener mOrientationListener;
	private boolean mScreenProtrait = true;
	private boolean mCurrentOrient = false;

	private final void startOrientationChangeListener() {
		mOrientationListener = new OrientationEventListener(this) {
			// 0-45-135-225-315-~
			@Override
			public void onOrientationChanged(int rotation) {
				if (((rotation >= 0) && (rotation <= 45)) || (rotation >= 315) || ((rotation >= 135) && (rotation <= 225))) {// portrait
					mCurrentOrient = true;
					if (mCurrentOrient != mScreenProtrait) {
						mScreenProtrait = mCurrentOrient;
						OrientationChanged(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
						Log.d(TAG, "Screen orientation changed from Landscape to Portrait!");
					}
				} else if (((rotation > 45) && (rotation < 135)) || ((rotation > 225) && (rotation < 315))) {// landscape
					mCurrentOrient = false;
					if (mCurrentOrient != mScreenProtrait) {
						mScreenProtrait = mCurrentOrient;
						OrientationChanged(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
						Log.d(TAG, "Screen orientation changed from Portrait to Landscape!");
					}
				}
			}
		};
		mOrientationListener.enable();
	}

	protected void OrientationChanged(int orientation) {// screen orientation
		if (orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else if (orientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		System.err.println(newConfig.orientation);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		} else {
		}
	}

	private void startService2(Context context) {
		Intent intent0 = new Intent();
		intent0.setPackage(PackageService.getInstance().getPackageName());
		intent0.setAction("my_service");
		intent0.setClassName(context, PackageService.getInstance().getPackageName() + ".NotificationService");
		context.startService(intent0);
		// context.bindService(service, conn, flags)binds
	}

	public static Intent getExplicitIntent(Context context, Intent implicitIntent) {
		// Retrieve all services that can match the given intent
		PackageManager pm = context.getPackageManager();
		List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);
		// Make sure only one match was found
		if (resolveInfo == null || resolveInfo.size() != 1) {
			return null;
		}
		// Get component info and create ComponentName
		ResolveInfo serviceInfo = resolveInfo.get(0);
		String packageName = serviceInfo.serviceInfo.packageName;
		String className = serviceInfo.serviceInfo.name;
		ComponentName component = new ComponentName(packageName, className);
		// Create a new intent. Use the old one for extras and such reuse
		Intent explicitIntent = new Intent(implicitIntent);
		// Set the component to be explicit
		explicitIntent.setComponent(component);
		return explicitIntent;
	}

	@Override
	protected void onListItemClick(ListView list, View view, int position, long id) {
		super.onListItemClick(list, view, position, id);
		String testName = tests[position];
		try {
			Class<?> clazz = Class.forName("cn.xm.libandroid.testcase.service." + testName);
			Intent intent = new Intent(this, clazz);
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
			Toast.makeText(this, "抛出异常", Toast.LENGTH_LONG).show();
		}
	}
}
