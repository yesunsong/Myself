package cn.xm.libandroid.testcase.service;

import android.os.Bundle;
import cn.xm.libandroid.service.TelephonyService;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 需要权限：android.permission.READ_PHONE_STATE.

 * @author yesunsong
 *
 */
public class TelephonyServiceTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		log("手机IMEI码：" + TelephonyService.getInstance().getImei());
		log("手机SIM卡状态：" + TelephonyService.getInstance().checkSimState());
		log("手机网络制式：" + TelephonyService.getInstance().getNetworkType());
		log("手机运营商：" + TelephonyService.getInstance().getProvider());
		log("手机IMSI：" + TelephonyService.getInstance().getPhoneImsi());
	}
}
