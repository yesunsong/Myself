package cn.xm.libandroid.net;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImageService {

	/**
	 * 获取图片
	 * @param path 图片路径
	 * @return
	 * @throws Exception
	 */
	public static Bitmap getImage(String path) throws Exception {
		URL url=new URL(path);
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		connection.setConnectTimeout(5000);
		connection.setRequestMethod("GET");
		if (connection.getResponseCode() == 200) {
			InputStream inStream= connection.getInputStream();//通过输入流获得图片数据  
			// 方法一：
			// Bitmap bitmap=BitmapFactory.decodeStream(inStream);
			// inStream.close();
			// 方法二：
			byte[] data = StreamTool.readInputStream(inStream);// 获得图片的二进制数据
			Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
			return bitmap;
		}
		return null;
	}
}
