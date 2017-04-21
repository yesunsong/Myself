import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import cn.xm.yss.AntUtils;
import cn.xm.yss.OSinfo;

public class TargetRunnable implements Runnable {
//	public LinkedList<String> targets;
//	public HashMap<String, String> properties;
	public String xml_path;
	public HashMap<String, Property> infos;
	public String Resources_Path = "";
	public String TexturePacker_Path = "";
	
	@Override
	public void run() {
		Iterator<?> iterator =  infos.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Property> entry = (Entry<String, Property>) iterator.next();
			String relativePath = entry.getKey();
			Property property = entry.getValue();
//			LinkedList<String> targets = new LinkedList<>();
//			targets.add("single");
			
			single_target(relativePath, property);
//			AntUtils.getInstance().executeTarget(xml_path, targets, properties);
		}
		
	}
	
	private void single_target(String relativePath, Property property) {
		String filename = "";
		String tmp = "";
		if (OSinfo.isWindows()) {
			filename = relativePath.substring(relativePath.indexOf("\\") + 1, relativePath.indexOf("."));
			tmp = relativePath.substring(0, relativePath.indexOf('\\'));
		} else if (OSinfo.isMacOSX() || OSinfo.isMacOS()) {
			filename = relativePath.substring(relativePath.indexOf("/") + 1, relativePath.indexOf("."));
			tmp = relativePath.substring(0, relativePath.indexOf('/'));
		}

		// p.setUserProperty("PixelFormat", property.PixelFormat);
		// p.setUserProperty("DitherMode", property.DitherMode);
		// p.setUserProperty("Use_alpha", property.Use_alpha);
		// p.setUserProperty("folder_path", Resources_Path + File.separator +
		// tmp);
		// p.setUserProperty("filename", filename);
		// p.setUserProperty("plist_name", filename);
		// p.setUserProperty("pvr_name", filename);
		// p.executeTarget(single_target);

		HashMap<String, String> properties = new HashMap<>();
		properties.put("TexturePacker_Path", TexturePacker_Path);
		properties.put("folder_path", Resources_Path);
		properties.put("PixelFormat", property.PixelFormat);
		properties.put("DitherMode", property.DitherMode);
		properties.put("Use_alpha", property.Use_alpha);
		properties.put("folder_path", Resources_Path + File.separator + tmp);
		properties.put("filename", filename);
		properties.put("plist_name", filename);
		properties.put("pvr_name", filename);

		LinkedList<String> targets = new LinkedList<>();
		targets.add("single");

		AntUtils.getInstance().executeTarget(xml_path, targets, properties);
		
//		try {
//			TargetRunnable targetRunnable = new TargetRunnable();
//			targetRunnable.xml_path = xml_path;
//			targetRunnable.properties = properties;
//			targetRunnable.targets = targets;
//
//			Thread thread = new Thread(targetRunnable);
//			thread.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

	}

}
