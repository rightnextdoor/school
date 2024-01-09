package com.school.roster.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.school.roster.services.UserService;

import lombok.RequiredArgsConstructor;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	@Autowired
	private UserService userService;
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	
	 @Bean
	 public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
	      return authenticationConfiguration.getAuthenticationManager();
	 }

	 
	 @Bean
	 public AuthenticationProvider authenticationProvider() {
		 final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		 authenticationProvider.setUserDetailsService(userService);
		 authenticationProvider.setPasswordEncoder(getPasswordEncoder());
		 return authenticationProvider;
	 }
	
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//		http.cors().and().csrf().disable()
//		.authorizeHttpRequests()
//			.requestMatchers("/api/v1/auth/**").permitAll()
////			.requestMatchers("/createUser").permitAll()
////			.requestMatchers("/user/**").hasRole("Admin")
//			.anyRequest()
//			.authenticated()
//		.and()
//		.sessionManagement()
//			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		.and()
//		.authenticationProvider(authenticationProvider())
//		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
//		.and()
//		.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////		.httpBasic();
//		return http.build();
//	}

	 @Bean
	 public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		 http
		 	.cors()
		 	.and()
		 	.csrf()
		 	.disable()
		 	.authorizeHttpRequests()
		 	.requestMatchers("/api/v1/auth/**")
		 	.permitAll()
		 	.anyRequest()
		 	.authenticated()
		 	.and()
		 	.sessionManagement()
		 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		 	.and()
		 	.exceptionHandling().authenticationEntryPoint(unauthorizedHandler)
		 	.and()
		 	.authenticationProvider(authenticationProvider())
		 	.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		 return http.build();
	 }
	
	@Bean 
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
//		return NoOpPasswordEncoder.getInstance();
	}
}
