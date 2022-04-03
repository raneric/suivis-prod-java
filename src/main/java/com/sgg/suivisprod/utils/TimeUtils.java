package com.sgg.suivisprod.utils;

public class TimeUtils {

	public static String convertDoubleTimeAsFormatedString(double doubleTime) {
		String stringTime = Double.toString(doubleTime);
		return getHour(stringTime) + ":" + getMinute(doubleTime);
	}

	private static String getHour(String stringTime) {
		String hour = stringTime.substring(0, stringTime.indexOf("."));
		return (hour.length() < 2) ? "0" + hour : hour;
	}

	private static String getMinute(double doubleTime) {
		int    minute         = (int) ((doubleTime - (int) doubleTime) * 60);
		String minuteAsString = Integer.toString(minute);
		return (minuteAsString.length() < 2) ? "0" + minuteAsString : minuteAsString;
	}
}
