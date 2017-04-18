package cn.xm.libandroid.testcase;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class AssetTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AssetManager assetManager=getAssets();
		InputStream inputStream=null;
		try {
			inputStream=assetManager.open("texts/myawesometext.txt");
			String text=loadTextFile(inputStream);
			textView.setText(text);
		} catch (Exception e) {
			textView.setText("Couldn't load file");
		}finally{
			if (inputStream!=null) {
				try {
					inputStream.close();
				} catch (Exception e) {
					textView.setText("Couldn't close file");
				}
			}
		}
	}

	@SuppressLint("NewApi")
	private String loadTextFile(InputStream inputStream) throws IOException {
		ByteArrayOutputStream byteStream=new ByteArrayOutputStream();
		byte[] bytes=new byte[4096];
		int len=0;
		while ((len = inputStream.read(bytes))>0) {
			byteStream.write(bytes,0,len);
		}
		return new String(byteStream.toByteArray(),Charset.defaultCharset());
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}
}
