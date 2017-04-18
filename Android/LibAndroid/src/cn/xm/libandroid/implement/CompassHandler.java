package cn.xm.libandroid.implement;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 执行所有罗盘处理
 * @author yesunsong
 *
 */
public class CompassHandler implements SensorEventListener {
	private float yaw;
	private float pitch;
	private float roll;
	
	public CompassHandler(Context context){
		SensorManager sensorManager= (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
		if (sensorManager.getSensorList(Sensor.TYPE_ORIENTATION).size()!=0) {
			Sensor compass=sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
			sensorManager.registerListener(this, compass,SensorManager.SENSOR_DELAY_GAME);
		}
	}
	
	@Override
	public void onSensorChanged(SensorEvent event) {
		yaw=event.values[0];
		pitch=event.values[1];
		roll=event.values[2];
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		//nothing to do here
	}
	
	public float getYaw() {
		return yaw;
	}
	
	public float getPitch() {
		return pitch;
	}
	
	public float getRoll() {
		return roll;
	}

}
