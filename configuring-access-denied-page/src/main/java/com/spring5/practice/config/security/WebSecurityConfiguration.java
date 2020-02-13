package com.spring5.practice.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;

	private final UserDetailsService userDetailsService;

	@Autowired
	public WebSecurityConfiguration(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
		this.passwordEncoder = passwordEncoder;
		this.userDetailsService = userDetailsService;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        // We are disabling CSRF so that our forms don't complain for a CSRF token.
        // Beware that it can create a security vulnerability
		http.csrf().disable();

        // We are permitting all static resources to be accessed publicly
		http
			.authorizeRequests()
			.antMatchers("/images/**", "/css/**", "/js/**").permitAll()
                // We are restricting endpoints for individual roles.
                // Only users with allowed roles will be able to access individual endpoints.
		.and()
			.authorizeRequests()
			.antMatchers("/course/add").hasRole("ADMIN")
			.antMatchers("/course/show-all").hasAnyRole("ADMIN", "USER")
			.antMatchers("/course/edit").hasAnyRole("USER")
                // Following line denotes that all requests must be authenticated.
                // Hence, once a request comes to our application, we will check if the user is authenticated or not.
			.anyRequest().authenticated()

                // Here we are configuring our login form
		.and()
			.formLogin()
                .loginPage("/login") // Login page will be accessed through this endpoint. We will create a controller method for this.
                .loginProcessingUrl("/login-processing") // This endpoint will be mapped internally. This URL will be our Login form post action.
                .permitAll() // We re permitting all for login page
			.usernameParameter("username")
			.passwordParameter("password")
                .defaultSuccessUrl("/") // If the login is successful, user will be redirected to this URL.
                .failureUrl("/login?error=true") // If the user fails to login, application will redirect the user to this endpoint
		.and()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/")
		.and()
				.exceptionHandling()
//				.accessDeniedPage("/403");
				.accessDeniedHandler(new CustomAccessDeniedHandler());
	}
}
