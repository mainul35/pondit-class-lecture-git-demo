package com.spring5.practice.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("mainul35").password("{noop}secret").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("mainul36").password("{noop}secret").roles("USER");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http
			.authorizeRequests()
			.antMatchers("/images/**", "/css/**", "/js/**").permitAll()
		.and()
			.authorizeRequests()
			.antMatchers("/course/add").hasRole("ADMIN")
			.antMatchers("/course/show-all").hasAnyRole("ADMIN", "USER")
			.antMatchers("/course/edit").hasAnyRole("USER")
			.anyRequest().authenticated()
		.and()
			.formLogin()
			.loginPage("/login").loginProcessingUrl("/login-processing")
			.permitAll()
			.usernameParameter("username")
			.passwordParameter("password")
			.defaultSuccessUrl("/").failureUrl("/login?error=true")
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/");
	}
	
	
}
