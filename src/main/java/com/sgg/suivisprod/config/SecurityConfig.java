package com.sgg.suivisprod.config;
import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sgg.suivisprod.service.CustomUserDetailsService;
import com.sgg.suivisprod.utils.Roles;

import static com.sgg.suivisprod.utils.AppCont.DASHBOARD_PATH;
import static com.sgg.suivisprod.utils.AppCont.HISTORY_PATH;
import static com.sgg.suivisprod.utils.AppCont.NEW_TASK_PATH;
import static com.sgg.suivisprod.utils.AppCont.PRODUCTIVITY_PATH;
import static com.sgg.suivisprod.utils.AppCont.ROOT_PATH;
import static com.sgg.suivisprod.utils.AppCont.LOGOUT_PATH;
import static com.sgg.suivisprod.utils.AppCont.LOGIN_PATH;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Resource
	CustomUserDetailsService userDetailsServie;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider());
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsServie);
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeHttpRequests()
				.antMatchers(ROOT_PATH,DASHBOARD_PATH, NEW_TASK_PATH, HISTORY_PATH, PRODUCTIVITY_PATH)
				.authenticated()
				.antMatchers(LOGIN_PATH, LOGOUT_PATH)
				.permitAll()
				.and()
				.formLogin()
				.loginPage(LOGIN_PATH)
				.and()
				.logout();
		//http.authorizeHttpRequests().antMatchers("/*","/**").permitAll();

	}
}