package test;

import cn.xm.yss.FileUtils;
import test.base.BaseTest;

public class FileUtilsTest extends BaseTest{

	@Override
	public void test() {
		String content = FileUtils.getInstance().ReadFile("log.md");
		System.out.println("file content:\n" + content);
		//
	}
}
