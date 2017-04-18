package cn.xm.libandroid;

import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

public class OlympicsReceiver extends BroadcastReceiver {
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
//			startService1(context);
			startService2(context);
//			startActivity(context);
		}	
	}
	
	private void startService1(Context context) {
		Intent intent0=new Intent();
		intent0.setAction("my_service");
		
		Intent intent2=new Intent(getExplicitIntent(context,intent0));
		context.startService(intent2);
	}
	
	private void startService2(Context context) {
		Intent intent0=new Intent();
		intent0.setPackage("com.example.testandroid");
		intent0.setAction("my_service");
		context.startService(intent0);
	}

	private void startActivity(Context context) {
		Intent bootActivityIntent = new Intent(context,AndroidBasicStarter.class);
		bootActivityIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(bootActivityIntent);
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

}
