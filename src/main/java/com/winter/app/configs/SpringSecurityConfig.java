package com.winter.app.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import com.winter.app.configs.jwt.JwtAuthenticationFilter;
import com.winter.app.configs.jwt.JwtLoginFilter;
import com.winter.app.configs.jwt.JwtTokenManager;
import com.winter.app.member.MemberService;

@Configuration
//@EnableWebSecurity(debug = true)
@EnableWebSecurity
public class SpringSecurityConfig {
	
	@Autowired
	private LoginSuccessHandler loginSuccessHandler; // 가져오기
	
	@Autowired
	private LoginFailHandler loginFailHandler;
	
	@Autowired
	private AddLogoutHandler addLogoutHandler;
	
	@Autowired
	private AddLogoutSuccessHandler addLogoutSuccessHandler;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private JwtTokenManager jwtTokenManager;
	
	@Autowired
	private AuthenticationConfiguration authenticationConfiguration;
	
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
			// 개발자가 로그인 검증을 하지 않음 -> Spring Security Filter에서 로그인 검증 진행
//			.formLogin(form -> {
//				form
//					.loginPage("/member/login") // 로그인을 진행할 URL, GET + POST 모두에게 적용됨
//					// .usernameParameter("username") // default가 username, 다를 경우 직접 명시
//					// .passwordParameter("password") // defailt가 password, 다를 경우 직접 명시
//					// .defaultSuccessUrl("/") // 로그인 성공 시 이동할 URL(redirect)
//					// .successForwardUrl("URL") // forward 방식
//					//.failureUrl("/member/login"); // 로그인 실패 시 이동할 URL(redirect)
//					.successHandler(loginSuccessHandler) // 추가적인 작업을 하고 싶을 때 해줘야함
//					.failureHandler(loginFailHandler);
//					// defaultSyccessUrl 이랑 successHandler 둘 중 하나만 사용 가능(fail도 동일함)!
//			})
			// formLogin을 아예 사용하지 않음 -> JWT Token 인증 방식을 사용할 것이기 때문에
			.formLogin(form -> {
				form.disable();
			})
			// httpBasic에 관련된 설정 - 나중에 알기
//			.httpBasic(basic -> {
//				basic.disable();
//			})
			// logout에 관련된 설정
			// 개발자가 로그아웃 처리를 하지 않음 -> Spring Security Filter에서 로그아웃 진행
			// 이건 세션 인증 방식에서 하는 로그아웃임
//			.logout(logout -> {
//				logout
//					.logoutUrl("/member/logout") // logout을 담당하는 url
//					.addLogoutHandler(addLogoutHandler)
//					.logoutSuccessHandler(addLogoutSuccessHandler)
//					.invalidateHttpSession(true) // session 유지 시간을 0로 설정
//					.deleteCookies("JSESSIONID"); // 쿠키를 지우기
//					// .logoutSuccessUrl("/"); // 로그아웃 성공 시 이동할 URL
//			})
			// 이건 JWT 인증 방식에서 하는 로그아웃임
			.logout(logout -> {
				logout
					.logoutUrl("/member/logout") // logout을 담당하는 url
//					.addLogoutHandler(addLogoutHandler) // 핸들러에서 해도 댐
//					.logoutSuccessHandler(addLogoutSuccessHandler) // 핸들러에서 해도 댐
					.invalidateHttpSession(true) // session 유지 시간을 0로 설정
					.deleteCookies("accessToken") // 쿠키를 지우기
					.logoutSuccessUrl("/"); // 로그아웃 성공 시 이동할 URL
			})
			// remember me!
//			.rememberMe(remember -> {
//				remember
//					.rememberMeParameter("remember-me") // 기본이 remember-me
//					.tokenValiditySeconds(60) // remember할 시간
//					.key("remember-token-key") // 중요! - 암호화를 진행할 키!
//					.userDetailsService(memberService)
//					.authenticationSuccessHandler(loginSuccessHandler) // 자동 로그인 성공 시 실행할 핸들러
//					.useSecureCookie(false); // 보안된 쿠키를 사용할꺼임? 일단 안함 
//				// 로그아웃 시 리멤버미 쿠키는 삭제됨!!!
//			})
			// 동시접속 - 세션인증 방식이 아닌 토큰인증 방식(JWT)에서는 의미가 없음, 따라서 주석처리
//			.sessionManagement(sessionMangement -> {
//				sessionMangement
//				.invalidSessionUrl("/member/login")
//					.maximumSessions(1)
//					.maxSessionsPreventsLogin(true) // false 이면 다른 브라우저에서 로그인했을 때, 이전 사용자를 로그아웃 시킴, true이면 현재 사용자가 접속하여할 때 막음
//					.expiredUrl("/");
//			})
			// 세션 인증 방식을 쓰지 않겠다
			.sessionManagement(sessionMangement -> {
				sessionMangement
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 세션을 사용하지 않음
			})
			// JWT 인증 필터 등록
			.addFilter(new JwtLoginFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager)) // UsernamePasswordAuthenticationFilter
			// JWT 검증 필터 등록 - 먼저 검증부터, 로그인이 안되어있다면 밑으로 체인
			.addFilter(new JwtAuthenticationFilter(authenticationConfiguration.getAuthenticationManager(), jwtTokenManager)) // BasicAuthenticationFilter
			// 소셜
			.oauth2Login(auth -> {
				auth.
				userInfoEndpoint(info -> {
					info.
					userService(memberService);
				});
			})
			;
		
		return httpSecurity.build();
	}
	
	
	
}
