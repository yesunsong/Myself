package cn.xm.libandroid.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface FileIO {
	/** 从应用程序的APK文件中读取资源文件 */
	public InputStream readAsset(String filename) throws IOException;

	/** 从SD卡（外部存储）上读 文件 */
	public InputStream readFile(String filename) throws IOException;

	/** 从SD卡（外部存储）上写文件 */
	public OutputStream writeFile(String filename) throws IOException;
}
