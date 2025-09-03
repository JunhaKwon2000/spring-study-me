package com.winter.app.configs.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 모든 요청 시 실행되는 필터
public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private JwtTokenManager jwtTokenManager;
	
	public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtTokenManager jwtTokenManager) {
		super(authenticationManager);
		this.jwtTokenManager = jwtTokenManager;
	}
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 토큰을 검증하자
		// 1. 토큰을 꺼내기
		
		String token = null;
		
		Cookie[] cookies = request.getCookies();

        if (cookies == null) {
            chain.doFilter(request, response);
            return;
        }

		for (Cookie c : cookies) {
			if ("accessToken".equals(c.getName())) {
				token = c.getValue();
				break;
			}
		}
		// System.out.println("Token: " + token);
		// 2. 검증
		if (token != null) {
			try {
				Authentication authentication = jwtTokenManager.getAuthenticationByToken(token);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				System.out.println("Authentication: " + authentication.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				if(e instanceof SecurityException || e instanceof MalformedJwtException || e instanceof SignatureException) {
					System.out.println("Invalid JWT Token"); // 유효하지 않은 토큰
				} else if (e instanceof ExpiredJwtException) {
					System.out.println("Expired JWT Token"); // 토큰이 만료됨(중요! -> 갱신이 필요함, refresh token 필요)
                    for (Cookie c : cookies) {
                        if("refreshToken".equals(c.getName())) {
                            String refreshToken = c.getValue();
                            try {
                                Authentication authentication = jwtTokenManager.getAuthenticationByToken(refreshToken);
                                SecurityContextHolder.getContext().setAuthentication(authentication);

                                String accessTokenRefreshed = jwtTokenManager.createAccessToken(authentication);
                                Cookie cookie = new Cookie("accessToken", accessTokenRefreshed);
                                cookie.setPath("/");
                                cookie.setHttpOnly(true); // JS에서 접근 불가
                                cookie.setMaxAge(180);
                                response.addCookie(cookie);

                            } catch (Exception ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }

				} else if (e instanceof UnsupportedJwtException) {
					System.out.println("Unsupported JWT Token"); // 지원하지 않는 토큰
				} else if (e instanceof IllegalArgumentException) {
                    System.out.println("JWT claims string is empty."); // 잘못된 토큰
				}
			}			
		}
		
		chain.doFilter(request, response);
	}
}
