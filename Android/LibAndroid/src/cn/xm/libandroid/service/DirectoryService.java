package cn.xm.libandroid.service;

import java.io.File;

import android.annotation.SuppressLint;
import android.content.ContextWrapper;
import android.os.Environment;

public class DirectoryService {
	private static DirectoryService instance;
	private static ContextWrapper contextWrapper;

	private DirectoryService() {
	}

	public static DirectoryService getInstance() {
		if (instance == null) {
			instance = new DirectoryService();
		}
		return instance;
	}

	public static void init(ContextWrapper contextWrapper) {
		DirectoryService.contextWrapper = contextWrapper;
	}

	// <<===============================>>
	/**返回 Android的根目录*/
	public File getAndroidRootDir(){
		return Environment.getRootDirectory();
	}
	
	/**返回 Android的数据目录*/
	public File getAndroidDataDir(){
		return Environment.getDataDirectory();
	}
	
	/**返回 Android的下载/缓存内容的目录*/
	public File getAndroidDownloadCacheDir(){
		return Environment.getDownloadCacheDirectory();
	}
	
	// <<===============================>>
	/**返回 应用的文件目录*/
	public File getAppFileDir() {
		return contextWrapper.getFilesDir();
	}
	
	/**返回 应用的缓存目录*/
	public File getAppCacheDir() {
		return contextWrapper.getCacheDir();
	}
	
	/**返回 应用的指定数据库目录*/
	public File getAppDatabaseDir(String name){
		return	contextWrapper.getDatabasePath(name);
	}
	
	/**返回 应用的指定目录【没有的话，创建】*/
	public File getAppDir(String name, int mode) {
		return contextWrapper.getDir(name, mode);
	}
	
	// <<===============================>>
	@SuppressLint("NewApi")
	/**【API Level 11】*/
	public  boolean isExternalStorageEmulated() {
		return Environment.isExternalStorageEmulated();	
	}
	
	@SuppressLint("NewApi")
	/**API Level 9*/
	public  boolean isExternalStorageRemovable() {
		return Environment.isExternalStorageRemovable();	
	}
	
	/**外部存储共享目录【但是这个目录很可能当前不能访问，比如这个目录被用户的PC挂载，或者从设备中移除，或者其他问题发生，你可以通过getExternalStorageState()来获取当前状态；还有多用户或者多外部存储的情况】*/
	public File getExternalStorageDir(){
		return Environment.getExternalStorageDirectory();
	}
	
	/**共享目录下的分类目录*/
	public File getExternalStoragePublicDir(String type){
		return Environment.getExternalStoragePublicDirectory(type);
	}
	
	public File getExternalCacheDir(){
		return contextWrapper.getExternalCacheDir();
	}

	/**任何应用私有的文件的应该被放置在 Context.getExternalFilesDir 返回的目录下，在应用被卸载的时候，系统会清理的就是这个目录。
	 *   * {@link android.os.Environment#DIRECTORY_MUSIC}.*/
	public File getExternalFilesDir(String type){
		return contextWrapper.getExternalFilesDir(type);
	}
	
}
