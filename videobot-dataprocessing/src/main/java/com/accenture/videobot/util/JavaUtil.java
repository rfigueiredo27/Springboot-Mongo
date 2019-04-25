package com.accenture.videobot.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public abstract class JavaUtil {
	
	public static String GetTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()+" ");
	}
	
	public static String GetTimeStampBracket() {
		return "[" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + "] ";
	}
	
	public static String GetTimeStampFile() {
		return new SimpleDateFormat("yyyyMMdd-HHmmss").format(Calendar.getInstance().getTime());
	}
	
	public static Date GetTimeStampDate() {
		return Calendar.getInstance().getTime();
	}

}
