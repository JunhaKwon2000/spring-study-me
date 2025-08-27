package com.winter.app.configs.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler;
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private MyLogoutHandler myLogoutHandler;
	
	@Autowired
	private MyLogoutSuccessHandler myLogoutSuccessHandler;

	@Bean
	WebSecurityCustomizer customizer() {
		return web -> {
			web.ignoring().requestMatchers("/css/**", "/js/**", "/vendor/**", "/files/**");
		};
	}
	
	@Bean
	SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
		.cors(cors -> cors.disable())
		.csrf(csrf -> csrf.disable())
		.authorizeHttpRequests(auth -> {
			auth
			.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN")
			.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
			.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
			.anyRequest().permitAll();
		})
		.formLogin(login -> {
			login
			.loginPage("/member/login")
			.usernameParameter("username")
			.passwordParameter("password")
			.successHandler(loginSuccessHandler)
			.failureHandler(loginFailHandler);
		})
		.logout(logout -> {
			logout
			.logoutUrl("/member/logout")
			.addLogoutHandler(myLogoutHandler)
			.logoutSuccessHandler(myLogoutSuccessHandler)
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID");
		});
		
		return httpSecurity.build();
	}
	
	
	
}
