package cn.xm.libandroid.service;

import android.content.Context;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;
import cn.xm.libandroid.service.base.Service;

/***
 * 
 * @author yesunsong
 *
 */
public class TelephonyService extends Service {
	/** 未知运营商 */
	public static String UNKNOW = "未知";
	/** 中国移动 */
	public static String CMCC = "中国移动";
	/** 中国联通 */
	public static String CHU = "中国联通";
	/** 中国电信 */
	public static String CHA = "中国电信";
	
	private static TelephonyService instance;
	private TelephonyManager telephonyManager;

	private TelephonyService(Context context) {
		telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
	}

	public static TelephonyService getInstance() {
		if (instance == null) {
			instance = new TelephonyService(context);
		}
		return instance;
	}

	// <<===============================>>

	public String getPhoneImsi() {
		return telephonyManager.getSubscriberId();
	}

	/**
	 * 返回 运营商
	 * 
	 * @return
	 */
	public String getProvider() {
		String provider = getProvider1();
		if (provider.equals(UNKNOW)) {
			provider = getProvider0();
		}
		return provider;
	}

	/** 返回 运营商 */
	private String getProvider0() {
		String provider = UNKNOW;
		String simOperator = telephonyManager.getSimOperator();
		if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
			if (simOperator != null) {
				if (simOperator.equals("46000") || simOperator.equals("46002")
						|| simOperator.equals("46007")) {
					provider = CMCC;
				} else if (simOperator.equals("46001")) {
					provider = CHU;
				} else if (simOperator.equals("46003")) {
					provider = CHA;
				}
			}
		}
		return provider;
	}
	
	/** 返回 运营商 */
	private String getProvider1() {
		String provider = UNKNOW;
		String subscriberId = getPhoneImsi();
		if (subscriberId != null) {
			if (subscriberId.startsWith("46000")
					|| subscriberId.startsWith("46002")) {
				// 因为移动网络编号46000下的IMSI已经用完，所以虚拟了一个46002编号，134/159号段使用了此编号
				provider = CMCC;
			} else if (subscriberId.startsWith("46001")) {
				provider = CHU;
			} else if (subscriberId.startsWith("46003")) {
				provider = CHA;
			}
		}
		return provider;
	}

	public int getNetworkType() {
		return telephonyManager.getNetworkType();
	}

	/** 返回 手机IMEI码 */
	public String getImei() {
		String imei = telephonyManager.getDeviceId();
		if (imei == null || imei.equals("")) {
			imei = Secure.getString(context
					.getContentResolver(), Secure.ANDROID_ID);
		}
		if (imei == null || imei.equals("")) {
			imei = "000000000000000";
		}
		return imei;
	}
	
	/** 检查sim卡状态 */
	public boolean checkSimState() {
		if (telephonyManager.getSimState() == TelephonyManager.SIM_STATE_ABSENT
				|| telephonyManager.getSimState() == TelephonyManager.SIM_STATE_UNKNOWN) {
			return false;
		}
		return true;
	}

}
