package cn.xm.libandroid.testcase;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class SharedPreferencesTest extends BaseActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// 公共的
		SharedPreferences prefs = PreferenceManager
				.getDefaultSharedPreferences(this);
		// 允许选择共享首选项的私密程度
		SharedPreferences prefs1 = getPreferences(Context.MODE_PRIVATE);
		// 保存
		Editor editor = prefs.edit();
		editor.putString("key1", "banana");
		editor.commit();
		// 取值
		String value1 = prefs.getString("key1", null);
	}

}
