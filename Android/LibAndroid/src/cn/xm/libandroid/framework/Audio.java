package cn.xm.libandroid.framework;

import java.io.IOException;

public interface Audio {
	/**
	 * 
	 * @param fileName
	 *            APK中的资源文件
	 * @return
	 * @throws IOException
	 *             指定文件不存在或受损时 抛出异常
	 */
	public Music newMusic(String filename) throws IOException;

	/**
	 * 
	 * @param fileName
	 *            APK中的资源文件
	 * @return
	 * @throws IOException
	 *             指定文件不存在或受损时 抛出异常
	 */
	public Sound newSound(String filename) throws IOException;
}
