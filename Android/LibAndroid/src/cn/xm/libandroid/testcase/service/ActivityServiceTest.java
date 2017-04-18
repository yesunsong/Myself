package cn.xm.libandroid.testcase.service;

import android.os.Bundle;
import cn.xm.libandroid.service.ActivityService;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class ActivityServiceTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		log("Primitive OpenGL ES Ver："+ActivityService.getInstance().getPrimitiveOpenGLESVer());
		log("OpenGL ES Ver："+ActivityService.getInstance().getOpenGLESVer());
		log("是否有某个服务正在进行："+ActivityService.getInstance().hasRunningService(""));		
	}
}
