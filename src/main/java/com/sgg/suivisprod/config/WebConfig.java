package com.sgg.suivisprod.config;

import static com.sgg.suivisprod.constant.PathConst.HOME_VIEW;
import static com.sgg.suivisprod.constant.PathConst.LOGIN_PATH;
import static com.sgg.suivisprod.constant.PathConst.LOGIN_VIEW;
import static com.sgg.suivisprod.constant.PathConst.WEB_INF_PATH;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

import com.sgg.suivisprod.services.DateFormatterService;
import com.sgg.suivisprod.services.DoubleToStringWorkingTimeConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	ApplicationContext applicationContext;

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

	@Bean
	public ThymeleafViewResolver viewResolver() {
		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine());
		viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] {
				".html", ".xhtml"
		});
		return viewResolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine = new SpringTemplateEngine();
		templateEngine.setTemplateResolver(templateResolver());
		templateEngine.setEnableSpringELCompiler(true);
		return templateEngine;
	}

	@Bean
	public SpringResourceTemplateResolver templateResolver() {
		SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
		templateResolver.setApplicationContext(this.applicationContext);
		templateResolver.setPrefix(WEB_INF_PATH);
		templateResolver.setSuffix(".html");
		templateResolver.setTemplateMode(TemplateMode.HTML);
		templateResolver.setCacheable(true);
		return templateResolver;

	}
}
