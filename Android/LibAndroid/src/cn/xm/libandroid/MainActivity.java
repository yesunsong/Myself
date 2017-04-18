package cn.xm.libandroid;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.xm.libandroid.net.HtmlService;
import cn.xm.libandroid.net.ImageService;
import cn.xm.libandroid.testcase.base.BaseActivity;

public class MainActivity extends BaseActivity {
	public static String TAG = MainActivity.class.getSimpleName();
	/** 代理activity */
	private Activity proxyActivity;
	private AssetManager mAssetManager;
	private Resources mResources;
	private ImageView imageView;
	private TextView webCodeView;
	Handler handler = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.picture);
		imageView = (ImageView) findViewById(R.id.imageView);
		webCodeView = (TextView) findViewById(R.id.webCodeView);

		handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (msg.what == 1) {
					String path = "http://192.168.1.181:8080/ServerForPicture/yesunsong.jsp";
					try {
						String html = HtmlService.getHtml(path);
						webCodeView.setText(html);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
	}

	public void showImage(View view) {
		AsyncTaskThread thread = new AsyncTaskThread();
		thread.execute("http://192.168.1.181:8080/ServerForPicture/loading4-01.jpg");
	}

	public void showHtml(View view) {
		AsyncTaskThread0 thread = new AsyncTaskThread0();
		thread.execute("http://192.168.1.181:8080/ServerForPicture/yesunsong.jsp");

		// Message message=new Message();
		// message.what=1;
		// handler.sendMessage(message);

		// Thread thread=new Thread(new Runnable() {
		// public void run() {
		// String
		// path="http://192.168.1.181:8080/ServerForPicture/yesunsong.jsp";
		// try {
		// String html=HtmlService.getHtml(path);
		// webCodeView.setText(html);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
		// }
		// });
		// thread.start();
	}

	class AsyncTaskThread0 extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			String html = "";
			try {
				html = HtmlService.getHtml(params[0]);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return html;
		}

		@Override
		protected void onPostExecute(String result) {
			if (result != null) {
				webCodeView.setText(result);
			}
		}
	}

	// ====================
	class AsyncTaskThread extends AsyncTask<String, Integer, Bitmap> {

		@Override
		protected Bitmap doInBackground(String... params) {
			// publishProgress(0);
			// HttpClient client=new DefaultHttpClient();
			// publishProgress(30);HttpGet get=new HttpGet(params[0]);
			// final Bitmap bitmap;
			// try {
			// HttpResponse response=client.execute(get);
			// bitmap =
			// BitmapFactory.decodeStream(response.getEntity().getContent());
			// } catch (Exception e) {
			// e.printStackTrace();
			// return null;
			// }
			// publishProgress(100);
			//
			Bitmap bitmap = null;
			// String
			// path="http://192.168.1.181:8080/ServerForPicture/icon.png";
			try {
				bitmap = ImageService.getImage(params[0]);
			} catch (Exception exception) {
			}
			return bitmap;
		}

		@Override
		protected void onPostExecute(Bitmap result) {
			if (result != null) {
				imageView.setImageBitmap(result);
			}
		}
	}

	public void test() {
		Log.e(TAG, "test");
	}

	protected void testProtected() {
		Log.e(TAG, "testProtected");
	}

	protected void testProtected(int a, String b) {
		Log.e(TAG, "testProtected:a=" + a + ",b=" + b);
	}

	public void setActivity(Activity proxyActivity) {
		Log.d("sys", "setActivity..." + proxyActivity);
		this.proxyActivity = proxyActivity;
	}

	public void onProxyCreate(Bundle savedInstanceState) {
		TextView textView = new TextView(proxyActivity);
		textView.setText(getResources().getString(R.string.hello_world));
		proxyActivity.setContentView(textView);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
			// Nothing need to be done here
		} else {
			// Nothing need to be done here
		}
	}

	// {
	// Log.i(TAG, "path:" + this.getFilesDir().getAbsolutePath());
	// try {
	// // 保存文件
	// String text = "content00";
	// FileOutputStream outputStream = this.openFileOutput("test.txt",
	// MODE_WORLD_READABLE);
	// outputStream.write(text.getBytes());
	// outputStream.write("ctest。。。".getBytes());
	// outputStream.close();
	// // 读取文件
	// FileInputStream fin = this.openFileInput("test.txt");
	// byte[] b = new byte[fin.available()];
	// ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	//
	// while ((fin.read(b)) != -1) {
	// buffer.write(b);
	// }
	// byte[] data;
	// data = buffer.toByteArray();
	//
	// buffer.close();
	// fin.close();
	// Log.i(TAG, new String(data));
	// //
	// if (Environment.getExternalStorageState().equals(
	// Environment.MEDIA_MOUNTED)) {
	// File sdCardDir = Environment.getExternalStorageDirectory();// 获取SDCard目录
	// // File saveFile = new File(sdCardDir.getAbsoluteFile(),
	// // "a.txt");
	// //
	// String wholePathName = "/sdcard/test/a.txt";
	// // 检查文件夹
	// String parentDirs = wholePathName.substring(0,
	// wholePathName.lastIndexOf("/"));
	// //
	// boolean isCreateDirSuc = false;
	// boolean isCreateFileSuc = false;
	// File dir = new File(parentDirs);
	// if (!dir.exists()) {
	// isCreateDirSuc = dir.mkdirs();
	// } else {
	// isCreateDirSuc = true;
	// }
	// if (isCreateDirSuc) {
	// // 检查文件
	// File oneFile = new File(wholePathName);
	// if (!oneFile.exists()) {
	// isCreateFileSuc = oneFile.createNewFile();
	// } else {
	// isCreateFileSuc = true;
	// }
	//
	// if (isCreateFileSuc) {
	// // 写文件
	// InputStream in = this.getAssets().open("test.txt");
	//
	// FileOutputStream stream = new FileOutputStream(
	// oneFile);
	// DataOutputStream fos = new DataOutputStream(stream);
	//
	// byte[] buff = new byte[4096];
	// int n = in.read(buff);
	// while (n > 0) { // or in.available() > 0
	// fos.write(buff, 0, n);
	// n = in.read(buff);
	// }
	// fos.close();
	// in.close();
	//
	// Toast.makeText(MainActivity.this,
	// R.string.hello_world, Toast.LENGTH_LONG)
	// .show();
	// } else {
	// Toast.makeText(MainActivity.this, "创建文件失败",
	// Toast.LENGTH_LONG).show();
	// }
	// } else {
	// Toast.makeText(
	// MainActivity.this,
	// "创建文件夹失败,父目录：可读："
	// + dir.getParentFile().getParentFile()
	// .canRead()
	// + ",可写："
	// + dir.getParentFile().getParentFile()
	// .canWrite(), Toast.LENGTH_LONG)
	// .show();
	// }
	// }
	// } catch (IOException e) {
	// e.printStackTrace();
	// Log.i("test", e.toString());
	// Toast.makeText(MainActivity.this, e.toString(),
	// Toast.LENGTH_LONG).show();
	// }
	// }
}
