package test;

import java.util.ArrayList;

import cn.xm.yss.JavaMacro;
import cn.xm.yss.StringUtils;
import test.base.BaseTest;

public class StringUtilsTest extends BaseTest {

	@Override
	public void test(){
		ArrayList<String> ExcludedDirs =new ArrayList<String>();
		ExcludedDirs.add("G:/Myself/tools_project");		
		StringUtils.getInstance().replace(ExcludedDirs,JavaMacro.SLASH, JavaMacro.BACKSLASH);
		System.out.println(ExcludedDirs);
		//
		
	}
}
