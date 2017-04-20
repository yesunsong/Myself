import java.io.File;
import java.lang.Thread.State;
import java.util.ArrayList;
import java.util.HashMap;

import cn.xm.yss.AntUtils;
import cn.xm.yss.JavaMacro;
import cn.xm.yss.JsonUtils;
import cn.xm.yss.OSinfo;
import cn.xm.yss.StringUtils;
import net.sf.json.JSONObject;

public class Main {
	//
	public static String curVersion;
	//
	private static ArrayList<String> commonDirs;
	private static ArrayList<String> commonFiles;
	//
	private static ArrayList<String> curDirs;
	private static ArrayList<String> curFiles;

	private static String Resources_Path = "";

	private static final String DIR_KEY = "dir";
	private static final String FILE_KEY = "file";

	private static ArrayList<Thread> threads;

	public static void main(String[] args) {
		commonDirs = new ArrayList<>();
		commonFiles = new ArrayList<>();
		curDirs = new ArrayList<>();
		curFiles = new ArrayList<>();
		threads = new ArrayList<>();
		// 
		String user_dir = System.getProperty("user.dir");
		String config_path = user_dir + File.separator + "delete_cfg" + File.separator + "config.json";
		String xml_path = user_dir + File.separator + "delete_cfg" + File.separator + "deleteRes.xml";
		System.out.println("-----config path:" + config_path);
		System.out.println("-----xml path:" + xml_path);

		JSONObject jsonObject = JsonUtils.getInstance().parse(config_path);
		curVersion = JsonUtils.getInstance().getString("curVer");
		commonDirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "common", DIR_KEY);
		commonFiles = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "common", FILE_KEY);
		curDirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, curVersion, DIR_KEY);
		curFiles = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, curVersion, FILE_KEY);

		if (OSinfo.isWindows()) {
			Resources_Path = JsonUtils.getInstance().getString("Win_Resources_Path");
			Resources_Path = StringUtils.getInstance().replace(Resources_Path, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(commonDirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(curDirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
		} else if (OSinfo.isMacOS() || OSinfo.isMacOSX()) {
			Resources_Path = JsonUtils.getInstance().getString("Mac_Resources_Path");
		}

		AntUtils.getInstance().executeTarget(xml_path, "init");
		
		execute(commonDirs, true);
		execute(commonFiles, false);
		execute(curDirs, true);
		execute(curFiles, false);

		while (true) {
			int count = 0;
			for (Thread thread : threads) {
				if (thread.getState() == State.TERMINATED) {
					count++;
				}
			}
			if (count == threads.size()) {
				HashMap<String, String> map = new HashMap<>();
				map.put("resources", Resources_Path);
				AntUtils.getInstance().executeTarget(xml_path, "clean", map);
				System.out.println("The process of deleting resource ends!!! -------------");
				break;
			}
		}
	}

	public static void execute(ArrayList<String> list, boolean isCopyDir) {
		if (!list.isEmpty()) {
			for (int i = 0; i < list.size(); i++) {
				CopyRunnable copyRunnable = new CopyRunnable();
				copyRunnable.Resources_Path = Resources_Path;
				copyRunnable.isCopyDir = isCopyDir;
				
				String tmp = list.get(i);
				File tmpFile = new File(Resources_Path + (OSinfo.isWindows()?JavaMacro.BACKSLASH:JavaMacro.SLASH) + tmp);
				if (!tmpFile.exists()) {
					System.out.println(tmpFile.getAbsolutePath() + " not occur");
					continue;
				}
				if (isCopyDir) {
					copyRunnable.directory = list.get(i);
				} else {
					copyRunnable.file = list.get(i);
				}

				Thread thread = new Thread(copyRunnable);
				thread.start();

				threads.add(thread);
			}
		}
	}
}