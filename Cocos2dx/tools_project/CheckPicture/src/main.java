import java.io.File;
import java.util.ArrayList;

import cn.xm.yss.FileUtils;
import cn.xm.yss.ImageUtils;
import cn.xm.yss.JavaMacro;
import cn.xm.yss.JsonUtils;
import cn.xm.yss.OSinfo;
import cn.xm.yss.StringUtils;
import net.sf.json.JSONObject;

/**
 * 检查png图的位深度（8,24,32）
 * 
 * @author yesunsong
 *
 */
public class Main {
	private static ArrayList<String> excludedFiles;
	private static ArrayList<String> excludedDirs;
	private static StringBuffer buffer;
	private static String resource_path;
	
	public static void main(String[] args) {
		excludedFiles = new ArrayList<String>();
		excludedDirs = new ArrayList<String>();
		buffer = new StringBuffer();
		
		// 配置文件的路径
		String config_path = System.getProperty("user.dir") + File.separator + "CheckPicture_cfg" + File.separator + "config.json";
		JSONObject jsonObject = JsonUtils.getInstance().parse(config_path);
		
		excludedFiles = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "Exculded", "file");
		excludedDirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "Exculded", "dir");
		resource_path = (String) jsonObject.get("Resources_Path");

		for (int i = 0; i < excludedFiles.size(); i++) {
			String tmp = resource_path + File.separator + excludedFiles.get(i);
			excludedFiles.set(i, tmp);
		}
		for (int i = 0; i < excludedDirs.size(); i++) {
			String tmp = resource_path + File.separator + excludedDirs.get(i);
			excludedDirs.set(i, tmp);
		}
		StringUtils.getInstance().replace(excludedFiles, JavaMacro.SLASH, JavaMacro.BACKSLASH);
		StringUtils.getInstance().replace(excludedDirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
		
		checkRecursive(resource_path);
		
		FileUtils.getInstance().SaveFile("png8.txt", buffer.toString());
		
		System.out.println("The process which check png8 in special directory ends!!!");
	}

	private static void checkRecursive(String path) {
		File file = new File(path);
		if (file.exists() && file.isDirectory()) {
			File[] files = file.listFiles();
			for (File temp : files) {
				if (temp.exists()) {
					String tempStr = temp.getPath();

					if (temp.isFile()) {
						if (isExcludedFile(tempStr)) {
							continue;
						}
						if (temp.getName().endsWith(JavaMacro.PNG_SUFFIX)) {
							int bitDepth = ImageUtils.getInstance().checkPngBitDepth(temp.getPath());
							if (bitDepth == 8) {
								buffer.append(temp.getPath()).append(JavaMacro.LineBreak);
							}
						}
					} else if (temp.isDirectory()) {
						if (isExcludedDir(tempStr)) {
							continue;
						}
						checkRecursive(temp.getPath());
					}
				}
			}
		}
	}

	private static boolean isExcludedFile(String targetFile) {
		for (String string : excludedFiles) {
			if (string.equals(targetFile)) {
				return true;
			}
		}
		return false;
	}

	private static boolean isExcludedDir(String targetFile) {
		for (String string : excludedDirs) {
			if (string.equals(targetFile)) {
				return true;
			}
		}
		return false;
	}

}