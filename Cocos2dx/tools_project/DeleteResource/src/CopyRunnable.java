import java.io.File;
import java.util.HashMap;

import cn.xm.yss.AntUtils;

public class CopyRunnable implements Runnable {

	public String directory = "";
	public String file = "";
	public boolean isCopyDir = true;

	public String Resources_Path = "";
	
	@Override
	public void run() {
		String user_dir = System.getProperty("user.dir");
		String xml_path = user_dir + File.separator + "delete_cfg" + File.separator + "deleteRes.xml";
		System.out.println("-----xml path:" + xml_path);

		HashMap<String, String> map = new HashMap<>();
		map.put("resources", Resources_Path);
		
		if (isCopyDir) {
			map.put("directory", directory);
			AntUtils.getInstance().executeTarget(xml_path, "copyDir",map);
		} else {
			map.put("file", file);
			AntUtils.getInstance().executeTarget(xml_path, "copyFile",map);
		}
	}

}
