package com.winter.app.configs;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		// 로그인 실패 시 Spring Security 에서 Exception을 발생시킴
		// InternalAuthenticationServiceException - ID가 틀린 경우
		// BadCredentialsException - ID가 맞고 비밀번호가 틀린 경우
		// DisabledException - 유효하지 않은 사용자인 경우(enabled = false)
		// AccountExpiredException - 사용자의 계정의 유효기간이 만료된 경우(accountNonExpired = false)
		// LockedException - 사용자 계정이 잠겨져 있을 경우(accountNonLocked = false)
		// CredentialsExpiredException - 사용자의 자격 증명 유효 기간이 만료된 경우(=비밀번호가 만료되었을 경우) (credentialsNonExpired = false)
		// AuthenticationCredentialsNotFoundException - 그 외 기타 로그인 실패 오류
		// log.info("exception:{}", exception);
		String failMsg = "로그인 실패! 관리자에게 문의하세요";
		
		if (exception instanceof BadCredentialsException) {
			failMsg = "비밀번호가 틀렸습니다";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			failMsg = "ID가 틀렸습니다";
		} else if (exception instanceof DisabledException) {
			failMsg = "유효하지 않은 사용자입니다";
		} else if (exception instanceof AccountExpiredException) {
			failMsg = "계정의 유효기간이 지났습니다";
		} else if (exception instanceof LockedException) {
			failMsg = "정지당한 사용자입니다";
		} else if (exception instanceof CredentialsExpiredException) {
			failMsg = "비밀번호 유효기간이 지났습니다";
		} else if (exception instanceof AuthenticationCredentialsNotFoundException) {
			failMsg = "로그인 실패! 관리자에게 문의하세요";
		}
		
		failMsg = URLEncoder.encode(failMsg, "UTF-8");
		response.sendRedirect("/member/login?failMsg=" + failMsg);
	}

}
