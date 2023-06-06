package com.spring5.practice.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
		// We are disabling CSRF so that our forms don't complain about a CSRF token.
		// Beware that it can create a security vulnerability
		return http.csrf(AbstractHttpConfigurer::disable)
				// Here we are configuring our login form
//				.formLogin(Customizer.withDefaults())
				.formLogin(formLogin -> {
							formLogin
									.loginPage("/login") // Login page will be accessed through this endpoint. We will create a controller method for this.
									.loginProcessingUrl("/login-processing") // This endpoint will be mapped internally. This URL will be our Login form post action.
									.usernameParameter("username")
									.passwordParameter("password")
									.permitAll() // We re permitting all for login page
									.defaultSuccessUrl("/") // If the login is successful, user will be redirected to this URL.
									.failureUrl("/login?error=true"); // If the user fails to login, application will redirect the user to this endpoint
						}
				)
		.authorizeHttpRequests(authorize ->
						authorize
								// We are permitting all static resources to be accessed publicly
								.requestMatchers("/images/**", "/css/**", "/js/**", "/WEB-INF/views/**").permitAll()
								// We are restricting endpoints for individual roles.
								// Only users with allowed roles will be able to access individual endpoints.
								.requestMatchers("/course/add").hasRole("ADMIN")
								.requestMatchers("/course/show-all").hasAnyRole("ADMIN", "USER")
								.requestMatchers("/course/edit").hasAnyRole("USER")
								// Following line denotes that all requests must be authenticated.
								// Hence, once a request comes to our application, we will check if the user is authenticated or not.
								.anyRequest().authenticated()
				)

				.logout(logout ->
						logout
								.logoutUrl("/logout")
								.logoutSuccessUrl("/")
				)
				.build();

	}
	@Bean
	public UserDetailsService userDetailsService() {

		UserDetails user = User
				.withUsername("user")
				.password("{noop}password")
				.roles("USER")
				.build();
		UserDetails admin = User
				.withUsername("admin")
				.password("{noop}password")
				.roles("ADMIN", "USER")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public HandlerMappingIntrospector mvcHandlerMappingIntrospector() {
		return new HandlerMappingIntrospector();
	}
}
