package cn.xm.yss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import cn.xm.yss.JavaMacro;

/**
 * 文件操作工具类
 * 
 * @author yesunsong
 *
 */
public class FileUtils {
	private static FileUtils utils;

	private FileUtils() {
	}

	public static FileUtils getInstance() {
		if (utils == null) {
			utils = new FileUtils();
		}
		return utils;
	}

	/**
	 * 是否包含子目录
	 * 
	 * @param file
	 * @return
	 */
	public boolean hasDirectory(File file) {
		boolean hasDirectory = false;
		if (!file.exists()) {
			return hasDirectory;
		}

		if (!file.isDirectory()) {
			return hasDirectory;
		}
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				hasDirectory = true;
				break;
			}
		}
		return hasDirectory;
	}
	
	/**
	 * 读取指定路径下的文本文件
	 * @param Path
	 * @return
	 */
	public String ReadFile(String Path) {
		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString + JavaMacro.LineBreak;	
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}
	
	/**
	 * 
	 * @param instance
	 * @param path 相对路径
	 * @return
	 */
	public String readFileInJar(Class instance,String path) {
		StringBuffer title = new StringBuffer();

		// 获取jar中资源的输入流
		InputStream is = this.getClass().getResourceAsStream(path);

		BufferedReader buff = new BufferedReader(new InputStreamReader(is));
		try {
			String line;
			while ((line = buff.readLine()) != null) {
				title.append(line);
			}
			buff.close();
			is.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return title.toString();
	}
}
