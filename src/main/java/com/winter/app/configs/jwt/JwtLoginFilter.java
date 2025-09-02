package com.winter.app.configs.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 로그인 요청 시 실행되는 필터
// username이랑 password를 받아서 UserDetailsService의 loadUserByUsername() 메서드를 호출
public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {

	// 인증 매니저
	private AuthenticationManager authenticationManager;

	// JWT 토큰 매니저
	private JwtTokenManager jwtTokenManager;
	
	// 직접 주입
	public JwtLoginFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		System.err.println("JwtLoginFilter 생성자");
		this.authenticationManager = authenticationManager;
		this.jwtTokenManager = jwtTokenManager;
		this.setFilterProcessesUrl("/member/loginProcess"); // 로그인 URL 설정(기본값은 /login) - 어떤 url로 요청이 들어왔을 때 이 필터가 동작할지 설정
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		System.err.println("JwtLoginFilter attemptAuthentication");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		// System.out.println("JwtLoginFilter: " + username + ", " + password);
		
		// 인증 처리
		// UserDetailsService의 loadUserByUsername() 메서드가 호출됨
		// password가 일치하는지도 검사 -> 해당 Authentication 객체를 SecurityContextHolder에 저장
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);
		
		// 인증 매니저에게 인증을 요청 -> 인증이 성공하면 Authentication 객체가 리턴됨
		return authenticationManager.authenticate(token);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		System.err.println("JwtLoginFilter successfulAuthentication");
		// 인증이 성공했을 때 실행되는 메서드 - attemptAuthentication() 메서드가 성공했을 때 실행
		String token = jwtTokenManager.createToken(authResult); // JWT 토큰 생성
		
		Cookie cookie = new Cookie("accessToken", token);
		cookie.setPath("/");
		cookie.setHttpOnly(true); // JS에서 접근 불가
		response.addCookie(cookie);
		response.sendRedirect("/");
	}
	
}
