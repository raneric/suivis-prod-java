package com.sgg.suivisprod.utils;

public class TimeUtils {

	public static String convertDoubleTimeAsFormatedString(double doubleTime) {
		String stringTime = Double.toString(doubleTime);
		return getStringHour(stringTime) + ":" + getStringMinute(doubleTime);
	}

	private static String getStringHour(String stringTime) {
		String hour = stringTime.substring(0, stringTime.indexOf("."));
		return (hour.length() < 2) ? "0" + hour : hour;
	}

	private static String getStringMinute(double doubleTime) {
		int    minute         = (int) ((doubleTime - (int) doubleTime) * 60);
		String minuteAsString = Integer.toString(minute);
		return (minuteAsString.length() < 2) ? "0" + minuteAsString : minuteAsString;
	}

	public static double convertFormatedStringTimeToDouble(String stringFormatedTime) {

		return 1.0;
	}

	private double convertDoubleTimeAsForrmatedAsString(String time) {
		return getHourAsDouble(time) + getMinuteAsDouble(time);
	}

	private double getHourAsDouble(String time) {
		return Double.parseDouble(time.substring(0, time.indexOf(":")));
	}

	private double getMinuteAsDouble(String time) {
		return Double.parseDouble(time.substring(time.indexOf(":")));
	}
}
