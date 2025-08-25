package com.winter.app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
	
	// exclude static resources from security(css, js, etc)
	// default
	@Bean
	WebSecurityCustomizer webSecurityCustomizer() {
		// WebSecurity web
		return web -> {
			web.ignoring().requestMatchers("/css/**", "/js/**", "/vendor/**", "/files/**");
		};
	}
	
	// 인증 + 권한 설정
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors(cors -> cors.disable())
			.csrf(csrf -> csrf.disable())
			// 권한에 관련된 설정
			.authorizeHttpRequests(auth -> {
				auth
					.requestMatchers("/notice/add", "/notice/update", "/notice/delete").hasRole("ADMIN") // DB에 "ROLE_ADMIN" 이렇게 되어있으면, ROLE_ 이것을 자동으로 제거해줌
					.requestMatchers("/products/add", "/products/update", "/products/delete").hasAnyRole("MANAGER", "ADMIN")
					// .requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").access("hasRole('ROLE_MEMBER') or hasRole('ROLE_MANAGER')")
					.requestMatchers("/member/detail", "/member/logout", "/member/update", "/member/delete").authenticated()
					.anyRequest().permitAll();
					// 만약 로그인이 안된 경우 만약에 권한 같은 것을 물으면 바로 로그인 창으로 가짐, 그리고 만약 인증에 성공한 경우 원래 가려고 하던 곳으로 인도해줌
			})
			// form에 관련된 설정
			.formLogin(form -> {
				form
					.loginPage("/member/login") // GET + POST 모두에게 적용됨
					// .usernameParameter("username") // default가 username, 다를 경우 작성
					// .passwordParameter("password") // defailt가 password, 다를 경우 작성
					.defaultSuccessUrl("/")
					.failureUrl("/member/login");
			})
			.logout(logout -> {
				logout
					.logoutUrl("/member/logout") // logout을 담당하는 url
					.invalidateHttpSession(true) // session 지우기
					.deleteCookies("JSESSIONID")
					.logoutSuccessUrl("/");
			});
		
		return httpSecurity.build();
	}
	
	
	
}
