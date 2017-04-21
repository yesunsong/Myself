package cn.xm.yss;

import java.io.FileInputStream;
import java.io.IOException;

public class ImageUtils {
	private static ImageUtils utils;

	private ImageUtils() {
	}

	public static ImageUtils getInstance() {
		if (utils == null) {
			utils = new ImageUtils();
		}
		return utils;
	}
	
	/** 检查png的位深度 */
	public int checkPngBitDepth(String path) {
		try {
			FileInputStream fis = new FileInputStream(path);
			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			byte[] buff = new byte[1024];
			int len = 0;
			while ((len = fis.read(buff)) != -1) {
				bos.write(buff, 0, len);
			}
			fis.close();
			
			// 得到图片的字节数组
			byte[] result = bos.toByteArray();
			// 字节数组转成十六进制
//			String str = StringUtils.getInstance().byte2HexStr(result);
			// System.out.println("++++" + byte2HexStr(result));
			// System.out.println(str);
			
			// png位深度
			int bitsPerPixel = result[24] & 0xff;
			if ((result[25] & 0xff) == 2) {
				bitsPerPixel *= 3;
			} else if ((result[25] & 0xff) == 6) {
				bitsPerPixel *= 4;
			}
			// System.out.println(bitsPerPixel);
			return bitsPerPixel;
		} catch (IOException e) {
			e.printStackTrace();
			return -1;
		}
	}

}
