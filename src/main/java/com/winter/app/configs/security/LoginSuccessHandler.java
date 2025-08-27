package com.winter.app.configs.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		String rememberId = request.getParameter("rememberId");
		String username = request.getParameter("username");
		if ("remember".equals(rememberId)) {
			Cookie cookie = new Cookie("rememberId", username);
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cookie);
		} else {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if ("rememberId".equals(c.getName())) {
					c.setMaxAge(0);
					response.addCookie(c);
				}
			}
		}
		
		response.sendRedirect("/");
	}

}
