package cn.xm.libandroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import cn.xm.libandroid.AndroidUtils.CNotification;
import cn.xm.libandroid.testcase.service.NotificationServiceTest;

/**
 * 消息通知的服务
 * @author yesunsong
 *
 */
public class NotificationService extends Service {
	MessageThread thread;
	PendingIntent contentIntent;
	CNotification messageNotification;
	NotificationManager manager;
	private int messageNotificationID = 1000;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		thread.isRunning=false;
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		messageNotification = new CNotification();
		messageNotification.setIcon(R.drawable.icon);
		messageNotification.setTickerText("新消息");
		messageNotification.setDefaults(Notification.DEFAULT_SOUND);;
		
		manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Intent intent2=new Intent(this, NotificationServiceTest.class);
		contentIntent=PendingIntent.getActivity(this, 0, intent2, 0);
		
		thread = new MessageThread();
		thread.isRunning=true;
		thread.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	class MessageThread extends Thread{
		public boolean isRunning=true;

		public void run(){
//			while (isRunning) {
				try {
					Thread.sleep(5000);
					String message=getServerMessage();
					if(message!=null && !message.equals("")){
						messageNotification.setLatestEventInfo(getApplicationContext(), "title", message , contentIntent);
						manager.notify(messageNotificationID, messageNotification);
						messageNotificationID++;
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//			}
		}
	}
	
	public String getServerMessage(){
		return "NEWS";
	}

}
