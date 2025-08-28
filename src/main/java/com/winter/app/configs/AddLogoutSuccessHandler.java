package com.winter.app.configs;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AddLogoutSuccessHandler implements LogoutSuccessHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String myKakaoRestApiKey;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		log.info("로그아웃 성공 핸들러");
		log.info("{}", authentication);
		
		// 로그아웃을 성공해도 세션만 지워지지 authentication은 살아있음 -> 이걸 활용할 수 있음(로그아웃 시간 기록 등등)
		// 카카오로 할때만
		// 다를 때는 그냥
		// response.sendRedirect("https://kauth.kakao.com/oauth/logout?client_id="+myKakaoRestApiKey+"&logout_redirect_uri=http://localhost");
	}

	
	
}
