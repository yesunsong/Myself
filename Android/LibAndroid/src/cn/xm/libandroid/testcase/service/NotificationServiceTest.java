package cn.xm.libandroid.testcase.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore.Audio;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import cn.xm.libandroid.R;
import cn.xm.libandroid.AndroidUtils.CNotification;
import cn.xm.libandroid.implement.Application;
import cn.xm.libandroid.service.NotificationService;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 消息状态栏
 * @author yesunsong
 *
 */
public class NotificationServiceTest extends BaseActivity {
	private Button baseNotification;
	private Button updateBaseNotification;
	private Button clearBaseNotification;
	private Button mediaNotification;
	private Button clearMediaNotification;
	private Button clearALL;
	private Button customNotification;
	// 通知显示内容
	private PendingIntent pd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification_service_main);
		init();
	}

	private void init() {
		baseNotification = (Button) findViewById(R.id.le10bt01);
		updateBaseNotification = (Button) findViewById(R.id.le10bt02);
		clearBaseNotification = (Button) findViewById(R.id.le10bt03);
		mediaNotification = (Button) findViewById(R.id.le10bt04);
		clearMediaNotification = (Button) findViewById(R.id.le10bt05);
		clearALL = (Button) findViewById(R.id.le10bt06);
		customNotification = (Button) findViewById(R.id.le10bt07);

		baseNotification.setOnClickListener(onclick);
		updateBaseNotification.setOnClickListener(onclick);
		clearBaseNotification.setOnClickListener(onclick);
		mediaNotification.setOnClickListener(onclick);
		clearMediaNotification.setOnClickListener(onclick);
		clearALL.setOnClickListener(onclick);
		customNotification.setOnClickListener(onclick);

		Intent intent = new Intent(this, NotificationServiceTest.class);
		pd = PendingIntent.getActivity(NotificationServiceTest.this, 0, intent, 0);
	}

	OnClickListener onclick = new OnClickListener() {
		private int Notification_ID_BASE = 110;
		private int Notification_ID_MEDIA = 119;
		private CNotification baseNF;
		private CNotification mediaNF;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.le10bt01:
				baseNF = new CNotification();
				baseNF.setIcon(R.drawable.icon); 
				baseNF.setTickerText("You clicked BaseNF!");
				baseNF.setDefaults(Notification.DEFAULT_SOUND);
				baseNF.setDefaults(Notification.DEFAULT_VIBRATE);
				baseNF.setDefaults(Notification.DEFAULT_LIGHTS);
				baseNF.setFlags(Notification.FLAG_INSISTENT);
				baseNF.setFlags(Notification.FLAG_AUTO_CANCEL);
				baseNF.setFlags(Notification.FLAG_NO_CLEAR);
				baseNF.setLatestEventInfo(NotificationServiceTest.this, "Title01","Content01", pd);
				NotificationService.getInstance().notify(Notification_ID_BASE, baseNF);
				break;

			case R.id.le10bt02:
				baseNF.setLatestEventInfo(NotificationServiceTest.this, "Title02","Content02", pd);
				NotificationService.getInstance().notify(Notification_ID_BASE, baseNF);
				break;

			case R.id.le10bt03:
				NotificationService.getInstance().cancel(Notification_ID_BASE);
				break;

			case R.id.le10bt04:
				long[] vir = { 0, 100, 200, 300 };
				mediaNF = new CNotification();
				mediaNF.icon = R.drawable.icon;
				mediaNF.tickerText = "You clicked MediaNF!";
				mediaNF.setSound(Uri.withAppendedPath(Audio.Media.INTERNAL_CONTENT_URI, "6"));
				mediaNF.setVibrate(vir);
				mediaNF.setLatestEventInfo(Application.activity, "Title03","Content03", pd);
				NotificationService.getInstance().notify(Notification_ID_MEDIA, mediaNF);
				break;

			case R.id.le10bt05:
				NotificationService.getInstance().cancel(Notification_ID_MEDIA);
				break;

			case R.id.le10bt06:
				NotificationService.getInstance().cancelAll();
				break;

			case R.id.le10bt07:
				// 自定义下拉视图，比如下载软件时，显示的进度条。
				RemoteViews contentView = new RemoteViews(getPackageName(),R.layout.notification_service_custom);
				contentView.setImageViewResource(R.id.image, R.drawable.icon);
				contentView.setTextViewText(R.id.text,"Hello, this message is in a custom expanded view");
				
				CNotification notification = new CNotification();
				notification.setIcon(R.drawable.icon);
				notification.setTickerText("Custom!");
				notification.setContentView(contentView);
				notification.setContentIntent(pd);
				notification.setFlags(Notification.FLAG_NO_CLEAR);

				NotificationService.getInstance().notify(3, notification);
				break;
			}
		}
	};
}
