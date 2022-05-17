package com.sgg.suivisprod.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.format.Formatter;

/**
 * This date formatter format java date object to String date in frormat
 * dd-mm-yyy
 *
 */
public class DateFormatterService implements Formatter<Date> {

	@Override
	public String print(Date dateObject, Locale locale) {
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		return formatter.format(dateObject);
	}

	@Override
	public Date parse(String stringDate, Locale locale) throws ParseException {
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.parse(stringDate);
	}

}
