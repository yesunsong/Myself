package cn.xm.yss;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import cn.xm.yss.JavaMacro;

/**
 * 文件操作工具类
 * 
 * @author yesunsong
 *
 */
public class FileUtils {
	private static FileUtils utils;
	/** UTF8字符集 */
	private static String characterSet = "UTF-8";

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
	 * 
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

	public ArrayList<String> ReadFile2Array(String path) {
		String content = ReadFile(path);
		String[] tmp =content.split(JavaMacro.LineBreak);
		
		ArrayList<String> list = new ArrayList<>();
		for (int i = 0; i < tmp.length; i++) {
			list.add(tmp[i]);
		}		
		return list;
	}

	/** 保存文件 */
	public void SaveFile(String filename, String content) {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(new File(filename)));
			// 指定编码格式，不指定中文会出现乱码
			String str = new String(content.getBytes(characterSet), characterSet);
			writer.write(str);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** 读取指定目录下的文件 */
	public ArrayList<String> readDirectory(String directory) {
		ArrayList<String> list = new ArrayList<>();

		File dir = new File(directory);
		if (dir.exists() && dir.isDirectory()) {
			File[] files = dir.listFiles();
			readFiles(files, list);
		}

		return list;
	}

	/** 递归读取目录 */
	private void readFiles(File[] files, ArrayList<String> list) {
		File tmpFile;
		for (int i = 0; i < files.length; i++) {
			tmpFile = files[i];
			if (tmpFile.isDirectory()) {
				readFiles(tmpFile.listFiles(), list);
			} else {
				list.add(tmpFile.getPath());
				// System.out.println(tmpFile.getPath());
			}
		}
	}

	/**
	 * 
	 * @param instance
	 * @param path
	 *            相对路径
	 * @return
	 */
	public String readFileInJar(Class<?> instance, String path) {
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
