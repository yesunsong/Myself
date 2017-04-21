import java.util.HashMap;

import cn.xm.yss.AntUtils;

public class CopyRunnable implements Runnable {

	public String directory = "";
	public String file = "";
	public String Resources_Path = "";
	public String xml_path;
	public boolean isCopyDir = true;
	
	@Override
	public void run() {
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
