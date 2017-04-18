package cn.xm.libandroid.AndroidUtils;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.net.Uri;
import android.widget.RemoteViews;

/**
 * 状态栏通知类
 * @author yesunsong
 *
 */
public class CNotification extends Notification {

	/**设置 通知在状态栏显示的图标*/
	public void setIcon(int icon) {
		this.icon = icon;
	}
	
	/**设置 通知在状态栏显示的提示信息*/
	public void setTickerText(String tickerText){
		this.tickerText = tickerText;
	}
	
	public void setWhen(long when){
		this.when = when;
	}
	
	// Notification.FLAG_INSISTENT:让声音、振动无限循环，直到用户响应
	// Notification.FLAG_AUTO_CANCEL:通知被点击后，自动消失
	// Notification.FLAG_NO_CLEAR:点击'Clear'时，不清楚该通知(QQ的通知无法清除，就是用的这个)
	public void setFlags(int flags){
		this.flags |= flags;
	}
	
	/**设置 自定义声音*/
	public void setSound(Uri sound){
		this.sound = sound;
	}
	
	/**
	 * 设置 震动
	 * @param vibrate long[] vir = { 0, 100, 200, 300 };第一个参数: 振动前等待的时间;第二个参数： 第一次振动的时长、以此类推
	 */
	public void setVibrate(long[] vibrate){
		this.vibrate=vibrate;
	}
	
	/**通知的默认参数:Notification.DEFAULT_ALL, Notification.DEFAULT_SOUND, DEFAULT_VIBRATE, DEFAULT_LIGHTS*/
	public void setDefaults(int defaults){
		this.defaults |= defaults;
	}
	
	public void setContentIntent(PendingIntent contentIntent){
		this.contentIntent=contentIntent;
	}
	
	/**
	 * 设置自定义下拉视图【使用此设置时，不需要再调用setLatestEventInfo()方法,但是必须定义 contentIntent】
	 * @param contentView
	 */
	public void setContentView(RemoteViews contentView){
		this.contentView = contentView;
	}
	
	/**
	 * 更新通知 【比如状态栏提示有一条新短信，还没来得及查看，又来一条新短信的提示。此时采用更新原来通知的方式比较。(再重新发一个通知也可以，但是这样会造成通知的混乱，而且显示多个通知给用户，对用户也不友好)】
	 * @param context
	 * @param contentTitle 下拉状态栏时显示的消息标题
	 * @param contentText 下拉状态栏时显示的消息内容
	 * @param contentIntent 点击该通知时执行页面跳转
	 */
	public void setLatestEventInfo(Context context,String contentTitle, String contentText, PendingIntent contentIntent) {
		super.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
	}
	
	
}
