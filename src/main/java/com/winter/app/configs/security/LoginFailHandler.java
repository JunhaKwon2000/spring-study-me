package com.winter.app.configs.security;

import java.io.IOException;
import java.net.URLEncoder;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginFailHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
		
		String failMsg = "Login Fail";
		
		if (exception instanceof BadCredentialsException) {
			failMsg = "Wrong Password";
		} else if (exception instanceof InternalAuthenticationServiceException) {
			failMsg = "No ID";
		} 
		
		failMsg = URLEncoder.encode(failMsg, "UTF-8");
		response.sendRedirect("/member/login?failMsg=" + failMsg);
	}

}
