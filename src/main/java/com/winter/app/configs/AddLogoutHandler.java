package com.winter.app.configs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.member.MemberVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class AddLogoutHandler implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-id}")
	private String myKakaoRestApiKey;
	
	@Value("${http://localhost/login/oauth2/code/kakao}")
	private String mykakaoRedirectUriKey;

	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
//		log.info("로그아웃 핸들러");
//		log.info("{}", authentication);
		if (authentication instanceof OAuth2AuthenticationToken) {
			MemberVO memberVO = (MemberVO) authentication.getPrincipal();
			if ("kakao".equalsIgnoreCase(memberVO.getSns())) {
				// this.useKakao(memberVO);
			}
		}
	}
	
	private void useKakao(MemberVO memberVO) {
		
		// 파라미터
		Map<String, Object> param = new HashMap<>();
		param.put("target_id_type", "user_id");
		param.put("target_id", memberVO.getName()); // 소셜 계정은 name에 넣어놨음 service에서
		
		WebClient webClient = WebClient.create();
		
		Mono<String> response = webClient.post().uri("https://kapi.kakao.com/v1/user/logout").header("Authorization", "Bearer " + memberVO.getAccessToken()).bodyValue(param).retrieve().bodyToMono(String.class);
		log.info("{}", response.block());
	}
	
	// 이거는 테스트용
	private void useWithKakao(MemberVO memberVO) {
		
		WebClient webClient = WebClient.create();
		Mono<String> response = webClient.get().uri("https://kauth.kakao.com/oauth/logout?client_id={id}&logout_redirect_uri={uri}", myKakaoRestApiKey, "http://localhost/member/logout").retrieve().bodyToMono(String.class);
		log.info("Logout Test:{}", response.block());
	}

}
