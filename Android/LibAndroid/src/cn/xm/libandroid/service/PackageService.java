package cn.xm.libandroid.service;

import java.util.List;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import cn.xm.libandroid.service.base.Service;

public class PackageService extends Service {
	/**微信应用包名*/
	public static final String WeChat= "com.tencent.mm";
	/**腾讯QQ应用包名*/
	public static final String QQ= "com.tencent.mobileqq";
	
	private static PackageService instance; 
	private final PackageManager packageManager;
	private ApplicationInfo metaDataInfo;
	private PackageInfo packageInfo;
	
	private PackageService(Context context) {
		packageManager = (PackageManager) context.getPackageManager();
		try {
			packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
			metaDataInfo = packageManager.getApplicationInfo(getPackageName(),PackageManager.GET_META_DATA);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static PackageService getInstance() {
		if (instance == null) {
			instance = new PackageService(context);
		}
		return instance;
	}
	// <<===============================>>
	
	/**
	 * 版本号，可以显示给用户
	 * 
	 * @param context
	 * @return
	 */
	public String getVersionName() {
		if (packageInfo != null) {
			return packageInfo.versionName;
		}
		return "";
	}

	/**
	 * 主要是版本升级所用，只要判断该值就能确定是否需要升级，不显示给用户
	 * 
	 * @param context
	 * @return
	 */
	public int getVersionCode() {
		if (packageInfo != null) {
			return packageInfo.versionCode;
		}
		return -1;
	}
	
	/** 获取 包名 */
	public  String getPackageName() {
		return context.getPackageName();
	}
	
	public ApplicationInfo getMetaDataInfo() {
		return metaDataInfo;
	}
	
	/**
	 * 是否安装某个应用
	 * @param context
	 * @return
	 */
	public boolean isApplicationAvailable(Context context,String package_name) {
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);// 获取所有已安装程序的包信息
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                if (pn.equals(package_name)) {
                    return true;
                }
            }
        }

        return false;
    }
}
