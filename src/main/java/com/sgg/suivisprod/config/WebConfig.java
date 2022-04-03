package com.sgg.suivisprod.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static com.sgg.suivisprod.utils.AppCont.LOGIN_PATH;
import static com.sgg.suivisprod.utils.AppCont.LOGIN_VIEW;
import static com.sgg.suivisprod.utils.AppCont.HOME_VIEW;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName(HOME_VIEW);
		registry.addViewController(LOGIN_PATH).setViewName(LOGIN_VIEW);
	}

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
          .addResourceHandler("/resources/**")
          .addResourceLocations("/resources/");	
    }
	
}
