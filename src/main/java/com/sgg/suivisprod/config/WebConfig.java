package com.sgg.suivisprod.config;

import static com.sgg.suivisprod.constant.PathConst.HOME_VIEW;
import static com.sgg.suivisprod.constant.PathConst.LOGIN_PATH;
import static com.sgg.suivisprod.constant.PathConst.LOGIN_VIEW;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sgg.suivisprod.service.DateFormatterService;
import com.sgg.suivisprod.service.DoubleToStringWorkingTimeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(HOME_VIEW);
		registry.addViewController(LOGIN_PATH).setViewName(LOGIN_VIEW);
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(totalWorkingTimeConverter());
		registry.addFormatter(dateFormatter());
	}

	@Bean
	public DoubleToStringWorkingTimeConverter totalWorkingTimeConverter() {
		return new DoubleToStringWorkingTimeConverter();
	}

	@Bean
	public DateFormatterService dateFormatter() {
		return new DateFormatterService();
	}

}
