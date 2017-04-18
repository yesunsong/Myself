package cn.xm.libandroid.AndroidUtils.no;

import android.content.Context;
import android.location.LocationManager;
import cn.xm.libandroid.service.base.Service;

public class LocatioinService extends Service {
	private static LocatioinService instance;
	private LocationManager locationManager;
	
	private LocatioinService(Context context){
		locationManager=(LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	}
	
	public static LocatioinService getInstance(){
		if (instance==null) {
			instance=new LocatioinService(context);
		}
		return instance;
	}
	// <<===============================>>
}
