package com.winter.app.configs;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

// import com.winter.app.member.MemberVO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	// 로그인 성공 시 실행 - 성공 시 하고 싶은 일을 handle 할 수 있도록 도와주는 친구
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 아이디 기억하기 - 아이디 기억 정보를 쿠키에 담아주고, 이를 브라우저에 세팅해놓음
		String rememberMe = request.getParameter("rememberMe");
		String remeberUsername = request.getParameter("username"); 
		// MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		// String rememberUsername = memberVO.getUsername();
		if ("1".equals(rememberMe)) {
			Cookie cookie = new Cookie("rememberMe", remeberUsername);
			cookie.setMaxAge(60 * 60 * 24 * 365);
			response.addCookie(cookie);
			// 쿠키는 경로 설정을 안하면 이 쿠키를 만든 URL에서만 visible하게 됨
		} else {
			Cookie[] cookies = request.getCookies();
			for (Cookie c : cookies) {
				if ("rememberMe".equals(c.getName())) {
					c.setMaxAge(0);
					response.addCookie(c);
					break;
				}
			}
		}
		response.sendRedirect("/");
	}

}
