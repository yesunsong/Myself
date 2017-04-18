package cn.xm.libandroid.framework;

public interface Game {
	public Input getInput();

	public FileIO getFileIO();

	public Graphics getGraphics();

	public Audio getAudio();

	/** 设置游戏的当前画面 */
	public void setScreen(Screen screen);

	/** 返回当前的活动Screen实例 */
	public Screen getCurrenScreen();

	public Screen getStartScreen();
}
