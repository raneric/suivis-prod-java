package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.utils.TimeUtils.convertDoubleTimeAsFormatedString;
import static com.sgg.suivisprod.utils.TimeUtils.convertStringTimeAsDouble;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * This formatter convert double working time from DB to HH:MM String format
 *
 */
public class TotalWorkingTimeFormatterService  implements Formatter<Double>{

	@Override
	public String print(Double totalWorkingTime, Locale locale) {
		return convertDoubleTimeAsFormatedString(totalWorkingTime);
	}

	@Override
	public Double parse(String stringTime, Locale locale) throws ParseException {
		return convertStringTimeAsDouble(stringTime);
	}
}
