package cn.xm.libandroid.net;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTool {

	public static byte[] readInputStream(InputStream inputStream) throws Exception {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] buffers = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffers)) != -1) {
			bos.write(buffers, 0, len);
		}
		bos.close();
		inputStream.close();
		return bos.toByteArray();
	}

}
