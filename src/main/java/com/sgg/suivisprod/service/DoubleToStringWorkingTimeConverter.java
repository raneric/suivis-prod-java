package com.sgg.suivisprod.service;

import org.springframework.core.convert.converter.Converter;
import static com.sgg.suivisprod.utils.TimeUtils.convertDoubleTimeAsFormatedString;

public class DoubleToStringWorkingTimeConverter implements Converter<Double, String> {

	@Override
	public String convert(Double source) {
		return convertDoubleTimeAsFormatedString(source);
	}

}
