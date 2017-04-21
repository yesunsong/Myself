import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import cn.xm.yss.ArrayUtils;
import cn.xm.yss.JavaMacro;
import cn.xm.yss.JsonUtils;
import cn.xm.yss.OSinfo;
import cn.xm.yss.StringUtils;
import net.sf.json.JSONObject;

public class GeneratePVRMain {
	private static final String RGBA8888 = "RGBA8888";
	private static final String RGBA4444 = "RGBA4444";
	private static final String Dither_FS_ALPHA = "fs-alpha";
	private static final String Dither_NONE = "none-nn";
	private static final String ALPHA = "--premultiply-alpha";
	private static String single_target = "single";

	private static String Resources_Path = "";
	private static String TexturePacker_Path = "";
	private static ArrayList<String> ExcludedDirs;
	private static ArrayList<String> ExcludedFiles;
	private static ArrayList<String> RGBA8888Dirs;
	private static ArrayList<String> RGBA8888Files;
	private static ArrayList<String> UseAlphaDirs;
	private static ArrayList<String> UseAlphaFiles;

	private static Project p;

	private static HashMap<String, Property> infos;

	private static int none_pro = -1;
	private static int rgba8888_pro = 1;
	private static int alpha_pro = 2;

	private static String xml_path;

	public static void main(String[] args) {
		String user_dir = System.getProperty("user.dir");

		String config_path = user_dir + File.separator + "generatePVR_cfg" + File.separator + "config.json";
		xml_path = user_dir + File.separator + "generatePVR_cfg" + File.separator + "generatePVR.xml";
		System.out.println("-----config path:" + config_path);
		System.out.println("-----xml path:" + xml_path);

		// String JsonContext = FileUtils.getInstance().ReadFile(config_path);
		JSONObject jsonObject = JsonUtils.getInstance().parse(config_path);

		ExcludedDirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "ExculdedDir", "dir");
		ExcludedFiles = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "ExculdedFile", "file");
		RGBA8888Dirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "RGBA8888Dir", "dir");
		RGBA8888Files = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "RGBA8888File", "file");
		UseAlphaDirs = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "UseAlphaDir", "dir");
		UseAlphaFiles = JsonUtils.getInstance().JSONArray2JavaArray(jsonObject, "UseAlphaFile", "file");

		infos = new HashMap<String, Property>();
		if (OSinfo.isWindows()) {
			Resources_Path = JsonUtils.getInstance().getString("Win_Resources_Path");
			TexturePacker_Path = JsonUtils.getInstance().getString("Win_TexturePacker_Path");

			Resources_Path = StringUtils.getInstance().replace(Resources_Path, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			TexturePacker_Path = StringUtils.getInstance().replace(TexturePacker_Path, JavaMacro.SLASH,
					JavaMacro.BACKSLASH);

			StringUtils.getInstance().replace(ExcludedDirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(ExcludedFiles, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(RGBA8888Dirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(RGBA8888Files, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(UseAlphaFiles, JavaMacro.SLASH, JavaMacro.BACKSLASH);
			StringUtils.getInstance().replace(UseAlphaDirs, JavaMacro.SLASH, JavaMacro.BACKSLASH);
		} else if (OSinfo.isMacOS() || OSinfo.isMacOSX()) {
			Resources_Path = (String) jsonObject.get("Mac_Resources_Path");
			TexturePacker_Path = (String) jsonObject.get("Mac_TexturePacker_Path");
		}

		System.out.println("Resources Path    :" + Resources_Path);
		System.out.println("TexturePacker Path:" + TexturePacker_Path);
		System.out.println("Exculded Dir      :" + ExcludedDirs.toString());
		System.out.println("Exculded File     :" + ExcludedFiles.toString());
		System.out.println("Inculded Dir      :" + RGBA8888Dirs.toString());
		System.out.println("Inculded File\t  :" + RGBA8888Files.toString());
		System.out.println("Use Alpha File\t  :" + UseAlphaFiles.toString());

		File buildFile = new File(xml_path);

		p = new Project();

		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(2);
		p.addBuildListener(consoleLogger);
		try {
			p.fireBuildStarted();

			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();

			helper.parse(p, buildFile);

			executeAnt(p);
		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}
	}

	private static void executeAnt(Project p) {
		p.setUserProperty("TexturePacker_Path", TexturePacker_Path);
		p.setUserProperty("folder_path", Resources_Path);

		onlyGeneratePVR();

		check2(new File(Resources_Path));
		execute();
	}

//	private 
	private static void execute() {
		int index = 0;
		boolean hasRemaining = (infos.size()%10 == 0);
		HashMap<String, Property> tmpInfos = new HashMap<>();
		
		Iterator<?> iter = infos.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry<String, Property> entry = (Map.Entry<String, Property>) iter.next();
			String relativePath = (String) entry.getKey();
			Property property = (Property) entry.getValue();			
			
			if (index%10 == 0) {
				TargetRunnable targetRunnable = new TargetRunnable();
				targetRunnable.xml_path = xml_path;
				targetRunnable.Resources_Path = Resources_Path;
				targetRunnable.TexturePacker_Path = TexturePacker_Path;
				targetRunnable.infos = infos;
				
				Thread thread = new Thread(targetRunnable);
				thread.start();
				
				tmpInfos.clear();
			}else {
				if (hasRemaining) {
					if (index == (infos.size() - 1)) {
						TargetRunnable targetRunnable = new TargetRunnable();
						targetRunnable.xml_path = xml_path;
						targetRunnable.Resources_Path = Resources_Path;
						targetRunnable.TexturePacker_Path = TexturePacker_Path;
						targetRunnable.infos = tmpInfos;
						
						Thread thread = new Thread(targetRunnable);
						thread.start();
						
						tmpInfos.clear();
					}else{
						tmpInfos.put(relativePath, property);
					}
				}else{
					tmpInfos.put(relativePath, property);
				}
			}
			
			index++;
			
//			single_target(relativePath, property);
		}
	}

	private static void onlyGeneratePVR() {
		for (int i = 0; i < ExcludedFiles.size(); i++) {
			String filepath = ExcludedFiles.get(i);
			File file = new File(Resources_Path + File.separator + filepath);
			String filename = file.getName().substring(0, file.getName().indexOf("."));

			p.setUserProperty("folder_path", file.getParentFile().getPath());
			p.setUserProperty("filename", filename);
			p.setUserProperty("plist_name", filename + "_tmp");
			p.setUserProperty("pvr_name", filename);

			boolean isIn = ArrayUtils.getInstance().removeWhenOccur(RGBA8888Files, filepath);
			if (isIn) {
				p.setUserProperty("PixelFormat", RGBA8888);
				p.setUserProperty("DitherMode", Dither_NONE);
				p.setUserProperty("Use_alpha", ALPHA);
			} else {
				p.setUserProperty("PixelFormat", RGBA4444);
				p.setUserProperty("DitherMode", Dither_FS_ALPHA);
				p.setUserProperty("Use_alpha", "");
			}
			p.executeTarget("rename_file");
			p.executeTarget(single_target);
			p.setUserProperty("delete_filename", file.getParent() + File.separator + filename + ".png");
			p.executeTarget("delete_file");
		}

		for (int i = 0; i < ExcludedFiles.size(); i++) {
			String filepath = ExcludedFiles.get(i);
			File file0 = new File(Resources_Path + File.separator + filepath);
			String filename = file0.getName().substring(0, file0.getName().indexOf("."));

			p.setUserProperty("folder_path", file0.getParentFile().getPath());
			p.setUserProperty("filename", filename);
			p.setUserProperty("plist_name", filename + "_tmp");
			p.setUserProperty("pvr_name", filename);

			p.setUserProperty("delete_filename", file0.getParent() + File.separator + filename + "_tmp.plist");
			p.executeTarget("delete_file");

			p.executeTarget("recovery_file");
			ExcludedFiles.remove(i);
			i--;
		}
	}

	private static void check2(File file) {
		if (file.exists()) {
			if (file.isDirectory()) {
				checkDirectory2(file);
			} else {
				String relativePath = getRelativePath(file);
				if (relativePath.endsWith(JavaMacro.PNG_SUFFIX)) {
					System.out.println("record file:" + relativePath);

					boolean isInExcludedFile = ArrayUtils.getInstance().removeWhenOccur(ExcludedFiles, relativePath);
					if (isInExcludedFile) {
						return;
					}
					setProperty(relativePath, none_pro);

					boolean isInRGBA8888 = ArrayUtils.getInstance().removeWhenOccur(RGBA8888Files, relativePath);
					if (isInRGBA8888) {
						setProperty(relativePath, rgba8888_pro);
					}
					boolean isInAlpha = ArrayUtils.getInstance().removeWhenOccur(UseAlphaFiles, relativePath);
					if (isInAlpha) {
						setProperty(relativePath, alpha_pro);
					}
				}
			}
		}
	}

	private static String getRelativePath(File dir) {
		int index = dir.getPath().indexOf(Resources_Path);
		String relativePath;
		if (dir.getPath().equals(Resources_Path)) {
			relativePath = "";
		} else {
			relativePath = dir.getPath().substring(index + Resources_Path.length() + 1, dir.getPath().length());
		}
		return relativePath;
	}

	private static void checkDirectory2(File dir) {
		String relativePath = getRelativePath(dir);

		boolean isInExcludedDir = ArrayUtils.getInstance().removeWhenOccur(ExcludedDirs, relativePath);
		if (isInExcludedDir) {
			return;
		}
		boolean isInIncludeDir = ArrayUtils.getInstance().removeWhenOccur(RGBA8888Dirs, relativePath);
		if (isInIncludeDir) {
			recordDirectory(dir, rgba8888_pro);
			return;
		}
		boolean isInAlpha = ArrayUtils.getInstance().removeWhenOccur(UseAlphaDirs, relativePath);
		if (isInAlpha) {
			recordDirectory(dir, alpha_pro);
			return;
		}
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			check2(files[i]);
		}
	}

	private static void setProperty(String relativePath, int property) {
		boolean isContain = infos.containsKey(relativePath);
		Property property2;
		if (isContain) {
			property2 = infos.get(relativePath);
		} else {
			property2 = new Property();
		}
		if (property == rgba8888_pro) {
			property2.PixelFormat = RGBA8888;
			property2.DitherMode = Dither_NONE;
		} else if (property == alpha_pro) {
			property2.Use_alpha = ALPHA;
		}
		infos.put(relativePath, property2);
	}

	private static void recordDirectory(File dir, int property) {
		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			if (file.isDirectory()) {
				recordDirectory(file, property);
			} else {
				String relativePath = getRelativePath(file);
				if (relativePath.endsWith(JavaMacro.PNG_SUFFIX)) {
					System.out.println("recordDirectory:" + relativePath);
					setProperty(relativePath, property);
				}
			}
		}
	}

//	private static void single_target(String relativePath, Property property) {
//		String filename = "";
//		String tmp = "";
//		if (OSinfo.isWindows()) {
//			filename = relativePath.substring(relativePath.indexOf("\\") + 1, relativePath.indexOf("."));
//			tmp = relativePath.substring(0, relativePath.indexOf('\\'));
//		} else if (OSinfo.isMacOSX() || OSinfo.isMacOS()) {
//			filename = relativePath.substring(relativePath.indexOf("/") + 1, relativePath.indexOf("."));
//			tmp = relativePath.substring(0, relativePath.indexOf('/'));
//		}
//
//		// p.setUserProperty("PixelFormat", property.PixelFormat);
//		// p.setUserProperty("DitherMode", property.DitherMode);
//		// p.setUserProperty("Use_alpha", property.Use_alpha);
//		// p.setUserProperty("folder_path", Resources_Path + File.separator +
//		// tmp);
//		// p.setUserProperty("filename", filename);
//		// p.setUserProperty("plist_name", filename);
//		// p.setUserProperty("pvr_name", filename);
//		// p.executeTarget(single_target);
//
//		HashMap<String, String> properties = new HashMap<>();
//		properties.put("TexturePacker_Path", TexturePacker_Path);
//		properties.put("folder_path", Resources_Path);
//		properties.put("PixelFormat", property.PixelFormat);
//		properties.put("DitherMode", property.DitherMode);
//		properties.put("Use_alpha", property.Use_alpha);
//		properties.put("folder_path", Resources_Path + File.separator + tmp);
//		properties.put("filename", filename);
//		properties.put("plist_name", filename);
//		properties.put("pvr_name", filename);
//
//		LinkedList<String> targets = new LinkedList<>();
//		targets.add(single_target);
//
////		try {
////			TargetRunnable targetRunnable = new TargetRunnable();
////			targetRunnable.xml_path = xml_path;
////			targetRunnable.properties = properties;
////			targetRunnable.targets = targets;
////
////			Thread thread = new Thread(targetRunnable);
////			thread.start();
////		} catch (Exception e) {
////			e.printStackTrace();
////		}
//	}

}
