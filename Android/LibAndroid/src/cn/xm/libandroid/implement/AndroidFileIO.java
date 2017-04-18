package cn.xm.libandroid.implement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;
import cn.xm.libandroid.framework.FileIO;

public class AndroidFileIO implements FileIO {

	private Context context;
	private AssetManager assets;
	/** 外部存储的路径 */
	private String externalStoragePath;

	public AndroidFileIO(Context context) {
		this.context = context;
		this.assets = context.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory()
				.getAbsolutePath() + File.separator;
	}

	@Override
	public InputStream readAsset(String filename) throws IOException {
		return assets.open(filename);
	}

	@Override
	public InputStream readFile(String filename) throws IOException {
		return new FileInputStream(new File(externalStoragePath + filename));
	}

	@Override
	public OutputStream writeFile(String filename) throws IOException {
		return new FileOutputStream(new File(externalStoragePath) + filename);
	}

	private SharedPreferences getPreferences() {
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
}
