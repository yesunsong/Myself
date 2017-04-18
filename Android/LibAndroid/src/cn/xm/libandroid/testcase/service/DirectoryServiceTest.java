package cn.xm.libandroid.testcase.service;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import cn.xm.libandroid.service.DirectoryService;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class DirectoryServiceTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		builder.append("Android的根目录：").append(DirectoryService.getInstance().getAndroidRootDir()).append("\n");
		builder.append("Android的数据目录：").append(DirectoryService.getInstance().getAndroidDataDir()).append("\n");
		builder.append("Android的下载/缓存内容的目录：").append(DirectoryService.getInstance().getAndroidDownloadCacheDir()).append("\n");
		builder.append("\n");
		builder.append("应用的缓存目录：").append(DirectoryService.getInstance().getAppCacheDir()).append("\n");
		builder.append("应用的文件目录：").append(DirectoryService.getInstance().getAppFileDir()).append("\n");
		builder.append("应用的指定目录：").append(DirectoryService.getInstance().getAppDir("dex",Context.MODE_PRIVATE)).append("\n");
		builder.append("\n");
		builder.append("外置存储是否可移除：").append(DirectoryService.getInstance().isExternalStorageRemovable()).append("\n");
		builder.append("外置存储是否模拟的：").append(DirectoryService.getInstance().isExternalStorageEmulated()).append("\n");
		builder.append("外置存储的目录：").append(DirectoryService.getInstance().getExternalStorageDir()).append("\n");
		builder.append("外置存储的缓存目录：").append(DirectoryService.getInstance().getExternalCacheDir()).append("\n");
		builder.append("外置存储的指定文件目录：").append(DirectoryService.getInstance().getExternalFilesDir(Environment.DIRECTORY_MUSIC)).append("\n");
		builder.append("外置存储的指定共有目录：").append(DirectoryService.getInstance().getExternalStoragePublicDir(Environment.DIRECTORY_MUSIC)).append("\n");
		textView.setText(builder.toString());
	}
}
