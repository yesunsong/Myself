package cn.xm.libandroid.testcase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import android.os.Bundle;
import android.os.Environment;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * @PS 需要获取权限：android.permission.WRITE_EXTERNAL_STORAGE
 * @author yesunsong
 *
 */
public class ExternalStorageTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		String state=Environment.getExternalStorageState();
		if (!state.equals(Environment.MEDIA_MOUNTED)) {
			textView.setText("No external storage mounted");
		}else {
			File externalDir=Environment.getExternalStorageDirectory();
			File textFile=new File(externalDir.getAbsolutePath()+File.separator+"text.txt");
			try {
				writeTextFile(textFile, "This is a test.Roger");
				String text = readTextFile(textFile);
				textView.setText(text);
				if (!textFile.delete()) {
					textView.setText("Couldn't remove temporary file");
				}
			} catch (IOException e) {
				e.printStackTrace();
				textView.setText("Something went wrong! "+e.getMessage());
			}
		}
	}

	private String readTextFile(File textFile) throws IOException {
		BufferedReader reader=new BufferedReader(new FileReader(textFile));
		StringBuilder text=new StringBuilder();
		String line;
		while ((line=reader.readLine())!=null) {
			text.append(line);
			text.append("\n");
		}
		reader.close();
		return text.toString();
	}

	private void writeTextFile(File textFile, String text) throws IOException {
		BufferedWriter writer=new BufferedWriter(new FileWriter(textFile));
		writer.write(text);
		writer.close();
	}
}
