package com.ha.chargecapture.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${basic.auth.user}")
	String basicAuthUser;

	@Value("${basic.auth.password}")
	String basicAuthPwd;

	@Value("${basic.auth.role}")
	String basicAuthRole;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// http.csrf().csrfTokenRepository(csrfTokenRepository()).requireCsrfProtectionMatcher(csrfRequestMatcher)

		http.csrf().disable()

				.authorizeRequests().antMatchers("/*.*", "/").permitAll().antMatchers("/chargecapture/*").permitAll()
				.and().httpBasic().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser(basicAuthUser).password(basicAuthPwd).roles(basicAuthRole);
	}

}
