package cn.xm.libandroid.testcase;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import cn.xm.libandroid.AndroidUtils.no.SensorService;
import cn.xm.libandroid.testcase.base.BaseActivity;

/**
 * 测试加速计
 * @author yesunsong
 *
 */
public class AccelerometerTest extends BaseActivity implements SensorEventListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		if (SensorService.getInstance().hasAccelerometer()) {
			textView.setText("No accelerometer installed");
		} else {
			Sensor accelerometer = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			if (!sensorManager.registerListener(this, accelerometer,SensorManager.SENSOR_DELAY_GAME)) {
				textView.setText("Couldn't regster sensor listener");
			}
		}
	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		builder.setLength(0);
		builder.append("x:").append(event.values[0]).append(",");
		builder.append("y:").append(event.values[1]).append(",");
		builder.append("z:").append(event.values[2]);
		textView.setText(builder.toString());
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
	}
}
