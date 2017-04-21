import java.io.File;
import java.util.HashMap;

import cn.xm.yss.AntUtils;
import cn.xm.yss.JsonUtils;
import cn.xm.yss.OSinfo;

public class Main {
	private static String target = "delete_png";

	public static void main(String[] args) {
		String user_dir = System.getProperty("user.dir");
		// 配置文件的路径
		String config_path = user_dir + File.separator + "removePNG_cfg" + File.separator + "config.json";
		String xml_path = user_dir + File.separator + "removePNG_cfg" + File.separator + "removePNG.xml";
		System.out.println("-----config path:" + config_path);
		System.out.println("-----xml path:" + xml_path);

		String resource_path = "";
		JsonUtils.getInstance().parse(config_path);
		if (OSinfo.isWindows()) {
			resource_path = JsonUtils.getInstance().getString("Win_Resources_Path");
		}else if(OSinfo.isMacOS() || OSinfo.isMacOSX()){
			resource_path = JsonUtils.getInstance().getString("Mac_Resources_Path");
		}
		
		HashMap<String, String> map = new HashMap<>();
		map.put("directory_path", resource_path);
		AntUtils.getInstance().executeTarget(xml_path, target,map);
		
		System.out.println("The process of removing png ends!!!");
	}
}