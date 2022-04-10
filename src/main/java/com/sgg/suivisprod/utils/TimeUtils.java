package com.sgg.suivisprod.utils;

public class TimeUtils {

	/**
	 * 
	 * @param doubleTime
	 * @return
	 */
	public static String convertDoubleTimeAsFormatedString(double doubleTime) {
		String stringTime = Double.toString(doubleTime);
		return getStringHour(stringTime) + ":" + getStringMinute(doubleTime);
	}

	/**
	 * 
	 * @param stringTime
	 * @return
	 */
	private static String getStringHour(String stringTime) {
		String hour = stringTime.substring(0, stringTime.indexOf("."));
		return (hour.length() < 2) ? "0" + hour : hour;
	}

	/**
	 * 
	 * @param doubleTime
	 * @return
	 */
	private static String getStringMinute(double doubleTime) {
		int    minute         = (int) ((doubleTime - (int) doubleTime) * 60);
		String minuteAsString = Integer.toString(minute);
		return (minuteAsString.length() < 2) ? "0" + minuteAsString : minuteAsString;
	}
	
	/**
	 * 
	 * @param time
	 * @return
	 */
	private double convertDoubleTimeAsForrmatedAsString(String time) {
		return getHourAsDouble(time) + getMinuteAsDouble(time);
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	private double getHourAsDouble(String time) {
		return Double.parseDouble(time.substring(0, time.indexOf(":")));
	}

	/**
	 * 
	 * @param time
	 * @return
	 */
	private double getMinuteAsDouble(String time) {
		return Double.parseDouble(time.substring(time.indexOf(":")));
	}
}
