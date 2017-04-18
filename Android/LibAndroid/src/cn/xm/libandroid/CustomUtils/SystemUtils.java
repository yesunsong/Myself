package cn.xm.libandroid.CustomUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import cn.xm.libandroid.service.WifiService;

/**
 * 系统信息工具类
 * 
 * @author yesunsong
 *
 */
public class SystemUtils {
	/** 检测sd卡是否能够读写 */
	public static boolean canWriteSDCARD() {
		// 外部SD卡是否可用
		boolean mExternalStorageAvailable = false;
		// 外部SD卡是否可写
		boolean mExternalStorageWriteable = false;
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			mExternalStorageAvailable = mExternalStorageWriteable = true;
		} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
			mExternalStorageAvailable = true;
			mExternalStorageWriteable = false;
		} else {
			mExternalStorageAvailable = mExternalStorageWriteable = false;
		}
		return mExternalStorageWriteable;
	}

	/** 获取 SD卡的目录[可能是内置SD卡的，也可能是外置SD卡的，依手机生产商的设定] */
	public static String getSDCardRoot() {
		String sdDir = "";
		String state = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(state)) {
			sdDir = Environment.getExternalStorageDirectory().getAbsolutePath();
		}
		return sdDir;
	}

	/**
	 * 检查是否有足够的SD卡存储空间
	 * 
	 * @param needSize
	 *            所需大小
	 * @return
	 */
	public static boolean hasEnoughSpace(long needSize) {
		return (getAvailableStore(SDCARD_ROOT) > needSize);
	}
	
	public final static String SDCARD_ROOT = "/sdcard";
	/**
	 * 获取存储卡的剩余容量，单位为字节
	 * 
	 * @param filePath
	 * @return availableSpace
	 */
	public static long getAvailableStore(String filePath) {
		// 取得sdcard文件路径
		StatFs statFs = new StatFs(filePath);
		// 获取block的SIZE
		long blocSize = statFs.getBlockSize();
		// 获取BLOCK数量
		long totalBlocks = statFs.getBlockCount();
		// 可使用的Block的数量
		long availableBlocks = statFs.getAvailableBlocks();

		long total = totalBlocks * blocSize;
		long availableSpace = availableBlocks * blocSize;

		return availableSpace;
	}

	/** 返回 SD卡的总容量 */
	public int getExternalStorageTotalSize() {
		String path = getSDCardRoot();
		String totalSize = "0";
		if (!path.equals("")) {
			StatFs stat = new StatFs(path);
			long blockCount = stat.getBlockCount();
			long blockSize = stat.getBlockSize();

			totalSize = Formatter.formatFileSize(getContext(), blockCount * blockSize);
		}
		return Integer.parseInt(totalSize);
	}

	/** 返回 SD卡的可用容量 */
	public int getExternalStorageAvailableSize() {
		String path = getSDCardRoot();
		String availableSize = "0";
		if (!path.equals("")) {
			StatFs stat = new StatFs(path);// 创建StatFs对象，用来获取文件系统的状态
			long blockCount = stat.getBlockCount();
			long availableBlocks = stat.getAvailableBlocks();

			availableSize = Formatter.formatFileSize(getContext(), blockCount * availableBlocks);// 获得SD卡可用容量
		}
		return Integer.parseInt(availableSize);
	}

	/** 返回 ROM容量 */
	private String getRomSpace() {
		File path = Environment.getDataDirectory();
		StatFs stat = new StatFs(path.getPath());

		long blockCount = stat.getBlockCount();
		long blockSize = stat.getBlockSize();
		long availableBlocks = stat.getAvailableBlocks();

		String totalSize = Formatter.formatFileSize(getContext(), blockCount * blockSize);
		String availableSize = Formatter.formatFileSize(getContext(), blockCount * availableBlocks);

		return "手机Rom总容量:" + totalSize + "\n手机Rom可用容量:" + availableSize;
	}

	// =====================
	public static void copyAsset(String assetName) {
		Float percent = Float.valueOf(0f);
		copyAsset(assetName, percent);
	}
	
	//TODO
	private static Context mContext;
	public static void setContext(Context context){
		mContext=context;
	}
	
	public static Context getContext( ){
		return mContext;
	}
	
	public static void setAssetManager(AssetManager assetManager){
		
	}
	//TODO
	public static AssetManager getAssetManager(){
		return null;
	}

	/** 将apk包里asset目录下的某个文件拷贝到SD卡上 */
	public static void copyAsset(String assetName, Float percent) {
		try {
			// String wholePathName = Application.getResLocation() + assetName;
			String wholePathName = assetName;
			// 检查文件夹
			int fileNameStart = wholePathName.lastIndexOf("/");
			String dirStr = wholePathName.substring(0, fileNameStart);
			File dir = new File(dirStr);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			// 检查文件
			File oneFile = new File(wholePathName);
			if (!oneFile.exists()) {
				oneFile.createNewFile();
			}

			InputStream in = getAssetManager().open(assetName);
			long assetsFileSize = getStreamLength(in); // get the file size
			FileOutputStream stream = new FileOutputStream(oneFile);
			DataOutputStream fos = new DataOutputStream(stream);

			long copySize = 0;
			byte[] buff = new byte[4096];
			int n = in.read(buff);
			while (n > 0) { // or in.available() > 0
				// write file
				fos.write(buff, 0, n);

				copySize += n;
				percent = Float.valueOf((float) copySize / assetsFileSize);

				n = in.read(buff);
			}
			fos.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			// if (Logger.isShowLogger) {
			// Logger.getInstance().log(assetName + ":copy assets error");
			// }
		}
	}

	/**
	 * 获取文件大小
	 * 
	 * @param in
	 * @return
	 */
	public static long getStreamLength(InputStream in) {
		long len = 0;

		byte[] buff = new byte[4096];
		try {
			int n = in.read(buff);
			while (n > 0) { // or in.available() > 0
				len += n;
				n = in.read(buff);
			}
			in.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return len;
	}
	
	/**
	 * 获取MAC地址
	 * 
	 * @param context
	 * @return
	 */
	public static String getMacAddress(Context context) {
		if (context == null) {
			return "";
		}

		String localMac = null;
		if (WifiService.getInstance().isWifiEnabled()) {
			localMac = WifiService.getInstance().getMacAddress();
		}

		if (localMac != null && localMac.length() > 0) {
			localMac = localMac.replace(":", "-").toLowerCase();
			return localMac;
		}

		localMac = getMacFromCallCmd();
		if (localMac != null) {
			localMac = localMac.replace(":", "-").toLowerCase();
		}

		return localMac;
	}

	/**
	 * 通过callCmd("busybox ifconfig","HWaddr")获取mac地址
	 * 
	 * @attention 需要设备装有busybox工具
	 * @return Mac Address
	 */
	private static String getMacFromCallCmd() {
		String result = "";
		result = callCmd("busybox ifconfig", "HWaddr");

		if (result == null || result.length() <= 0) {
			return null;
		}

		// Debug.LogV("tag", "cmd result : " + result);

		// 对该行数据进行解析
		// 例如：eth0 Link encap:Ethernet HWaddr 00:16:E8:3E:DF:67
		if (result.length() > 0 && result.contains("HWaddr") == true) {
			String Mac = result.substring(result.indexOf("HWaddr") + 6, result.length() - 1);
			if (Mac.length() > 1) {
				result = Mac.replaceAll(" ", "");
			}
		}

		return result;
	}

	public static String callCmd(String cmd, String filter) {
		String result = "";
		String line = "";
		try {
			Process proc = Runtime.getRuntime().exec(cmd);
			InputStreamReader is = new InputStreamReader(proc.getInputStream());
			BufferedReader br = new BufferedReader(is);

			// 执行命令cmd，只取结果中含有filter的这一行
			while ((line = br.readLine()) != null && line.contains(filter) == false) {
			}

			result = line;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
