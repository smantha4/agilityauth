package com.servicemesh.auth.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.servicemesh.auth.app.AgilityAuthManagerBean;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AgilityAuthManagerBean agilityAuthManagerBean;

	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return agilityAuthManagerBean;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests().anyRequest().authenticated().and().httpBasic();
	}

}
