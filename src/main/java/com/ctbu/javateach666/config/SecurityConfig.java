package com.ctbu.javateach666.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter.XFrameOptionsMode;
/**
 * 安全框架配置类
 *
 * @author luokan
 */
@Configuration
@EnableWebMvcSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Resource
	private DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
	      .jdbcAuthentication()
	      	.dataSource(dataSource)
	      	.usersByUsernameQuery("select username,password,enable from account where username=?")
	      	.authoritiesByUsernameQuery("select username,authorities from authorities where username=?")
	      	.passwordEncoder(new BCryptPasswordEncoder());
	      	
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.headers().frameOptions().disable();
		http.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsMode.SAMEORIGIN));
		http.csrf().disable();
		http
		.formLogin()
        	.loginPage("/login")
        	.defaultSuccessUrl("/main")
        	.and()
        		.logout()
        		.logoutSuccessUrl("/login")
        .and()
        	.rememberMe()
        		.tokenValiditySeconds(2419200)
        		.key("javateachKey")
        .and()
			.authorizeRequests()
				.antMatchers("/main").authenticated()
				//.antMatchers("/lk/**").hasAuthority("USER")
				.anyRequest().permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		//web.ignoring().antMatchers("/static/**");
	}
	
}
