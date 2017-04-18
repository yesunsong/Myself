package cn.xm.libandroid.AndroidUtils.no;

import android.app.SearchManager;
import android.content.Context;
import cn.xm.libandroid.service.base.Service;

public class SearchService extends Service {
	private static SearchService instance;
	private SearchManager searchManager;
	
	private SearchService(Context context){
		searchManager=(SearchManager) context.getSystemService(Context.SEARCH_SERVICE);
	}
	
	public static SearchService getInstance(){
		if (instance==null) {
			instance=new SearchService(context);
		}
		return instance;
	}
	// <<===============================>>
}
