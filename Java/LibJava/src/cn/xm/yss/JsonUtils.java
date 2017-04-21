package cn.xm.yss;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtils {
	private static JsonUtils utils;
	private JSONObject jsonObject;
	private JsonUtils() {
	}

	public static JsonUtils getInstance() {
		if (utils == null) {
			utils = new JsonUtils();
		}
		return utils;
	}

	public JSONObject parse(String path) {
		String JsonContext = FileUtils.getInstance().ReadFile(path);
		jsonObject = JSONObject.fromObject(JsonContext);
		return jsonObject;
	}

	public ArrayList<String> JSONArray2JavaArray(JSONObject object, String key1, String key2) {
		ArrayList<String> list = new ArrayList<String>();

		JSONArray tmp = object.getJSONArray(key1);
		if (!tmp.isEmpty()) {
			String value = "";
			JSONObject obj;
			for (int index = 0; index < tmp.size(); index++) {
				obj = (JSONObject) tmp.get(index);
				value = (String) obj.get(key2);
				if (value != null) {
					list.add(value);
					System.out.println(value);
				}
			}
		}
		return list;
	}
	
	public String getString(String key){
		if (jsonObject != null) {
			return (String)jsonObject.get(key);
		}
		return "";
	}

	public int getInt(String key){
		if (jsonObject != null) {
			return (int)jsonObject.get(key);
		}
		return -1;		
	}
}
