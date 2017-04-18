package cn.xm.libandroid.implement;

import java.util.Calendar;

import cn.xm.libandroid.framework.Time;

public class AndroidTime implements Time {

	public int getSystemYear(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
	public int getSystemMonth(){
		return Calendar.getInstance().get(Calendar.MONTH);
	}
	
	public int getSystemDay(){
		return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	}
	
	public int getSystemHour(){
		return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	public int getSystemMinute(){
		return Calendar.getInstance().get(Calendar.MINUTE);
	}
	
	public int getSystemSecond(){
		return Calendar.getInstance().get(Calendar.YEAR);
	}
	
}
