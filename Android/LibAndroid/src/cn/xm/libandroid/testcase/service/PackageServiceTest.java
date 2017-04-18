package cn.xm.libandroid.testcase.service;

import android.os.Bundle;
import cn.xm.libandroid.service.PackageService;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class PackageServiceTest extends BaseActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		log("VersionCode："+PackageService.getInstance().getVersionCode());
		log("VersionName："+PackageService.getInstance().getVersionName());
		log("包名："+PackageService.getInstance().getPackageName());
		log("MetaDataInfo："+PackageService.getInstance().getMetaDataInfo().toString());		
		
		log("是否安装微信应用："+PackageService.getInstance().isApplicationAvailable(this, PackageService.WeChat));		
		log("是否安装QQ应用："+PackageService.getInstance().isApplicationAvailable(this, PackageService.QQ));
	}
	
}
