import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;

import cn.xm.yss.FileUtils;
import cn.xm.yss.JsonUtils;
import net.sf.json.JSONObject;

/**
 * 检查Cocos2dx游戏中的冗余资源 用法：
 * 
 * @author yesunsong
 *
 */
public class RedundantResources {
	/** 资源的相对路径-资源大小 */
	private HashMap<String, String> resourcesInGame;
	/** 资源的相对路径-资源大小 */
	private HashMap<String, String> resourcesInLocal;

	/** 本地资源目录(Debug/Release目录) */
	private String localDir = "";
	/** 游戏资源信息文件所在的目录 */
	private String gameResInfoFile = "";

	public RedundantResources() {
		resourcesInGame = new LinkedHashMap<String, String>();
		resourcesInLocal = new LinkedHashMap<String, String>();
	}

	@SuppressWarnings("unused")
	public void check() {
		// 配置文件的路径
		String config_path = System.getProperty("user.dir") + File.separator + "CheckRedundantResources_cfg"
				+ File.separator + "config.json";
		JSONObject jsonObject = JsonUtils.getInstance().parse(config_path);
		localDir = JsonUtils.getInstance().getString("Resources_Path");
		gameResInfoFile = JsonUtils.getInstance().getString("Game_Res_Info_File");

		ArrayList<String> list1 = FileUtils.getInstance().ReadFile2Array(gameResInfoFile);
		ArrayList<String> list2 = FileUtils.getInstance().readDirectory(localDir);

		for (int i = 0; i < list1.size(); i++) {
			String tmp = list1.get(i);
			resourcesInGame.put(tmp, tmp);
		}

		for (int i = 0; i < list2.size(); i++) {
			String tmp = list2.get(i);
			resourcesInLocal.put(tmp, tmp);
		}

		checkRedundantRes();

		System.out.println("The process which check redundant resources ends!!!");
	}

	/** 检查冗余 */
	private void checkRedundantRes() {
		Iterator<Entry<String, String>> iterator = resourcesInLocal.entrySet().iterator();
		Entry<String, String> entry;
		String content = "";
		while (iterator.hasNext()) {
			entry = iterator.next();
			if (!resourcesInGame.containsKey(entry.getKey())) {
				content += entry.getKey() + "\n";
				// System.out.println("找到未使用的资源："+entry.getKey());
			} else {
				// System.out.println("找到使用的资源："+entry.getKey());
			}
		}

		FileUtils.getInstance().SaveFile("redundantRes.txt", content);
	}
}
