package com.sgg.suivisprod.services;

import static com.sgg.suivisprod.utils.TimeUtils.convertDoubleTimeAsFormatedString;
import static com.sgg.suivisprod.utils.TimeUtils.convertStringTimeAsDouble;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.format.Formatter;

public class TotalWorkingTimeFormatterService  implements Formatter<Double>{

	@Override
	public String print(Double totalWorkingTime, Locale locale) {
		// TODO Auto-generated method stub
		return convertDoubleTimeAsFormatedString(totalWorkingTime);
	}

	@Override
	public Double parse(String stringTime, Locale locale) throws ParseException {
		// TODO Auto-generated method stub
		return convertStringTimeAsDouble(stringTime);
	}
}
