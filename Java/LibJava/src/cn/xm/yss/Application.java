package cn.xm.yss;

public class Application {
	private static Application utils;

	private Application() {
	}

	public static Application getInstance() {
		if (utils == null) {
			utils = new Application();
		}
		return utils;
	}

	/**
	 * 打开默认浏览器
	 * @param url 网页地址
	 */
	public void openUrl(String url) {
		// 判断当前系统是否支持Java AWT Desktop扩展
		if (java.awt.Desktop.isDesktopSupported()) {
			try {
				// 创建一个URI实例,注意不是URL
				java.net.URI uri = java.net.URI.create("http://www.jb51.net");
				// 获取当前系统桌面扩展
				java.awt.Desktop dp = java.awt.Desktop.getDesktop();
				// 判断系统桌面是否支持要执行的功能
				if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
					// 获取系统默认浏览器打开链接
					dp.browse(uri);
				}
			} catch (java.lang.NullPointerException e) {
				// 此为uri为空时抛出异常
			} catch (java.io.IOException e) {
				// 此为无法获取系统默认浏览器
			}
		}
	}
}
